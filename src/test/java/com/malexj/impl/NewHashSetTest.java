package com.malexj.impl;

import static junit.framework.TestCase.assertNotNull;

import java.util.Map;
import org.apache.log4j.Logger;
import org.junit.Test;

public class NewHashSetTest {

  private static final Logger LOG = Logger.getLogger(NewHashSetTest.class);

  /**
   * Dummy value to associate with an Object in the backing Map
   */
  private static final String PRESENT_VAL = "PRESENT";

  @Test
  public void testDefValue() {
    Map<String, String> info = new NewHashSet<>().getInfo();
    info.forEach((k, v) -> LOG.debug("KEY=" + k + ", VAL=" + v));

    String present = info.get(PRESENT_VAL);
    assertNotNull(present);
  }

}