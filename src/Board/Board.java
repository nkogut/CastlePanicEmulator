package Board;

import java.util.ArrayList;
import java.util.Arrays;
import Board.Tiles.Monsters.Monster;
import Main.Main;

public class Board {
    private static int towersRemaining = 6;
    private static boolean[] walls = {true, true, true, true, true, true};
    private static boolean[] reinforcements = new boolean[6];
    private static boolean[] towers = {true, true, true, true, true, true};
    private static ArrayList<Monster> monstersOnBoard = new ArrayList<>();
    private static ArrayList<ArrayList<ArrayList<Monster>>> monsterPositions = new ArrayList<>(6);

    public static boolean getWallsInArc(int arc) {
        return walls[arc];
    }
    public static boolean getReinforcementsInArc(int arc) {
        return reinforcements[arc];
    }
    public static boolean setReinforcements(int arc) {
        if (!getWallsInArc(arc) || getReinforcementsInArc(arc)) return false;
        else {
            reinforcements[arc] = true;
            return true;
        }
    }

    public static boolean setWalls(int arc) {
        if (!getWallsInArc(arc)) {
            walls[arc] = true;
            return true;
        }
        return false;
    }

    public static boolean getTowersInArc(int arc) {
        return towers[arc];
    }
    public static ArrayList<Monster> getMonstersOnBoard(){
        return monstersOnBoard;
    }
    public static int getTowersRemaining() {
        return towersRemaining;
    }

    /**
     * move all monsters in an arc forward
     * @param arc move every monster in this arc
     */
    public static void moveArcForward(int arc) {
        ArrayList<Monster> collidingMonsters = new ArrayList<>();
        for (Monster monster : monstersOnBoard) {
            if (monster.getArc() == arc) {
                if (!monster.changeRing(-1)) {
                    collidingMonsters.add(monster);
                }
            }
        }
        if (!collidingMonsters.isEmpty()) {
            Monster chosenMonster = pickMonsterToCollide(collidingMonsters);
            collide(chosenMonster, arc, collidingMonsters.getFirst().getRing() - 1);
        }
    }

    public static void moveAllForward() {
        for (int i=0; i<6; i++) {
            moveArcForward(i);
        }
    }

    public static void moveAllRadially(int arc, int direction) {
//        TODO
//        Does any boss make this need to apply to 1 arc?
    }

    public static ArrayList<Monster> getMonstersAtPosition(int arc, int ring) {
        return monsterPositions.get(arc).get(ring);
    }

    /**
     * choose a monster to take damage from collision
     * @param arr array list of monsters
     */
    private static Monster pickMonsterToCollide(ArrayList<Monster> arr) {
        int len = arr.size();
        Monster chosenMonster;
        if (len == 1) {
            chosenMonster = arr.getFirst();
        } else {
            System.out.println("pick the index of the monster to take damage");
//            int index = Integer.parseInt(scanner.nextLine());
            int index = Main.scObject.scanInt(5);
            chosenMonster = arr.get(index);
        }
        return chosenMonster;
    }

    /**
     * Checks if a structure exists and damages it.
     * @param arc
     * @param ring
     * @return false if there is no structure to damage, otherwise true
     */
    public static boolean damageStructure(int arc, int ring) {
        if (ring == 0 && towers[arc]) {
            towers[arc] = false;
            modifyTowersRemaining(-1);
        } else if (ring == 1) {
            if (reinforcements[arc]) {
                reinforcements[arc] = false;
            } else {
                walls[arc] = false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Handle collision of a monster once a collision has already been determined to have occurred.
     * Damage the monster and structure
     */
    public static void collide(Monster monster, int arc, int ring) {
        monster.modifyHealth(-1, false);
        damageStructure(arc, ring);
    }

    /**
     * Update a count of towers remaining, end game if none are left
     * @param num how much to change count by (usually -1 or 1)
     */
        private static void modifyTowersRemaining(int num) {
            towersRemaining += num;
    }

    /**
     * tells the next arc in a (counter)clockwise direction
     * @param currentArc The object's current arc #
     * @param direction rotation direction (-1 for counterclockwise, 1 for clockwise)
     * @return # of the arc in the desired position
     */
        public static int rotatedArcPosition(int currentArc, int direction) {
            if (direction == 1) {
                return (currentArc + 1) % 6;
            } else if (currentArc > 0) {
                return currentArc - 1;
            } else {
                return 6;
            }
        }

    public static void displayBoard() {

        int longestStr = 0;
        String currStr;
//        for setting each string to the same length

        String[][] arr = new String[5][6];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = "";
            }
        }

        for (Monster monster : monstersOnBoard) {
            currStr = arr[monster.getRing()][monster.getArc()];
            currStr += (monster.getName());
            arr[monster.getRing()][monster.getArc()] = currStr;
            if (currStr.length() > longestStr) longestStr = currStr.length();
        }
        for (int i = 0; i <6; i++) {
            String obs = "";
            if (towers[i]) obs += "tow ";
            if (walls[i]) obs += "wal ";
            if (obs.length() > longestStr) longestStr = obs.length();
            arr[0][i] = obs;
        }

//        Make all strings the same length
        String s;
        for (int i=0; i < 5; i++) {
            for (int j=0; j < 6; j++) {
                 s = arr[i][j];
                 while (s.length() < longestStr)
                     s += " ";
                 arr[i][j] = s;
                 }
            System.out.println(Arrays.toString(arr[i]));
        }

            System.out.println("------------------------------------------------");
            System.out.println("Arcs: R = (0,1), G = (2,3), B = (4,5), archer = ring 3");
        }

    public static void createMonsterBoard() {
        for (int i = 0; i < 6; i++) {
            ArrayList<ArrayList<Monster>> tempArc = new ArrayList<>(5);
            for (int j = 0; j < 5; j++) {
                ArrayList<Monster> tempRing = new ArrayList<>();
                tempArc.add(tempRing);
            }
            monsterPositions.add(tempArc);
        }
    }


    /**
     * Move existing monster within 3D arraylist for convenience of attack targeting
     * @param monster monster to move on the board
     * @param oldArc Which arc the monster is moving from
     * @param oldRing which ring the monster is moving from
     * @param newArc Which arc to put it in
     * @param newRing Which ring to put it in
     */
    public static void updateMonsterBoardPosition(Monster monster, int oldArc, int oldRing, int newArc, int newRing) {
        monsterPositions.get(oldArc).get(oldRing).remove(monster);
        monsterPositions.get(newArc).get(newRing).add(monster);
    }

    /**
     * @see Board#updateMonsterBoardPosition(Monster, int, int, int, int)
     * Works like the above, but for creating/killing monsters, so it only takes the current positions. Also takes new @param alive
     *
     * @param alive If the monster is alive. If it is not, remove it from the ArrayList
     *
     */
    public static void updateMonsterBoardPosition(Monster monster, int arc, int ring, boolean alive) {
        if (alive) {
            monstersOnBoard.add(monster);
            monsterPositions.get(arc).get(ring).add(monster);
        } else {
            monstersOnBoard.remove(monster);
            monsterPositions.get(arc).get(ring).remove(monster);
        }
    }

    public static int[] colorToArc(char color) {
        int[] arr;

        switch (color) {
            case 'r':
                arr = new int[]{0, 1};
                break;
            case 'g':
                arr = new int[]{2, 3};
                break;
            case 'b' :
                arr = new int[]{4, 5};
                break;
            case 'a':
//                all
                arr = new int[]{0, 1, 2, 3, 4, 5};
                break;
            default:
                arr = new int[0];
        }
        return arr;
    }

    public static char arcToColor(int arc) {
        switch (arc) {
            case 0: case 1:
                return 'r';
            case 2: case 3:
                return 'g';
            default:
                return 'b';
        }
    }

    /**
     * Prompts user to select a monster by position. Used by attacks
     * @return a monster if one can be selected. Otherwise, null (if no monsters in specified position)
     */
    public static Monster selectMonster() {
        System.out.println("Select the monster's arc");
        int arc = Main.scObject.scanInt(5);
        System.out.println("Select the monster's ring");
        int ring = Main.scObject.scanInt(4);

//        System.out.println("targeting arc" + arc + "ring :" + ring);
//        System.out.println(monsterPositions);

        ArrayList<Monster> monsters = monsterPositions.get(arc).get(ring);
        if (monsters.isEmpty()) {
            return null;
        } else if (monsters.size() == 1) {
            return monsters.getFirst();
        } else {
            int i = 1;
            System.out.println("Select one of the following by their number:");
            for (Monster monster : monsters) {
                System.out.println(Integer.toString(i) + monster);
            }
            int response = Main.scObject.scanInt(5);
            return monsters.get(response);
        }
    }

    public static void addNewMonster(Monster monster) {
        updateMonsterBoardPosition(monster, monster.getArc(), monster.getRing(), true);

    }
}
