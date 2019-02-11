package com.malexj;

import com.malexj.impl.NewLinkedHashMap;
import org.apache.log4j.Logger;

public class AppLinkedHashMap {

  private static final Logger LOG = Logger.getLogger(AppHashSet.class);

  public static void main(String[] args) {
    NewLinkedHashMap<String, Integer> map = new NewLinkedHashMap<>();
    map.put("abc", 1);
    map.put("aaa", 2);
    map.put("aab", 3);
    LOG.debug(map);
  }

}
