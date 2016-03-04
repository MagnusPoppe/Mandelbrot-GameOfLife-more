package Oblig4.Mandelbrot;


import Oblig4.Scale.Coords;

import java.util.ArrayList;

/**
 * Created by jooivind on 25.02.2016.
 * Klasse for å regne ut punkter i mandelbrot
 */
public class Mandelbrot {
    //+- mayb
    public static final Coords defaultMandelbrotCoords = new Coords(-2.0,2.0,-2.0,2.0);
    public static int iterationLimit = 200;
    private double xIncrement = 0.001; // OBS: Må matche med oppløsning på det endelige bildet!
    private double yIncrement = 0.001; // ------------------------ "" ------------------------
    // Instansvariabler
    private final ArrayList<ArrayList<Integer>> pointLines = new ArrayList<>();

    private Coords coords;

    public Mandelbrot(double xIncrement, double yIncrement)
    {
        this(defaultMandelbrotCoords, xIncrement,yIncrement);
    }

    public Mandelbrot(Coords area, double xIncrement, double yIncrement) {
        coords = area;
        this.xIncrement = xIncrement;
        this.yIncrement = yIncrement;

        constructPoints();
    }

    private void constructPoints() {

        for (double y = coords.getFromY(); y < coords.getToY(); y += yIncrement) {
            ArrayList<Integer> line = new ArrayList<>();
            for (double x = coords.getFromX(); x < coords.getToX(); x += xIncrement) {
                line.add(calculatePoint(new Complex(x, y)));
            }
            pointLines.add(line);
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


    public ArrayList<ArrayList<Integer>> getPointLines()
    {
        return pointLines;
    }

    public Coords getCoords()
    {
        return coords;
    }
}
