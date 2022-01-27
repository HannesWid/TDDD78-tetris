package se.liu.tetris.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OldTetrisViewer
{
    private JFrame frame;
    private JTextArea textArea;
    private Board tetrisBoard;

    public OldTetrisViewer(Board board) {
        frame = new JFrame();
        textArea = new JTextArea();
        tetrisBoard = board;
        String boardString = new BoardToTextConverter(tetrisBoard).convertToText();
        textArea.setText(boardString);
    }


    public void show(){
        frame.setLayout(new BorderLayout());
        frame.add(textArea, BorderLayout.CENTER);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        frame.pack();
        frame.setVisible(true);
    }

    public void timer(){
        final Action doOneStep = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                tetrisBoard.randomizeBoard();
                String boardString = new BoardToTextConverter(tetrisBoard).convertToText();
                textArea.setText(boardString);

            }
        };

        final Timer clockTimer = new Timer(500, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();
    }

    public static void main(String[] args) {
        Board gameBoard = new Board(14,12);
        OldTetrisViewer gameWindow = new OldTetrisViewer(gameBoard);
        gameWindow.timer();
        gameWindow.show();
    }
}
