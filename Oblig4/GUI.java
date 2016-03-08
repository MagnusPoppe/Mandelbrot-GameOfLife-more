package Oblig4;


/**
 * @author Magnus, Jo og Morten
 */

import Oblig4.Bifurcation.BifurcationPane;
import Oblig4.Conway.Conway;
import Oblig4.Mandelbrot.MandelPane;
import Oblig4.cellulærAutomat.AutomatPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application
{
	//Globale elementer:
	private BorderPane root;
	private final static double STAGEX = 790;
	private final static double STAGEY = 880;
	private final static int WINDOWX = 800;
	private final static int WINDOWY = 800;

	//Grafiske elementer til top-menyen:
	private GridPane menu;
	private Label mandelbrot, bifurcation, cellulærAutomat, conway;
	public static final Color NOTSELECTED = Color.rgb(120,120,120);
	public static final Color SELECTED = Color.SKYBLUE;
	public static final Font menufont = new Font("Roboto, Helvetica, Arial", 18);

	//Grafiske elementer til tegneområdet:
	private StackPane stack;
	private Group markings;
	private Pane presenter;

	/**
	 * Entrypoint
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Starter kontrolleren:
		root = new BorderPane();
		build();
		// Bestemmer stage/scene innstillinger:
		Scene scene = new Scene(root, STAGEX, STAGEY);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Obligatorisk øving #4");
		stage.show();
	}

	/**
	 * Brukes til å bygge alle elementene som går inn i GUI.
	 * Metoden er kun til for å slippe rot i startmetoden.
	 */
	public void build()
	{
		//Definerer menyen:
		createMenu();

		//Definerer visningsområdet
		stack = new StackPane();
		markings = new Group();
		presenter = new Pane();
		stack.getChildren().addAll(presenter);
		presenter.getChildren().add(markings);

		//Setter opp vinduet:
		root = new BorderPane();
		root.setTop(menu);
		root.setCenter(stack);
	}

	/**
	 * Lager elementer til menyen og setter stil på dem.
	 */
	public void createMenu()
	{
		//Creating menu elements:
		mandelbrot = new Label("  Mandelbrot");
		bifurcation = new Label("Bifurcation");
		cellulærAutomat = new Label("Cellulær automat");
		conway = new Label("Conway's \"game of life\"");

		//Styling:
		mandelbrot.setTextFill(NOTSELECTED);
		mandelbrot.setFont(menufont);
		mandelbrot.setAlignment(Pos.CENTER);
		bifurcation.setTextFill(NOTSELECTED);
		bifurcation.setFont(menufont);
		bifurcation.setAlignment(Pos.CENTER);
		cellulærAutomat.setTextFill(NOTSELECTED);
		cellulærAutomat.setFont(menufont);
		cellulærAutomat.setAlignment(Pos.CENTER);
		conway.setTextFill(NOTSELECTED);
		conway.setFont(menufont);
		conway.setAlignment(Pos.CENTER);

		// Eventhandlere for labels
		addMenuEventHandlers();

		//Adding to grid:
		menu = new GridPane();
		menu.addRow(0, mandelbrot, bifurcation, cellulærAutomat, conway);
		ColumnConstraints cc = new ColumnConstraints();
		cc.setPercentWidth(25);
		menu.getColumnConstraints().addAll(cc, cc, cc, cc);

	}

	/**
	 * Her ligger logikken rundt utskifting av "visninger"
	 */
	private void addMenuEventHandlers()
	{
		mandelbrot.setOnMouseClicked(e -> {
			setGraphics(new MandelPane(WINDOWX,WINDOWY));
			selectLabel(mandelbrot);
		});

		bifurcation.setOnMouseClicked(e -> {
			setGraphics(new BifurcationPane(WINDOWX,WINDOWY));
			selectLabel(bifurcation);
		});

		cellulærAutomat.setOnMouseClicked(e -> {
			setGraphics(new AutomatPane(WINDOWX,WINDOWY));
			selectLabel(cellulærAutomat);
		});

		conway.setOnMouseClicked(e -> {
			selectLabel(conway);
			Conway con = new Conway();
			setGraphics(con.getGUI());
			root.setBottom(con.getMenu());
		});
	}

	/**
	 * Går gjennom alle "meny" labler og setter dem til fargen NOTSELECTED
	 */
	private void deselectMenuLabels()
	{
		menu.getChildren().forEach(e -> {
			if (e instanceof Label) {
				Label lbl = (Label) e;
				lbl.setTextFill(NOTSELECTED);
				e = lbl;
			}
		});
		root.setBottom(null);
	}

	/**
	 * Markerer en label som valgt
	 */
	private void selectLabel(Label label)
	{
		deselectMenuLabels();
		label.setTextFill(SELECTED);
	}

	/**
	 * Skifter "visnings" panen
	 * @param toPresent
	 */
	private void setGraphics(Pane toPresent)
	{
		stack.getChildren().remove(presenter);
		presenter = toPresent;
		stack.getChildren().add(presenter);
	}

}