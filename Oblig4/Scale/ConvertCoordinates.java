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
 *
 * MERK:
 * Klassen er "delvis" konvertert til en statisk klasse,
 * da invariantene i klassen må oppdateres fra gang til gang...
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
		scalingFactorX = computeXScalingFactor(from,to);
		scalingFactorY = computeYScalingFactor(from,to);
		scalingConstantX = computeScalingConstantX(to);
		scalingConstantY = computeScalingConstantY(to);
	}

	/**
	 * Konverter fra "from" koordinatsystem til "to" koordinatsystem
	 * @param x - x koordinat
	 * @param y - y koordinat
	 * @return
	 */
	public double[] convert(double x, double y)
	{
		double newX = (x - from.getFromX())*scalingFactorX+scalingConstantX;
		double newY = (y - from.getFromY())*scalingFactorY+scalingConstantY;
		return new double[]{newX,newY};
	}

	public static Coords convert(Coords toScale, Coords from, Coords to)
	{

		double scalingFactorX = computeXScalingFactor(from,to);
		double scalingFactorY = computeYScalingFactor(from,to);
		double scalingConstantX = computeScalingConstantX(to);
		double scalingConstantY = computeScalingConstantY(to);

		double newX = (toScale.getFromX() - from.getFromX())*scalingFactorX+scalingConstantX;
		double newY = (toScale.getFromY() - from.getFromY())*scalingFactorY+scalingConstantY;
		double newToX = (toScale.getToX() - from.getFromX())*scalingFactorX+scalingConstantX;
		double newToY = (toScale.getToY() - from.getFromY())*scalingFactorY+scalingConstantY;
		return new Coords(newX,newToX,newY,newToY);
	}

	public static double computeXIncrement(Coords from, Coords to)
	{
		return computeXScalingFactor(to,from);
	}

	public static double computeYIncrement(Coords from, Coords to)
	{
		return computeYScalingFactor(to,from);
	}

// --------- Lagt til for å unngå duplisering av kode!
	private static double computeXScalingFactor(Coords from, Coords to)
	{
		return (to.getToX()-to.getFromX())/(from.getToX()-from.getFromX());
	}

	private static double computeYScalingFactor(Coords from, Coords to)
	{
		return (to.getToY()-to.getFromY())/(from.getToY()-from.getFromY());
	}

	private static double computeScalingConstantX(Coords to)
	{
		return to.getFromX();
	}

	private static double computeScalingConstantY(Coords to)
	{
		return to.getFromY();
	}

}
