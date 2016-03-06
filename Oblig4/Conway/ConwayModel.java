package Oblig4.Conway;

/**
 * Created by Morten on 03/03/2016.
 * @author Morten Ibsgaard - 141606
 */
public class ConwayModel {
    private boolean[][] lifeArr;
    private int size;

    public ConwayModel(int size) {
        this.size = size;
        lifeArr = new boolean[size][size];
        lifeArr[1][2] = true;
        lifeArr[2][2] = true;
        lifeArr[3][2] = true;
    }

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
    private int tellNabo(int x, int y) {
        if (erLovligNabo(x, y) && lifeArr[x][y]) return 1;
        return 0;
    }
    private boolean erLovligNabo(int x, int y) {
        if (x>=0 && x<size && y>=0 && y<size) return true;
        return false;
    }
    public void mark(int x, int y) {
        lifeArr[x][y] = !lifeArr[x][y];
    }

    public boolean[][] getLifeArr() {
        return lifeArr;
    }

}
