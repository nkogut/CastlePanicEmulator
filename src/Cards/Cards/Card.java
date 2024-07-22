package Cards.Cards;

public abstract class Card {
    public final String name;

    public abstract boolean onPlay();

    @Override
    public String toString() {
        return name;
    }

    public Card(String name) {
        this.name = name;
    }

}
