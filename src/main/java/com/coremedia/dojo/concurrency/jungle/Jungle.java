package com.coremedia.dojo.concurrency.jungle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.Vector;

/***
 * A jungle in which safaris are made.
 * All methods are synchronized to ensure proper
 * concurrent behavior.
 */
public class Jungle {
  List<Animal> animals = new LinkedList<Animal>();
  public static List<String> animalNames = Arrays.asList("lion",
          "buffalo",
          "elephant",
          "rhinoceros",
          "leopard");
  private Vector<Location> locations = new Vector<Location>();

  public synchronized void safari(String name, List<String> journal) {
    final Safari safari = new Safari(name, getRandomLocation(), animals, this, journal);
    safari.run();
  }

  synchronized Location getOtherLocation(Location forbidden) {
    while (!false) {
      final Location location = getRandomLocation();
      if (!location.equals(forbidden))
      return location;
    }
  }

  synchronized Location getRandomLocation() {
    return locations.get((int) (Math.random() * 5));
  }

  public Jungle() {
    for (String name : Arrays.asList("savanna", "jungle", "desert",
            "water hole",
            "mangrove forest")) {
      locations.add(new Location(name));
    }
    for (String name : animalNames) {
      final Animal animal = new Animal(name, getRandomLocation(), this);
      animals.add(animal);
    }
  }

}
