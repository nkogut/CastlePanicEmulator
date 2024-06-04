package Board.Tiles.Monsters;

import Board.Board;

public class Healer extends Boss{

    @Override
    public void onEnter(){
        for (Monster monster : Board.getMonstersOnBoard()) {
            monster.modifyHealth(1, false);
        }
    }
    public Healer() {
        super(2, "Healer");
    }
}
