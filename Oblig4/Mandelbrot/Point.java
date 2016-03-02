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

	// Fargevalg:
		static boolean grayScale;
		static boolean blueScale;
		static boolean greenScale;
		static boolean redScale;
		static boolean skyblueScale;

	public Point(Color color)
	{
		this.color = color;
	}

	public Point(int iterations)
	{
		if(iterations == 100) iterations = 10;
		else iterations += 50;

		if (blueScale) color = Color.rgb(10,10,iterations);
		else if (greenScale) color = Color.rgb(10,iterations,10);
		else if (redScale) color = Color.rgb(iterations,10,10);
		else if (skyblueScale) color = Color.rgb(50,iterations,iterations);
		else color = Color.rgb(iterations,iterations,iterations);
	}

	public Color getColor()
	{
		return color;
	}

	// Metoder for å endre på fargene til mandelbrot
	public static void setGreyScale() {
		reset();
		grayScale = true;
	}
	public static void setBlueScale() {
		reset();
		blueScale = true;
	}
	public static void setGreenScale() {
		reset();
		greenScale = true;
	}
	public static void setRedScale() {
		reset();
		redScale = true;
	}
	public static void setSkyblueScale() {
		reset();
		skyblueScale = true;
	}

	public static void reset() {
		grayScale = false;
	}
}
