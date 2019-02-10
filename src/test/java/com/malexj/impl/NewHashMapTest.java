package com.malexj.impl;

import static junit.framework.TestCase.assertEquals;

import java.util.ArrayList;
import java.util.Map;
import java.util.SplittableRandom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

public class NewHashMapTest {

  /**
   * link -> {@link java.util.HashMap} field: modCount
   *
   * The number of times this HashMap has been structurally modified Structural modifications are
   * those that change the number of mappings in the HashMap or otherwise modify its internal
   * structure (e.g., rehash).  This field is used to make iterators on Collection-views of the
   * HashMap fail-fast.  (See ConcurrentModificationException).
   */
  private static final String MOD_COUNT_VAL = "modCount";


  private static final String LOAD_FACTOR_VAL = "loadFactor";

  @Test
  public void testNumOfBasket() {
    NewHashMap<Integer, Integer> map = new NewHashMap<>();
    map.put(1, 2);

    int expected = 16;
    assertEquals(expected, map.numOfBasket());
  }

  @Test
  public void testInfo() {
    Map<String, String> info = new NewHashMap<>().getInfo();
    assertEquals("0.75", info.get(LOAD_FACTOR_VAL));
    assertEquals("0", info.get(MOD_COUNT_VAL));
  }


  @Test
  public void TestNumberOfBasket() {
    User user = new User(new SplittableRandom().nextInt(0, Integer.MAX_VALUE), "Name");

    NewHashMap<User, Integer> map = new NewHashMap<>();
    map.put(user, 123);

    int num = map.calculatePositionInBasket(user);

    ArrayList<NodeN>[] table = map.getTable();
    User actualUser = (User) table[num].get(0).getKey();
    assertEquals(user, actualUser);
  }


  /**
   * modCount - The number of times this HashMap has been structurally modified
   */
  @Test
  public void testLoadFactorVal() {
    NewHashMap<User, Integer> map = new NewHashMap<>();

    int expectedModCount = 0;

    for (int i = 0; i <= 7; i++) {
      expectedModCount++;
      map.put(new User(1, "Name"), i);
    }

    Map<String, String> info = map.getInfo();
    assertEquals("0.75", info.get(LOAD_FACTOR_VAL));
    assertEquals(Integer.toString(expectedModCount), info.get(MOD_COUNT_VAL));
    assertEquals(16, map.numOfBasket());
  }


  @Getter
  @Setter
  @AllArgsConstructor
  private class User {

    private int id;
    private String name;

    @Override
    public int hashCode() {
      return id;
    }

    @Override
    public String toString() {
      return "U[id: " + id + ", name: " + name + ']';
    }
  }
}