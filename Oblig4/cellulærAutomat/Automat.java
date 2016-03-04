package Oblig4.cellulærAutomat;

/**
 * Created by Magnus Poppe Wang on 03.03.2016.
 * <p>
 * Denne klassen er modell i MVC for Cellulær Automat.
 *
 * @author Magnus Poppe Wang
 */
public class Automat
{
    /**
     * SSS, SSH, SHH, SHS, HSS, HSH HHS, HHH
     */
    RuleSet rules;

    public Automat(boolean[] rules)
    {
        this.rules = new RuleSet(rules);
    }

    /**
     * Regner ut neste linje utifra regelsettet og
     * data om linjen den jobber med.
     *
     * @param line
     * @return
     */
    public boolean[] calculateNextLine(boolean[] line)
    {
        boolean[] next = new boolean[line.length];

        for (int i = 0; i < line.length; i++)
        {
            boolean left = (i <= 0) ? line[line.length - 1] : line[i - 1];
            boolean middle = line[i];
            boolean right = (i > line.length - 2) ? line[0] : line[i + 1];
            next[i] = rules.checkRule(new Rule(left, middle, right));
        }
        return next;
    }
}
