package exercise;

// BEGIN
public class Segment {
    private final Point beginPoint;
    private final Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        int x1 = beginPoint.getX();
        int y1 = beginPoint.getY();

        int x2 = endPoint.getX();
        int y2 = endPoint.getY();

        int x3 = (x1 + x2) / 2;
        int y3 = (y1 + y2) / 2;

        Point midPoint = new Point(x3, y3);

        return midPoint;
    }
}
// END
