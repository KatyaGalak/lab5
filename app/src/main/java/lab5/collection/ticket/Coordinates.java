package lab5.collection.ticket;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"x", "y"})
public class Coordinates implements Comparable<Coordinates> {
    private double x;
    private float y; // Значение поля должно быть больше -11

    public void validate() throws IllegalArgumentException {
        if (y <= -11) throw new IllegalArgumentException("y must be > -11");
    }

    public Coordinates(double x, float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {}

    public double getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public int compareTo(Coordinates coordinates) {
        if (coordinates == null) return 1;

        if (Double.compare(this.x, coordinates.x) == 0)
            return Double.compare(this.x, coordinates.x);

        return Float.compare(this.y, coordinates.y);
    }

    @Override
    public String toString() {
        return "Coordinates [" +
                    "\n\t\t x = " + x +
                    "\n\t\t y = " + y + " \n\t ]";

    }
}
