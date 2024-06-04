package Cards;

import Cards.Cards.Card;

import java.util.ArrayList;

public class DiscardPile {
    private static ArrayList<Card> cards = new ArrayList<>();

    public static ArrayList<Card> getContents() {
        return cards;
    }

    public static void addCard(Card card) {
        cards.add(card);
    }
}
