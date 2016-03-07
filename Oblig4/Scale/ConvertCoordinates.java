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
	/**
	 * Konvertere et koordinatobjekt fra et koordinatsystem til et annet.
	 * @param toScale koordinater man ønsker å "skalere"
	 * @param from koordinatsystemet koordinatetene man ønsker å skalere er definert i
	 * @param to koordinatsystemet man skal konvertere til.
	 * @return skalert koordinater
	 * */
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

	/**
	 * Regne ut inkrement nødvendig i et koordinatsystem for å matche antall punkter i et annet.
	 * Brukes for å regne ut inkrement for mandelbrot, og bifurkasjon løkker.
	 * I koordinatsystemets X-retning
	 * @param from typisk: bilde-koordinater eksempel: {0, 800, 0, 800} (i.e fra 0 til bredde-1, fra 0 til høyde-1)
	 * @param to koordinatsystemm for en funksjon f.eks {0, 1, 0, 1}
	 * @return 1/800
	 */
	public static double computeXIncrement(Coords from, Coords to)
	{
		return computeXScalingFactor(to,from);
	}

	/**
	 * Regne ut inkrement nødvendig i et koordinatsystem for å matche antall punkter i et annet.
	 * Brukes for å regne ut inkrement for mandelbrot, og bifurkasjon løkker.
	 * I koordinatsystemets Y-retning
	 * @param from typisk: bilde-koordinater eksempel: {0, 800, 0, 800} (i.e fra 0 til bredde-1, fra 0 til høyde-1)
	 * @param to koordinatsystemm for en funksjon f.eks {0, 1, 0, 1}
	 * @return 1/800
	 */
	public static double computeYIncrement(Coords from, Coords to)
	{
		return computeYScalingFactor(to,from);
	}

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
