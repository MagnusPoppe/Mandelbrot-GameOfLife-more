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

	/**
	 * Kunne like gjerne stått: MandelPane callBack,
	 * men ville abstrahere vekk unødvendige detaljer-
	 * Hensikten med denne referansen er å kunne kalle "callbBack.draw" når man endrer på fargen
	 * i.e. bildet blir tegnet på nytt dersom fargen endres.
	 */
	private Draw callBack;

	public ColorSelector(ColorChoice choice, Draw callBack)
	{
		this.chosenColor = choice;
		this.callBack = callBack;
	}

	/**
	 * Set fargen
	 * @param colorChoice fargen bestemt i ENUM ColorChoice
	 */
	public void setColor(ColorChoice colorChoice)
	{
		this.chosenColor = colorChoice;
		callBack.draw();
	}

	/**
	 * Metoden oversetter "iterasjoner" fra mandelbrot til en JavaFX farge
	 * @param iterations antall iterasjoner et punkt har i mandelbrot
	 * @return JavaFX Color
	 */
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

	/**
	 * Brukes for å "normalisere" fargene - Dersom en iterasjon har nått maksgrensen satt i mandelbrot klassen,
	 * vil vi ha svart farge
	 * Ellers skaleres fargen til en skala (baseLimit-255) fra  0-iterationLimit (definert i mandelbrot)
	 * @param iterations iterasjoner
	 * @return gyldig rgb-nivå
	 */
	private int normalizeColor(int iterations)
	{
		if (iterations == Mandelbrot.iterationLimit) {
			return 0;
		}
		iterations= (int)(iterations*scalingFactor);
		return iterations+baseLine;
	}
}