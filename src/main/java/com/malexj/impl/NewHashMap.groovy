package com.malexj.impl

import java.lang.reflect.Field

class NewHashMap<K,V> extends HashMap<K,V> {

    NewHashMap() {
        super()
    }

    NewHashMap(int initialCapacity) {
        super(initialCapacity)
    }

    NewHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor)
    }

    int countCollidingEntries() {
        println("Class: " + this.getClass().getSuperclass().getSimpleName())

        def fs = this.getClass().getSuperclass().getDeclaredFields()
        def table
        def count = 0

        // find HashMap.Node<K,V> in map
        for (Field field : fs) {
            if (field.getName() == "table") {
                field.setAccessible(true)
                table = field.get(super)
            } else {
                field.setAccessible(true)
                println(field.getName() + ":  " + field.get(super))
            }
        }
        println()

        // find the basket number and items in the linked list
        for (int i = 0; i < table.length - 1; i++) {
            sleep(100)
            def e = table[i]
            if (e != null) {
                print("[" + i + "] " + e)
                while (e.next != null) {
                    e = e.next
                    print(", " + e)
                    count++
                }
                println()
            }
        }
        print("\nThe number of cells in the basket: ")
        print(table.length)
        print("\n")
        return count
    }

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

        if(Objects.isNull(table)){
            return new ArrayList<NodeN>[0]
        }

        def length = table.length-1;
        ArrayList<NodeN>[] list = new ArrayList<NodeN>[length]

        for (int i = 0; i < length; i++) {
            def obj = table[i]
            list[i] = new ArrayList<NodeN>()
            getNext(obj, list[i])
        }

        return list
    }

    void getNext(def obj, ArrayList<NodeN> list) {
        if (Objects.isNull(obj)) {
            return
        }
        list.add(new NodeN(obj.key, obj.value))
        getNext(obj.next, list)
    }
}
