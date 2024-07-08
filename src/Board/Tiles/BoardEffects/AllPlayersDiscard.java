package Board.Tiles.BoardEffects;
import Cards.Player;
import Main.Main;
import Cards.Hand;

public class AllPlayersDiscard extends BoardEffect {
    public void onDraw() {
        System.out.println("All players discard a card");
        for (Player player : Main.getAllPlayers()) {
            Hand hand = player.getHand();
            if (hand.getNumCards() == 0) {
                System.out.println(player + "Has no cards");
            } else {
                System.out.println(player + "must discard a card. There hand is:");
                hand.discardCard(hand.selectCard());
            }
        }
    }
}
