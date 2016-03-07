package Oblig4.Mandelbrot;

import Oblig4.GUI;
import Oblig4.Mandelbrot.Logikk.ColorChoice;
import Oblig4.Mandelbrot.Logikk.ColorSelector;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;

/**
 * Created by Jo Øivind Gjernes on 04.03.2016.
 *
 * GUI-element for å velge farger til mandelbrot.
 * Opprettes som en ferdig GridPane
 */
public class SelectorPane extends GridPane
{

	private Label grayWhite;
	private Label red;
	private Label blue;
	private Label green;
	private ColorSelector cs;

	/**
	 * Opprette en "farge-valg" pane.
	 * @param cs Farge-velger fra klassen som implementerer draw.
	 */
	public SelectorPane(ColorSelector cs)
	{
		super();


		this.cs = cs;

		init();
		initHandlers();
	}

	/**
	 * Sette opp layout
	 */
	private void init()
	{
		grayWhite = new Label("Gråtoner");
		red = new Label("Rød");
		blue = new Label("Blå");
		green = new Label("Grønn");


		this.addRow(0, grayWhite,red,blue,green);
		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(25);
		this.setPadding(new Insets(20,20,20,20));

		this.getColumnConstraints().addAll(cc,cc,cc,cc);

		// Sett font, o.l. for alle elementer som er lagt til;
		this.getChildren().forEach(e->{
			if(e instanceof Label) {
				Label mod = (Label) (e);
				mod.setFont(GUI.menufont);
				mod.setTextFill(GUI.NOTSELECTED);
				mod.setTextAlignment(TextAlignment.CENTER);
				e = mod;
			}
		});

		grayWhite.setTextFill(GUI.SELECTED);
	}

	/**
	 * Fjern markeringen på alle labels.
	 */
	private void deselect()
	{
		this.getChildren().forEach(e->{
			if(e instanceof Label) {
				Label mod = (Label) (e);
				mod.setTextFill(GUI.NOTSELECTED);
				e = mod;
			}
		});
	}

	/**
	 * Velg farge - gyldige valg definert i enum colorchoice
	 * @param choice enum- fargevalg
	 */
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

