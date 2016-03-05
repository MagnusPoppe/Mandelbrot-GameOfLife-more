package Oblig4.Mandelbrot;

import Oblig4.Mandelbrot.Logikk.*;
import Oblig4.Scale.ConvertCoordinates;
import Oblig4.Scale.Coords;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Jo Øivind Gjernes on 04.03.2016.
 *
 *
 */
public class MandelPane extends Pane implements Draw
{

	private Mandelbrot mandelbrot;
	private FigureImage figureImage;
	private ColorSelector colorSelector;
	private Stack<ClickCoords> clickStack;
	private ImageView imageView;
	private SelectorPane selectorPane;
	private VBox personalLayout;


	/**
	 * Konstruktør for "MandelPane"
	 *
	 * @param width Ønsket bredde
	 * @param height Ønsket høyde
	 */
	public MandelPane(int width, int height)
	{
		super();
		clickStack = new Stack<>();
		imageView = new ImageView();
		figureImage = new FigureImage(width,height);
		colorSelector = new ColorSelector(ColorChoice.BLACKWHITE, this);
		ConvertCoordinates conv = new ConvertCoordinates(Mandelbrot.defaultMandelbrotCoords, figureImage.getCoords());
		mandelbrot = new Mandelbrot(figureImage.getCoords());
		selectorPane = new SelectorPane(colorSelector);
		addEventHandlers();
		draw();
		personalLayout = new VBox();
		this.getChildren().add(personalLayout);
		personalLayout.getChildren().addAll(imageView,selectorPane);
	}

	/**
	 * TODO: Fikse skikkelig zoom
	 */
	private void addEventHandlers()
	{
		imageView.setOnMouseClicked(e -> {
			if(clickStack.isEmpty()){
				clickStack.push(new ClickCoords((int)e.getSceneX(), (int)e.getSceneY()));
			} else {
				ClickCoords coords1 = clickStack.pop();
				ClickCoords coords2 = new ClickCoords((int)e.getSceneX(),(int)e.getSceneY());
				Coords coords = new Coords(Math.min(coords1.x,coords2.x),Math.max(coords1.x,coords2.x),Math.min(coords1.y,coords2.y),Math.max(coords1.y,coords2.y));
				Coords converted = ConvertCoordinates.convert(coords, figureImage.getCoords(), mandelbrot.getCoords());
				converted.enforceProportions();
				try {
					mandelbrot = new Mandelbrot(converted, figureImage.getCoords());
				} catch (Exception exep){
					mandelbrot = new Mandelbrot(Mandelbrot.defaultMandelbrotCoords, figureImage.getCoords());
					System.err.println(exep.getMessage());
				}
				draw();
			}
		});

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
	 * Zoom ???
	 * @param zoomTo
	 */
	public void zoom(Coords zoomTo)
	{
		ConvertCoordinates converter = new ConvertCoordinates(figureImage.getCoords(), mandelbrot.getCoords());
		double[] point1 = converter.convert(zoomTo.getFromX(),zoomTo.getFromY());
		double[] point2 = converter.convert(zoomTo.getToX(),zoomTo.getToY());
		Coords newCoords = new Coords(point1[0],point2[0],point1[1],point2[1]);
		mandelbrot = new Mandelbrot(newCoords, figureImage.getCoords());
	}

}
