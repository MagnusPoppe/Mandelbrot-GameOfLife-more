package Oblig4.Mandelbrot;

import Oblig4.Scale.ConvertCoordinates;
import Oblig4.Scale.Coords;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Created by Jo Øivind Gjernes on 04.03.2016.
 */
public class MandelPane extends Pane
{

	private Mandelbrot mandelbrot;
	private MandelbrotImage mandelbrotImage;
	private ColorSelector colorSelector;

	public MandelPane(int width, int height)
	{
		mandelbrotImage = new MandelbrotImage(width,height);
		colorSelector = new ColorSelector(ColorChoice.BLACKWHITE);
		ConvertCoordinates conv = new ConvertCoordinates(Mandelbrot.defaultMandelbrotCoords, mandelbrotImage.getCoords());
		mandelbrot = new Mandelbrot(conv.computeXIncrement(), conv.computeYIncrement());
		draw();
	}

	private void draw()
	{
		this.getChildren().removeAll();

		PixelWriter pxl = mandelbrotImage.getPixelWriter();

		for(int y = 0; y<mandelbrotImage.getHeight();++y)
		{
			ArrayList<Integer> pointLine = mandelbrot.getPointLines().get(y);

			for(int x = 0;x<mandelbrotImage.getWidth();++x)
			{
				pxl.setColor(x,y, colorSelector.getColor(pointLine.get(x)));
			}
		}
		this.getChildren().add(new ImageView(mandelbrotImage));
	}

	public void zoom(Coords zoomTo)
	{
		ConvertCoordinates converter = new ConvertCoordinates(mandelbrotImage.getCoords(), mandelbrot.getCoords());
		double[] point1 = converter.convert(zoomTo.getFromX(),zoomTo.getFromY());
		double[] point2 = converter.convert(zoomTo.getToX(),zoomTo.getToY());
		Coords newCoords = new Coords(point1[0],point2[0],point1[1],point2[1]);
		mandelbrot = new Mandelbrot(newCoords, ConvertCoordinates.computeXIncrement(newCoords, mandelbrotImage.getCoords()),ConvertCoordinates.computeYIncrement(newCoords,mandelbrotImage.getCoords()));
	}

}
