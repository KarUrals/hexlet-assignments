package exercise;

// BEGIN
public class Circle {
    private final Point circleCenter;
    private final int radius;

    public Circle(Point circleCenter, int radius) {
        this.circleCenter = circleCenter;
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public double getSquare() throws NegativeRadiusException{
        if (this.radius < 0) {
            throw new NegativeRadiusException("radius cannot be less then 0");
        }
        return Math.PI * Math.pow(this.radius, 2);
    }
}
// END
