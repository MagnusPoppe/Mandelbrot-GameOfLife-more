package Oblig4.Conway;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

/**
 * Meny-klassen til å lage spillmenyobjekt som skal legges til hovedvinduet.
 * Inneholder bare metoder for å kontrollere spillet.
 *
 * @author Morten Ibsgaard - 141606
 */
public class ConwayMenu extends HBox {
    private Button nextBtn; // Neste generasjon
    private Button timerBtn; // Start/stopp automatisk fremgang
    private ComboBox patternCombo; // ComboBox med forskjellige mønstre
    private Slider speedSlider; // Velger hvor fort automatiske fremgangen skal være
    private Slider sizeSlider; // Setter størrelsen på gridden.

    public ConwayMenu() {
        super(5);

        sizeSlider = new Slider(Conway.MIN_SIZE, Conway.MAX_SIZE, Conway.INIT_SIZE);
        sizeSlider.setId("size");
        nextBtn = new Button("Neste");
        nextBtn.setId("neste");

        timerBtn = new Button("Start/stopp");
        timerBtn.setId("timer");
        speedSlider = new Slider(Conway.MIN_SPEED, Conway.MAX_SPEED, (Conway.MIN_SPEED + Conway.MAX_SPEED - Conway.DEFAULT_SPEED));
        speedSlider.setId("speed");

        patternCombo = new ComboBox();
        patternCombo.setId("pattern");
        patternCombo.setPrefWidth(110);

        this.getChildren().addAll(new Label("Size: "), sizeSlider, nextBtn, timerBtn, new Label("Speed: "), speedSlider, new Label("Pattern: "), patternCombo);
        this.setMinHeight(25);

    }

    /**
     *
     * @return Hvilket pattern som er valgt i drop-downen. null hvis ingen.
     */
    public String getSelectedPattern() {
        return (String) patternCombo.getSelectionModel().getSelectedItem();
    }

    /**
     * Får inn en array med mulige mønstre og lager verdiene i ComboBoxen
     * @param s String-array med mønstre henta fra ConwayPattern klassen.
     */
    public void setPatterns(String[] s) {
        for (String str : s) patternCombo.getItems().add(str);
    }

    public void setListener(EventHandler<Event> listener) {
        nextBtn.setOnMouseClicked(listener);
        timerBtn.setOnMouseClicked(listener);
        patternCombo.setOnAction(listener);
        speedSlider.setOnMouseDragged(listener);
        sizeSlider.setOnMouseReleased(listener);
    }
}
