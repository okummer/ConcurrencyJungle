package com.coremedia.dojo.concurrency.jungle;

class Animal {
  String name;
  Location location;

  Animal(String name, Location location, Jungle jungle) {
    this.jungle = jungle;
    this.name = name;
    location.enteredBy(this);
    this.location = location;
  }

  protected final synchronized void seen() {
    if (Math.random() < 0.5) {
      goAway();
    }
  }

  protected final synchronized void photograph(Safari  safari) {
    safari.photographed(this);
    goAway();
  }

  public Jungle jungle;

  protected final synchronized void goAway() {
    Location otherLocation = jungle.getOtherLocation(location);
    location.leavedBy(this);
    location = otherLocation;
    location.enteredBy(this);
  }

  @Override
  public synchronized String toString() {
    return name;
  }

  @Override
  public synchronized boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    if (o == null) {
      return false;
    }


    Animal animal = (Animal) o;

    if (!name.equals(animal.name)) {
      return false;
    }

    return true;
  }

  @Override
  public synchronized int hashCode() {
    return toString() != null ? toString().hashCode() : 0;
  }
}
