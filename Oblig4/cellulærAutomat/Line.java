package Oblig4.cellulærAutomat;

/**
 * Created by Magnus Poppe Wang on 03.03.2016.
 *
 * Denne klassen er linjen, som representeret
 * på bildet. Denne er ofte brukt implementert
 * som en tabell med Line objekter kalt lines.
 * @author Magnus Poppe Wang
 */
public class Line
{

    /**
     * Pkts er hver punkt på linjen.
     */
    boolean[] pkts;

    public Line(boolean[] line)
    {
        pkts = line;
    }

    /**
     * Setter.
     * @param pkts
     */
    public void setPkts(boolean[] pkts)
    {
        this.pkts = pkts;
    }

    /**
     * Getter.
     * @return
     */
    public boolean[] getPkts()
    {
        return pkts;
    }
}
