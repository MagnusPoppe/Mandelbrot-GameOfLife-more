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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
        static HBox menu;
        static Label mandelbrot, serpinski, tree;
        final static Color NOTSELECTED = Color.BLACK;
        final static Color SELECTED = Color.SKYBLUE;

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

    }

    /**
     * Brukes til å bygge alle elementene som går inn i GUI.
     * Metoden er kun til for å slippe rot i startmetoden.
     */
    public static void build()
    {
        //Definerer menyen:
            menu = new HBox(15);
            mandelbrot = new Label("Mandelbrot");
            mandelbrot.setOnMouseClicked(e->testDraw());
            serpinski = new Label("Serpinski");
            tree = new Label("Tree");
            menu.getChildren().addAll(mandelbrot, serpinski, tree);

        //Definerer visningsområdet
            presenter = new ImageView();

        //Setter opp vinduet:
            root = new BorderPane();
            root.setTop(menu);
            root.setCenter(presenter);
    }

    public static void drawColoredPoints(ArrayList<ColoredPoint> points)
    {

    }

    public static void testDraw()
    {
        CustomWritableImage fraktal = new CustomWritableImage(800,800);

        // Koordinatsystem for mandelbroten
        Coords coords = new Coords(-2.0,2.0,-2,2);

        // For å regne ut inkrement
        ConvertCoordinates convert = new ConvertCoordinates(coords, fraktal.getCoords());

        // Opprette mandelbrot objekt
        Mandelbrot mandel = new Mandelbrot(coords, convert.computeIncrement());

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
