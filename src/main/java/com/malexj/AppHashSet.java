package com.malexj;

import com.malexj.impl.NewHashSet;
import org.apache.log4j.Logger;

public class AppHashSet {

  private static final Logger LOG = Logger.getLogger(AppHashSet.class);

  public static void main(String[] args) {
    NewHashSet<Integer> set = new NewHashSet<>();
    set.add(1);
    set.add(2);
    set.add(3);
    LOG.debug(set);
  }

}
