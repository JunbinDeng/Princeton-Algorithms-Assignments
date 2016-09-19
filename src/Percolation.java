import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP = 0;
    private boolean[][] opened;
    private int size;
    private WeightedQuickUnionUF qf;
    private int bottom;

    /**
     * Create n-by-n grid, with all sites blocked
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Arg n must be > 0");
        }
        opened = new boolean[n][n];
        qf = new WeightedQuickUnionUF(n * n + 2);
        size = n;
        bottom = n * n + 1;
    }

    /**
     * Open site (row i, column j) if it is not open already
     */
    public void open(int i, int j) {
        opened[i - 1][j - 1] = true;

        if (i == 1) {
            qf.union(getQFIndex(i, j), TOP);
        }
        if (i == size) {
            qf.union(getQFIndex(i, j), bottom);
        }

        for (int k = 0; k < 4; k++) {
            unionNeighbor(i, j, k);
        }
    }

    /**
     * Is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        return opened[i - 1][j - 1];
    }

    /**
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        return qf.connected(getQFIndex(i, j), TOP);
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return qf.connected(bottom, TOP);
    }

    private int getQFIndex(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size) {
            throw new IndexOutOfBoundsException("Row and column must be >= 1 and <= " + size);
        }
        return size * (i - 1) + j;
    }

    private void unionNeighbor(int i, int j, int direction) {
        switch (direction) {
            case 0:  // Left
                if (j > 1 && isOpen(i, j - 1)) {
                    qf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
                }
                break;
            case 1:  // Top
                if (i > 1 && isOpen(i - 1, j)) {
                    qf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
                }
                break;
            case 2: // Right
                if (j < size && isOpen(i, j + 1)) {
                    qf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
                }
                break;
            case 3:  // Bottom
                if (i < size && isOpen(i + 1, j)) {
                    qf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
                }
                break;
            default:
                break;
        }
    }
}
