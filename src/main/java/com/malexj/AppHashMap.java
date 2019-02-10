package com.malexj;

import com.malexj.impl.NewHashMap;
import org.apache.log4j.Logger;

public class AppHashMap {

  private static final Logger LOG = Logger.getLogger(AppHashMap.class);

  public static void main(String[] args) {
    NewHashMap map = new NewHashMap();

    String s1 = "FB";
    String s2 = "Ea";

    map.put(s1, 1);
    map.put(s2, 1);
    map.put("Xxx", 10);
    map.put("Z", 123);
    map.put("All", 123);
    map.put(null, 456);

    int count = map.countCollidingEntries();
    LOG.debug("Number of collisions: " + count);
  }
}
