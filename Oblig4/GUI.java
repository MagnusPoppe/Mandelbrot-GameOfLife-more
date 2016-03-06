package Oblig4;/**
 * Created by Magnu on 25.02.2016.
 */

import Oblig4.Bifurcation.BifurcationPane;
import Oblig4.Mandelbrot.MandelPane;
import Oblig4.cellulærAutomat.AutomatPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application
{
    //Globale elementer:
        BorderPane root;
        final double STAGEX = 600;
        final double STAGEY = 680;

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
        Pane presenter;

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

        // Lyttefunksjoner:
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
        mandelbrot = new Label("Mandelbrot");
        bifurcation = new Label("Bifurcation");
        cellulærAutomat = new Label("Cellulær automat");
        conway = new Label("Conway's \"game of life\"");

        //Styling:
        mandelbrot.setTextFill(NOTSELECTED);
        mandelbrot.setFont(menufont);
        mandelbrot.setAlignment(Pos.CENTER);
        mandelbrot.setOnMouseClicked(e->{
            deselect();
            stack.getChildren().remove(presenter);
            presenter = null;
            presenter = new MandelPane(600,600); // TODO: Fjerne magiske konstanter
            stack.getChildren().add(presenter);
            mandelbrot.setTextFill(SELECTED);
        });
        bifurcation.setTextFill(NOTSELECTED);
        bifurcation.setFont(menufont);
        bifurcation.setAlignment(Pos.CENTER);
        bifurcation.setOnMouseClicked(e->{
            deselect();
            stack.getChildren().remove(presenter);
            presenter = null;
            presenter = new BifurcationPane(600,600); // TOOD: Fjerne magiske konstanter
            stack.getChildren().add(presenter);
            bifurcation.setTextFill(SELECTED);
        });
        cellulærAutomat.setTextFill(NOTSELECTED);
        cellulærAutomat.setFont(menufont);
        cellulærAutomat.setAlignment(Pos.CENTER);
        cellulærAutomat.setOnMouseClicked(e->{
            deselect();
            stack.getChildren().remove(presenter);
            presenter = new AutomatPane(600,599);
            stack.getChildren().add(presenter);
            cellulærAutomat.setTextFill(SELECTED);
        });
        conway.setTextFill(NOTSELECTED);
        conway.setFont(menufont);
        conway.setAlignment(Pos.CENTER);

        //Adding to grid:
        menu = new GridPane();
        menu.addRow(0, mandelbrot, bifurcation, cellulærAutomat, conway, new Button("LØKSUPPE"));
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(25);
        menu.getColumnConstraints().addAll(cc, cc, cc, cc);

    }

    /**
     * TODO: JAVADOC
     */
    private void deselect()
    {
        // Skrøpelig metode! Krasjer hvis man legger til noe annet en labels
        menu.getChildren().forEach(e->{
            if(e instanceof Label) {
                Label lbl = (Label) e;
                lbl.setTextFill(NOTSELECTED);
                e = lbl;
            }
        });
    }

}
