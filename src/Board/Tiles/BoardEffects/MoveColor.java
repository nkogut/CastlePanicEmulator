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
        switch (color) {
            case 'r':
                name = "All red monsters move forward";
                break;
            case 'b':
                name = "All blue monsters move forward";
                break;
            case 'g':
                name = "All green monsters move forward";
                break;
        }
    }

}
