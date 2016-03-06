package Oblig4.Bifurcation.Logikk;

import Oblig4.Scale.ConvertCoordinates;
import Oblig4.Scale.Coords;

import java.util.ArrayList;

/**
 * Created by Jo Øivind Gjernes on 05.03.2016.
 *
 * TODO: Fikse en bedre implementasjon.
 * TODO: - Bare samle alle punktene for hver R
 * TODO: - Så tegne de som er funnet.
 * TODO: - Problem: må regne om koordinater?
 *
 */
public class Bifurcation
{
	public static final Coords defaultBifurcationCoords = new Coords(2.4,4.0,0.0,1.0);
	private Coords coords;
	private bifurcFunction bifurc;


	private ArrayList<ArrayList<Boolean>> points;
	private double xIncrement;
	private double yIncrement;
	private static final int initialIterations = 75;
	private static final int testIterations = 300;


	public Bifurcation(Coords drawCoords)
	{
		this(defaultBifurcationCoords, drawCoords);
	}

	public Bifurcation(Coords coords, Coords drawCoords)
	{
		this(coords, drawCoords, BifurcationFunction.FUNC1);
	}

	public Bifurcation(Coords coords, Coords drawCoords, BifurcationFunction functionType)
	{
		points = new ArrayList<>();
		this.coords = coords;
		setBifurcationFunction(functionType);

		xIncrement = ConvertCoordinates.computeXIncrement(coords, drawCoords);
		yIncrement = ConvertCoordinates.computeYIncrement(coords, drawCoords);

		computePoints();
	}


	private void computePoints()
	{
		// BoundsCheck.
		for(double xn=coords.getFromY();xn<=coords.getToY();xn+=yIncrement){
			ArrayList<Boolean> line = new ArrayList<>();
			for(double r=coords.getFromX();r<=coords.getToX();r+=xIncrement){
				line.add(computePoint(xn,r));
			}
			points.add(line);
		}
	}

	public ArrayList<ArrayList<Boolean>> getPoints()
	{
		return points;
	}

	public Coords getCoords()
	{
		return coords;
	}

	/**
	 *
	 * @param xn y-aksen, lite intuitivt
	 * @param r x-aksen
	 * @return er dette punktet en "stabil" verdi?
	 */
	private boolean computePoint(double xn, double r)
	{
		double initialXN = xn;
		for(int i=0;i<initialIterations;++i){
			xn = bifurc.numericMethod(xn,r);
		}
		for(int i=0;i<testIterations;++i){
			xn=bifurc.numericMethod(xn,r);
			if(xn>=(initialXN-yIncrement/2.0d)&&xn<=(initialXN+yIncrement/2.0)) return true;
		}
		return false;
	}

	private void setBifurcationFunction(BifurcationFunction function)
	{
		switch(function)
		{

			case FUNC1:
				bifurc = Bifurcation::func1;
				break;
			case FUNC2:
				bifurc = Bifurcation::func2;
				break;
		}
	}
	/**
	 * Funksjonen:
	 * x(n+1) = r * x(n) * (1 - xn)
	 * @param xn forrige iterasjon
	 * @param r konstant
	 * @return neste iterasjon
	 */
	private static double func1(double xn, double r)
	{
		double newVal = r*xn;
		newVal *= (1.0d-xn);
		return newVal;
	}

	/**
	 * Funksjonen:
	 * x(n+1) = (r+1)xn - r * x_n ^ 2
	 * @param xn forrige iterasjon
	 * @param r konstant
	 * @return neste iterasjon
	 */
	private static double func2(double xn, double r)
	{
		double newVal = r+1.0d;
		newVal *= xn;
		newVal -= r*xn*xn;
		return newVal;
	}


	/**
	 * ;)
	 */
	@FunctionalInterface
	public interface bifurcFunction {
		double numericMethod(double xn, double r);
	}

}
