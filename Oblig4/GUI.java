package Oblig4;/**
 * Created by Magnu on 25.02.2016.
 */

import Oblig4.Mandelbrot.MandelPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application
{
    //Globale elementer:
        BorderPane root;
        final double STAGEX = 800;
        final double STAGEY = 880;

    //Grafiske elementer til top-menyen:
        GridPane menu, mandelMenu;
        Label mandelbrot, bifurcation, cellulærAutomat, conway,
                     blueScale, redScale, greenScale, skyblueScale, greyScale;
        public static final Color NOTSELECTED = Color.DARKGRAY;
        public static final Color SELECTED = Color.SKYBLUE;
        public static final Font menufont = new Font("Roboto, Helvetica, Arial", 18);

    //Grafiske elementer til tegneområdet:
        StackPane stack;
        Group markings;
        Pane pane;
        ImageView presenter;
        private static MandelPane mandelPane;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        // Starter kontrolleren:
            root = new BorderPane();
            mandelPane = new MandelPane(800,800);
            build();
        // Bestemmer stage/scene innstillinger:
            Scene scene = new Scene(root, STAGEX, STAGEY);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Obligatorisk øving #4");
            stage.show();

        // Lyttefunksjoner:


    }

    /**
     * Setter valgt fargen på en gitt label.
     * @param lbl
     */
    public void select(Label lbl)
    {
        lbl.setTextFill(SELECTED);
    }

    /**
     * Fjerner "valgt" fargen for alle labels.
     */
    public void deSelect()
    {
        mandelbrot.setTextFill(NOTSELECTED);
        bifurcation.setTextFill(NOTSELECTED);
        cellulærAutomat.setTextFill(NOTSELECTED);
        conway.setTextFill(NOTSELECTED);
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
        pane = mandelPane;
        presenter = new ImageView();
        stack.getChildren().addAll(presenter, pane);
        pane.getChildren().add(markings);

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
        mandelbrot = new Label("Mandelbrot");
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

        //Adding to grid:
        menu = new GridPane();
        menu.addRow(0, mandelbrot, bifurcation, cellulærAutomat, conway);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(25);
        menu.getColumnConstraints().addAll(cc, cc, cc, cc);

    }

    public void createMandelbrotMenu()
    {
        redScale     = new Label("Rød");
        greenScale   = new Label("Grønn");
        blueScale    = new Label("Blå");
        greyScale    = new Label("Gråtoner");
        skyblueScale = new Label("skyblå<3");

        mandelMenu = new GridPane();
        mandelMenu.addRow(0, redScale, greenScale, blueScale, greyScale, skyblueScale);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(20);
        mandelMenu.getColumnConstraints().addAll(cc, cc, cc, cc, cc);
        root.setBottom(mandelMenu);

    }
}
