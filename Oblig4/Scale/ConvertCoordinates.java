package Oblig4.Scale;

import Oblig4.Mandelbrot.Point;

/**
 * Created by Jo Øivind Gjernes on 25.02.2016.
 *
 * Klasse for å konvertere fra ett koordinatsystem til et annnet
 *
 *
 * BESKRIVELSE:
 * Man initialiserer det med hvilket koordinat system man skal konvertere fra
 * og hvilket man skal konvertere til.
 * så kan man putte inn et koordinat og få tilsvarende koordinat ut i det andre systemet.
 */
public class ConvertCoordinates
{
	private Coords from;
	private Coords to;

	private double scalingFactorX;
	private double scalingFactorY;
	private double scalingConstantX;
	private double scalingConstantY;

	public ConvertCoordinates(Coords from, Coords to)
	{
		this.from = from;
		this.to = to;
		computeScalingFactors();
	}

	private void computeScalingFactors()
	{
		scalingFactorX = (from.getToX()-from.getFromX())/(to.getToX()-to.getFromX());
		scalingFactorY = (from.getToY()-from.getFromY())/(to.getToY()-to.getFromY());
		scalingConstantX = (to.getToX()-to.getFromX())/2;
		scalingConstantY = (to.getToY()-to.getFromY())/2;
	}

	Point convert(Point in)
	{
		double newX = in.getX()*scalingFactorX+scalingConstantX;
		double newY = in.getY()*scalingFactorY+scalingConstantY;
		in.setX(newX);
		in.setY(newY);
		return in;
	}


}
