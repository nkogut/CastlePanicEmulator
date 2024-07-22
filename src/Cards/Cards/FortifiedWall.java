package Cards.Cards;

import Board.Board;
import Main.Main;

public class FortifiedWall extends Card {

    @Override
    public boolean onPlay() {
        int arc = Main.scObject.scanInt(5);
        return Board.setReinforcements(arc);
    }

    public FortifiedWall() {
        super("Fortified Wall");
    }
}
