package Cards.Cards;

import Board.Board;
import Cards.Hand;
import Main.Main;

public class Mortar extends Card {

    @Override
    public boolean onPlay() {
        Hand activeHand = Main.currPlayer.getHand();
        for (int i=0; i<activeHand.getNumCards(); i++) {
            if (activeHand.getCard(i).name.equals("Brick")) {
                int arc = Main.scObject.scanInt(5);
                if (Board.setWalls(arc)) {
                    activeHand.discardCard(i);
                    return true;
                }
            }
        }
        return false;
    }

    public Mortar() {
        super("Mortar");
    }
}
