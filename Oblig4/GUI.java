package Oblig4;/**
 * Created by Magnu on 25.02.2016.
 */

import Oblig4.Eksempel.CustomWritableImage;
import Oblig4.Mandelbrot.ColoredPoint;
import Oblig4.Mandelbrot.Mandelbrot;
import Oblig4.Mandelbrot.PointLine;
import Oblig4.Scale.ConvertCoordinates;
import Oblig4.Scale.Coords;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application
{
    //Globale elementer:
        static BorderPane root;
        final static double STAGEX = 800;
        final static double STAGEY = 880;
        Ctrl ctrl;

    //Grafiske elementer til top-menyen:
        static GridPane menu;
        static Label mandelbrot, bifurcation, cellulærAutomat, conway;
        final static Color NOTSELECTED = Color.DARKGRAY;
        final static Color SELECTED = Color.SKYBLUE;
        final static Font menufont = new Font("Roboto, Helvetica, Arial", 18);

    //Grafiske elementer til tegneområdet:
        static ImageView presenter;
        static WritableImage graph;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        //Starter kontrolleren:
            ctrl = new Ctrl();

        //Bestemmer stage/scene innstillinger:
            Scene scene = new Scene(root, STAGEX, STAGEY);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Obligatorisk øving #4");
            stage.show();

        //Lyttefunksjoner:

            mandelbrot.setOnMouseClicked(e-> {
                testDraw();
                select(mandelbrot);
            });
    }

    /**
     * Brukes til å bygge alle elementene som går inn i GUI.
     * Metoden er kun til for å slippe rot i startmetoden.
     */
    public static void build()
    {
        //Definerer menyen:
            createMenu();

        //Definerer visningsområdet
            presenter = new ImageView();

        //Setter opp vinduet:
            root = new BorderPane();
            root.setTop(menu);
            root.setCenter(presenter);
    }

    /**
     * Lager elementer til menyen og setter stil på dem.
     */
    public static void createMenu() {
        //Creating menu elements:
            mandelbrot = new Label("Mandelbrot");
            bifurcation = new Label("Bifurcation");
            cellulærAutomat = new Label("Cellulær automat");
            conway = new Label("Conway's \"game of life\"");

        //Styling:
            mandelbrot.setTextFill(NOTSELECTED);
            mandelbrot.setFont(menufont);
            bifurcation.setTextFill(NOTSELECTED);
            bifurcation.setFont(menufont);
            cellulærAutomat.setTextFill(NOTSELECTED);
            cellulærAutomat.setFont(menufont);
            conway.setTextFill(NOTSELECTED);
            conway.setFont(menufont);

        //Adding to grid:
            menu = new GridPane();
            menu.addRow(0, mandelbrot, bifurcation, cellulærAutomat, conway);
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(25);
            menu.getColumnConstraints().addAll(cc, cc, cc, cc);

    }

    /**
     * Setter valgt fargen på en gitt label.
     * @param lbl
     */
    public static void select(Label lbl) {
        lbl.setTextFill(SELECTED);
    }

    /**
     * Fjerner "valgt" fargen for alle labels.
     */
    public static void deSelect() {
        mandelbrot.setTextFill(NOTSELECTED);
        bifurcation.setTextFill(NOTSELECTED);
        cellulærAutomat.setTextFill(NOTSELECTED);
        conway.setTextFill(NOTSELECTED);
    }

    public static void drawColoredPoints(ArrayList<ColoredPoint> points)
    {

    }

    public static void testDraw()
    {
        CustomWritableImage fraktal = new CustomWritableImage(800,800);

        // Koordinatsystem for mandelbroten
        Coords coords = new Coords(-2.0,0.5,-1.25,1.25); //Hvor kommer disse tallene fra? - Magnus

        // For å regne ut inkrement
        ConvertCoordinates convert = new ConvertCoordinates(coords, fraktal.getCoords());

        // Opprette mandelbrot objekt
        Mandelbrot mandel = new Mandelbrot(coords, convert.computeXIncrement(),convert.computeYIncrement());

        ArrayList<PointLine> line = mandel.getPoints();

        // For å skrive til bilde
        PixelWriter pixelWriter = fraktal.getPixelWriter();
            for(int y = 0;y<800;++y){
                    // En linje for hver y koordinat (langsgående.
                    PointLine current = line.get(y);
                    for(int x=0;x<800;++x){
                            pixelWriter.setColor(x,y,current.getPoint(x).getColor());
                    }
            }
        presenter.setImage(fraktal);
    }
}
