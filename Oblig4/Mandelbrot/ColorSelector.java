package Oblig4.Mandelbrot;

import javafx.scene.paint.Color;

/**
 * Created by Jo Øivind Gjernes on 04.03.2016.
 */
public class ColorSelector
{
	private ColorChoice chosenColor;
	private static final int baseLine = 40;
	private static final double scalingFactor = 255.0 / Mandelbrot.iterationLimit;

	public ColorSelector(ColorChoice choice)
	{
		this.chosenColor = choice;
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

		return (int)(iterations*scalingFactor);
	}
}