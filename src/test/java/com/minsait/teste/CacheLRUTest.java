package com.minsait.teste;

import static org.junit.jupiter.api.Assertions.*;

import com.minsait.teste.Algoritimos.CacheLRU;
import org.junit.jupiter.api.Test;

public class CacheLRUTest {

    @Test
    public void testRemoveEldestEntryWhenCapacityExceeded() {
        CacheLRU<Integer, String> cache = new CacheLRU<>(3);

        cache.put(1, "Item1");
        cache.put(2, "Item2");
        cache.put(3, "Item3");

        assertEquals(3, cache.size());
        assertTrue(cache.get(1).equals("Item1"));

        cache.put(4, "Item4");

        assertEquals(3, cache.size());
        assertNull(cache.get(2), "O item mais antigo deve ser removido");
        assertEquals("Item1", cache.get(1));
        assertEquals("Item3", cache.get(3));
        assertEquals("Item4", cache.get(4));
    }
}
