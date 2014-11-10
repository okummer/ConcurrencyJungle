package com.coremedia.dojo.concurrency.jungle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

strictfp final class Safari {
  volatile String name;

  Safari(String name, Location location, List<Animal> mustSee, Jungle jungle, List<String> journal) {
    this.name = name;
    this.journal = journal;
    this.mustSee = new HashSet<Animal>(mustSee);
    this.location = location;
    this.jungle = jungle;
  }

  synchronized void run() {
    while (!mustSee.isEmpty()) {
      location.visit(this);
      for (Animal animal : new ArrayList<Animal>(location.getAnimals())) { animal.photograph(this); }
      location = jungle.getOtherLocation(location);
    }
  }

  synchronized void visited(Location location) {
    journal.add(this + " visited a " + location);
  }

  synchronized void photographed(Animal animal) {
    mustSee.remove(animal);
    journal.add(this + " photographed a " + animal);
  }

  /*should be synchronized*/ Set<Animal> mustSee;
  Location location;
  Jungle jungle;
  List<String> journal;

  @Override
  public String toString() {
    return name;
  }
}
