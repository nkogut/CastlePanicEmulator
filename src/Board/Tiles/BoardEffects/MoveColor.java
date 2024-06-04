package Board.Tiles.BoardEffects;

import static Board.Board.colorToArc;
import static Board.Board.moveArcForward;

public class MoveColor extends BoardEffect {
    int[] arcs;

    public void onDraw() {
        for (int arc : arcs) {
            moveArcForward(arc);
        }

    }
    public MoveColor(char color) {
        arcs = colorToArc(color);
    }

}
