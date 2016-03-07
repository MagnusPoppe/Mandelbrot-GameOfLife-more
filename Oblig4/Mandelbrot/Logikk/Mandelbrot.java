package Oblig4.Mandelbrot.Logikk;


import Oblig4.Scale.ConvertCoordinates;
import Oblig4.Scale.Coords;

import java.util.ArrayList;

/**
 * Created by jooivind on 25.02.2016.
 * Klasse for å regne ut punkter i mandelbrot
 */
public class Mandelbrot
{
	//+- mayb
	public static final Coords defaultMandelbrotCoords = new Coords(-2.0, 2.0, -2.0, 2.0);
	public static int iterationLimit = 200;
	private double xIncrement = 0.001; // OBS: Må matche med oppløsning på det endelige bildet!
	private double yIncrement = 0.001; // ------------------------ "" ------------------------
	// Instansvariabler
	private final ArrayList<ArrayList<Integer>> pointLines = new ArrayList<>();

	private Coords coords;

	public Mandelbrot(Coords drawArea)
	{
		this(defaultMandelbrotCoords, drawArea);
	}

	/**
	 * Opprette et mandelbrot objekt
	 *
	 * @param area     koordinater for mandelbrot-systemet
	 * @param drawArea koordinater for bildet som mandelbrot skal tegnes på
	 * @throws IllegalArgumentException kastest dersom zoom nivået blir for "høyt"
	 */
	public Mandelbrot(Coords area, Coords drawArea) throws IllegalArgumentException
	{
		coords = area;
		this.xIncrement = ConvertCoordinates.computeXIncrement(area, drawArea);
		this.yIncrement = ConvertCoordinates.computeYIncrement(area, drawArea);
		constructPoints();
	}

	/**
	 * Denne sjekker om incrementene er større en minste grense
	 * (Bugger ut dersom incrementene blir for små.)
	 *
	 * @return Sann hvis incrementene er store nok
	 */
	private boolean boundsCheck()
	{
		if (this.xIncrement < 1E-16) return false;
		if (this.yIncrement < 1E-16) return false;
		return true;
	}

	/**
	 * Konstruere en liste med punkter i mandelbrot figuren. Hvert punkt er reperesenteret av antallet iterasjoner man kan kjøre
	 * på mandelbrot-funksjonen før lengden overskrider 4.0
	 *
	 * @throws IllegalArgumentException kaster IllegalARgumentException dersom
	 */
	private void constructPoints() throws IllegalArgumentException
	{
		// Hopp ut dersom steppet er for lite!
		if (!boundsCheck()) throw new IllegalArgumentException("For liten zoom!");

		for (double y = coords.getFromY(); y <= coords.getToY(); y += yIncrement) {
			ArrayList<Integer> line = new ArrayList<>();
			for (double x = coords.getFromX(); x <= coords.getToX(); x += xIncrement) {
				line.add(calculatePoint(new Complex(x, y)));
			}
			pointLines.add(line);
		}
	}

	/**
	 * Regner ut antall iterasjoner før "lengden" på det komplekse tallet overskrider 2.0
	 * (tar ikke kvadratrot, så sammenligner med kvadratet av vektorlengden altså 4.0)
	 *
	 * @param C komplekst tall
	 * @return antall iterasjoner
	 */
	private static int calculatePoint(Complex C)
	{
		Complex x = new Complex(); // 0+0i

		int iterations = 0;
		for (; iterations < iterationLimit; ++iterations) {
			x = nextIteration(x, C);
			if (x.getLengthSquared() > 4.0) break;
		}

		return iterations;
	}

	/**
	 * Beregn neste iterasjon
	 * Funksjon: forrige_iterasjon * forrige_iterasjon + konstant
	 *
	 * @param prev forrige iterasjon
	 * @param c    konstant
	 * @return svaret
	 */
	private static Complex nextIteration(Complex prev, Complex c)
	{
		prev.square();
		prev.add(c);
		return prev;
	}

	/**
	 * Få ut beregnet data!
	 *
	 * @return liste over liste av punkter
	 */
	public ArrayList<ArrayList<Integer>> getPointLines()
	{
		return pointLines;
	}

	/**
	 * Returnerer koordinatene som det jobbes på
	 *
	 * @return koordinatene
	 */
	public Coords getCoords()
	{
		return coords;
	}
}
