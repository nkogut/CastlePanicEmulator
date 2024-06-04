package Cards;

import Cards.Cards.Card;
import Main.Main;

import java.util.ArrayList;

public class DiscardPile {
    private static ArrayList<Card> cards = new ArrayList<>();

    public static ArrayList<Card> getContents() {
        return cards;
    }
    public static int getSize() {
        return cards.size();
    }

    public static boolean addCard(Card card) {
        cards.add(card);
        return true;
    }

    public static boolean removeCard(Card card) {
//      This is used by the Scavenge card
        if (!cards.contains(card)) return false;
        cards.remove(card);
        return true;
    }

    public static String displayContents() {
//        Same as Hand's toString() method - used for selecting cards with Scavenge
        String str = "";
        for (int i = 0; i < cards.size(); i++) {
            str += System.lineSeparator() + i + " " + cards.get(i);
        }
        return str;
    }

    public static Card selectCard() {
        displayContents();
        System.out.println("Pick the card by its position");
        return cards.get(Main.scObject.scanInt(cards.size()));
    }
}
