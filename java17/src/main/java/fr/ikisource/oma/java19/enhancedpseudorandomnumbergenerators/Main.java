package fr.ikisource.oma.java19.enhancedpseudorandomnumbergenerators;

import java.util.Comparator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        System.out.println("Algorithms :");
        displayAlgorithms();

        System.out.println();

        System.out.print("generates 10 random integers with 'L128X256MixRandom' algorithm : ");
        IntStream intStream = getPseudoInts("L128X256MixRandom", 10);
        System.out.println(intStream.boxed().map(i -> i.toString()).collect(Collectors.joining(",")));
    }

    public static IntStream getPseudoInts(String algorithm, int streamSize) {

        // returns an IntStream with size @streamSize of random numbers generated using the @algorithm
        // where the lower bound is 0 and the upper is 100 (exclusive)
        return RandomGeneratorFactory.of(algorithm)
                .create()
                .ints(streamSize, 0, 100);
    }
    public static void displayAlgorithms() {
        RandomGeneratorFactory.all()
                .sorted(Comparator.comparing(RandomGeneratorFactory::name))
                .forEach(factory -> System.out.println(String.format("%s\t%s\t%s\t%s",
                        factory.group(),
                        factory.name(),
                        factory.isJumpable(),
                        factory.isSplittable())));
    }
}
