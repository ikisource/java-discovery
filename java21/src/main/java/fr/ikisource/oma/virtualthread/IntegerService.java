package fr.ikisource.oma.virtualthread;

public class IntegerService {

    public static Integer getRandomInteger(Integer i1) {

        Integer i = 0;
        try {
            Thread.sleep(2000);
            i = (int) (Math.random() * (100 - 1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public static Integer getInteger(String value) {

        return Integer.valueOf(value);
    }

    public static Integer sum(Integer i1, Integer i2) {

        return i1 + i2;
    }


}
