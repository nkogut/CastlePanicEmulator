package Board.Tiles.Monsters;

public abstract class Boss extends Monster {

    public abstract void onEnter();

    public Boss(int maxHealth, String name) {
        super(maxHealth, name);
    }
}
