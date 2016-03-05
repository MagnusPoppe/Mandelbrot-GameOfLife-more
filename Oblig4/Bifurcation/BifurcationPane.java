package Oblig4.Bifurcation;

import Oblig4.Bifurcation.Logikk.Bifurcation;
import Oblig4.Bifurcation.Logikk.BifurcationFunction;
import Oblig4.Mandelbrot.FigureImage;
import Oblig4.Mandelbrot.Logikk.ClickCoords;
import Oblig4.Scale.ConvertCoordinates;
import Oblig4.Scale.Coords;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Jo Øivind Gjernes on 05.03.2016.
 *
 * TODO: Legge til funksjonsvalg
 */
public class BifurcationPane extends Pane
{
	private FigureImage bifurcationImage;
	private Bifurcation bifurcation;
	private ImageView imageView;
	private Stack<ClickCoords> mouseClicks;
	private BifurcationFunction func;

	public BifurcationPane(int width, int height)
	{
		super();
		func = BifurcationFunction.FUNC1;
		mouseClicks = new Stack<>();
		bifurcationImage = new FigureImage(width,height);
		bifurcation = new Bifurcation(bifurcationImage.getCoords()); // DefaultConstruct
		imageView = new ImageView();
		this.getChildren().add(imageView);
		setupEventHandlers();
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

	private void setupEventHandlers()
	{
		imageView.setOnMouseClicked(e->{
			if(mouseClicks.isEmpty()){
				mouseClicks.push(new ClickCoords((int)e.getSceneX(),(int)e.getSceneX()));
			} else {
				ClickCoords coords1 = mouseClicks.pop();
				ClickCoords coords2 = new ClickCoords((int)e.getSceneX(), (int)e.getSceneY());
				Coords coords = new Coords(Math.min(coords1.x,coords2.x),Math.max(coords1.x,coords2.x),Math.min(coords1.y,coords2.y),Math.max(coords1.y,coords2.y));
				Coords converted = ConvertCoordinates.convert(coords, bifurcationImage.getCoords(), bifurcation.getCoords());
				converted.enforceBifurcProportions();
				try {
					bifurcation = new Bifurcation(converted, bifurcationImage.getCoords(),func);
				} catch (Exception exep){
					bifurcation = new Bifurcation(bifurcationImage.getCoords());
					System.err.println(exep.getMessage());
				}
				draw();
			}
		});
	}
}
