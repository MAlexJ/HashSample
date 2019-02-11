package com.malexj.impl;

import static junit.framework.TestCase.assertEquals;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.junit.Test;

public class CheckNumberOfBasketsInsideMap {

  private static final Logger LOG = Logger.getLogger(CheckNumberOfBasketsInsideMap.class);

  /**
   * The next size value at which to resize (capacity * load factor)
   */
  private static final String THRESHOLD_VAL = "threshold";

  /**
   * The load factor for the hash table
   */
  private static final String LOAD_FACTOR_VAL = "loadFactor";

  /**
   * One object is put to the map
   */
  @Test
  public void testWhenOneObjectIsAdded() {
    NewHashMap<Integer, Integer> map = new NewHashMap<>();
    map.put(1, 2);

    int expected = 16;
    assertEquals(expected, map.numOfBasket());
  }


  /**
   * 12 objects added to the map
   */
  @Test
  public void testWhenManyObjectIsAdded() {
    // given
    NewHashMap<Integer, Integer> map = new NewHashMap<>();
    for (int i = 0; i < 12; i++) {
      map.put(i, i * 10);
    }

    // when
    // (capacity * load factor) = 16 * 0.75 = 12
    String defaultThreshold = "12";

    // then
    int expected = 16;
    assertEquals(expected, map.numOfBasket());

    String threshold = map.getInfo().get(THRESHOLD_VAL);
    LOG.debug("Default threshold: " + threshold);
    assertEquals(defaultThreshold, threshold);
  }

  @Test
  public void testDefaultThreshold() {
    String threshold = new NewHashMap<>().getInfo().get(THRESHOLD_VAL);

    String defaultThreshold = "0";
    assertEquals(defaultThreshold, threshold);
  }


  @Test
  public void testThreshold() {
    // given
    NewHashMap<Integer, Integer> map = new NewHashMap<>();
    map.put(1, 2);
    LOG.debug("Map size: " + map.size());

    // when
    float loadFactor = Float.parseFloat(map.getInfo().get(LOAD_FACTOR_VAL));
    LOG.debug("LOAD_FACTOR: " + loadFactor);

    int capacity = map.numOfBasket();
    LOG.debug("CAPACITY: " + capacity);

    String defaultThreshold = Integer.toString((int) (capacity * loadFactor));
    LOG.debug("Calculate threshold: " + defaultThreshold);

    // then
    String threshold = map.getInfo().get(THRESHOLD_VAL);
    assertEquals(defaultThreshold, threshold);
  }

  @Test
  public void testOverThreshold() {
    // given
    NewHashMap<Integer, Integer> map = new NewHashMap<>();
    for (int i = 1; i < 20; i++) {
      map.put(i, i * 10);
    }
    LOG.debug("Map size: " + map.size());

    // when
    float loadFactor = Float.parseFloat(map.getInfo().get(LOAD_FACTOR_VAL));
    LOG.debug("LOAD_FACTOR: " + loadFactor);

    int capacity = map.numOfBasket();
    LOG.debug("CAPACITY: " + capacity);

    String defaultThreshold = Integer.toString((int) (capacity * loadFactor));
    LOG.debug("Calculate threshold: " + defaultThreshold);

    // then
    String threshold = map.getInfo().get(THRESHOLD_VAL);
    assertEquals(defaultThreshold, threshold);
  }

  @Test
  public void testThresholdIfMapHasCollision() {
    // given
    NewHashMap<Collision, Integer> map = new NewHashMap<>();
    int size = 20;
    for (int i = 0; i < size; i++) {
      map.put(new Collision(1), i);
    }
    LOG.debug("Map size: " + map.size());

    // when
    float loadFactor = Float.parseFloat(map.getInfo().get(LOAD_FACTOR_VAL));
    LOG.debug("LOAD_FACTOR: " + loadFactor);

    int capacity = map.numOfBasket();
    LOG.debug("CAPACITY: " + capacity);

    String defaultThreshold = Integer.toString((int) (capacity * loadFactor));
    LOG.debug("Calculate threshold: " + defaultThreshold);

    // then
    String threshold = map.getInfo().get(THRESHOLD_VAL);
    assertEquals(defaultThreshold, threshold);

    // check collision
    int positionInBasket = map.calculatePositionInBasket(new Collision(1));
    ArrayList<NodeN> nodes = map.getTable()[positionInBasket];
    assertEquals(size, nodes.size());
    map.printMap();
  }

  @Getter
  @AllArgsConstructor
  class Collision {

    private int val;

    @Override
    public int hashCode() {
      return val;
    }

    @Override
    public String toString() {
      return "C[val=" + val + "]";
    }
  }
}
