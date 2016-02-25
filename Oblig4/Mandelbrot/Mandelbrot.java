package Oblig4.Mandelbrot;


import Oblig4.Scale.Coords;
import javafx.scene.paint.Color;

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
    private double increment = 0.01; // OBS: Må matche med oppløsning på det endelige bildet!

    private double xFrom;
    private double xTo;
    private double yFrom;
    private double yTo;

    // Instansvariabler
    private ArrayList<Point> generatedData;

    public Mandelbrot()
    {
        this(0.0-xRangeDefault, xRangeDefault, 0.0-yRangeDefault, yRangeDefault);
    }
    public Mandelbrot(double xFrom, double xTo, double yFrom, double yTo)
    {
        this(xFrom,xTo,yFrom,yTo, 0.01);
    }

    public Mandelbrot(double xFrom, double xTo, double yFrom, double yTo, double increment)
    {
        generatedData = new ArrayList<>();
        this.increment = increment;
        //magi
        this.xFrom = xFrom;
        this.xTo = xTo;
        this.yFrom = yFrom;
        this.yTo = yTo;
        constructPoints();
    }

    public Mandelbrot(Coords area, double increment)
    {
       this(area.getFromX(), area.getToX(), area.getFromY(), area.getToY(), increment);
    }

    /**
     * Funksjon for å sette inkremeneter
     * @param increment
     */
    public void setIncrement(double increment)
    {
        this.increment = increment;
    }

    private void constructPoints()
    {
        for(double x = xFrom;x<=xTo;x+=increment){
            for(double y=yFrom;y<=yTo;y+=increment){
                Point p = calculatePoint(new Complex(x,y));
                generatedData.add(p);
            }
        }
    }

    private static Point calculatePoint(Complex C)
    {
        Complex x = new Complex(); // 0+0i

        int iterations = 0;
        for(;iterations<iterationLimit;++iterations){
            x = nextIteration(x, C);
            if(x.getLengthSquared()>4.0) break;
        }

        // Sett farge
        Color color = new Color(0,0,(55/155.0)+iterations/(iterationLimit+55), 1.0); //R(0-1.0) G B Alpha=1.0
        if(iterations==iterationLimit) color = Color.BLACK;

        return new Point(C.getReal(), C.getImaginary(), color);
    }

    private static Complex nextIteration(Complex prev, Complex c)
    {
        Complex temp = prev.square();
        return temp.add(c);
    }

    public ArrayList<Point> getPoints()
    {
        return generatedData;
    }
}
