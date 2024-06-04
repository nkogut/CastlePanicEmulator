package Cards.Cards;
import Main.Main;
import Cards.Hand;

public class NiceShot extends Card {

    @Override
    public boolean onPlay() {
        System.out.println("Choose another card in your hand to use with");
        Hand activeHand = Main.currPlayer.getHand();
        Card selectedCard =  activeHand.selectCard();
        if (!(selectedCard instanceof Attack)) return false;
        // there may be a more elegant way to do this without explicitly checking type
        ((Attack) selectedCard).setDamage(99);
        if (activeHand.playCard(selectedCard)) {
            ((Attack) selectedCard).setDamage(1);
            return true;
        }
        ((Attack) selectedCard).setDamage(1);
        return false;
    }

    public NiceShot() {
        super("Nice Shot");
    }
}
