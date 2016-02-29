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
    private static int iterationLimit = 100;
    private double xIncrement = 0.01; // OBS: Må matche med oppløsning på det endelige bildet!
    private double yIncrement = 0.01; // ------------------------ "" ------------------------

    private double xFrom;
    private double xTo;
    private double yFrom;
    private double yTo;

    // Instansvariabler
    private ArrayList<PointLine> pointLines; //Skjønner ikke helt hvordan denne er implementert.. -Magnus

    public Mandelbrot()
    {
        this(0.0-xRangeDefault, xRangeDefault, 0.0-yRangeDefault, yRangeDefault);
    }
    public Mandelbrot(double xFrom, double xTo, double yFrom, double yTo)
    {
        this(xFrom,xTo,yFrom,yTo, 0.01, 0.01);
    }

    public Mandelbrot(double xFrom, double xTo, double yFrom, double yTo, double xIncrement, double yIncrement)
    {
        pointLines = new ArrayList<>();
        this.xIncrement = xIncrement;
        this.yIncrement = yIncrement;

        this.xFrom = xFrom;
        this.xTo = xTo;
        this.yFrom = yFrom;
        this.yTo = yTo;
        constructPoints();
    }

    public Mandelbrot(Coords area, double xIncrement, double yIncrement)
    {
       this(area.getFromX(), area.getToX(), area.getFromY(), area.getToY(), xIncrement, yIncrement);
    }

    /**
     * Funksjon for å sette inkremeneter
     * @param xIncrement inkrement for punkter man går over
     */
    public void setxIncrement(double xIncrement)
    {
        this.xIncrement = xIncrement;
    }

    private void constructPoints()
    {
        for(double y = yFrom;y<=yTo;y+= yIncrement){
            PointLine pointLine = new PointLine();
            for(double x=xFrom;x<=xTo;x+= xIncrement){
                Point point = new Point(calculatePoint(new Complex(x,y)));
                pointLine.addPoint(point);
            }
            //
            pointLines.add(pointLine);
        }
    }

    private static int calculatePoint(Complex C)
    {
        Complex x = new Complex(); // 0+0i

        int iterations = 0;
        for(;iterations<iterationLimit;++iterations){
            x = nextIteration(x, C);
            if(x.getLengthSquared()>4.0) break;
        }

        return iterations;
    }

    private static Complex nextIteration(Complex prev, Complex c)
    {
        Complex temp = prev.square();
        return temp.add(c);
    }

    public ArrayList<PointLine> getPoints()
    {
        return pointLines;
    }
}
