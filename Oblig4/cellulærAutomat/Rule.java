package Oblig4.cellulærAutomat;

/**
 * Created by Magnus Poppe Wang on 03.03.2016.
 *
 * En regel. Som man vet er den neste linjen
 * bestemt av de tre binærverdiene som ligger
 * på linjen over.
 *
 * @author Magnus Poppe Wang
 */
public class Rule
{
    boolean left, middle, right;

    public Rule(boolean left, boolean middle, boolean right)
    {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }
}
