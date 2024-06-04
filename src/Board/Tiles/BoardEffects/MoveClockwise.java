package Board.Tiles.BoardEffects;

public class MoveClockwise extends BoardEffect {
    int direction;

    public void onDraw() {
//        If direction == 1: move clockwise
//        If direction == -1: move counterclockwise

    }

    /**
     * Set the direction to move all monsters
     * @param direction Which direction: 1 = clockwise, -1 = counterclockwise
     */
    public MoveClockwise(int direction) {
        this.direction = direction;
    }
}
