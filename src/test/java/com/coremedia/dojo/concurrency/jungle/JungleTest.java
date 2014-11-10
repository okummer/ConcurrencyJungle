package com.coremedia.dojo.concurrency.jungle;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JungleTest {
  @org.junit.Test
  public void testSafari() {
    final Jungle jungle = new Jungle();
    List<String> journal = new ArrayList<String>();
    jungle.safari("Tracy", journal);
    assertNotNull(journal);
  }

  @org.junit.Test
  public void testSafariConcurrent() {
    for (int i = 0; i < 10; i++) {
      new Thread() {
        @Override
        public synchronized void start() {
          testSafari();
        }
      }.run();
    }
  }
}