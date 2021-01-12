import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private HashSet<K> keys;
    private KVPairs<K, V>[] arrPairs;
    private int capacity;
    private double loadFactor;
    private int size;

    private class KVPairs<K, V> {
        K key;
        V value;

        KVPairs(K k, V v) {
            key = k;
            value = v;
        }
    }

    public MyHashMap() {
        keys = new HashSet<>();
        arrPairs = new KVPairs[16];
        loadFactor = 0.75;
        size = 0;
        capacity = 16;
    }

    public MyHashMap(int s) {
        keys = new HashSet<>(s);
        arrPairs = new KVPairs[s];
        loadFactor = 0.75;
        size = 0;
        capacity = s;
    }

    public MyHashMap(int s, double lfact) {
        keys = new HashSet<>(s, (float) lfact);
        arrPairs = new KVPairs[s];
        loadFactor = lfact;
        size = 0;
        capacity = s;
    }

    private int indexOf(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private void resize() {
        capacity = capacity * 2;
        KVPairs[] ogArr = arrPairs;
        arrPairs = new KVPairs[capacity];
        keys = new HashSet<>(capacity, (float) loadFactor);
        for (int i = 0; i < size; i += 1) {
            KVPairs<K, V> p = ogArr[i];
            if (p != null) {
                arrPairs[i] = p;
                keys.add(p.key);
            }
        }
    }

    private void checkLoadFactor() {
        if (size / capacity >= loadFactor) {
            resize();
        }
    }

    @Override
    public void clear() {
        keys.clear();
        arrPairs = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        return arrPairs[indexOf(key)].value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        checkLoadFactor();
        KVPairs pair = new KVPairs(key, value);
        int index = indexOf(key);
        if (!containsKey(key)) {
            keys.add(key);
            arrPairs[index] = pair;
            size += 1;
        } else {
            arrPairs[index] = pair;
        }
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("MyHashMap does not support remove.");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("MyHashMap does not support remove.");
    }
}
