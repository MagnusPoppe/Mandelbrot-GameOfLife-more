package Oblig4.cellulærAutomat;

import javafx.beans.NamedArg;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Created by Magnus Poppe Wang on 03.03.2016.
 *
 * View klassen for Cellulær automat i MVC. Denne
 * lager bildeobjekter og regner om linje til grafisk
 * linje.
 *
 * @author Magnus Poppe Wang
 */
public class AutomatImage extends WritableImage implements ColorSchema
{
    public AutomatImage(@NamedArg("width") int width, @NamedArg("height") int height)
    {
        super(width, height);
    }

    /**
     * Regner ut hvor stor en piksel er i forhold til
     * linjen Line.
     * @param lineSize
     * @return
     */
    public int getPixelSize(int lineSize)
    {
        return (int) super.getWidth() / lineSize;
    }

    /**
     * Metode til å tegne opp automaten i et writeable image.
     * Metoden tar en linje og regner om, slik at den kan tegne
     * opp linjen i forskjellige størrelser på firkantene.
     * <p>
     * Denne metoden inneholder veldig mye javadoc kommentarer
     * for å forklare meg selv logikken i koden.
     *
     * @return Ferdig tegnet bilde.
     */
    public AutomatImage writeImage(AutomatImage img, Line[] lines, int linewidth, int pixelSize)
    {

        // Ole! Han er den beste til å tegne!
        PixelWriter tegnerOle = img.getPixelWriter();

        for (int lineY = 0; lineY < linewidth; lineY++)
        {
            /** line:
             * Denne linjen representerer en linje.
             * lengde: (x * pixelsize) i X retning.
             * Tykkelse: (pixelSize) i Y retting.
             *
             * Hentes ny hver gang en "pixelSize" er utført i y retning.
             */
            Line line = lines[lineY];

            for (int y = 0; y < pixelSize; y++)
            {
                /** pixel:
                 * bredde: "pixelSize"
                 * tykkelse: 1
                 *
                 * Det hentes en ny pixel etter hver "pixelSize"
                 * er utført i X retning.
                 */
                boolean[] pixel = line.getPkts();

                for (int lineX = 0; lineX < linewidth; lineX++)
                {
                    for (int x = 0; x < pixelSize; x++) // den nåværende ruten
                    {
                        //Her skrives X aksen piksel for piksel.
                        if (pixel[lineX])
                            tegnerOle.setColor(
                                    lineX * pixelSize + x,
                                    lineY * pixelSize + y,
                                    TRUECOLOR
                            );
                        else
                            tegnerOle.setColor(
                                    lineX * pixelSize + x,
                                    lineY * pixelSize + y,
                                    FALSECOLOR
                            );
                    }
                }
            }
        }
        return img;
    }
}
