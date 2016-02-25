package Oblig4.Mandelbrot;

import javafx.scene.paint.Color;

/**
 * Kravspec for alle komplekse tall.
 *
 * Created by jooivind on 25.02.2016.
 */
public abstract class ColoredPoint {
    public abstract double getX();
    public abstract double getY();
    public abstract void setX(double x);
    public abstract void setY(double y);
    public abstract Color getColor();
}
