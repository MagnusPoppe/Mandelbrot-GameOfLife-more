package Oblig4.Conway;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *  Main Conway controller.
 * Står for kommunikasjon mellom Conway pakken og "omverdenen"
 * Leverer GUI i en GridPane med getGUI() og Spill-menyen som en HBox med getMenu()
 *
 * Zoomen ble IKKE bra. Hadde egentlig tenkt å legge det over til punktgrafikk istedenfor
 * GridPane, men det får bli neste gang ;)
 *
 * @author Morten Ibsgaard - 141606
 */
public class Conway implements EventHandler<Event> {
    private ConwayGUI gui;
    private ConwayModel model;
    private ConwayMenu menu;
    private GridPane[][] pArr;

    // Variabler som hører til gridstørrelse
    public static final int INIT_SIZE = 50;
    public static final int MIN_SIZE = 35;
    public static final int MAX_SIZE = 80;
    public int size;

    // Variabler tilhørende automatisk generasjonsutvikling
    private int interval;
    private boolean autoplay = false;
    public static final int MIN_SPEED = 100;
    public static final int MAX_SPEED = 2000;
    public static final int DEFAULT_SPEED = 500;
    private Timeline tl;

    public Conway() {
        size = INIT_SIZE;

        gui = new ConwayGUI();
        gui.buildGrid(size);
        gui.setListener(this); // Bruker controlleren her som listener på GUI elementene

        model = new ConwayModel(size);
        updateGrid();

        menu = new ConwayMenu();
        menu.setListener(this); // Bruker controlleren her som listener på meny-elementene
        menu.setPatterns(ConwayPattern.getPatterns());

        interval = DEFAULT_SPEED;
    }

    /**
     * Returnerer Pane som kan legges til i andre vinduer. GUI klassen extender GridPane, så kan bruke hele GUI'n som parameter.
     * @return Game griden
     */
    public Pane getGUI() {
        return gui;
    }

    /**
     * Returnerer komplett Pane som kan legges til hvor det trengs.
     * @return Pane/HBox med spill-menyen
     */
    public Pane getMenu() {
        return menu;
    }

    /**
     * Hele griden er lagt opp i 2 deler: En funksjonell og en grafisk.
     * Den funksjonelle ligger i Model objektet (ConwayModel), og er en todimensjonal boolean tabell
     * Denne tabellen inneholder verdier som svarer til om en celle er levende eller død
     * Denne tabellen blir sendt til GUI'n som har en todimensjonal tabell av Pane elementer som tilsvarer boolean tabellen i Modell klassen
     * Denne metoden henter tabellen fra Model og sender til GUIet som oppdaterer fargene på skjermen.
     * Komplett seperasjon av logikk og GUI,
     */
    public void updateGrid() {
        boolean[][] lifeArr = model.getLifeArr();
        gui.updateGrid(lifeArr);
    }

    /**
     * Et steg i generasjon
     */
    public void stepGeneration() {
        model.stepGeneration();
        updateGrid();
    }

    /**
     * Kommer fra musklikk på spillvinduet. Markerer enkeltrute.
     *
     * @param x X koordinaten
     * @param y Y koordinaten
     */
    public void mark(int x, int y) {
        model.mark(x, y);
        updateGrid();
    }

    /**
     * Setter i gang eller stopper automatisk fremgang
     */
    public void timer() {
        autoplay = !autoplay;
        if (autoplay) autoStep();
    }

    /**
     * Et automatisk steg i generasjonen.
     * Hvis automatisk fremgang (autoplay) er slått på, vil den automatisk calle på seg selv etter gitt intervall.
     */
    public void autoStep() {
        stepGeneration();
        tl = new Timeline(new KeyFrame(Duration.millis(interval), e -> {
            if (autoplay) autoStep();
        }));
        tl.play();
    }

    /**
     * Setter mønster på spillskjermen.
     * Navnet hentes fra ComboBoxen i spillmenyen og sendes til modell for å oppdatere lifeArr tabellen
     *
     * @param pattern Navn på som skal vises på skjermen.
     */
    public void setPattern(String pattern) {
        model.setPattern(pattern);
        updateGrid();
    }

    /**
     * Setter intervall på automatisk fremgang.
     * Merkelig formel pga jeg ville ha høyere verdi på slideren = raskere fremgang. Noe som
     * normalt ellers ville gitt høyere intervall siden jeg bruker den notasjonen.
     *
     * @param speed Fart som kommer fra Slideren
     */
    public void adjustSpeed(int speed) {
        interval = MIN_SPEED + MAX_SPEED - speed;
    }

    /**
     * Setter størrelse på gridden. Kommer fra sizeSlideren på spillmenyen.
     * size 78 likte skjermen IKKE, derfor har jeg bare tatt den vekk ;)
     * Oppdaterer først tabellen i model, så setter pattern hvis det er valgt,
     * bygger ny grid av riktig størrelse, og så setter listener på nye gridden.
     * @param size Gridstørrelse x * x ruter.
     */
    public void setSize(int size) {
        if (size == 78) size = 79;
        this.size = size;
        model.setSize(size);
        if (menu.getSelectedPattern() != null) {
            model.setPattern(menu.getSelectedPattern());
        }
        gui.buildGrid(size);
        gui.setListener(this);
        updateGrid();
    }

    /**
     * Hoved-eventhandleren. Sender videre til korrekte metoder.
     * @param event input event fra menyen og spillet
     */
    @Override
    public void handle(Event event) {
        Object obj = event.getSource();
        String id = ((Node) obj).getId().toLowerCase();
        if (obj instanceof Button) {
            if (id.equals("neste")) {
                stepGeneration();
            } else if (id.equals("timer")) {
                timer();
            }
        } else if (obj instanceof StackPane) {
            String[] coords = ((StackPane) obj).getId().split(";");
            mark(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
        } else if (id.equals("pattern")) {
            ComboBox cb = (ComboBox) obj;
            String selected = cb.getSelectionModel().getSelectedItem().toString();
            setPattern(menu.getSelectedPattern().toString());
        } else if (id.equals("speed")) {
            adjustSpeed((int) ((Slider)event.getSource()).valueProperty().get());
        } else if (id.equals("size")) {
            setSize((int)((Slider)event.getSource()).valueProperty().get());
        }
    }
}
