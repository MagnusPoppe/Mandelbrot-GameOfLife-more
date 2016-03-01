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
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Stack;

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
        static StackPane stack;
        static Group markings;
        static Pane pane;
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

            stack.setOnMousePressed( e1 -> {
                double fromX = e1.getX();
                double fromY = e1.getY();
                Line x1 = new Line(fromX, fromY, fromX, fromY);
                Line x2 = new Line(fromX, fromY, fromX, fromY);
                Line y1 = new Line(fromX, fromY, fromX, fromY);
                Line y2 = new Line(fromX, fromY, fromX, fromY);
                markings.getChildren().addAll(x1, x2, y1, y2);
                stack.setOnMouseDragged( e2 -> {
                    x1.setEndX(e2.getX());

                    x2.setStartY(e2.getY());
                    x2.setEndX(e2.getX());
                    x2.setEndY(e2.getY());

                    y1.setEndY(e2.getY());

                    y2.setStartX(e2.getX());
                    y2.setEndX(e2.getX());
                    y2.setEndY(e2.getY());
                });
                stack.setOnMouseReleased( e3 -> {
                    double toX = e3.getX();
                    double toY = e3.getY();
                    pane.getChildren().add(markings);
                });
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
            stack = new StackPane();
            markings = new Group();
            pane = new Pane();
            presenter = new ImageView();
            stack.getChildren().addAll(presenter, pane);
        //Setter opp vinduet:
            root = new BorderPane();
            root.setTop(menu);
            root.setCenter(stack);
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

    public static void testDraw()
    {
        CustomWritableImage fraktal = new CustomWritableImage(800,800);

        // Koordinatsystem for mandelbroten
        //Coords coords = new Coords(-2.0,0.5,-1.25,1.25); //Hvor kommer disse tallene fra? - Magnus
        Coords coords = new Coords(-2.0,2,-2,2);
        // For å regne ut inkrement
        ConvertCoordinates convert = new ConvertCoordinates(
                coords,
                fraktal.getCoords()
        );

        // Opprette mandelbrot objekt
        Mandelbrot mandel = new Mandelbrot(
                coords,
                convert.computeXIncrement(),
                convert.computeYIncrement()
        );

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
