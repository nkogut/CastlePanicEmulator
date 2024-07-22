package Cards.Cards;
import Main.Main;
import Cards.DiscardPile;

public class Scavenge extends Card {

    @Override
    public boolean onPlay() {
        if (DiscardPile.getSize() == 0) {
            return false;
        }
        Card selectedCard = DiscardPile.selectCard();
        DiscardPile.removeCard(selectedCard);
        return Main.currPlayer.getHand().addCard(selectedCard);
    }

    public Scavenge() {
        super("Scavenge");
    }
}
