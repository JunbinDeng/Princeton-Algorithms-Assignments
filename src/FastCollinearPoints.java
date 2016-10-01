import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> segments = new ArrayList<>();

    /**
     * Finds all line segments containing 4 or more points.
     */
    public FastCollinearPoints(Point[] points) {
        Point[] cPoints = points.clone();
        Arrays.sort(cPoints);
        if (hasRepeatedPoint(cPoints)) {
            throw new IllegalArgumentException("Repeated point is not allowed in the array.");
        }
        for (int i = 0; i < cPoints.length - 3; i++) {
            Arrays.sort(cPoints);
            Arrays.sort(cPoints, cPoints[i].slopeOrder());
            for (int p = 0, first = 1, last = 2; last < cPoints.length; last++) {
                while (last < cPoints.length && Double.compare(cPoints[p].slopeTo(cPoints[first]),
                        cPoints[p].slopeTo(cPoints[last])) == 0) {
                    last++;
                }
                if (cPoints[p].compareTo(cPoints[first]) < 0 && last - first >= 3) {
                    segments.add(new LineSegment(cPoints[p], cPoints[last - 1]));
                }
                first = last;
            }
        }
    }

    /**
     * The number of line segments.
     */
    public int numberOfSegments() {
        return segments.size();
    }

    /**
     * The line segments.
     */
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }

    private boolean hasRepeatedPoint(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
