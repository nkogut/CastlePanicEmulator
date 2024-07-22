package Cards.Cards;
import Board.Board;
import Board.Tiles.Monsters.Monster;

public class Attack extends Card {
//    private String name;
    private final char color;
    private int damage;
    private final int[] arcs;
    private final int[] rings;
//    -1 ring for any

    public void setDamage(int num) {
//        Used by the Nice Shot card
        this.damage = num;
    }

    /**
     * Allow the user to select a monster. If it can be targeted by this attack, execute the attack. Otherwise, do nothing
     * @return true if attack was successful, otherwise false
     */
    @Override
    public boolean onPlay() {

        Monster monster =  Board.selectMonster();
        if (monster == null) {
            return false;
        }

        boolean targetableArc = false;
        boolean targetableRing = false;
        int monsterArc = monster.getArc();
        int monsterRing = monster.getRing();

        for (int arc : arcs) {
            if (arc == monsterArc) {
                targetableArc = true;
//                System.out.println("targetable arc");
                break;
            }
        }
        for (int ring : rings) {
            if (ring == monsterRing) {
//                System.out.println("targetable ring");
                targetableRing = true;
                break;
            }
        }
        if (targetableArc && targetableRing) {
            monster.modifyHealth(-damage, true);
            return true;
        }
        System.out.println("Illegal target, try again");
        return false;
    }

    /**
     * Instantiate a basic attack card
     * @param name Display name of attack
     * @param color Which color the attack can target. 'a' for all arcs/colors
     * @param rings Which rings the attack can target. -1 for all rings
     * @param damage How much damage the attack does
     */
    public Attack(String name, char color, int[] rings, int damage) {
        super(name);
        this.color = color;
        this.arcs = Board.colorToArc(color);
        this.rings = rings;
        this.damage = damage;
    }

    /**
     * Same as above but using int ring for attacks that only target 1 ring
     */
    public Attack(String name, char color, int ring, int damage) {
        super(name);
        this.color = color;
        this.arcs = Board.colorToArc(color);
        this.rings = new int[]{ring};
        this.damage = damage;
    }
}
