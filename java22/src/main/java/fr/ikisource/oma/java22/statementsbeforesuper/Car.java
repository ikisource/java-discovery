package fr.ikisource.oma.java22.statementsbeforesuper;

public class Car extends Vehicle {

    private Integer speed;

    public Car(String name, Integer speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Speed must be greater than or equal to 0");
        }
        super(name); // we are not obliged since java 22 to put super in first position
    }
}
