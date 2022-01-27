package se.liu.tetris.tetris;
/**
 * Fallhandlers handles the moves of the tetris block in the game
 */
public interface FallHandler
{
    public boolean hasCollision(Board board, boolean fallingDown);

    public void handleFall(Board board);

    public void removeFalling(Board board);
}
