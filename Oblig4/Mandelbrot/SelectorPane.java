package Oblig4.Mandelbrot;

import Oblig4.GUI;
import Oblig4.Mandelbrot.Logikk.ColorChoice;
import Oblig4.Mandelbrot.Logikk.ColorSelector;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

/**
 * Created by Jo Øivind Gjernes on 04.03.2016.
 *
 * GUI-element for å velge farger til mandelbrot.
 * Opprettes som en ferdig "HBox"
 */
public class SelectorPane extends HBox
{

	private Label grayWhite;
	private Label red;
	private Label blue;
	private Label green;
	private ColorSelector cs;

	public SelectorPane(ColorSelector cs)
	{
		super();
		grayWhite = new Label("Gråtoner");
		red = new Label("Rød");
		blue = new Label("Blå");
		green = new Label("Grønn");
		this.cs = cs;
		init();
		initHandlers();
	}

	private void init()
	{
		this.getChildren().addAll(grayWhite, red, blue, green);
		this.getChildren().forEach(e->{
			Label mod = (Label)(e);
			mod.setFont(GUI.menufont);
			mod.setTextFill(GUI.NOTSELECTED);
			mod.setTextAlignment(TextAlignment.CENTER);
			e = mod;
		});
		grayWhite.setTextFill(GUI.SELECTED);
	}

	private void deselect()
	{
		this.getChildren().forEach(e->{
			Label mod = (Label)(e);
			mod.setTextFill(GUI.NOTSELECTED);
			e = mod;
		});
	}

	private void select(ColorChoice choice)
	{
		deselect();
		switch(choice){

			case BLACKWHITE:
				cs.setColor(ColorChoice.BLACKWHITE);
				grayWhite.setTextFill(GUI.SELECTED);
				break;
			case RED:
				cs.setColor(ColorChoice.RED);
				red.setTextFill(GUI.SELECTED);
				break;
			case GREEN:
				cs.setColor(ColorChoice.GREEN);
				green.setTextFill(GUI.SELECTED);
				break;
			case BLUE:
				cs.setColor(ColorChoice.BLUE);
				blue.setTextFill(GUI.SELECTED);
				break;
		}
	}

	private void initHandlers()
	{
		grayWhite.setOnMouseClicked(e -> select(ColorChoice.BLACKWHITE));
		red.setOnMouseClicked(e -> select(ColorChoice.RED));
		blue.setOnMouseClicked(e -> select(ColorChoice.BLUE));
		green.setOnMouseClicked(e -> select(ColorChoice.GREEN));
	}
}

