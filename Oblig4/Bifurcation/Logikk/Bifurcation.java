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
	// -----
	public static final Coords defaultBifurcationCoords = new Coords(2.4,4.0,0.0,1.0);
	private static final int initialIterations = 75;
	private static final int testIterations = 300;


	// -----
	private ArrayList<ArrayList<Boolean>> points;
	private double xIncrement;
	private double yIncrement;
	private Coords coords;

	// funksjonen som brukes til å evaluere punkter
	private bifurcFunction bifurc;


	public Bifurcation(Coords drawCoords)
	{
		this(defaultBifurcationCoords, drawCoords);
	}

	public Bifurcation(Coords coords, Coords drawCoords)
	{
		this(coords, drawCoords, BifurcationFunction.FUNC1);
	}

	/**
	 * Konstruktør.
	 * Tegner punkter, slik at klassen er ferdig til bruk etter objektet er opprettet
	 * @param coords koordinater man ønsker å se på (input til bifurkasjonsfunksjonen
	 * @param drawCoords koordinatsystem det skal tegnes på
	 * @param functionType type bifurkajjonsfunksjon (valg definert i enum)
	 */
	public Bifurcation(Coords coords, Coords drawCoords, BifurcationFunction functionType)
	{
		points = new ArrayList<>();
		this.coords = coords;
		setBifurcationFunction(functionType);

		xIncrement = ConvertCoordinates.computeXIncrement(coords, drawCoords);
		yIncrement = ConvertCoordinates.computeYIncrement(coords, drawCoords);

		computePoints();
	}


	/**
	 * Iterere gjennom ønskete xn, r verdier.
	 * Der xn er illustrert langs y-aksen
	 * og r er illustrert langs x-aksen.
	 */
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

	/**
	 * Hent ut resultatet fra beregningen.
	 * @return resultat.
	 */
	public ArrayList<ArrayList<Boolean>> getPoints()
	{
		return points;
	}

	/**
	 * Hent ut hvilket "område" funksjonen regner ut.
 	 * @return koordinater
	 */
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

	/**
	 * Velg hvilken funksjon som skal brukes til utregningen av punkter!
	 * @param function man kan velge mellom "funksjon 1" og "funskjon 2" (definert i denne klassen)
	 */
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
