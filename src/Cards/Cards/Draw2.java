package Cards.Cards;
import Main.Main;

public class Draw2 extends Card {

    @Override
    public boolean onPlay() {
        Main.currPlayer.getHand().drawCard(2);
    return true;
    }

    public Draw2() {
        super("Draw 2");
    }
}
