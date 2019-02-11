package com.malexj.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NodeN<K, V> {

  private K key;
  private V value;
  private int hash;

}
