package com.malexj.impl;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.junit.Test;

public class NewHashMapTest {

    private static final Logger LOG = Logger.getLogger(NewHashMapTest.class);

    @Test
    public void test() {
        NewHashMap<User,Integer> map = new NewHashMap<>();

        for (int i = 0; i <= 6; i++) {
            map.put(new User(1, "Name"), i);
        }

        LOG.debug("countColliding: " + map.countCollidingEntries());
        LOG.debug("size: " + map.size());
    }

    @Test
    public void test2(){
        NewHashMap<User,Integer> map = new NewHashMap<>();

        for (int i = 0; i <= 6; i++) {
            map.put(new User(1, "Name"), i);
        }

        ArrayList<NodeN>[] table = map.getTable();
        System.out.println(Arrays.toString(table));
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