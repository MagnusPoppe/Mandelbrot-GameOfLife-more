package Oblig4.Mandelbrot;

import javafx.scene.paint.Color;

/**
 * Klassen brukes til å holde på fargen når den går
 * igjennom programmet. Brukes med "Mandelbrot"
 * klassen.
 *      - Magnus
 * Created by jooivind on 25.02.2016.
 */
public class Point implements Scalable {
    private Complex complex;
    private Color color;

    @Override
    public double getX() {
        return complex.getReal();
    }

    @Override
    public double getY() {
        return complex.getImaginary();
    }
}
