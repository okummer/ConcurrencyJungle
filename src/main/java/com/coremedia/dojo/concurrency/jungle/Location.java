package com.coremedia.dojo.concurrency.jungle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Location {
    String string;
    Set<Animal> animals = new HashSet<Animal>();

    // GETTERS

    synchronized Set<Animal> getAnimals() {
        return animals;
    }

    // CONSTRUCTOR

    Location(String name) {
        this.string = name;
    }

    synchronized void enteredBy(Animal animal) {
        animals.add(animal);
    }

    synchronized void leavedBy(Animal animal) {
            animals.remove(animal);
    }

    // BUSINESS METHODS

    synchronized void visit(Safari safari) {
        safari.visited(this);
        for (Animal animal : new ArrayList<Animal>(animals)) {
            animal.seen();
        }

    }

    @Override
    synchronized public String toString() {
        return string;
    }

    public synchronized boolean equals(Location o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Location location = (Location) o;

        if (!string.equals(location.string)) {
            return false;
        }

        return true;
    }

    public synchronized int hashcode() {
        return string.hashCode();
    }
}
