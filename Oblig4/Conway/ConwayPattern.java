package Oblig4.Conway;

import java.util.Arrays;

/**
 * Lager objekt som inneholder mønsteret som skal vises på spill-vinduet.
 * @author Morten Ibsgaard - 141606
 */
public class ConwayPattern {
    private int size;
    private String pattern;
    private boolean[][] lifeArr;

    // Liste over mulige patterns.
    private static final String[] PATTERNS = new String[]{"Glider", "Small Exploder", "Exploder", "10 Cell Row", "Light Spaceship", "Tumbler", "Glider Gun"};

    public ConwayPattern(int size, String pattern) {
        this.size = size;
        this.pattern = pattern;
    }

    /**
     * Henter boolean tabell med mønsteret som skal vises, og plasserer det midt i en full størrelse tabell.
     *
     * @return Ferdig boolean av størrelse passende til hovedvinduet med valgt mønster sentrert.
     */
    public boolean[][] generatePattern() {
        boolean[][] b = getPattern();
        boolean[][] lifeArr = new boolean[size][size];
        int startY = (size - b[0].length) / 2;
        int startX = (size - b.length) / 2;
        for (int i=startX, i2 = 0; i2<b.length; i++, i2++) {
            for (int j=startY, j2 = 0; j2<b[i2].length; j++, j2++) {
                lifeArr[i][j] = b[i2][j2];
            }
        }
        return lifeArr;
    }

    /**
     * Får inn String-Array med mønster og gjør det om til boolean tabell med mønster
     *
     * @return boolean tabell som bare inneholder mønsteret som skal vises.
     */
    private boolean[][] getPattern() {
        String[] pStr = pattern();
        boolean[][] b = new boolean[pStr.length][pStr[0].length()];
        for (int i=0; i<pStr.length; i++) {
            for (int j=0; j<pStr[i].length(); j++) {
                if (pStr[i].charAt(j) == '1') b[i][j] = true;
            }
        }
        return b;
    }

    /**
     * Metode hvor selve mønstrene er lagret på en visuell måte i String-Arrays
     *
     * @return String-array med mønster
     */
    private String[] pattern() {
        if (pattern.equals("Glider")) return new String[] { "010",
                                                            "001",
                                                            "111" };
        if (pattern.equals("Small Exploder")) return new String[] { "010",
                                                                    "111",
                                                                    "101",
                                                                    "010" };
        if (pattern.equals("Exploder")) return new String[] {       "10101",
                                                                    "10001",
                                                                    "10001",
                                                                    "10001",
                                                                    "10101"};
        if (pattern.equals("10 Cell Row")) return new String[] { "1111111111" };
        if (pattern.equals("Light Spaceship")) return new String[] {    "01111",
                                                                        "10001",
                                                                        "00001",
                                                                        "10010" };
        if (pattern.equals("Tumbler")) return new String[] {    "0110110",
                                                                "0110110",
                                                                "0010100",
                                                                "1010101",
                                                                "1010101",
                                                                "1100011" };
        if (pattern.equals("Glider Gun")) return new String[] { "00000000000000000000000110000000001100",
                                                                "00000000000000000000001010000000001100",
                                                                "11000000011000000000001100000000000000",
                                                                "11000000101000000000000000000000000000",
                                                                "00000000110000001100000000000000000000",
                                                                "00000000000000001010000000000000000000",
                                                                "00000000000000001000000000000000000000",
                                                                "00000000000000000000000000000000000110",
                                                                "00000000000000000000000000000000000101",
                                                                "00000000000000000000000000000000000100",
                                                                "00000000000000000000000000000000000000",
                                                                "00000000000000000000000000000000000000",
                                                                "00000000000000000000000011100000000000",
                                                                "00000000000000000000000010000000000000",
                                                                "00000000000000000000000001000000000000"};
        return null;
    }
    public static String[] getPatterns() {
        return PATTERNS;
    }
}
