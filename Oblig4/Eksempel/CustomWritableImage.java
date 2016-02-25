package Oblig4.Eksempel;

import Oblig4.Scale.Coords;
import javafx.beans.NamedArg;
import javafx.scene.image.WritableImage;

/**
 * Created by Jo Øivind Gjernes on 25.02.2016.
 *
 * Tanken er at man skal lettere skal kunne hente ut Koordinatsystemet fra "writableimage" slik at man kan skalere
 * figurer på bildet.
 */
public class CustomWritableImage extends WritableImage
{
	Coords coords;
	public CustomWritableImage(@NamedArg("width") int width, @NamedArg("height") int height)
	{
		super(width, height);
		coords = new Coords((double)width,(double)height);
	}

	public Coords getCoords()
	{
		return coords;
	}
}