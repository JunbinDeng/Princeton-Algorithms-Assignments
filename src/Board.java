import edu.princeton.cs.algs4.Queue;

public class Board {
    private static final int BLANK = 0;
    private int[][] blocks;
    private int n;

    /**
     * Construct a board from an n-by-n array of blocks
     * (where blocks[i][j] = block in row i, column j).
     */
    public Board(int[][] blocks) {
        n = blocks.length;
        this.blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(blocks[i], 0, this.blocks[i], 0, n);
        }
    }

    /**
     * Board dimension n.
     */
    public int dimension() {
        return n;
    }

    /**
     * Number of blocks out of place.
     */
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int goalAt = goalAt(i, j);
                if (goalAt != blocks[i][j] && goalAt != BLANK) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    /**
     * Sum of Manhattan distances between blocks and goal.
     */
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int goalAt = goalAt(i, j);
                int targetAt = blocks[i][j];
                if (goalAt == targetAt || targetAt == BLANK) {
                    continue;
                }
                int di = (targetAt - 1) / n - i;
                int dj = (targetAt - 1) % n - j;
                int distance = Math.abs(di) + Math.abs(dj);
                manhattan += distance;
            }
        }
        return manhattan;
    }

    /**
     * Is this board the goal board?
     */
    public boolean isGoal() {
        return hamming() == 0;
    }

    /**
     * A board that is obtained by exchanging any pair of blocks.
     */
    public Board twin() {
        Board board = new Board(blocks);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (blocks[i][j] != BLANK && blocks[i][j + 1] != BLANK) {
                    board.swap(i, j, i, j + 1);
                    return board;
                }
            }
        }
        return board;
    }

    /**
     * Does this board equal y?
     */
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        Board that = (Board) y;
        if (dimension() != that.dimension()) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * All neighboring boards.
     */
    public Iterable<Board> neighbors() {
        int i = 0, j = 0;
        found:
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                if (blocks[k][l] == BLANK) {
                    i = k;
                    j = l;
                    break found;
                }
            }
        }

        Queue<Board> boards = new Queue<>();
        if (i > 0) {
            Board board = new Board(blocks);
            board.swap(i, j, i - 1, j);
            boards.enqueue(board);
        }
        if (i < n - 1) {
            Board board = new Board(blocks);
            board.swap(i, j, i + 1, j);
            boards.enqueue(board);
        }
        if (j > 0) {
            Board board = new Board(blocks);
            board.swap(i, j, i, j - 1);
            boards.enqueue(board);
        }
        if (j < n - 1) {
            Board board = new Board(blocks);
            board.swap(i, j, i, j + 1);
            boards.enqueue(board);
        }
        return boards;
    }

    /**
     * String representation of this board (in the output format specified below).
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(blocks[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int goalAt(int i, int j) {
        if (i == n - 1 && j == n - 1) {
            return 0;
        }
        return i * n + j + 1;
    }

    private void swap(int i, int j, int i1, int j1) {
        int temp = blocks[i][j];
        blocks[i][j] = blocks[i1][j1];
        blocks[i1][j1] = temp;
    }

    /**
     * Unit tests (not graded).
     */
    public static void main(String[] args) {

    }
}
