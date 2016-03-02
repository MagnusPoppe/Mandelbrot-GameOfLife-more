package Oblig4.Scale;

/**
 * Created by Jo Øivind Gjernes on 25.02.2016.
 *
 * Klasse for å konvertere fra ett koordinatsystem til et annet
 *
 *
 * BESKRIVELSE:
 * Man initialiserer det med hvilket koordinatsystem man skal konvertere fra
 * og hvilket man skal konvertere til. Så kan man putte inn et koordinat og
 * få tilsvarende koordinat ut i det andre systemet.
 */
public class ConvertCoordinates
{
	private Coords from;
	private Coords to;

	private double scalingFactorX;
	private double scalingFactorY;
	private double scalingConstantX;
	private double scalingConstantY;

	/**
	 * Opprette et konverterinngsobjekt dette kan konvertere et punkt fra et
	 * koordinatsystem til et annet (x,y)-planet.
	 * @param from koordinatsystemet man konverterer fra
	 * @param to koordinatsystemet man konverterer til
	 */
	public ConvertCoordinates(Coords from, Coords to)
	{
		this.from = from;
		this.to = to;
		computeScalingFactors();
	}

	/**
	 * Beregne skalerningsfaktorer (slik at man slipper det ved hver operasjon)
	 */
	private void computeScalingFactors()
	{
		scalingFactorX = (to.getToX()-to.getFromX())/(from.getToX()-from.getFromX());
		scalingFactorY = (to.getToY()-to.getFromY())/(from.getToY()-from.getFromY());
		scalingConstantX = to.getFromX();
		scalingConstantY = to.getFromY();
	}

	public double computeXIncrement()
	{
		return 1.0/scalingFactorX;
	}

	public double computeYIncrement()
	{
		return 1.0/scalingFactorY;
	}

	/**
	 * Metoden mottar "skjerm-koordinatsystem" og konverterer til
	 * mandelbrotsystemet.
	 *
	 * @param old Mandelbrot coordinates
	 * @param zoom The new "zoomed" coordinates
	 * @param window the window coordnates
	 * @return new mandelbrot koordinates.
	 */
	public static Coords computeNewMandelbrot(Coords old, Coords zoom, Coords window) {
		double windowWidth = window.getToX() - window.getFromX();
		double windowHeight = window.getToY() - window.getFromY();

		double relativeStartX = zoom.getFromX() / windowWidth;
		double relativeEndX = zoom.getToX() / windowWidth;
		double relativeStartY = zoom.getFromY() / windowHeight;
		double relativeEndY = zoom.getToY() / windowHeight;

		double oldRelativeStartX = 0;
		double oldRelativeEndX = old.getToX() - old.getFromX();
		double oldRelativeStartY = 0;
		double oldRelativeEndY = old.getToY() - old.getFromY();

		double newStartX = relativeStartX * (old.getToX() - old.getFromX()) + old.getFromX();
		double newEndX = relativeEndX * (old.getToX() - old.getFromX()) + old.getFromX();
		double newStartY = relativeStartY * (old.getToY() - old.getFromY()) + old.getFromY();
		double newEndY = relativeEndY * (old.getToY() - old.getFromY()) + old.getFromY();

		return new Coords(newStartX, newEndX, newStartY, newEndY);
	}
}
