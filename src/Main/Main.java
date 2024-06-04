package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Board.Board;
import Board.Tiles.Tile;
import Board.Tiles.BoardEffects.*;
import Board.Tiles.Monsters.*;
import Cards.DrawPile;
import Cards.Hand;
import Cards.Player;

public class Main {
    private static int numPlayers;
    private static int maxTrades;
    public static Player currPlayer;
    public static boolean missing = false;
    private static String[] playerNames;
    private static Player[] playersArray;
    private static ArrayList<Tile> tileBag = new ArrayList<>(43);
    public static ScannerClass scObject;
    public static int getNumPlayers() {
        return numPlayers;
    }
    public static String[] getPlayerNames() {
        return playerNames;
    }
    public static Player getPlayerByName(String name) {
        for (int i = 0; i < numPlayers; i++) {
            if (playerNames[i].equalsIgnoreCase(name)) {
                return playersArray[i];
            }
        }
        return null;
    }

    public static boolean setMissing(boolean state) {
        if (missing) return false;
        else {
            missing = state;
            return true;
        }
    }

    /**
     * Creates the initial contents of the tile bag with preset tiles. The starting 6 tiles are not included
     */
    private static void createTileBag() {
        tileBag.add(new DrawMoreMonsters(3));
        tileBag.add(new DrawMoreMonsters(4));
        tileBag.add(new MoveClockwise(1));
        tileBag.add(new MoveClockwise(-1));
        tileBag.add(new Plague("Archers"));
        tileBag.add(new Plague("Knights"));
        tileBag.add(new Plague("Swordsman"));
        tileBag.add(new AllPlayersDiscard());

        tileBag.add(new Healer());
        tileBag.add(new GoblinKing());
        tileBag.add(new TrollMage());
        tileBag.add(new OrcWarlord());

        for (int i = 0; i < 2; i++) {
            tileBag.add(new MoveColor('r'));
            tileBag.add(new MoveColor('g'));
            tileBag.add(new MoveColor('b'));
        }
        for (int i = 0; i < 3; i++) {
            tileBag.add(new Monster(1, "Goblin"));
        }
        for (int i = 0; i < 4; i++) {
            tileBag.add(new Boulder());
        }
        for (int i = 0; i < 9; i++) {
            tileBag.add(new Monster(2, "Orc"));
            tileBag.add(new Monster(3, "Troll"));
        }
        Collections.shuffle(tileBag);
    }

    /**
     * Draw and remove 1+ tiles from the bag and play them
     * @param numTimes The number of tiles to be removed 1 by 1
     */
    public static void pullTile(int numTimes) {

        for (int i = 0; i < numTimes; i++) {
            if (tileBag.isEmpty()) {
                return;
            }
            Tile pulledTile = tileBag.get(0);
            String tileType = String.valueOf(pulledTile.getClass());

            if (tileType.startsWith("class Board.Tiles.Monsters.")) {
                Random rand = new Random();
                int arc = rand.nextInt(6);
                System.out.println(pulledTile + " - placed in arc " + arc);

                Monster monster = (Monster) tileBag.remove(0);
                monster.setArc(arc);
                Board.addNewMonster(monster);
            } else {
                BoardEffect boardEffect = (BoardEffect) tileBag.remove(0);
                System.out.println("drew " + boardEffect.getClass().getSimpleName());
                boardEffect.onDraw();
            }
        }
        }

    /**
     * Places 6 preset starting monsters on the board
     */
    private static void setStartingMonsters() {
//        NOTE: The player should be able to choose where each is placed
            Monster[] startingMonsters = new Monster[]{
                    new Monster(1, "Goblin", 3, 0),
                    new Monster(1, "Goblin", 3, 2),
                    new Monster(1, "Goblin", 3, 4),
                    new Monster(2, "Orc", 3, 1),
                    new Monster(2, "Orc", 3, 3),
                    new Monster(3, "Troll", 3, 5)};

            for (Monster monster : startingMonsters) {
                Board.addNewMonster(monster);
            }
        }

    /**
     * Set up initial board, deck, and player states
     */
    private static void setUp() {
        scObject = new ScannerClass();
        createTileBag();
        Board.createMonsterBoard();
        setStartingMonsters();
        DrawPile.createDrawPile();

        System.out.println("Please enter the number of players");
        numPlayers = scObject.scanInt(5);
        if (numPlayers == 2) {
            maxTrades = 2;
        } else {
            maxTrades = 1;
        }

        System.out.println("type the name of each player");
        playersArray = new Player[numPlayers];
        playerNames = new String[numPlayers];

        String name;
        for (int i=0; i < numPlayers; i++) {
            name = scObject.scanStr();
            playerNames[i] = name;
            playersArray[i] = new Player(name);
        }
    }

    /**
     * Find player with most points if all monsters are defeated
     * @return Highest scoring player
     */
    private static Player determineWinner() {
        int currHighScore = 0;
        Player currHighPlayer = null;
        int currPoints;
        for (Player player : playersArray) {
            currPoints = player.getPoints();
            System.out.println(player + " - " + currPoints);
            if (currPoints > currHighScore) {
                currHighScore = currPoints;
                currHighPlayer = player;
            }
        }
        System.out.println(currHighPlayer + "won");
        return currHighPlayer;
    }

    public static void discardToDraw() {
        System.out.println("Discard to draw? 1 yes - 0 no");
        if (scObject.scanInt(1) == 1) {
            currPlayer.getHand().discardCard();
            currPlayer.getHand().drawCard(1);
        }
    }

    /**
     * @see Hand#trade() ;
     */
    public static void trade() {
            int completedTrades = 0;

            for (Player player : playersArray) {
                System.out.println(player);
                System.out.println(player.getHand());
            }

            while (completedTrades < maxTrades) {
                System.out.println("Trade? 1 yes - 0 no");
                if (scObject.scanInt(1) == 1) {
                    currPlayer.getHand().trade();
                    completedTrades++;
                } else {
                    return;
            }
        }
    }

    /**
     * Allow the current player to play cards during the Play Cards phase
     */
    public static void playCards() {
        int response;
        while (true) {
            Board.displayBoard();
            System.out.println("Hand: " + currPlayer.getHand());
            System.out.println("Would you like to play a card? -1 to end phase");
            response = scObject.scanInt(currPlayer.getHand().getNumCards());
            if (response == -1) {
                return;
            } else {
//                attempt to play the card at the given index
                System.out.println("trying to play");
                if (currPlayer.getHand().playCard(response)) {
                    Board.displayBoard();
                    System.out.println("Hand: " + currPlayer.getHand());
                }
            }

        }
    }

    public static void turn() {
        System.out.println(currPlayer + "'s turn");
        currPlayer.getHand().drawToFull();
        System.out.println("Hand: " + currPlayer.getHand());

        discardToDraw();
        trade();
        playCards();

        Board.moveAllForward();
        if (!missing) pullTile(2);
        else {
            setMissing(false);
            System.out.println("Missing used - draw no monsters this turn");
        }
    }

    /**
     * Control current player and call turn() until the game ends
     */
    private static void mainLoop() {

        int currPlayerIndex = 0;

        while (Board.getTowersRemaining() > 0) {
            if (tileBag.isEmpty() && Board.getMonstersOnBoard().isEmpty()) {
                determineWinner();
//                determineWinner can output the winner too
                break;
            }
            currPlayer = playersArray[currPlayerIndex];
            turn();
            for (Player player : playersArray) {
                System.out.println(player + " - " + player.getPoints());
            }
//            Move to next player's turn
            if (currPlayerIndex < numPlayers - 1) {
                currPlayerIndex++;
            } else {
                currPlayerIndex = 0;
            }
        }

    }

    public static void main(String[] Args) {
        setUp();
        mainLoop();
    }
}
