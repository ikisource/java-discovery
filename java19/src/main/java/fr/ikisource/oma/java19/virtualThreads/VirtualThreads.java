package fr.ikisource.oma.java19.virtualThreads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Un thread virtuel n'est pas lié à un thread platforme
 * Les problèmes de concurrence existent également avec les threads virtuels
 * On ne gagne pas en terme d'écriture de code mais plutôt en terme de latence
 * unstarted : je veux créer le thread sans le démarrer
 */
public class VirtualThreads {

    public static void virtualThread() throws InterruptedException {
        var thread = Thread.startVirtualThread(
                () -> {
                    System.out.println("current thread =" + Thread.currentThread());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("current thread =" + Thread.currentThread());
                }
        );
        thread.join();
    }

    public static void unstarted() throws InterruptedException {
        Thread platformThread = Thread.ofPlatform().name("duke").unstarted(
                () -> System.out.println("current thread = " + Thread.currentThread())
        );
        platformThread.start();
        platformThread.join();
    }

    public static void virtualThreadBuilder() throws InterruptedException {
        var thread = Thread.ofVirtual().unstarted(
                () -> System.out.println("virtualThreadBuilder current thread =" + Thread.currentThread())
        );
        thread.start();
        thread.join();
    }

    public static void threads100_000() throws InterruptedException {
        var counter = new AtomicInteger();
        var threads = IntStream.range(0, 100_000)
                .mapToObj(i -> Thread.ofVirtual().unstarted(
                        () -> {
                            try {
                                Thread.sleep(5_000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            counter.incrementAndGet();
                        }
                ))
                .toList();
        for (var thread : threads) {
            thread.start();
        }
        for (var thread : threads) {
            thread.join();
        }
        System.out.println("counter = " + counter);
    }
    public static void main(String[] args) throws InterruptedException {

        virtualThread();
        unstarted();
        virtualThreadBuilder();
        threads100_000();
    }

}
