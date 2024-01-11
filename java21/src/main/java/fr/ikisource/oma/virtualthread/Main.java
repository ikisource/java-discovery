package fr.ikisource.oma.virtualthread;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        long t0 = Instant.now().toEpochMilli();
        // blockingCode();
        nonBlockingCode();
        System.out.println("duration = " + (Instant.now().toEpochMilli() - t0) + " ms");

        /*Runnable runnable = () -> count.getAndIncrement();

        Thread virtualThread = Thread.ofVirtual()
                .name("Virtual thread")
                .start(runnable);

        System.out.println(" count = " + count.get());*/

    }

    public static void blockingCode() {

        Integer firstInteger = IntegerService.getInteger("123");
        Integer randomInteger = IntegerService.getRandomInteger(1);
        Integer sum = IntegerService.sum(firstInteger, randomInteger);

        System.out.println("sum = " + sum);
    }

    public static void nonBlockingCode() {

        try {
            Integer sum = CompletableFuture.runAsync(
                    () -> IntegerService.getInteger("123"))
                    .thenApply(i -> IntegerService.getRandomInteger(1))
                    .exceptionally(e -> null)
                    .thenApply(a -> IntegerService.sum(1, 2)).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
