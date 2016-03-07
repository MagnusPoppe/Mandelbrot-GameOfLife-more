package Oblig4.Mandelbrot;

import Oblig4.Mandelbrot.Logikk.ColorChoice;
import Oblig4.Mandelbrot.Logikk.ColorSelector;
import Oblig4.Mandelbrot.Logikk.Draw;
import Oblig4.Mandelbrot.Logikk.Mandelbrot;
import Oblig4.Scale.Coords;
import Oblig4.Scale.Zoom;
import Oblig4.Scale.ZoomInterface;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by Jo Øivind Gjernes on 04.03.2016.
 *
 *
 */
public class MandelPane extends Pane implements Draw,ZoomInterface
{

	private Mandelbrot mandelbrot;
	private FigureImage figureImage;
	private ColorSelector colorSelector;
	private ImageView imageView;
	private SelectorPane selectorPane;
	private VBox personalLayout;
	private Zoom zoom;


	/**
	 * Konstruktør for "MandelPane"
	 *
	 * @param width Ønsket bredde
	 * @param height Ønsket høyde
	 */
	public MandelPane(int width, int height)
	{
		super();

		imageView = new ImageView();
		figureImage = new FigureImage(width,height);
		colorSelector = new ColorSelector(ColorChoice.BLACKWHITE, this);
		mandelbrot = new Mandelbrot(figureImage.getCoords());
		selectorPane = new SelectorPane(colorSelector);
		zoom = new Zoom(figureImage.getCoords(),Mandelbrot.defaultMandelbrotCoords,this);
		draw();
		personalLayout = new VBox();
		this.getChildren().add(personalLayout);
		personalLayout.getChildren().addAll(imageView,selectorPane);
	}

	/**
	 * Tegn mandelbrot data inn på et bildeobjekt. (generer ikke underliggende mandelbrot data først)
	 */
	public void draw()
	{

		PixelWriter pxl = figureImage.getPixelWriter();

		for(int y = 0; y< figureImage.getHeight()&&y<mandelbrot.getPointLines().size(); ++y)
		{
			ArrayList<Integer> pointLine = mandelbrot.getPointLines().get(y);

			for(int x = 0; x< figureImage.getWidth()&&x<pointLine.size(); ++x)
			{
				pxl.setColor(x,y, colorSelector.getColor(pointLine.get(x)));
			}
		}
		imageView.setImage(figureImage);
	}

	/**
	 * Zooming av mandelbrot objektet
	 * @param zoomTo
	 */
	public void zoom(Coords zoomTo)
	{
		try {
			// Opprett nye punkter for mandelbrot funksjonen
			mandelbrot = new Mandelbrot(zoomTo, figureImage.getCoords());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			mandelbrot = new Mandelbrot(Mandelbrot.defaultMandelbrotCoords, figureImage.getCoords());
			zoom = new Zoom(figureImage.getCoords(), Mandelbrot.defaultMandelbrotCoords, this);
		}
		draw(); // oppdater grafikk
	}

	@Override
	public ImageView getZoomObject()
	{
		return imageView;
	}

}
