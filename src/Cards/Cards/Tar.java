package Cards.Cards;

import Board.Board;
import Board.Tiles.Monsters.Monster;

public class Tar extends Card {
    @Override
    public boolean onPlay() {
        Monster monster = Board.selectMonster();
        if (monster == null) {
            return false;
        }

        monster.tar();
        return true;
    }

    public Tar() {
        super("Tar");
    }
}
