package Cards;
import Cards.Cards.Card;
import Main.Main;
import Main.ScannerClass;
import java.util.ArrayList;
import java.util.Scanner;

public class Hand {
    private final int maxHandSize;
    private ArrayList<Card> cards = new ArrayList<>(6);

    public int getNumCards() {
        return cards.size();
    }

    /**
     * Draw card(s) from the top of the face down deck
     * @param num Number of cards to draw
     */
    public void drawCard(int num) {
        for (int i=0; i<num; i++) {
            cards.add(DrawPile.drawCard());
        }
    }

    public void drawToFull() {
        int numCards = cards.size();
        if (numCards < maxHandSize) {
            drawCard(maxHandSize - numCards);
        }
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    /**
     * Exchange cards with another player, 1 card pair at a time (applicable for 2 player games)
     * @return true if the trade is successful, otherwise false
     */
    public boolean trade() {
        System.out.println("With whom?");
        Hand otherHand = Main.getPlayerByName(Main.scObject.scanStr(Main.getPlayerNames())).getHand();

        Card myCard = selectCard("Which card would you like to trade?");
        Card otherCard = otherHand.selectCard("For which card?");
        discardCard(myCard);
        otherHand.discardCard(otherCard);
        cards.add(otherCard);
        otherHand.cards.add(myCard);
        return true;
    }

    public boolean playCard(int index) {
        if (getCard(index).onPlay()) {
//            Card was successfully played
            discardCard(index);
            return true;
        }
        return false;
    }

    public void discardCard() {
        if (cards.isEmpty()) {
            return;
        }
            Card card = selectCard();
            cards.remove(card);
            DiscardPile.addCard(card);
        }

    public void discardCard(int index) {
        if (cards.isEmpty()) {
            return;
        }
        Card card = cards.get(index);
        cards.remove(card);
        DiscardPile.addCard(card);
    }

    public void discardCard(Card card) {
        if (cards.isEmpty()) {
            return;
        }
        cards.remove(card);
        DiscardPile.addCard(card);
    }

    private Card selectCard(){
        System.out.println("Pick the card by its position");
        return cards.get(Main.scObject.scanInt(getNumCards()));
    }

    private Card selectCard(String message){
        System.out.println(message);
        return cards.get(Main.scObject.scanInt(getNumCards()));
    }

    @Override
    public String toString() {
        String str = "----" + Main.currPlayer + "'s hand----";
        for (int i = 0; i < cards.size(); i++) {
            str += System.lineSeparator() + i + " " + cards.get(i);
        }
        str += "\n------------";
        return str;
    }

    public Hand() {
        switch(Main.getNumPlayers()) {
            case 2:
                maxHandSize = 6;
                break;
            case 3: case 4: case 5:
                maxHandSize = 5;
                break;
            default:
                maxHandSize = 4;
                break;
        }
        drawToFull();
    }
}
