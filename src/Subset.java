import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {

    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        String in;
        do {
            in = StdIn.readString();
            randomizedQueue.enqueue(in);
        } while (!StdIn.isEmpty());
        int times = Integer.parseInt(args[0]);
        for (int i = 0; i < times; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}