package Board.Tiles.Monsters;

import Board.Board;

public class OrcWarlord extends Boss {
    @Override
    public void onEnter() {
        for (int arc : Board.colorToArc(Board.arcToColor(this.getArc()))) {
            Board.moveArcForward(arc);
        }
    }
    public OrcWarlord() {
        super(3, "Orc Warlord");
    }
}
