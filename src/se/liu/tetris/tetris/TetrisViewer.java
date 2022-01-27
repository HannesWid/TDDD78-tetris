package se.liu.tetris.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.TimerTask;

public class TetrisViewer
{
    private JFrame frame;
    private Board tetrisBoard;
    private TetrisComponent tetroComponent;
    private JMenuBar menuBar;
    private HighscoreList highscoreList;
    private Timer clockTimer;


    public TetrisViewer(Board board) {
        frame = new JFrame();
        clockTimer = null;
        this.tetrisBoard = board;
        highscoreList = new HighscoreList();
        tetroComponent = new TetrisComponent(tetrisBoard);
        board.addBoardListener(tetroComponent);
        menuBar = new JMenuBar();
        addQuitOption();
    }
    private void addQuitOption(){
        final JMenuItem quit = new JMenuItem("Quit");
        menuBar.add(quit);
        quit.addActionListener(e -> {
            int quit1 = JOptionPane.showConfirmDialog(null, "Do you really want to quit", "Select an option",
                                                      JOptionPane.YES_NO_OPTION);
            if(quit1 == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });
    }

    public void show(){
        frame.setJMenuBar(menuBar);
        frame.setLayout(new BorderLayout());
        frame.add(tetroComponent, BorderLayout.CENTER);
        frame.setSize(tetroComponent.getPreferredSize());
        frame.setVisible(true);
    }

    public void timer(){
        final Action doOneStep = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                final int speedTick = 150;
                tetrisBoard.tick();
                tetroComponent.repaint();
                if(clockTimer.getDelay() > speedTick) {
                    clockTimer.setDelay(clockTimer.getDelay() - 1);
                }
                if(tetrisBoard.getGameOver()){
                    newBoard();
                }
            }
        };

        clockTimer = new Timer(500, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();
    }


    public void moveAction() {
        frame.addKeyListener(new GameKeyListener());
    }

    public static void main(String[] args) {
        Board gameBoard = new Board(10,15);
        TetrisViewer gameWindow = new TetrisViewer(gameBoard);
        IconPanel startImage = new IconPanel();
        startImage.showIconPanel();
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask()
        {
            @Override public void run() {
                gameWindow.show();
                gameWindow.moveAction();
                gameWindow.timer();
            }
        }, startImage.getTimeDisplayed());
    }

    private void newBoard(){
        String username = JOptionPane.showInputDialog("What is your name?");
        if(username == null) {
            username = "Anonymous player";
        }
        Highscore userScore = new Highscore(username, tetrisBoard.getPlayerScore());
        try {
            highscoreList.addScore(userScore);
        }
        catch(IOException e){
            e.printStackTrace();
            int couldNotSaveFileValue = JOptionPane.showConfirmDialog(null,"Could not save score file, try again?" +
                                                                           "Otherwise saves only temporary ",
                                                            "Could not save file", JOptionPane.YES_NO_OPTION);
            if(couldNotSaveFileValue == 0){
                trySaveHighscoreAgain();
            }
        }
        showHighScoreList();
        frame.dispose();
        frame = new JFrame();
        tetrisBoard = new Board(10,15);
        tetroComponent = new TetrisComponent(tetrisBoard);
        clockTimer.setDelay(500);
        tetrisBoard.addBoardListener(tetroComponent);
        menuBar = new JMenuBar();
        addQuitOption();
        show();
        moveAction();
    }

    private void trySaveHighscoreAgain(){
        try {
            highscoreList.saveJsonFile();
        }
        catch(IOException e){
            e.printStackTrace();
            int couldNotSaveFile = JOptionPane.showConfirmDialog(null,"Could not save file again, try again? Otherwise saves only locally",
                                                            "Could not save file", JOptionPane.YES_NO_OPTION);
            if(couldNotSaveFile == 0){
                trySaveHighscoreAgain();
            }
        }
    }

    private void showHighScoreList(){
        StringBuilder highscoreListText = new StringBuilder();
        highscoreListText.append("Highscorelist: ");
        for(int i = 0; i < highscoreList.getLength(); i++) {
            int score = highscoreList.getHighScore(i).score;
            String name = highscoreList.getHighScore(i).name;
            highscoreListText.append("\n");
            highscoreListText.append(i + 1).append(". ");
            highscoreListText.append(name).append(": ");
            highscoreListText.append(score);
        }
        JOptionPane.showMessageDialog(null, highscoreListText.toString());
    }

    private class GameKeyListener extends KeyAdapter
    {
        @Override public void keyPressed(final KeyEvent e) {
            int keyInt = e.getKeyCode();
            switch (keyInt) {
                case KeyEvent.VK_LEFT:
                    if(clockTimer.isRunning()) {
                        tetrisBoard.move(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(clockTimer.isRunning()) {
                        tetrisBoard.move(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(clockTimer.isRunning()) {
                        tetrisBoard.rotate(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(clockTimer.isRunning()) {
                        tetrisBoard.rotate(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    if(clockTimer.isRunning()){
                        clockTimer.stop();
                    }
                    else{
                        clockTimer.start();
                    }
                    break;
            }
        }

    }
}