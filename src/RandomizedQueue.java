import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int n;

    /**
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        n = 0;
    }

    /**
     * Is the queue empty?
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Return the number of items on the queue.
     */
    public int size() {
        return n;
    }

    /**
     * Add the item.
     */
    public void enqueue(Item item) {
        checkItem(item);
        if (n == items.length) {
            resize(2 * n);
        }
        items[n++] = item;
        randomSwap();
    }

    /**
     * Remove and return a random item.
     */
    public Item dequeue() {
        checkAccessible();
        randomSwap();
        Item item = items[--n];
        int length = items.length;
        if (n > 0 && n == length / 4) {
            resize(length / 2);
        }
        return item;
    }

    private void resize(int max) {
        Object[] temp = new Object[max];
        System.arraycopy(items, 0, temp, 0, n);
        items = (Item[]) temp;
    }

    private void randomSwap() {
        if (n <= 1) {
            return;
        }
        int index = StdRandom.uniform(n);
        Item temp = items[n - 1];
        items[n - 1] = items[index];
        items[index] = temp;
    }

    /**
     * Return (but do not remove) a random item.
     */
    public Item sample() {
        checkAccessible();
        randomSwap();
        return items[n - 1];
    }

    /**
     * Return an independent iterator over items in random order.
     */
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private void checkItem(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add a null item to queue.");
        }
    }

    private void checkAccessible() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot access to an empty queue.");
        }
    }

    private class RandomizedIterator implements Iterator<Item> {
        private int index;

        @Override
        public boolean hasNext() {
            return !isEmpty() && index < n;
        }

        @Override
        public Item next() {
            if (index >= n) {
                throw new NoSuchElementException("No more items in queue.");
            }
            return items[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove item in queue.");
        }
    }

    /**
     * Unit testing
     */
    public static void main(String[] args) {
        String item1 = "0";
        String item2 = "1";
        String item3 = "5";
        String item4 = "4";
        String item5 = "3";

        RandomizedQueue<String> items = new RandomizedQueue<>();
        items.enqueue(item1);
        items.enqueue(item2);
        items.enqueue(item5);
        items.enqueue(item4);
        if (!items.isEmpty()) {
            items.dequeue();
        }
        items.enqueue(item3);
        for (String item : items) {
            StdOut.print(item + " ");
        }
    }
}