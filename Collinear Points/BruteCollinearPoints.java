import java.util.*;
import edu.princeton.cs.algs4.*;

public class BruteCollinearPoints {
    private LineSegment[] segments;
    
    private void checkDuplicatePoints(Point[] points) {
        for (int i = 0; i < points.length-1; i++) {
            for (int j = i+1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicate Points!");
                }
            }
        }
    }
    
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        checkDuplicatePoints(points);
        ArrayList<LineSegment> foundSegments = new ArrayList();
        
        Point[] copypoints = Arrays.copyOf(points, points.length);
        Arrays.sort(copypoints);
        
        for (int p = 0; p < copypoints.length - 3; p++) {
            for (int q = p+1; q < copypoints.length - 2; q++) {
                for (int r = q+1; r < copypoints.length - 1; r++) {
                    for (int s = r+1; s < copypoints.length; s++) {
                        if (copypoints[p].slopeTo(copypoints[q]) == copypoints[p].slopeTo(copypoints[r]) &&
                                copypoints[p].slopeTo(copypoints[r]) == copypoints[p].slopeTo(copypoints[s])) {
                            foundSegments.add(new LineSegment(copypoints[p], copypoints[s]));
                        }
                    }
                }
            }
        }
        
        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }
    
    public int numberOfSegments() {
        // the number of line segments
        return segments.length;
    }
    public LineSegment[] segments() {
        // the line segments
        return Arrays.copyOf(segments, numberOfSegments());
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
