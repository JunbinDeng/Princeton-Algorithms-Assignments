import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private int size;

    private class Node {
        Item item;
        Node next;
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
        return null;
    }

    /**
     * Return (but do not remove) a random item.
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot query item from an empty deque.");
        }
        int index = StdRandom.uniform(size);
        Node current = first;
        for (int i = 0; i < size; i++) {
            if (index == i) {
                return current.item;
            }
            if (current.next != null) {
                current = current.next;
            } else {
                break;
            }
        }
        return current.item;
    }

    /**
     * Return an independent iterator over items in random order.
     */
    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    /**
     * Unit testing
     */
    public static void main(String[] args) {

    }
}