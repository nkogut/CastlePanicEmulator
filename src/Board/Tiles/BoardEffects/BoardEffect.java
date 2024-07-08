package Board.Tiles.BoardEffects;

import Board.Tiles.Tile;

/**
 * Tile Ex. rotate all clockwise, move all forward
 */
public abstract class BoardEffect extends Tile {
    public abstract void onDraw();
    public String name;
}
