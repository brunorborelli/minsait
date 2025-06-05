package com.minsait.teste.Algoritimos;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheLRU<K, V> {
    private final int capacidadeMaxima;
    private final Map<K, V> cache;

    public void put(K key, V value) {
        cache.put(key, value);
    }
    public V get(K key) {
        return cache.get(key);
    }
    public void remove(K key) {
        cache.remove(key);
    }
    public int size() {
        return cache.size();
    }

    public void imprimeCache() {
        System.out.println("Cache: " + cache);
    }

    public CacheLRU(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.cache = new LinkedHashMap<K, V>(capacidadeMaxima, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > CacheLRU.this.capacidadeMaxima;
            }
        };
    }

}
