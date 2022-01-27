package se.liu.tetris.tetris;

import java.util.Random;

public class TetrominoMaker
{
    private final static Random RND = new Random();

    public int getNumberOfTypes() {
	int numberOfTypes = SquareType.values().length - 1;
	return numberOfTypes;
    }
    public int getRandomIntType(){
        return RND.nextInt(SquareType.values().length - 1);
    }

    public Poly getPoly(int n) {
	SquareType[][] tetro;
	switch (n) {
	    case 0: // Tetromino I
	    {
		tetro = new SquareType[4][4];
		fillEmptySquares(tetro);
		tetro[1][0] = SquareType.I;
		tetro[1][1] = SquareType.I;
		tetro[1][2] = SquareType.I;
		tetro[1][3] = SquareType.I;
		Poly tetromino = new Poly(tetro);
		return tetromino;
	    }
	    case 1: // Tetromino O
	    {
		tetro = new SquareType[2][2];
		fillEmptySquares(tetro);
		tetro[0][0] = SquareType.O;
		tetro[0][1] = SquareType.O;
		tetro[1][0] = SquareType.O;
		tetro[1][1] = SquareType.O;
		Poly tetromino = new Poly(tetro);
		return tetromino;
	    }
	    case 2: // Tetromino T
	    {
		tetro = new SquareType[3][3];
		fillEmptySquares(tetro);
		tetro[0][1] = SquareType.T;
		tetro[1][0] = SquareType.T;
		tetro[1][1] = SquareType.T;
		tetro[1][2] = SquareType.T;
		Poly tetromino = new Poly(tetro);
		return tetromino;
	    }
	    case 3: // Tetromino S
	    {
		tetro = new SquareType[3][3];
		fillEmptySquares(tetro);
		tetro[0][1] = SquareType.S;
		tetro[0][2] = SquareType.S;
		tetro[1][0] = SquareType.S;
		tetro[1][1] = SquareType.S;
		Poly tetromino = new Poly(tetro);
		return tetromino;
	    }
	    case 4: // Tetromino Z
	    {
		tetro = new SquareType[3][3];
		fillEmptySquares(tetro);
		tetro[0][0] = SquareType.Z;
		tetro[0][1] = SquareType.Z;
		tetro[1][1] = SquareType.Z;
		tetro[1][2] = SquareType.Z;
		Poly tetromino = new Poly(tetro);
		return tetromino;
	    }
	    case 5: // Tetromino J
	    {
		tetro = new SquareType[3][3];
		fillEmptySquares(tetro);
		tetro[0][0] = SquareType.J;
		tetro[1][0] = SquareType.J;
		tetro[1][1] = SquareType.J;
		tetro[1][2] = SquareType.J;
		Poly tetromino = new Poly(tetro);
		return tetromino;
	    }
	    case 6: // Tetromino L
	    {
		tetro = new SquareType[3][3];
		fillEmptySquares(tetro);
		tetro[0][2] = SquareType.L;
		tetro[1][0] = SquareType.L;
		tetro[1][1] = SquareType.L;
		tetro[1][2] = SquareType.L;
		Poly tetromino = new Poly(tetro);
		return tetromino;
	    }
	    default:
		throw new IllegalArgumentException("Invalid index: " + n);
	}
    }
    public void fillEmptySquares(SquareType[][] tetro){
	for(int i = 0; i< tetro.length;i++){
	    for(int e = 0; e < tetro.length;e++){
	        if(tetro[i][e] ==  null) {
		    tetro[i][e] = SquareType.EMPTY;
		}
	    }
	}
    }
    public static void main(String[] args) {
	TetrominoMaker test = new TetrominoMaker();
	System.out.println(test.getNumberOfTypes());
	System.out.println(test.getRandomIntType());
	Poly testPoly = test.getPoly(1);
	System.out.println(testPoly);
    }
}
