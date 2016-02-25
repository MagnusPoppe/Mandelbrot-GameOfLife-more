package Oblig4.Scale;

import Oblig4.Mandelbrot.Point;

import java.util.ArrayList;

/**
 * Created by jooivind on 25.02.2016.
 * <p>
 * Inneholder metoder for å mappe et koordinatsystem til et annet
 */
public class Scaler
{
	/**
	 * Tar inn en liste med punkter og konverterer den fra ett koordinatsystem til et annet
	 * @param toScale liste med punkter å skalere til
	 * @param fromCoords beskrivelse av koordinatsystemet som skal konverteres fra
	 * @param toCoords beskrivelse av koordinatsystemet det skal konverteres til
	 * @return Oppdatert liste (det er den samme)
	 */
	public static ArrayList<Point> scalePoints(ArrayList<Point> toScale, Coords fromCoords, Coords toCoords)
	{
		ConvertCoordinates coord = new ConvertCoordinates(fromCoords, toCoords);
		toScale.forEach((Point p) -> {
			p = coord.convert(p);
		});
		return toScale;
	}
}
