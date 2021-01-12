import edu.princeton.cs.algs4.Queue;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> toSort = new Queue<>();
        toSort.enqueue("Tori");
        toSort.enqueue("Wendy");
        toSort.enqueue("Doug");
        toSort.enqueue("Liam");
        toSort.enqueue("Taylor");
        toSort.enqueue("Taylor");
        toSort.enqueue("Austin");
        int size1 = toSort.size();
        Queue<String> sorted = QuickSort.quickSort(toSort);
        int size2 = sorted.size();
        assertTrue(isSorted(sorted));
        assertEquals(size1, size2);
        assertTrue(size2 != 0);
    }

    @Test
    public void testMergeSort() {
        Queue<String> toSort = new Queue<>();
        toSort.enqueue("Tori");
        toSort.enqueue("Storm");
        toSort.enqueue("Eda");
        toSort.enqueue("Hannah");
        toSort.enqueue("Neema");
        toSort.enqueue("Kit");
        toSort.enqueue("Cyrus");
        toSort.enqueue("Tori");
        int size1 = toSort.size();
        Queue<String> sorted = MergeSort.mergeSort(toSort);
        int size2 = sorted.size();
        assertTrue(isSorted(sorted));
        assertEquals(size1, size2);
    }

    @Test
    public void testMergeSortEdge() {
        Queue<String> toSort = new Queue<>();
        for (int i = 0; i < 99; i += 1) {
            toSort.enqueue("Tori");
        }
        int size1 = toSort.size();
        Queue<String> sorted = MergeSort.mergeSort(toSort);
        int size2 = sorted.size();
        assertTrue(isSorted(sorted));
        assertEquals(size1, size2);
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
