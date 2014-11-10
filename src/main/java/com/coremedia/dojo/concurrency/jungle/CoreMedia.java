package com.coremedia.dojo.concurrency.jungle;

import java.util.LinkedList;

public class CoreMedia {
  public static void main(String[] args) {
    final LinkedList<String> journal = new LinkedList<String>();
    new Jungle().safari("Clarence", journal);
    System.out.println(journal);
  }
}
