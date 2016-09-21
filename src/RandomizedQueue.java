import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private int size;

    private class Node {
        private Item item;
        private Node next;
    }

    /**
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        // Empty
    }

    /**
     * Is the queue empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items on the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Add the item.
     */
    public void enqueue(Item item) {
        checkItem(item);
        Node node = new Node();
        node.item = item;
        node.next = null;
        if (isEmpty()) {
            first = node;
        } else {
            Node oldFirst = first;
            first = node;
            first.next = oldFirst;
        }
        size++;
    }

    /**
     * Remove and return a random item.
     */
    public Item dequeue() {
        checkAccessible();
        Node node;
        if (size == 1) {
            Node lastFirst = first;
            first = null;
            node = lastFirst;
        } else {
            int index = StdRandom.uniform(size);
            Node current = first;
            node = first.next;
            for (int i = 0; i < size; i++) {
                if (index == i || current.next.next == null) {
                    node = current.next;
                    current.next = current.next.next;
                    break;
                }
                current = current.next;
            }
        }
        size--;
        return node.item;
    }

    /**
     * Return (but do not remove) a random item.
     */
    public Item sample() {
        checkAccessible();
        int index = StdRandom.uniform(size);
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (index == i || current.next == null) {
                break;
            }
            current = current.next;
        }
        return current.item;
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

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("No more items in queue.");
            }
            Item item = current.item;
            current = current.next;
            return item;
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
        String item1 = "1";
        String item2 = "2";
        String item3 = "3";
        String item4 = "4";

        RandomizedQueue<String> items = new RandomizedQueue<>();
        items.enqueue(item2);
        items.enqueue(item3);
        items.enqueue(item1);
        items.enqueue(item4);
        items.dequeue();
        items.dequeue();

        for (String item : items) {
            StdOut.print(item + " ");
        }
    }
}