package Oblig4.Conway;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * Created by Morten on 03/03/2016.
 *
 * @author Morten Ibsgaard - 141606
 */
public class Conway implements EventHandler<MouseEvent> {
    private ConwayGUI gui;
    private ConwayModel model;
    private ConwayMenu menu;
    private GridPane[][] pArr;

    public int size = 40;

    // Timer
    private int interval = 500;
    private boolean autoplay = false;
    Timeline tl;

    public Conway() {
        gui = new ConwayGUI();
        gui.buildGrid(size);
        gui.setListener(this);

        model = new ConwayModel(size);
        updateGrid();

        menu = new ConwayMenu();
        menu.setListener(this);
    }

    public Node getGUI() {
        return gui;
    }
    public Node getMenu() {
        return menu;
    }
    public void updateGrid() {
        boolean[][] lifeArr = model.getLifeArr();
        gui.updateGrid(lifeArr);
    }

    public void stepGeneration() {
        model.stepGeneration();
        updateGrid();
    }

    public void mark(int x, int y) {
        model.mark(x, y);
        updateGrid();
    }

    public void timer() {
        autoplay = !autoplay;
        if (autoplay) autoStep();
    }
    public void autoStep() {
        stepGeneration();
        tl = new Timeline(new KeyFrame(Duration.millis(interval), e -> {
            if (autoplay) autoStep();
        }));
        tl.play();
    }

    @Override
    public void handle(MouseEvent event) {
        Object obj = event.getSource();
        if (obj instanceof Button) {
            String id = ((Button) obj).getId().toLowerCase();
            if (id.equals("neste")) {
                stepGeneration();
            } else if (id.equals("timer")) {
                timer();
            }
        } else if (obj instanceof StackPane) {
            String[] id = ((StackPane) obj).getId().split(";");
            mark(Integer.parseInt(id[0]), Integer.parseInt(id[1]));

        }
    }
}
