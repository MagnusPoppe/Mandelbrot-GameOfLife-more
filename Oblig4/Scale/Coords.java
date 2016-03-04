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
	 * Konstruktør for et koordinatsystem med origo 0,0
	 *
	 * @param x bredde
	 * @param y høyde
	 */
	public Coords(double x, double y)
	{
		this(0, x, 0, y);
	}

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
