package Cards.Cards;
import Main.Main;

public class Missing extends Card {

    @Override
    public boolean onPlay() {
        return Main.setMissing(true);
    }

    public Missing() {
        super("Missing");
    }
}
