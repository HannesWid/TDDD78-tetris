package se.liu.tetris.tetris;

/**
 * This abstract class gathers common functionality between the different fallHandlers
 */
public abstract class AbstractFallHandler implements FallHandler
{
    protected boolean pointOnPoly(Poly falling, int y, int x, int fallingY, int fallingX){
	return (y >= fallingY && y < fallingY + falling.getSize() &&
		x >= fallingX && x < fallingX + falling.getSize());
    }

    protected boolean onPolyNotEmpty(Poly falling, int y, int x, int fallingY, int fallingX){
	return (falling.getSquareAt(y - fallingY, x - fallingX) != SquareType.EMPTY);
    }
}
