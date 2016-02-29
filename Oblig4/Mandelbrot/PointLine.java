package Oblig4.Mandelbrot;

import java.util.ArrayList;

/**
 * Created by Jo Øivind Gjernes on 28.02.2016.
 */
public class PointLine
{
	private ArrayList<ColoredPoint> pointLine;

	public PointLine()
	{
		pointLine = new ArrayList<>();
	}

	public void addPoint(ColoredPoint p)
	{
		pointLine.add(p);
	}

	public ArrayList<ColoredPoint> getPointLine()
	{
		return pointLine;
	}

	public ColoredPoint getPoint(int index)
	{
		return pointLine.get(index);
	}

}
