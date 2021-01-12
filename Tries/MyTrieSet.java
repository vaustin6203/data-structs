import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class MyTrieSet implements TrieSet61B {
    private HashMap<String, Node> root = new HashMap<>();

    private class Node {
        private boolean isKey;
        private HashMap<String, Node> map;
        private String key;
        private Node next;

        private Node(String c) {
            map = new HashMap<>();
            isKey = false;
            map.put(c, this);
            key = c;
            next = null;
        }
    }

    @Override
    public void clear() {
        root.clear();
    }

    @Override
    public boolean contains(String key) {
        String first = String.valueOf(key.charAt(0));
        if (root.isEmpty() || !root.containsKey(first)) {
            return false;
        }
        Node curr = root.get(first);
        for (int i = 1; i < key.length(); i++) {
            String ch = String.valueOf(key.charAt(i));
            Node tnode = curr.map.get(ch);
            if (tnode == null) {
                return false;
            }
            curr = tnode;
        }
        return true;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        String first = String.valueOf(key.charAt(0));
        if (!root.containsKey(first)) {
            root.put(first, new Node(first));
        }
        Node curr = root.get(first);
        for (int i = 1, n = key.length(); i < n; i++) {
            String c = String.valueOf(key.charAt(i));
            if (!curr.map.containsKey(c)) {
                Node baby = new Node(c);
                curr.map.put(c, baby);
                curr.next = baby;
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> sList = new ArrayList<>();
        if (!contains(prefix)) {
            return sList;
        }
        String first = String.valueOf(prefix.charAt(0));
        Node curr = root.get(first);
        for (int i = 1; i < prefix.length(); i += 1) {
            first = String.valueOf(prefix.charAt(i));
            curr = curr.map.get(first);
        }
        StringBuilder keys = new StringBuilder(prefix);
        curr = curr.next;
        while (curr != null) {
            keys.append(curr.key);
            if (curr.isKey) {
                sList.add(keys.toString());
                keys = new StringBuilder(prefix);
            }
            curr = curr.next;
        }
        return sList;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException("longestPrefixOf is an unsupported method");
    }
}
