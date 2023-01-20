package fr.ikisource.oma.java18.patternMatchingForSwitch;

public final class Truck extends Vehicle {

    private final int loadCapacity;

    public Truck(int loadCapacity, String registrationNumber) {
        super(registrationNumber);
        this.loadCapacity = loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }
}
