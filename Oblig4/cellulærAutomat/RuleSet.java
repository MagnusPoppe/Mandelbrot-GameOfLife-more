package Oblig4.cellulærAutomat;

/**
 * Created by Magnus Poppe Wang on 03.03.2016.
 *
 * Klassen er et regelsett med matching
 * for den cellulære automaten.
 * @author Magnus Poppe Wang
 */
public class RuleSet
{
    /**
     * Hvordan userRules var originalt tenkt:
     * boolean SSS;
     * boolean SSH;
     * boolean SHS;
     * boolean SHH;
     * boolean HSS;
     * boolean HHS;
     * boolean HSH;
     * boolean HHH;
     */

    boolean[] userRules;

    /**
     * Tabell for matching av de forskjellige
     * mulige.
     */
    Rule[] MATCHING = {
            new Rule(false, false, false), //0 SSS
            new Rule(false, false, true),  //1 SSH
            new Rule(false, true, false),  //2 SHS
            new Rule(false, true, true),   //3 SHH
            new Rule(true, false, false),  //4 HSS
            new Rule(true, false, true),   //5 HSH
            new Rule(true, true, false),   //6 HHS
            new Rule(true, true, true)     //7 HHH
    };

    /**
     * Konstruktør
     * @param choices
     */
    public RuleSet(boolean[] choices)
    {
        if (choices.length != 8) return;
        userRules = choices;
    }

    /**
     * Takes a rule and checks it against the MATCHING
     * table.
     *
     * @param rule
     * @return the cooresponding user rule.
     */
    public boolean checkRule(Rule rule)
    {
        boolean output = false;
        for (int i = 0; i < 8; i++)
        {

            if (
                rule.left == MATCHING[i].left &&
                rule.middle == MATCHING[i].middle &&
                rule.right == MATCHING[i].right
            )
            {
                output = userRules[i];
            }
        }
        return output;
    }
}
