package Board.Tiles.BoardEffects;

public class Plague extends BoardEffect {
    int ring;
    String type;

    public void onDraw() {

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
