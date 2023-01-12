package fr.ikisource.oma.java14.record;

import java.util.Iterator;

public record Range(int begin, int end) implements Iterable<Integer> {

    public Range {
        if (begin > end) {
            throw new IllegalArgumentException("begin must be lesser than end");
        }
    }

    public Range(int end) {
        this(0, end);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
        int index = begin;
            @Override
            public boolean hasNext() {
                return index < end;
            }

            @Override
            public Integer next() {
                return index++;
            }
        };
    }
    public static void main(String[] args) {

        var range = new Range(0, 10);
        System.out.println("range = " + range);

        // accesseur
        int begin = range.begin();
        System.out.println("begin = " + begin);
        for (int index : range) {
            System.out.println("index = " + index);
        }
    }
}

