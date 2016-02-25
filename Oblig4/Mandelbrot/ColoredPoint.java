package Oblig4.Mandelbrot;

import javafx.scene.paint.Color;

/**
 * Kravspec for alle komplekse tall.
 *
 * Created by jooivind on 25.02.2016.
 */
public interface ColoredPoint {
    public double getX();
    public double getY();
    public void setX(double x);
    public void setY(double y);
    public Color getColor();
}
