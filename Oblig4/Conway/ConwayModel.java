package Oblig4.Conway;

/**
 * Modellklassen i MVC strukturen
 * Håndterer hovedsakelig alt av hvilke celler skal være levende og hvilke skal være døde.
 * Alt lagres i en boolean tabell, lifeArr
 *
 * @author Morten Ibsgaard - 141606
 */
public class ConwayModel {
    private boolean[][] lifeArr;
    private int size;

    public ConwayModel(int size) {
        this.size = size;
        lifeArr = new boolean[size][size];
    }

    /**
     * Logikken bak generasjons-stegene
     * Lager ny tabell med samme størrelse som gamle.
     * Så sjekker den hver rute og hvor mange naboer den har, og håndterer deretter etter reglene.
     */
    public void stepGeneration() {
        boolean[][] newGen = new boolean[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                int naboer = naboer(i, j);
                if (lifeArr[i][j] && naboer == 2 || naboer == 3) newGen[i][j] = true;
                else if (!lifeArr[i][j] && naboer == 3) newGen[i][j] = true;
                else newGen[i][j] = false;
            }
        }
        lifeArr = newGen;
    }

    /**
     *
     * @return Antall naboer til gitt celle
     */
    private int naboer(int x, int y) {
        int num = tellNabo(x-1, y-1);
        num += tellNabo(x-1, y);
        num += tellNabo(x-1, y+1);
        num += tellNabo(x, y-1);
        num += tellNabo(x, y+1);
        num += tellNabo(x+1, y-1);
        num += tellNabo(x+1, y);
        num += tellNabo(x+1, y+1);
        return num;
    }

    /**
     * Sjekker om det er lovlig koordinat (utenfor koordinatsystemet)
     * Hvis det er det, så sjekker det om gitte koordinat er levende
     *
     * @return Om gitte celle eksisterer og er i live.
     */
    private int tellNabo(int x, int y) {
        if (erLovligNabo(x, y) && lifeArr[x][y]) return 1;
        return 0;
    }

    /**
     * Sjekker om gitt celle er lovlig (utenfor koordinatsystemet)
     * @return Om celle er lovlig celleverdi.
     */
    private boolean erLovligNabo(int x, int y) {
        if (x>=0 && x<size && y>=0 && y<size) return true;
        return false;
    }

    /**
     * Henter tabell fra pattern objektet og setter det som gjeldende lifeArr
     *
     * @param pattern boolean tabell med komplett kart over liv
     */
    public void setPattern(String pattern) {
        ConwayPattern p = new ConwayPattern(size, pattern);
        lifeArr = p.generatePattern();
    }

    /**
     * Setter ny størrelse på tabellen.
     * @param size Ny størrelse x * x
     */
    public void setSize(int size) {
        this.size = size;
        lifeArr = new boolean[size][size];
    }

    /**
     * Markerer gitt rute/celle
     */
    public void mark(int x, int y) {
        lifeArr[x][y] = !lifeArr[x][y];
    }

    public boolean[][] getLifeArr() {
        return lifeArr;
    }

}
