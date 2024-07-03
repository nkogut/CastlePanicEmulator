package Board.Tiles.BoardEffects;

import Main.Main;

public class DrawMoreMonsters extends BoardEffect {
    private int numTiles;
    public void onDraw() {
        Main.pullTile(numTiles);
    }

    public DrawMoreMonsters(int numTiles) {
        this.numTiles = numTiles;
        name = "Draw " + numTiles + " monsters";
    }

}