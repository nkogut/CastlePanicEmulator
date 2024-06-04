package Board.Tiles.Monsters;

import Board.Board;
public class TrollMage extends Boss {

    @Override
    public void onEnter() {
        Board.moveAllForward();
    }
    public TrollMage() {
        super(3, "Troll Mage");
    }
}
