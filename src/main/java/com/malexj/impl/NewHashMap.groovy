package com.malexj.impl

import java.lang.reflect.Field

class NewHashMap<K, V> extends HashMap<K, V> implements NewMap {

    NewHashMap() {
        super()
    }

    NewHashMap(int initialCapacity) {
        super(initialCapacity)
    }

    NewHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor)
    }

    /**
     * Get list Nodes
     */
    @Override
    ArrayList<NodeN>[] getTable() {

        def fs = this.getClass().getSuperclass().getDeclaredFields()
        def table = null
        for (Field field : fs) {
            if (field.getName() == "table") {
                field.setAccessible(true)
                table = field.get(super)
                break
            }
        }

        if (Objects.isNull(table)) {
            return new ArrayList<NodeN>[0]
        }

        ArrayList<NodeN>[] list = new ArrayList<NodeN>[table.length]
        for (int i = 0; i <= table.length - 1; i++) {
            def obj = table[i]
            list[i] = new ArrayList<NodeN>()
            getNext(obj, list[i])
        }

        return list
    }

    @Override
    int numOfBasket() {
        return getTable().length
    }

    @Override
    Map<String, String> getInfo() {
        Map<String, String> map = new HashMap<>();
        def fs = this.getClass().getSuperclass().getDeclaredFields()
        for (Field field : fs) {
            if (field.getName() != "table") {
                field.setAccessible(true)
                map.put(field.getName(), field.get(super).toString())
            }
        }
        return map
    }

    @Override
    <T> int calculatePositionInBasket(T key) {
        int hash = getKeyHashCode(key)
        return (numOfBasket() - 1) & hash
    }

    @Override
    void printMap() {
        ArrayList<NodeN>[] table = getTable()
        for (int i = 0; i < table.length; i++) {
            print("[" + i + "] ")
            printNode(table[i])
            println()
        }
    }

    private static void printNode(ArrayList<NodeN> list) {
        for (NodeN node : list) {
            print(node)
            print(" | ")
        }
    }

    /**
     * Get key hash code
     */
    private static <T> int getKeyHashCode(T obj) {
        int h
        return (obj == null) ? 0 : (h = obj.hashCode()) ^ (h >>> 16)
    }

    /**
     * Get next Node
     */
    void getNext(def obj, ArrayList<NodeN> list) {
        if (Objects.isNull(obj)) {
            return
        }
        list.add(new NodeN(obj.key, obj.value))
        getNext(obj.next, list)
    }
}
