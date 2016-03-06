package Oblig4.Scale;

/**
 * Created by jooivind on 25.02.2016.
 * <p>
 * Klasse for å beskrive et koordinatsystem
 */
public class Coords
{
	private double fromX;
	private double toX;
	private double fromY;
	private double toY;

	/**
	 * Konstruktør for koordinatsystem-beskrivelse
	 *
	 * @param fromX minste X koordinat i systemet
	 * @param toX   største X koordinat i systemet
	 * @param fromY minste Y koordinat i systemet
	 * @param toY   største Y koordinat i systemet
	 */
	public Coords(double fromX, double toX, double fromY, double toY)
	{
		this.fromX = fromX;
		this.toX = toX;
		this.fromY = fromY;
		this.toY = toY;
	}

	/**
	 * Sørge for 1:1 mellom høyde og bredde.
	 * Ingen logikk her- den skalerer med utgangspunkt i X-retningen, og velger Y-range deretter.
	 */
	public void enforceProportions()
	{
		this.toY = this.fromY+(this.toX-this.fromX);
	}

	public double getFromX()
	{
		return fromX;
	}

	public double getToX()
	{
		return toX;
	}

	public double getFromY()
	{
		return fromY;
	}

	public double getToY()
	{
		return toY;
	}

	@Override
	public String toString()
	{
		return "Coords{" +
			"fromX=" + fromX +
			", toX=" + toX +
			", fromY=" + fromY +
			", toY=" + toY +
			'}';
	}
}
