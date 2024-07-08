package Board.Tiles.BoardEffects;

import Cards.Cards.Card;
import Cards.Hand;
import Cards.Player;
import Main.Main;

import java.util.ArrayList;

public class Plague extends BoardEffect {
    int ring;
    String type;

    private boolean canDiscard(Hand hand) {
        if (hand.getNumCards() == 0) return false;
        for (int i = 0; i < hand.getNumCards(); i++) {
            if (hand.getCard(i).name.contains(type)) return true;
        }
        return false;
    }

    public void onDraw() {
        System.out.println("All players discard a " + type);
        for (Player player : Main.getAllPlayers()) {
            Hand hand = player.getHand();
            if (!canDiscard(hand)) {
                System.out.println(player + "Has no " + type + " cards");
            } else {
                System.out.println(player + "must discard a card:");
                while (true) {
                    Card choice = hand.selectCard();
                    if (choice.name.contains(type)) {
                        hand.discardCard(choice);
                        break;
                    }
                    else System.out.println("You must discard a card of type " + type);
                }
            }
        }
    }

    /**
     * Set the type of plague and which ring it can target
     * @param type The name of the plague, i.e. "Archer"
     */
    public Plague(String type) {
        this.type = type;
        switch (type) {
            case "Archer": ring = 3;
                break;
            case "Knight": ring = 2;
                break;
            default: ring = 1;
        }
    }
}
