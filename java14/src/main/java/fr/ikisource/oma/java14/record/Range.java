package fr.ikisource.oma.java14.record;

public record Range(int begin, int end) {

    public Range(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
}
