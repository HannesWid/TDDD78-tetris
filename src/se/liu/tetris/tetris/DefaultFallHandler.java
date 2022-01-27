package se.liu.tetris.tetris;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
/**
 * This Fallhandler handles the tetris moves as the original game.
 * DefaultFallhandler can't push down or move through other tetris blocks.
 */
public class DefaultFallHandler extends AbstractFallHandler
{
    private List<Point> currentlyDisplayed;

    public DefaultFallHandler() {
	this.currentlyDisplayed = new ArrayList<>();
    }

    @Override public boolean hasCollision(final Board board, boolean fallingDown) {
	boolean collision = false;
	int height = board.getHeight();
	int doubleMargin = Board.getDoubleMargin();
	int width = board.getWidth();
	int fallingY = board.getFallingY();
	int fallingX = board.getFallingX();
	Poly falling = board.getFalling();
	for (int y = 0; y < height + doubleMargin; y++) {
	    for (int x = 0; x < width + doubleMargin; x++) {
		if (pointOnPoly(falling, y, x, fallingY, fallingX) &&
		    onPolyNotEmpty(falling, y, x, fallingY, fallingX) &&
		    board.getSquareTypeAt(y, x) != SquareType.EMPTY) {
		    collision = true;
		    break;
		}
	    }
	}
	return collision;
    }

    @Override public void handleFall(final Board board) {
	int height = board.getHeight();
	int margin = Board.getMARGIN();
	int width = board.getWidth();
	int fallingY = board.getFallingY();
	int fallingX = board.getFallingX();
	Poly falling = board.getFalling();
	currentlyDisplayed.clear();
	for (int y = margin; y < height + margin; y++) {
	    for (int x = margin; x < width + margin; x++) {
		if (pointOnPoly(falling, y, x, fallingY, fallingX) &&
		    onPolyNotEmpty(falling, y, x, fallingY, fallingX) &&
		    (board.getSquareTypeAt(y,x) == SquareType.EMPTY)) {
		    board.setSquareTypeAt(falling.getSquareAt(y - fallingY, x - fallingX), y, x);
		    currentlyDisplayed.add(new Point(x, y));
		}
	    }
	}
    }
    public void removeFalling(Board board){
	int height = board.getHeight();
	int margin = Board.getMARGIN();
	int width = board.getWidth();
	int fallingY = board.getFallingY();
	int fallingX = board.getFallingX();
	Poly falling = board.getFalling();
	for (int y = margin; y < height + margin; y++) {
	    for (int x = margin; x < width + margin; x++) {
		if (pointOnPoly(falling, y, x, fallingY, fallingX) &&
		    onPolyNotEmpty(falling, y, x, fallingY, fallingX) &&
		    currentlyDisplayed.contains(new Point(x,y))){
		    board.setSquareTypeAt(SquareType.EMPTY, y, x);
		}
	    }
	}
    }
}

