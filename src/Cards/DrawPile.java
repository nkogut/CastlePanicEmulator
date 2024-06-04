package Cards;

import Cards.Cards.*;

import java.util.ArrayList;
import java.util.Collections;

public class DrawPile {
    private static final char[] COLORS = new char[]{'r', 'g', 'b'};
    private static final int[] ALL_RINGS = new int[] {0,1,2,3,4};
    private static final int[] ALL_RINGS_NO_FOREST = new int[] {0,1,2,3};
    public static ArrayList<Card> cards = new ArrayList<>(49);

    public static void createDrawPile() {
        for (char color : COLORS) {
            for (int i=0; i<3; i++) {
                cards.add(new Attack(Character.toUpperCase(color) + "Archer", color, 3, 1));
            }
        }
        for (char color : COLORS) {
            for (int i=0; i<3; i++) {
                cards.add(new Attack(Character.toUpperCase(color) + "Knight", color, 2, 1));
            }
        }
        for (char color : COLORS) {
            for (int i=0; i<3; i++) {
                cards.add(new Attack(Character.toUpperCase(color) + "Swordsman", color, 1, 1));
            }
        }
        for (char color : COLORS) {
            cards.add(new Attack(Character.toUpperCase(color) + "Hero", color, ALL_RINGS_NO_FOREST, 1));
        }
        cards.add(new Attack("W Archer", 'a', 3, 1));
        cards.add(new Attack("W Knight", 'a', 2, 1));
        cards.add(new Attack("W Swordsman", 'a', 1, 1));
        cards.add(new Attack("Barbarian", 'a', ALL_RINGS, 10));
        cards.add(new Tar());
        cards.add(new DriveHimBack());
        cards.add(new Missing());
        cards.add(new FortifiedWall());
        cards.add(new Scavenge());
        cards.add(new NiceShot());
        cards.add(new Draw2());
        for (int i=0; i < 4; i++) {
            cards.add(new Brick());
            cards.add(new Mortar());
        }


        Collections.shuffle(cards);
    }

    public static void shuffleDeck() {
        for (Card card : DiscardPile.getContents()) {
            cards.add(card);
            Collections.shuffle(cards);
        }
    }

    public static Card drawCard() {
        if (cards.isEmpty()) {
            shuffleDeck();
        }
        return cards.removeFirst();
    }
}
