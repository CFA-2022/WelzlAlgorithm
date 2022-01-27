
package Helper;

import java.awt.Point;

public class Circle {
    private Point center;
    private int radius;

    public Circle(Point center, int r) {
        this.center = center;
        this.radius = r;
    }

    public Point getCenter() {
        return this.center;
    }

    public int getRadius() {
        return this.radius;
    }
}
