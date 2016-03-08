package Oblig4.cellulærAutomat;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by Jo Øivind Gjernes on 05.03.2016.
 *
 * Laget en Pane for å samle konstrueringen av Magnus sin cellulære automat.
 */
public class AutomatPane extends Pane
{
	private static final boolean[] defaultRule = {true,true,true,true,true,true,true,true};
	AutomatCTRL automat;
	ImageView imgView;
	VBox localLayout;

	public AutomatPane(int width, int height)
	{
		localLayout = new VBox();
		automat = new AutomatCTRL(width,height);
		automat.createNewRules(defaultRule);
		imgView = new ImageView(automat.getImg());
		this.getChildren().add(localLayout);
		localLayout.getChildren().add(imgView);
		localLayout.getChildren().add(automat.getAutomatMenu());
	}
}
