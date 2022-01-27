package se.liu.tetris.tetris;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board
{
    private SquareType[][] squares;
    private int width;
    private int height;
    private Poly falling;
    private int fallingY;
    private int fallingX;
    private List<BoardListener> boardListeners;
    private TetrominoMaker tetroMaker;
    private final static int MARGIN = 2;
    private final static int DOUBLE_MARGIN = 2 * MARGIN;
    private boolean gameOver;
    private Map<Integer, Integer> scorePoints;
    private int playerScore;
    private FallHandler fallHandler;
    private final Random random = new Random();

    public boolean getGameOver(){
        return gameOver;
    }

    public int getPlayerScore(){
        return playerScore;
    }

    public Poly getFalling() {
	return falling;
    }

    public int getFallingY() {
	return fallingY;
    }

    public int getFallingX() {
	return fallingX;
    }

    public SquareType getFallingSquareType(int x, int y){
        return falling.getSquareAt(x, y);
    }

    public static int getMARGIN() {
	return MARGIN;
    }

    public static int getDoubleMargin() {
	return DOUBLE_MARGIN;
    }

    public void addBoardListener(BoardListener bl){
        boardListeners.add(bl);
    }

    private void notifyListeners(){
        for(BoardListener bl: boardListeners){
            bl.boardChanged();
	}
    }

    public Board(final int width, final int height) {
        this.playerScore = 0;
	this.width = width;
	this.height = height;
	this.squares = new SquareType[height + DOUBLE_MARGIN][width + DOUBLE_MARGIN];
	this.tetroMaker = new TetrominoMaker();
	this.falling = null;
	this.fallingY = 0;
	this.fallingX = 0;
	this.boardListeners = new ArrayList<>();
	this.gameOver = false;
	this.scorePoints = createScoreMap();
	this.fallHandler = new DefaultFallHandler();
	createBoard();
    }

    private Map<Integer, Integer> createScoreMap(){
	final int oneRow = 1;
	final int twoRows = 2;
	final int threeRows = 3;
	final int fourRows = 4;
	final int scoreOneRow = 100;
	final int scoreTwoRows = 300;
	final int scoreThreeRows = 500;
	final int scoreFourRows = 800;
	return Map.of(oneRow, scoreOneRow, twoRows,scoreTwoRows,
		      threeRows,scoreThreeRows,fourRows,scoreFourRows);
    }

    public static void main(String[] args) {
        Board myBoard = new Board(5,8);
    }

    public int getWidth() {
	return width;
    }
    public int getHeight() {
        return height;
    }

    public SquareType getSquareType(int y, int x) {
        return squares[y + MARGIN][x + MARGIN];
    }

    public SquareType getSquareTypeAt(int y, int x) {
	return squares[y][x];
    }

    public void setSquareTypeAt(SquareType square, int y, int x){
        squares[y][x] = square;
    }

    public void tick() {
        if(!gameOver) {
	    if (falling != null) {
		fallHandler.removeFalling(this);
		fallingY += 1;
		if (fallHandler.hasCollision(this, true)) {
		    fallingY -= 1;
		    fallHandler.handleFall(this);
		    falling = null;
		} else {
		    fallHandler.handleFall(this);
		}
	    } else {
		removeFullRow();
		potentialPowerup();
		final int numberOfPolys = 7;
		int randomInt = random.nextInt(numberOfPolys);
		falling = tetroMaker.getPoly(randomInt);
		fallingY = MARGIN;
		fallingX = (width + DOUBLE_MARGIN - 1) / 2;
		if (fallHandler.hasCollision(this, true)) {
		    gameOver = true;
		    falling = null;
		}
		else{
		    fallHandler.handleFall(this);
		}
	    }
	}
    }

    public void removeFullRow(){
        int rowsRemoved = 0;
        for(int y = MARGIN; y < height + MARGIN; y++){
            boolean fullRow = true;
            for(int x = MARGIN; x < width + MARGIN; x++) {
                if(squares[y][x] == SquareType.EMPTY) {
                    fullRow = false;
                    break;
		}
	    }
            if(fullRow){
                removeRow(y);
                rowsRemoved +=1;
	    }
	}
        if(rowsRemoved > 0){
            playerScore += scorePoints.get(rowsRemoved);
	}
    }

    private void removeRow(int rowIndex){
        for(int y = rowIndex; y > MARGIN; y --) {
	    for (int x = MARGIN; x < width + MARGIN; x++) {
		squares[y][x] = squares[y - 1][x];
	    }
	}
        for(int x = MARGIN; x < width + MARGIN; x++) {
            squares[MARGIN][x] = SquareType.EMPTY;
	}
    }

    public void move(Direction dir) {
	if (falling != null) {
	    if (dir == Direction.LEFT) {
		fallHandler.removeFalling(this);
		fallingX -= 1;
		if (fallHandler.hasCollision(this, false)) {
		    fallingX += 1;
		}
		fallHandler.handleFall(this);
		notifyListeners();
	    }
	    if (dir == Direction.RIGHT) {
		fallHandler.removeFalling(this);
		fallingX += 1;
		if (fallHandler.hasCollision(this, false)) {
		    fallingX -= 1;
		}
		fallHandler.handleFall(this);
		notifyListeners();
	    }
	}
    }

    public void rotate(Direction dir) {
        if(falling != null) {
	    if (dir == Direction.RIGHT) {
		fallHandler.removeFalling(this);
		falling = falling.rotate(true);
		if (fallHandler.hasCollision(this, false)) {
		    falling = falling.rotate(false);
		}
		fallHandler.handleFall(this);
		notifyListeners();
	    }
	    if (dir == Direction.LEFT) {
		fallHandler.removeFalling(this);
		falling = falling.rotate(false);
		if (fallHandler.hasCollision(this, false)) {
		    falling = falling.rotate(true);
		}
		fallHandler.handleFall(this);
		notifyListeners();
	    }
	}
    }

    private void createBoard(){
	for (int y = 0; y < height +DOUBLE_MARGIN; y++) {
	    for (int x = 0; x < width + DOUBLE_MARGIN; x++) {
	        if(y < MARGIN || y >= height + MARGIN || x < MARGIN || x >= width + MARGIN) {
	            squares[y][x] = SquareType.OUTSIDE;
		}
	        else {
	            squares[y][x] = SquareType.EMPTY;
		}
	    }
	}
    }

    public void randomizeBoard(){
        for(int y = MARGIN; y<height+MARGIN; y++){
            for(int x = MARGIN; x< width+MARGIN; x++){
                final int boundInt = 8;
                switch(random.nextInt(boundInt)){
                    case 0:
                        squares[y][x] = SquareType.EMPTY;
			break;
		    case 1:
			squares[y][x] = SquareType.I;
			break;
		    case 2:
			squares[y][x] = SquareType.O;
			break;
		    case 3:
			squares[y][x] = SquareType.T;
			break;
		    case 4:
			squares[y][x] = SquareType.S;
			break;
		    case 5:
			squares[y][x] = SquareType.Z;
			break;
		    case 6:
			squares[y][x] = SquareType.J;
			break;
		    case 7:
			squares[y][x] = SquareType.L;
			break;
		}
	    }
	}
        notifyListeners();
    }

    private void potentialPowerup() {
        int randomInt = random.nextInt(100);
	if(randomInt > 90) {
	    fallHandler = new Heavy();
	    System.out.println("Heavy Powerup");
	}
	else if(randomInt < 10) {
	    fallHandler = new Fallthrough();
	    System.out.println("Fallthrough Powerup");
	}
	else {
	    fallHandler = new DefaultFallHandler();
	}
    }
}
