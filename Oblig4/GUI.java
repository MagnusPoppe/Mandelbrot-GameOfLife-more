package Oblig4;

/**
 * Created by Magnus on 25.02.2016.
 */

import Oblig4.Conway.Conway;
import Oblig4.Eksempel.CustomWritableImage;
import Oblig4.Mandelbrot.Mandelbrot;
import Oblig4.Mandelbrot.Point;
import Oblig4.Mandelbrot.PointLine;
import Oblig4.Scale.ConvertCoordinates;
import Oblig4.Scale.Coords;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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

public class GUI extends Application
{
    //Globale elementer:
        static BorderPane root;
        final static double STAGEX = 600;
        final static double STAGEY = 680;
        Ctrl ctrl;
        private static Mandelbrot mandel;
        private static Coords coords;

    //Grafiske elementer til top-menyen:
        static GridPane menu, mandelMenu;
        static Label mandelbrot, bifurcation, cellulærAutomat, conway,
                     blueScale, redScale, greenScale, skyblueScale, greyScale;
        final static Color NOTSELECTED = Color.DARKGRAY;
        final static Color SELECTED = Color.SKYBLUE;
        final static Font menufont = new Font("Roboto, Helvetica, Arial", 18);

    //Grafiske elementer til tegneområdet:
        static StackPane stack;
        static Group zoomMarkings;
        static Pane zoomMarkLayer;
        static GridPane grid;
        static ImageView presenter;
        static WritableImage graph;
        static boolean started;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        // Starter kontrolleren:
            ctrl = new Ctrl();

        //build();
        // Bestemmer stage/scene innstillinger:
            Scene scene = new Scene(root, STAGEX, STAGEY);
            stage.setScene(scene);
            stage.setResizable(true);
            stage.setTitle("Obligatorisk øving #4");
            stage.show();


        // Lyttefunksjoner:

            coords = new Coords(-2.0, 2.0, -2.0, 2.0);

            mandelbrot.setOnMouseClicked(e-> {
                // Klargjør for ny vising:
                //presenter.setImage(null);
                //zoom(new Coords(0, 800, 0, 800));
                select(mandelbrot);
                display(createMandel());
            });
            bifurcation.setOnMouseClicked(e-> {
                // Klargjør for ny vising:
                    select(bifurcation);
            });
            cellulærAutomat.setOnMouseClicked(e-> {
                // Klargjør for ny vising:
                    select(cellulærAutomat);
            });
            conway.setOnMouseClicked(e-> {
                // Klargjør for ny vising:
                    select(GUI.conway);
                    Conway conway = new Conway();
                    root.setBottom(conway.getMenu());
                    display(conway.getGUI());

            });


    }

    /**
     * Genererer nytt bilde basert på en "coords" ramme.
     *
     * @param frame
     */
    public void zoom(Coords frame) {
        //Fikser på koordinatene mottat fra brukerinndata:
        frame = correctUserInput(frame);
        frame = correctAspectRatio(frame);

        CustomWritableImage img = new CustomWritableImage(800, 800);

        // Koordinatsystem for mandelbroten
        coords = ConvertCoordinates.computeNewMandelbrot(coords, frame, img.getCoords());

        // For å regne ut inkrement
        System.out.println("Nåværende rammestørrelse: "+(coords.getToX() - coords.getFromX()));

        ConvertCoordinates convert = new ConvertCoordinates(
                coords, img.getCoords()
        );

        // Opprette mandelbrot objekt
        mandel = new Mandelbrot(
                coords,
                convert.computeXIncrement(),
                convert.computeYIncrement()
        );

        // For å skrive til bilde
        ArrayList<PointLine> line = mandel.getPoints();
        PixelWriter pixelWriter = img.getPixelWriter();
        for(int y = 0;y<800;++y){
            // En linje for hver y koordinat (langsgående.
            PointLine current = line.get(y);
            for(int x=0;x<800;++x){
                pixelWriter.setColor(x,y,current.getPoint(x).getColor());
            }
        }
        presenter.setImage(img);
    }

    /**
     * Tillater brukeren å dra fra og til alle mulige retninger.
     * litt lang kode p.g.a. manglende setmetoder (gadd ikke å generere...) :D
     *
     * @param c Ting
     * @return
     */
    public Coords correctUserInput(Coords c)
    {
        double fromX, toX, fromY, toY;
        if (c.getToX() < c.getFromX()) {
            fromX = c.getToX();
            toX = c.getFromX();
        }
        else {
            fromX = c.getFromX();
            toX = c.getToX();
        }
        if (c.getToY() < c.getFromY()) {
            fromY = c.getToY();
            toY = c.getFromY();
        }
        else {
            fromY = c.getFromY();
            toY = c.getToY();
        }
        return new Coords(fromX, toX, fromY, toY);
    }

    /**
     * Fikser på skjermformatet når bruker zoomer så
     * det ikke blir strukket bilde. JEG SKAL IKKE HA NOE
     * STRUKKET BILDE !!!
     *
     * NOTAT: Det virker som første zoom alltid er buggy, mens
     *        resten er fine. Skjønner ikke helt hvorfor.
     *                                              -Magnus
     *
     * @param c
     * @return Perfect coordinates for zooming.
     */
    private Coords correctAspectRatio(Coords c)
    {
        //Hvis Y er størst:
        if ((c.getToX() - c.getFromX()) < (c.getToY() - c.getFromY())) {
            double centerX = ((c.getToX() - c.getFromX()) / 2);
            double centerY = ((c.getToY() - c.getFromY()) / 2);
            return new Coords(
                (c.getFromX() + centerX) - centerY,
                (c.getFromX() + centerX) + centerY,
                c.getFromY(),
                c.getToY()
            );
        }
        //MOTSATT:
        else {
            double centerX = ((c.getToX() - c.getFromX()) / 2);
            double centerY = ((c.getToY() - c.getFromY()) / 2);
            return new Coords(
                c.getFromX(),
                c.getToX(),
                (c.getFromY() + centerY) - centerX,
                (c.getFromY() + centerY) + centerX
            );
        }
    }

    /**
     * Setter valgt fargen på en gitt label.
     * @param lbl
     */
    public static void select(Label lbl)
    {
        deSelect();
        lbl.setTextFill(SELECTED);
        started = true;
    }

    /**
     * Fjerner "valgt" fargen for alle labels.
     */
    public static void deSelect() {
        // Fargemarkering meny
        mandelbrot.setTextFill(NOTSELECTED);
        bifurcation.setTextFill(NOTSELECTED);
        cellulærAutomat.setTextFill(NOTSELECTED);
        conway.setTextFill(NOTSELECTED);

        // Fjerner overlayen
        presenter.setImage(null);
        root.setBottom(null);
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
        //zoomMarkings = new Group();
        //zoomMarkLayer = new Pane();
        presenter = new ImageView();
        grid = null;
        //stack.getChildren().addAll(presenter, grid, zoomMarkLayer);

        //Setter opp vinduet:
        root = new BorderPane();
        root.setTop(menu);
        root.setCenter(stack);
        started = false;
    }
    public static void display(Node n) {
        //root.setBottom(null);
        stack.getChildren().clear();
        stack.getChildren().add(n);
    }
    public Node createMandel() {
        zoomMarkings = new Group();
        zoomMarkLayer = new Pane();
        zoomMarkLayer.getChildren().add(zoomMarkings);
        zoom(new Coords(0, 800, 0, 800));
        createMandelbrotMenu();
        zoomMarkings.setOnMousePressed( e1 -> {

            // Den krasjer om dette ikke gjøres.
            if ( !started ) return;

            double fromX = e1.getX();
            double fromY = e1.getY();
            Line x1 = new Line(fromX, fromY, fromX, fromY);
            Line x2 = new Line(fromX, fromY, fromX, fromY);
            Line y1 = new Line(fromX, fromY, fromX, fromY);
            Line y2 = new Line(fromX, fromY, fromX, fromY);
            zoomMarkings.getChildren().addAll(x1, x2, y1, y2);

            // Oppdatering per linje under dragning.
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

            // Når musen slippes:
            stack.setOnMouseReleased( e3 -> {
                double toX = e3.getX();
                double toY = e3.getY();
                zoomMarkings.getChildren().clear();

                Coords newFrame = new Coords(fromX, toX, fromY, toY);
                zoom(newFrame);
            });
        });
        return new StackPane(presenter, zoomMarkLayer);
    }

    /**
     * Lager elementer til menyen og setter stil på dem.
     */
    public static void createMenu()
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

    public static void createMandelbrotMenu()
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

        redScale.setOnMouseClicked(e-> Point.setRedScale());
        greenScale.setOnMouseClicked(e -> Point.setGreenScale());
        blueScale.setOnMouseClicked(e-> Point.setBlueScale());
        skyblueScale.setOnMouseClicked(e-> Point.setSkyblueScale());
        greyScale.setOnMouseClicked(e-> Point.reset());
    }
}
