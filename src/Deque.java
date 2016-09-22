import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Item item;
        private Node next;
    }

    /**
     * Construct an empty deque.
     */
    public Deque() {
        // Empty
    }

    /**
     * Is the deque empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items on the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Add the item to the front.
     */
    public void addFirst(Item item) {
        checkItem(item);
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            node.next = null;
            first = node;
            last = node;
        } else {
            node.next = first;
            first = node;
        }
        size++;
    }

    /**
     * Add the item to the end.
     */
    public void addLast(Item item) {
        checkItem(item);
        Node node = new Node();
        node.item = item;
        node.next = null;
        if (isEmpty()) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;
    }

    /**
     * Remove and return the item from the front.
     */
    public Item removeFirst() {
        checkRemovable();
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        size--;
        return item;
    }

    /**
     * Remove and return the item from the end.
     */
    public Item removeLast() {
        checkRemovable();
        Item item = last.item;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            for (Node current = first; current != null && current.next != null; current = current.next) {
                if (current.next.next != null) {
                    continue;
                }
                current.next = null;
                last = current;
            }
        }
        size--;
        return item;
    }

    /**
     * Return an iterator over items in order from front to end.
     */
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private void checkItem(Item item) {
        if (item == null) {
            throw new NullPointerException("Cannot add a null item to deque.");
        }
    }

    private void checkRemovable() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove item from an empty deque.");
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("No more next item in deque.");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove item in deque.");
        }
    }

    /**
     * Unit testing.
     */
    public static void main(String[] args) {
        String item1 = "0";
        String item2 = "1";
        String item3 = "5";

        Deque<String> items = new Deque<>();
        items.addFirst(item1);
        items.addFirst(item2);
        String last = items.removeLast();
        StdOut.println(last);
        if (!items.isEmpty()) {
            last = items.removeLast();
            StdOut.println(last);
        }
        items.addFirst(item3);
        if (!items.isEmpty()) {
            last = items.removeLast();
            StdOut.println(last);
        }
    }
}