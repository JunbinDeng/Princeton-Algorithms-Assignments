import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Perform trials independent experiments on an n-by-n grid
 */
public class PercolationStats {
    private final int trials;
    private double[] trialsGroup;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        trialsGroup = new double[trials];
        this.trials = trials;
        for (int trial = 0; trial < trials; trial++) {
            Percolation percolation = new Percolation(n);
            int openedSites = 0;
            while (!percolation.percolates()) {
                int p = StdRandom.uniform(1, n + 1);
                int q = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(p, q)) {
                    percolation.open(p, q);
                    openedSites++;
                }
            }
            double fraction = (double) openedSites / (n * n);
            trialsGroup[trial] = fraction;
        }
    }

    /**
     * Sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(trialsGroup);
    }

    /**
     * Sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(trialsGroup);
    }

    /**
     * Low endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    /**
     * High endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    /**
     * Test client
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            int n = Integer.parseInt(args[0]);
            int trals = Integer.parseInt(args[1]);
            PercolationStats stats = new PercolationStats(n, trals);
            StdOut.println("mean                    = " + stats.mean());
            StdOut.println("stddev                  = " + stats.stddev());
            StdOut.println("95% confidence interval = " + stats.confidenceLo()
                    + ", " + stats.confidenceHi());
        }
    }
}
