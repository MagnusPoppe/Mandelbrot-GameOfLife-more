package Oblig4.Conway;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Created by Morten on 03/03/2016.
 * @author Morten Ibsgaard - 141606
 */
public class ConwayGUI extends GridPane {
    private StackPane[][] pArr;

    public ConwayGUI() {
        this.setMaxHeight(800);
        this.setMaxWidth(800);
    }

    public void buildGrid(int size) {
        /*ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(810.0/size);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(800.0/size);
        for (int i=0; i<size; i++) {
            this.getColumnConstraints().add(column);
            this.getRowConstraints().add(row);
        }*/
        this.getChildren().clear();
        pArr = new StackPane[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                StackPane pane = new StackPane();
                pane.setMaxWidth(800.0/size);
                pane.setMaxHeight(800.0/size);
                pane.setMinWidth(800.0/size);
                pane.setMinHeight(800.0/size);
                //pane.minWidthProperty().bind(this.widthProperty().divide(size+0.1*size));
                //pane.minHeightProperty().bind(this.heightProperty().divide(size+0.1*size));
                pane.setId(j + ";" + i);
                this.add(pane, i, j);
                pArr[i][j] = pane;
            }
        }
    }

    public void updateGrid(boolean[][] lifeArr) {
        for (int i=0; i<lifeArr.length; i++) {
            for (int j=0; j<lifeArr[i].length; j++) {
                mark(pArr[j][i], lifeArr[i][j]);

                if (lifeArr[i][j]) {
                }
            }
        }
    }
    private void mark(StackPane p, boolean b) {
        String standardCSS = "-fx-border-style:solid; -fx-border-width: 0.1;";
        if (b) p.setStyle("-fx-background-color: limegreen;" + standardCSS);
        else p.setStyle("-fx-background-color: grey;" + standardCSS);
    }
    public void setListener(EventHandler<Event> listener) {
        for (StackPane[] s : pArr) {
            for (StackPane p : s) {
                p.setOnMousePressed(listener);
            }
        }
    }
}
