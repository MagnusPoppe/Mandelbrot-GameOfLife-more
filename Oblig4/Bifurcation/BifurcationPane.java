package Oblig4.Bifurcation;

import Oblig4.Bifurcation.Logikk.Bifurcation;
import Oblig4.Bifurcation.Logikk.BifurcationFunction;
import Oblig4.Mandelbrot.FigureImage;
import Oblig4.Scale.Coords;
import Oblig4.Scale.Zoom;
import Oblig4.Scale.ZoomInterface;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by Jo Øivind Gjernes on 05.03.2016.
 *
 * TODO: Legge til funksjonsvalg
 */
public class BifurcationPane extends Pane implements ZoomInterface
{
	private FigureImage bifurcationImage;
	private Bifurcation bifurcation;
	private ImageView imageView;
	private BifurcationFunction func;
	private Zoom zoom;

	public BifurcationPane(int width, int height)
	{
		super();
		func = BifurcationFunction.FUNC1;
		bifurcationImage = new FigureImage(width,height);
		bifurcation = new Bifurcation(bifurcationImage.getCoords()); // DefaultConstruct
		imageView = new ImageView();
		zoom = new Zoom( bifurcationImage.getCoords(),Bifurcation.defaultBifurcationCoords,this, true);
		this.getChildren().add(imageView);
		draw();
	}

	/**
	 * Siden bildekoordinatene starter med y=0 i toppen, tegnes bildet "opp-ned"
	 */
	private void draw()
	{
		int x,y;
		y=(int)bifurcationImage.getHeight()-1;
		PixelWriter pxl = bifurcationImage.getPixelWriter();

		for(ArrayList<Boolean> line : bifurcation.getPoints()){
			x=0;
			for(Boolean valuePresent : line){
				pxl.setColor(x,y,(valuePresent) ? Color.BLACK : Color.WHITE);
				++x;
			}
			--y;
			if(y<0) break;
		}
		imageView.setImage(bifurcationImage);
	}

	@Override
	public void zoom(Coords zoomTo)
	{
		bifurcation = null;
		bifurcation = new Bifurcation(zoomTo,bifurcationImage.getCoords());
		draw();
	}

	@Override
	public ImageView getZoomObject()
	{
		return imageView;
	}
}
