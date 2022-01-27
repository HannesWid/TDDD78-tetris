package se.liu.tetris.tetris;

public class BoardToTextConverter
{
    private Board board;

    public BoardToTextConverter(final Board board) {
	this.board = board;
    }

    public String convertToText()
    {
	StringBuilder builder = new StringBuilder();
	for (int x = 0; x < board.getHeight(); x++) {
	    if(x>0) {
		builder.append("\n");
	    }
	    for (int y = 0; y < board.getWidth(); y++) {
		switch (board.getSquareType(y, x)) {
		    case EMPTY:
			builder.append(" ");
			break;
		    case I:
			builder.append("I");
			break;
		    case J:
			builder.append("J");
			break;
		    case L:
			builder.append("L");
			break;
		    case O:
			builder.append("O");
			break;
		    case S:
			builder.append("S");
			break;
		    case T:
			builder.append("T");
			break;
		    case Z:
			builder.append("Z");
			break;
		    default:
			builder.append("$");
		}
	    }
	}
	String stringBoard = builder.toString();
	return stringBoard;
    }
}
