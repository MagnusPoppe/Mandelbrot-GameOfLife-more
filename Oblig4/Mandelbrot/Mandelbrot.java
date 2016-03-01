package Oblig4.Mandelbrot;


import Oblig4.Scale.Coords;

import java.util.ArrayList;

/**
 * Created by jooivind on 25.02.2016.
 * Klasse for å regne ut punkter i mandelbrot
 */
public class Mandelbrot {
    //+- mayb
    private static final double xRangeDefault = 2.0;
    private static final double yRangeDefault = 2.0;
    private static int iterationLimit = 200;
    private double xIncrement = 0.001; // OBS: Må matche med oppløsning på det endelige bildet!
    private double yIncrement = 0.001; // ------------------------ "" ------------------------

    private double xFrom;
    private double xTo;
    private double yFrom;
    private double yTo;

    // Instansvariabler
    private ArrayList<PointLine> pointLines; //Skjønner ikke helt hvordan denne er implementert.. -Magnus

    public Mandelbrot() {
        this(0.0 - xRangeDefault, xRangeDefault, 0.0 - yRangeDefault, yRangeDefault);
    }

    public Mandelbrot(double xFrom, double xTo, double yFrom, double yTo) {
        this(xFrom, xTo, yFrom, yTo, 0.01, 0.01);
    }

    public Mandelbrot(double xFrom, double xTo, double yFrom, double yTo, double xIncrement, double yIncrement) {
        pointLines = new ArrayList<>();
        this.xIncrement = xIncrement;
        this.yIncrement = yIncrement;

        this.xFrom = xFrom;
        this.xTo = xTo;
        this.yFrom = yFrom;
        this.yTo = yTo;
        constructPoints();
    }

    public Mandelbrot(Coords area, double xIncrement, double yIncrement) {
        this(area.getFromX(), area.getToX(), area.getFromY(), area.getToY(), xIncrement, yIncrement);
    }

    /**
     * Funksjon for å sette inkremeneter
     *
     * @param xIncrement inkrement for punkter man går over
     */
    public void setxIncrement(double xIncrement) {
        this.xIncrement = xIncrement;
    }

    private void constructPoints() {
        for (double y = yFrom; y <= yTo; y += yIncrement) {
            PointLine pointLine = new PointLine();
            for (double x = xFrom; x <= xTo; x += xIncrement) {
                Point point = new Point(calculatePoint(new Complex(x, y)));
                pointLine.addPoint(point);
            }
            //
            pointLines.add(pointLine);
        }
    }

    private static int calculatePoint(Complex C) {
        Complex x = new Complex(); // 0+0i

        int iterations = 0;
        for (; iterations < iterationLimit; ++iterations) {
            x = nextIteration(x, C);
            if (x.getLengthSquared() > 4.0) break;
        }

        return iterations;
    }

    private static Complex nextIteration(Complex prev, Complex c) {
        Complex temp = prev.square();
        return temp.add(c);
    }

    public ArrayList<PointLine> getPoints() {
        return pointLines;
    }

    public Coords getCoords() {
        return new Coords(xFrom, xTo, yFrom, yTo);
    }

    /**
     * Metoden mottar et nytt zoomet koordinatsystem og
     * regner om til mandelbrot koordinater.
     *
     * @param old Mandelbrot coordinates
     * @param zoom The new "zoomed" coordinates
     * @param window the window coordnates
     * @return new mandelbrot koordinates.
     */
    public static Coords newZoomCoords(Coords old, Coords zoom, Coords window) {
        double windowWidth = window.getToX() - window.getFromX();
        double windowHeight = window.getToY() - window.getFromY();

        double relativeStartX = zoom.getFromX() / windowWidth;
        double relativeEndX = zoom.getToX() / windowWidth;
        double relativeStartY = zoom.getFromY() / windowHeight;
        double relativeEndY = zoom.getToY() / windowHeight;

        double oldRelativeStartX = 0;
        double oldRelativeEndX = old.getToX() - old.getFromX();
        double oldRelativeStartY = 0;
        double oldRelativeEndY = old.getToY() - old.getFromY();

        double newStartX = relativeStartX * (old.getToX() - old.getFromX()) + old.getFromX();
        double newEndX = relativeEndX * (old.getToX() - old.getFromX()) + old.getFromX();
        double newStartY = relativeStartY * (old.getToY() - old.getFromY()) + old.getFromY();
        double newEndY = relativeEndY * (old.getToY() - old.getFromY()) + old.getFromY();

        return new Coords(newStartX, newEndX, newStartY, newEndY);
    }
}
