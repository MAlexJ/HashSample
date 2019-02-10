package com.malexj.impl;

import java.util.ArrayList;
import java.util.Map;

public interface NewMap {

  ArrayList<NodeN>[] getTable();

  int numOfBasket();

  Map<String, String> getInfo();

  <T> int calculatePositionInBasket(T obj);

  void printMap();
}
