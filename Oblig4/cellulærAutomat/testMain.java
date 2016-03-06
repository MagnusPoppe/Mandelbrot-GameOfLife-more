package Oblig4.cellulærAutomat;

/**
 *
 * DENNE KLASSEN ER KUN FOR TESTING
 * AV DET LOGISKE ASPEKTET MED CELLULÆR AUTOMAT.
 * VENNLIGST SE BORT IFRA DENNE KLASSEN.
 *
 * Created by Magnus Poppe Wang on 03.03.2016.
 *
public class testMain
{
    public static void main(String[] args)
    {

        int NUMBEROFLINES = 30;

        boolean[] eew = {
                true, true, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true,
                false, true, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true,
                true
        };

        Line[] lines = new Line[NUMBEROFLINES];

        //Setter første linje.
        lines[0] = new Line(eew);

        //Setter regelsett:
        /**
         * Matching for the available choices.

         Rule[] MATCHING = {
         new Rule( false, false, false ), //0 SSS
         new Rule( false, false, true  ), //1 SSH
         new Rule( false, true,  false ), //2 SHS
         new Rule( false, true,  true  ), //3 SHH

         new Rule( true,  false, false ), //4 HSS
         new Rule( true,  false, true  ), //5 HSH
         new Rule( true,  true,  false ), //6 HHS
         new Rule( true,  true,  true  )  //7 HHH
         };
         * /
        boolean[] rule30 = {
                true, true, true, false, false, false, false, true
        };

        Automat auto = new Automat(rule30);


        // PRINTING TO SCREEN
        for (int i = 0; i < NUMBEROFLINES - 1; i++)
        {
            //Finner neste linje:
            boolean[] line = lines[i].getPkts();

            //Skriver ut til skjerm.
            for (boolean point : line)
            {
                System.out.print(point ? "H " : "S ");
            }

            System.out.println();
            //Kalkulerer neste linje og legger til i
            line = auto.calculateNextLine(lines[i].getPkts());
            lines[i + 1] = new Line(line);

        }
    }
}
*/