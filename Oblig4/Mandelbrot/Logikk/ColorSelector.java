package Oblig4.Mandelbrot.Logikk;

import javafx.scene.paint.Color;

/**
 * Created by Jo Øivind Gjernes on 04.03.2016.
 *
 * Klasse for å velge farge på mandelbrot bildet gitt av antall iterasjoner.
 */
public class ColorSelector
{
	private ColorChoice chosenColor;
	private static final int baseLine = 30;
	private static final double scalingFactor = (255.0-baseLine) / Mandelbrot.iterationLimit;
	private Draw callBack;

	public ColorSelector(ColorChoice choice, Draw callBack)
	{
		this.chosenColor = choice;
		this.callBack = callBack;
	}

	public void setColor(ColorChoice colorChoice)
	{
		this.chosenColor = colorChoice;
		callBack.draw();
	}

	public Color getColor(int iterations)
	{
		iterations = normalizeColor(iterations);
		switch (chosenColor) {
			case BLACKWHITE:
				return Color.rgb(iterations, iterations, iterations);
			case RED:
				return Color.rgb(iterations, 0, 0);
			case GREEN:
				return Color.rgb(0, iterations, 0);
			case BLUE:
				return Color.rgb(0, 0, iterations);
			default:
				return Color.rgb(0,0,0);
		}
	}

	private int normalizeColor(int iterations)
	{
		if (iterations == Mandelbrot.iterationLimit) {
			return 0;
		}
		iterations= (int)(iterations*scalingFactor);
		return iterations+baseLine;
	}
}