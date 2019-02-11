package com.malexj.impl;

import java.util.Map;
import org.apache.log4j.Logger;
import org.junit.Test;

public class NewLinkedHashMapTest {

  private static final Logger LOG = Logger.getLogger(NewLinkedHashMapTest.class);

  private static final String HEAD_VAL = "head";
  private static final String TAIL_VAL = "tail";

  @Test
  public void test() {
    NewLinkedHashMap<String, Integer> map = new NewLinkedHashMap<>();
    map.put("aac", 1);
    map.put("abc", 2);
    map.put("aab", 3);
    Map<String, String> info = map.getInfo();
    info.forEach((k, v) -> LOG.debug("KEY=" + k + ", VAL=" + v));

    String head = info.get(HEAD_VAL);
    LOG.debug("HEAD=" + head);

    String tail = info.get(TAIL_VAL);
    LOG.debug("TAIl=" + tail);
  }

}