package com.malexj.impl

import java.lang.reflect.Field

class NewLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    NewLinkedHashMap() {
        super
    }

    Map<String, String> getInfo() {
        Map<String, String> map = new HashMap<>();
        def fs = this.getClass().getSuperclass().getDeclaredFields()
        for (Field field : fs) {
            field.setAccessible(true)
            map.put(field.getName(), field.get(super).toString())
        }
        return map
    }
}
