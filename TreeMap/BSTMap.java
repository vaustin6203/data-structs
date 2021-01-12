import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size = 0;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        private Node(K key, V value, Node l, Node r) {
            this.key = key;
            this.value = value;
            left = l;
            right = r;
            size += 1;
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        return get(key) != null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node n = get(root, key);
        if (n == null) {
            return null;
        } else {
            return n.value;
        }
    }

    private Node get(Node n, K k) {
        if (n == null) {
            return null;
        }
        int comp = k.compareTo(n.key);
        if (comp < 0) {
            return get(n.left, k);
        } else if (comp > 0) {
            return get(n.right, k);
        } else {
            return n;
        }
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node n, K key, V value) {
        if (n == null) {
            return new Node(key, value, null, null);
        }
        int comp = key.compareTo(n.key);
        if (comp < 0) {
            n.left = put(n.left, key, value);
        } else if (comp > 0) {
            n.right = put(n.right, key, value);
        } else {
            n.value = value;
        }
        return n;
    }

    private Node minKey(Node n) {
        if (n.left == null) {
            return n;
        } else {
            return minKey(n.left);
        }
    }

    public void printInOrder() {
        Node n = root;
        while (n != null) {
            Node m = minKey(n);
            System.out.println(m.value);
            n = n.left;
        }
        System.out.println(root.value);
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public java.util.Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /** Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
