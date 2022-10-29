package exercise;

// BEGIN
public class Cottage implements Home{
    private final double area;
    private final int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public int compareTo(Home anotherHome) {
        if (this.getArea() == anotherHome.getArea()) {
            return 0;
        } else if (this.getArea() < anotherHome.getArea()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return this.floorCount + " этажный коттедж площадью " + this.getArea() + " метров";
    }

}
// END
