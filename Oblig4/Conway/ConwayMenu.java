package Oblig4.Conway;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by Morten on 03/03/2016.
 * @author Morten Ibsgaard - 141606
 */
public class ConwayMenu extends HBox {
    private Button nextBtn;
    private Button timerBtn;
    public ConwayMenu() {
        super(5);
        nextBtn = new Button("Neste");
        nextBtn.setId("neste");
        timerBtn = new Button("Start/stopp");
        timerBtn.setId("timer");

        this.getChildren().addAll(nextBtn, timerBtn);
        //this.setMinHeight(30);

    }

    public void setListener(EventHandler<MouseEvent> listener) {
        nextBtn.setOnMouseClicked(listener);
        timerBtn.setOnMouseClicked(listener);
    }
}
