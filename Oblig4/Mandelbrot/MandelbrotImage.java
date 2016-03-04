package Oblig4.Mandelbrot;

import Oblig4.Scale.Coords;
import javafx.beans.NamedArg;
import javafx.scene.image.WritableImage;

/**
 * Created by Jo Øivind Gjernes on 04.03.2016.
 *
 * WritableImage - utvidet med funksjonalitet for å hente ut et koordinatsystem som beskriver bildet.
 */
public class MandelbrotImage extends WritableImage
{

	public MandelbrotImage(@NamedArg("width") int width, @NamedArg("height") int height)
	{
		super(width, height);
	}

	public Coords getCoords()
	{
		return new Coords(0,this.getWidth(), 0,this.getHeight());
	}
}
