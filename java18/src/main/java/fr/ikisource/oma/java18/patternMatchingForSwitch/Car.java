package fr.ikisource.oma.java18.patternMatchingForSwitch;

public non-sealed class Car extends Vehicle {

    private final int numberOfSeats;

    public Car(int numberOfSeats, String registrationNumber) {
        super(registrationNumber);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
