package Board.Tiles.Monsters;

import Main.Main;

public class GoblinKing extends Boss{

    @Override
    public void onEnter() {
        Main.pullTile(3);
    }

    public GoblinKing() {
        super(2, "Goblin King");
    }
}
