package se.liu.tetris.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class TetrisComponent extends JComponent implements BoardListener
{
    private int squareSize;
    private int visibleBorder;
    private final int scoreStringPosX;
    private final int scoreStringPosY;
    private Board tetrisBoard;

    public TetrisComponent(final Board tetrisBoard) {
	this.tetrisBoard = tetrisBoard;
	this.visibleBorder = 1;
	this.squareSize = 25;
        this.scoreStringPosX = 230;
        this.scoreStringPosY = 16;
    }
    public Dimension getPreferredSize(){
        return new Dimension(314,486);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        for(int y =  - visibleBorder; y < tetrisBoard.getHeight() + visibleBorder; y++){
            for( int x = - visibleBorder; x < tetrisBoard.getWidth() + visibleBorder; x++) {
                SquareType currentSquare = tetrisBoard.getSquareType(y, x);
                Color squareColor = squareColors.get(currentSquare);
                g2d.setColor(squareColors.get(currentSquare));
                g2d.fillRect((x  + visibleBorder) * squareSize, (y + visibleBorder) * squareSize, squareSize, squareSize);
                g2d.setColor(Color.BLACK);
                g2d.drawRect((x  + visibleBorder) * squareSize, (y + visibleBorder) * squareSize, squareSize, squareSize);
            }
        }
        g2d.setColor(Color.WHITE);
        String scoreString = "Score :" + tetrisBoard.getPlayerScore();
        g2d.drawString(scoreString, scoreStringPosX, scoreStringPosY);
    }

    private final EnumMap<SquareType, Color> squareColors = createColorMap();

    private static EnumMap<SquareType, Color> createColorMap() {
        EnumMap<SquareType, Color> colorMap = new EnumMap<>(SquareType.class);
        colorMap.put(SquareType.Z, Color.YELLOW);
        colorMap.put(SquareType.O, Color.BLUE);
        colorMap.put(SquareType.T, Color.green);
        colorMap.put(SquareType.L, Color.RED);
        colorMap.put(SquareType.J, Color.MAGENTA);
        colorMap.put(SquareType.S, Color.ORANGE);
        colorMap.put(SquareType.I, Color.CYAN);
        colorMap.put(SquareType.OUTSIDE, Color.GRAY);
        colorMap.put(SquareType.EMPTY, Color.BLACK);
        return colorMap;
    }

    @Override public void boardChanged() {
        repaint();
    }
}
