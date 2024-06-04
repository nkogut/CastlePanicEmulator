package Cards.Cards;

import Board.Board;
import Board.Tiles.Monsters.Monster;

public class DriveHimBack extends Card {

    @Override
    public boolean onPlay() {
        Monster monster = Board.selectMonster();
        if (monster == null) {
            return false;
        }
        monster.setRing(4);
        return true;
    }

    public DriveHimBack() {
        super("Drive Him Back");
    }
}
