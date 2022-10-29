package exercise;

// BEGIN
public class Flat implements Home{
    private final double area;
    private final double balconyArea;
    private final int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return this.area + this.balconyArea;
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
        return "Квартира площадью " + getArea() + " метров на " + this.floor + " этаже";
    }
}
// END
