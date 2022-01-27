package se.liu.tetris.tetris;

public class Poly
{
    private SquareType[][] polyType;
    private int size;

    public int getSize() {
	return size;
    }

    public Poly(final SquareType[][] polyType) {
	this.polyType = polyType;
	this.size = polyType.length;
    }
    public Poly rotate(boolean right) {
        Poly rotatedPoly;
	if(right) {
	    rotatedPoly = rotateRight(this);
	    return rotatedPoly;
	}
	else {
	    rotatedPoly = rotateRight(rotateRight(rotateRight(this)));
	}
	return rotatedPoly;
    }

    private Poly rotateRight(Poly poly) {
	Poly newPoly = new Poly(new SquareType[size][size]);
	for (int r = 0; r < size; r++) {
	    for (int c = 0; c < size; c++) {
		newPoly.polyType[c][size - 1 - r] = poly.polyType[r][c];
	    }
	}
	return newPoly;
    }

    public SquareType[][] getPolyType() {
	return polyType;
    }
    public SquareType getSquareAt(int x, int y){
        return polyType[x][y];
    }
}
