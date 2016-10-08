import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    /**
     * Find a solution to the initial board (using the A* algorithm).
     */
    public Solver(Board initial) {
        int moves = 0;
        Node node = new Node(initial, moves, null);
        Node twinNode = new Node(initial.twin(), moves, null);

        MinPQ<Node> nodes = new MinPQ<>();
        nodes.insert(node);
        nodes.insert(twinNode);
        node = nodes.delMin();

        while (!node.board.isGoal()) {
            Stack<Board> neighborBoards = new Stack<>();
            for (Board board : node.board.neighbors()) {
                neighborBoards.push(board);
            }
            moves = node.moves + 1;
            for (Board neighborBoard : neighborBoards) {
                Board neighborPrevBoard = null;
                if (node.prev != null) {
                    neighborPrevBoard = node.prev.board;
                }
                if (!neighborBoard.equals(neighborPrevBoard)) {
                    Node neighborNode = new Node(neighborBoard, moves, node);
                    nodes.insert(neighborNode);
                }
            }
            node = nodes.delMin();
        }
    }

    /**
     * Is the initial board solvable?
     */
    public boolean isSolvable() {
        return false;
    }

    /**
     * Min number of moves to solve initial board; -1 if unsolvable.
     */
    public int moves() {
        return 0;
    }

    /**
     * Sequence of boards in a shortest solution; null if unsolvable.
     */
    public Iterable<Board> solution() {
        return null;
    }

    private class Node {
        private Board board;
        private int moves;
        private Node prev;

        public Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
        }
    }

    /**
     * Solve a slider puzzle (given below).
     */
    public static void main(String[] args){
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
