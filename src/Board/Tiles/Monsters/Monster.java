package Board.Tiles.Monsters;

import Main.Main;
import Board.Board;
import Board.Tiles.Tile;

import java.lang.Math;

public class Monster extends Tile {
    private int ring;
    private int arc;
    private boolean isTarred = false;
    private int health;
    private final int maxHealth;
    private final String name;

    public int getRing() {
        return ring;
    }

    public int getArc() {
        return arc;
    }

    public String getName() {
        return name;
    }

    public void setArc(int newArc) {
        Board.updateMonsterBoardPosition(this, arc, ring, newArc, ring);
        arc = newArc;
    }

    public void setRing(int newRing) {
        Board.updateMonsterBoardPosition(this, arc, ring, arc, newRing);
        ring = newRing;
    }

    /**
     * Damage and heal monster, checking for death condition
     * @param num Amount to modify health by. Negative if damaging
     * @param playerKill if a player killed it, award points
     * @return how many points to award if killed by a player
     */
    public void modifyHealth(int num, boolean playerKill) {
        health += num;
        if (health > maxHealth) {
            health = maxHealth;
        }
        System.out.println("damage =" + num + "health = " + health);
        if (health <= 0) {
            Board.updateMonsterBoardPosition(this, arc, ring, false);
            if (playerKill) {
                if (String.valueOf(this.getClass()).startsWith("Board.Tiles.Monsters.Boss")) {
                    Main.currPlayer.addPoints(4);
                } else {
                    Main.currPlayer.addPoints(maxHealth);
                }
            }
        }
    }

    public void tar() {
        isTarred = true;
    }

    /**
     * Move 1 monster forward/back. Will move clockwise if in center
     * @param numRings number of rings to move, negative moves back
     * @return false if it collides after moving
     */
    public boolean changeRing(int numRings) {
        int movementStep;
        if (numRings > 0) {
           movementStep = 1;
        } else {
            movementStep = -1;
        }
        for (int i = 0; i < Math.abs(numRings); i++) {
            if (isTarred) {
                isTarred = false;
            } else if (ring + movementStep > 4) {
                return true;
            } else if (ring + movementStep > 1) {
                Board.updateMonsterBoardPosition(this, arc, ring, arc, ring + movementStep);
                ring += movementStep;

            } else if (ring + movementStep ==  0) {
                if (Board.getWallsInArc(arc)) {
                    return false;
                } else if (Board.getTowersInArc(arc)) {
                    return false;
                } else {
                    Board.updateMonsterBoardPosition(this, arc, ring, arc, ring + movementStep);
                    ring += movementStep;
                }
            }
//                }
//            } else if (ring + movementStep == 0) {
                else if (ring + movementStep < 0) {
                changeArc(1);
                return true;
            }
                else {
                    Board.updateMonsterBoardPosition(this, arc, ring, arc, ring + movementStep);
                    ring += movementStep;
            }
        }
        return true;
    }

    /**
     * Rotate a monster 1 arc
     * @param direction direction to rotate: 1 for clockwise, -1 for counterclockwise
     */
    public boolean changeArc(int direction) {
        if (isTarred) {
            isTarred = false;
        } else {
            int rotatedArc = Board.rotatedArcPosition(arc, direction);
            if (ring == 0 && Board.getTowersInArc(rotatedArc)) {
//                Board.collide(this, rotatedArc, ring);
                return true;
            }
            else if (ring == 1 && Board.getWallsInArc(rotatedArc)) {
                return true;
            }
            else {
                Board.updateMonsterBoardPosition(this, arc, ring, rotatedArc, ring);
                arc = rotatedArc;
            }
        }
        return false;
    }




    @Override
    public String toString() {
//        return "name: " + name + " ring: " + ring + " arc"  + arc + " health: " + health + "/" + maxHealth;
        return name + " - " + health + " health";
    }

    /**
     * Initialize a new basic monster WITH its position
     * @param maxHealth Maximum AND starting health
     * @param name Name (for display purposes)
     * @param ring Starting ring
     * @param arc Starting arc
     */
    public Monster(int maxHealth, String name, int ring, int arc) {
        this.name = name;
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.ring = ring;
        this.arc = arc;
    }

    /**
     * Initialize a new basic monster WITHOUT its position
     * @see Monster#Monster(int, String, int, int) 
     */
    public Monster(int maxHealth, String name) {
        this.name = name;
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.ring = 4;
    }
}
