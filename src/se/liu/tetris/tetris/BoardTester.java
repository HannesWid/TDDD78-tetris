package se.liu.tetris.tetris;

public class BoardTester
{
    public static void main(String[] args) {
	Board testBoard = new Board(10, 8);
	testBoard.randomizeBoard();
	BoardToTextConverter textBoard = new BoardToTextConverter(testBoard);
	System.out.println(textBoard.convertToText());
    }
}
