package se.liu.tetris.tetris;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * This Fallhandler makes the tetrisblock able to push down rows of blocks without support under.
 */
public class Heavy extends AbstractFallHandler
{
    private List<Point> currentlyDisplayed;

    public Heavy() {
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
		if (pointOnPoly(falling, y, x,fallingY,fallingX) &&
		    onPolyNotEmpty(falling, y, x, fallingY, fallingX) &&
		    board.getSquareTypeAt(y, x) != SquareType.EMPTY) {
		    if (!fallingDown || supportUnder(board, y, x)) {
			collision = true;
			break;
		    }

		}
	    }
	}
	return collision;
    }

    private boolean supportUnder(Board board, int y, int x) {
	while (true) {
	    int currentX = x;
	    int currentY = y + 1;
	    SquareType squareUnder = board.getSquareTypeAt(currentY, currentX);
	    if (squareUnder == SquareType.OUTSIDE) {
		return true;
	    }
	    if (squareUnder == SquareType.EMPTY) {
		return false;
	    } else {
		x = currentX;
		y = currentY;
	    }
	}
    }

    @Override public void handleFall(Board board) {
	int height = board.getHeight();
	int doubleMargin = Board.getDoubleMargin();
	int width = board.getWidth();
	int fallingY = board.getFallingY();
	int fallingX = board.getFallingX();
	Poly falling = board.getFalling();
	for (int y = 0; y < height + doubleMargin; y++) {
	    for (int x = 0; x < width + doubleMargin; x++) {
		if (pointOnPoly(falling, y, x, fallingY, fallingX) &&
		    onPolyNotEmpty(falling, y, x, fallingY, fallingX)) {
		    if(board.getSquareTypeAt(y, x) != SquareType.EMPTY){
		        moveDown(board,y,x);
		    }
		    board.setSquareTypeAt(falling.getSquareAt(y-fallingY, x - fallingX), y, x);
		    currentlyDisplayed.add(new Point(x,y));
		}
	    }
	}
    }

    private void moveDown(Board board, int y, int x) {
        if(board.getSquareTypeAt(y,x) != SquareType.EMPTY){
            int nextY = y + 1 ;
            moveDown(board, nextY, x);
            board.setSquareTypeAt(board.getSquareTypeAt(y,x), nextY, x);
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
		if (pointOnPoly(falling,y,x,fallingY, fallingX) &&
		    onPolyNotEmpty(falling, y, x, fallingY, fallingX) &&
		    currentlyDisplayed.contains(new Point(x,y))){
		    board.setSquareTypeAt(SquareType.EMPTY, y, x);
		}
	    }
	}
	board.removeFullRow();
    }
}
