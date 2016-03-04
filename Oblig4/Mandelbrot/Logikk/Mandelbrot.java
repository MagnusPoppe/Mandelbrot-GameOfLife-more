package Oblig4.Mandelbrot.Logikk;


import Oblig4.Scale.ConvertCoordinates;
import Oblig4.Scale.Coords;

import java.util.ArrayList;

/**
 * Created by jooivind on 25.02.2016.
 * Klasse for å regne ut punkter i mandelbrot
 */
public class Mandelbrot {
    //+- mayb
    public static final Coords defaultMandelbrotCoords = new Coords(-2.0,2.0,-2.0,2.0);
    public static int iterationLimit = 500;
    private double xIncrement = 0.001; // OBS: Må matche med oppløsning på det endelige bildet!
    private double yIncrement = 0.001; // ------------------------ "" ------------------------
    // Instansvariabler
    private final ArrayList<ArrayList<Integer>> pointLines = new ArrayList<>();

    private Coords coords;

    public Mandelbrot(Coords drawArea)
    {
        this(defaultMandelbrotCoords, drawArea);
    }

    public Mandelbrot(Coords area, Coords drawArea) throws IllegalArgumentException {
        coords = area;
        this.xIncrement = ConvertCoordinates.computeXIncrement(area,drawArea);
        this.yIncrement = ConvertCoordinates.computeYIncrement(area,drawArea);
        constructPoints();
    }

    private boolean boundsCheck()
    {
        if(this.xIncrement<1E-16) return false;
        if(this.yIncrement<1E-16) return false;
        return true;
    }
    private void constructPoints() throws IllegalArgumentException {
        // Hopp ut dersom steppet er for lite!
        if(!boundsCheck()) throw new IllegalArgumentException("For liten zoom!");

        for (double y = coords.getFromY(); y <= coords.getToY(); y += yIncrement) {
            ArrayList<Integer> line = new ArrayList<>();
            for (double x = coords.getFromX(); x <= coords.getToX(); x += xIncrement) {
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
        prev.square();
        prev.add(c);
        return prev;
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
