package Oblig4.cellulærAutomat;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

/**
 * Created by Magnus Poppe Wang on 03.03.2016.
 *
 *  * @author Magnus Poppe Wang
 *
 * <p>
 * AutomatCTRL brukes som kontroller i MVC til
 * den cellulære automaten. Den kobler sammen
 * klassene automat, AutomatImage og GUI.
 * <p>
 * Beklager kaos i denne klassen. Det er fordi den
 * endte opp med å bli en sammenslåing av to klasser.
 * <p>
 * Implementasjon i GUI, etter synkronisering med oppgaven:
 * <p>
 * cellulærAutomat.setOnMouseClicked(e-> {
 * // Klargjør for ny vising:
 * deSelect();
 * AutomatCTRL automat = new AutomatCTRL(800, 799);
 * <p>
 * // Setter nye regler:
 * boolean[] myRules = {true, false, false, true, true, false, true, false};
 * automat.createNewRules(myRules);
 * <p>
 * // Setter opp GUI:
 * presenter.setImage( automat.getImg() );
 * root.setBottom(automat.getAutomatMenu());
 * select( cellulærAutomat );
 * });
 */
public class AutomatCTRL implements ColorSchema
{
    //De grafiske elementene:
    AutomatImage img;
    int linewidth;
    Line[] lines;
    int pixelSize;

    //Regelsett og logikk:
    Automat rules;
    boolean[] userSelection;
    boolean[] firstLine;

    //menyelementer
    HBox root;
    GridPane selector;
    Label instructions;
    Rectangle[] rect;

    public AutomatCTRL(int imgSize800, int lineWidth)
    {
        //klargjør de grafiske elementene som skal til GUI
        img = new AutomatImage(imgSize800, imgSize800);
        lines = new Line[lineWidth];
        this.linewidth = lineWidth;

        //Setter opp menyen for Cellulær automat.
        root = new HBox(10);
        root.setAlignment(Pos.CENTER);
        selector = new GridPane();
        instructions = new Label("Velg regler: ");
        rect = new Rectangle[8];
        userSelection = new boolean[8];
        rectSetup();
        root.getChildren().addAll(instructions, selector);

        //Henter inn noe data som er nødvendig.
        pixelSize = img.getPixelSize(lineWidth);
        firstLine = autoSetFirstLine();
    }


    /********* HER KOMMER METODER TILHØRENDE GUI OG BILDET *********/


    /**
     * Denne metoden bygger opp "LINES" tabellen
     * med objekter av typen Line.
     *
     * @param userRules
     */
    public void generateLines(boolean[] userRules)
    {
        lines[0] = new Line(userRules);

        for (int i = 0; i < lines.length - 1; i++)
        {
            //Finner neste linje og lager den:
            boolean[] line = lines[i].getPkts();
            line = rules.calculateNextLine(lines[i].getPkts());
            lines[i + 1] = new Line(line);
        }
    }

    /**
     * Tegner bildet. Brukes som hjelpemetode til
     * createNewRules() metoden.
     *
     * @return
     */
    private AutomatImage drawImage()
    {
        return img.writeImage(img, lines, linewidth, pixelSize);
    }

    /**
     * Getter for å hente et ferdig generert bilde
     * av typen WriteableImage. Denne er trygg, siden
     * det alltid blir bygget et dummybilde som start.
     *
     * @return
     */
    public AutomatImage getImg()
    {
        return img;
    }

    /**
     * Klargjør første linje med en sort i midten.
     *
     * @return
     */
    private boolean[] autoSetFirstLine()
    {
        firstLine = new boolean[linewidth];
        for (boolean b : firstLine) b = false;
        firstLine[firstLine.length / 2] = true;
        return firstLine;
    }


    /********* HER KOMMER LOGISKE METODER *********/


    /**
     * Denne klassen bygger opp nye bilder ettersom
     * den mottar regelsett fra andre metoder.
     *
     * @param userRules
     */
    public void createNewRules(boolean[] userRules)
    {
        if (userRules.length != 8) return;
        rules = new Automat(userRules);
        generateLines(firstLine);
        drawImage();
    }

    /**
     * Leverer brukerens regelsett.
     *
     * @return
     */
    public boolean[] getUserSelection()
    {
        return userSelection;
    }

    /**
     * Genererer brukers regelsett utifra fargen
     * på de forskjellige rektanglene. Jeg var veldig
     * lei, og dermed gadd jeg ikke finne på noe bedre.
     */
    public void setUserSelection()
    {
        for (int i = 0; i < 8; i++)
        {
            if (rect[i].getFill() == FALSECOLOR) userSelection[i] = true;
            else userSelection[i] = false;
            //System.out.println(userSelection[i]);
        }

    }


    /********* HER KOMMER MENY-GUI METODENE *********/


    /**
     * Getter til selve undermenyen kyttet til
     * den cellulære automaten.
     *
     * @return
     */
    public HBox getAutomatMenu()
    {
        return root;
    }

    /**
     * Setter opp lyttere til de forskjellige
     * firkantene i menyen til GUI.
     *
     * @param r
     */
    private void setListener(Rectangle r)
    {
        //Setting actionlistener:
        r.setOnMouseClicked(e -> {
            //Changing the colors:
            if (r.getFill() == FALSECOLOR) r.setFill(TRUECOLOR);
            else r.setFill(FALSECOLOR);

            // Setting the rules:
            setUserSelection();
            createNewRules(getUserSelection());
        });
    }

    /**
     * Klargjør de forskjellige rektanglene
     * til å brukes i undermenyen.
     */
    private void rectSetup()
    {
        for (int i = 0; i < 8; i++)
        {
            rect[i] = new Rectangle(40, 40);
            rect[i].setFill(FALSECOLOR);
            rect[i].setStroke(BORDERCOLOR);
            userSelection[i] = false;
            selector.add(rect[i], i, 0);
            setListener(rect[i]);
        }
    }
}