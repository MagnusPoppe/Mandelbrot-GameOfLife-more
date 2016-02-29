package Oblig4.Mandelbrot;

import javafx.scene.paint.Color;

/**
 * Klassen brukes til å holde på fargen når den går
 * igjennom programmet. Brukes med "Mandelbrot"
 * klassen.
 *      - Magnus
 * Created by jooivind on 25.02.2016.
 */
public class Point extends ColoredPoint {
	private Color color;

	public Point(Color color)
	{
		this.color = color;
	}

	public Point(int iterations)
	{
		if(iterations == 100) iterations = 10;
		else iterations += 50;
		color = Color.rgb(10,10,iterations);
	}

	public Color getColor()
	{
		return color;
	}
}
