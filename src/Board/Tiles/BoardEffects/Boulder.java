package Board.Tiles.BoardEffects;

import java.util.ArrayList;
import java.util.Random;
import Board.Board;
import Board.Tiles.Monsters.Monster;

public class Boulder extends BoardEffect {
    public void onDraw() {
        Random rand = new Random();
        int arc = rand.nextInt(6);

        ArrayList<Monster> monsters;
        for (int i = 4; i >= 0; i--) {
            if (i <= 1 && Board.damageStructure(arc, i)) {
                return;
            }
            monsters = Board.getMonstersAtPosition(arc, i);
            for (int j=0; j<monsters.size(); j++) {
                monsters.get(j).modifyHealth(-10, false);
            }

        }
//        The boulder goes through the other side
        arc = (arc + 3) % 6;
        for (int i = 0; i <= 4; i++) {
            if (i <= 1 && Board.damageStructure(arc, i)) {
                return;
            }
            monsters = Board.getMonstersAtPosition(arc, i);
            for (Monster monster : monsters) {
                monster.modifyHealth(-10, false);
            }
        }
    }
    public Boulder() {
    }
}
