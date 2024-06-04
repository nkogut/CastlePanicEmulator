package Cards;

public class Player {
    private int points;
    private String[] killedMonsters;
    private Hand hand;
    private final String name;

    public void addPoints(int points) {
        this.points += points;
    }
    public int getPoints() {
        return points;
    }
    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return name;
    }

    public Player(String name) {
        this.name = name;
        hand = new Hand();
    }
}
