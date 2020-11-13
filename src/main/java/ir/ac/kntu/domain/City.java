package ir.ac.kntu.domain;

import java.awt.geom.Point2D;
import java.util.Objects;

public class City {

    private String name;

    private Point2D.Double coordinates;

    public City(String name, Point2D.Double coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.coordinates = new Point2D.Double(latitude,longitude);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point2D.Double getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point2D.Double coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name) &&
                Objects.equals(coordinates, city.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates);
    }
}
