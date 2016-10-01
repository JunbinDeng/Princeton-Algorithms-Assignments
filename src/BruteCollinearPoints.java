import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private List<LineSegment> segments = new ArrayList<>();

    /**
     * Finds all line segments containing 4 points.
     */
    public BruteCollinearPoints(Point[] points) {
        Point[] cPoints = points.clone();
        Arrays.sort(cPoints);
        if (hasRepeatedPoint(cPoints)) {
            throw new IllegalArgumentException("Repeated point is not allowed in the array.");
        }
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        double slope1 = points[i].slopeTo(points[j]);
                        double slope2 = points[i].slopeTo(points[k]);
                        double slope3 = points[i].slopeTo(points[l]);
                        if (slope1 == slope2 && slope1 == slope3) {
                            Point[] linePoints = new Point[4];
                            linePoints[0] = points[i];
                            linePoints[1] = points[j];
                            linePoints[2] = points[k];
                            linePoints[3] = points[l];
                            Arrays.sort(linePoints);
                            segments.add(new LineSegment(linePoints[0], linePoints[3]));
                        }
                    }
                }
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
        return segments.toArray(new LineSegment[numberOfSegments()]);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
