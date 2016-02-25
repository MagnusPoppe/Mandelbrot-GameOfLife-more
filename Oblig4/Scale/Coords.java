package Oblig4.Scale;

/**
 * Created by jooivind on 25.02.2016.
 */
public class Coords {
    private double fromX;
    private double toX;
    private double fromY;
    private double toY;

    public Coords(double x, double y)
    {
        this(0,x,0,y);
    }

    public Coords(double fromX, double toX, double fromY, double toY)
    {
        this.fromX = fromX;
        this.toX = toX;
        this.fromY = fromY;
        this.toY = toY;
    }
}
