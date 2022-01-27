package se.liu.tetris.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;


public class IconPanel
{
    public JPanel panel;
    public Dimension dimension;
    private JFrame frame;
    private Timer clockTimer = null;
    private final int timeDisplayed;

    public int getTimeDisplayed(){
        return timeDisplayed;
    }

    public IconPanel() {
        this.timeDisplayed = 3000;
        this.panel = new JPanel();
        this.frame = new JFrame();
        URL image = ClassLoader.getSystemResource("images/TetrisStartupScreen.png");
        ImageIcon icon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(icon);
        panel.add(imageLabel);
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        this.dimension = new Dimension(width, height);
    }

    public void showIconPanel(){
        initTimer();
        show();
    }

    private void initTimer(){
        final Action doOneStep = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                clockTimer.stop();
                frame.remove(panel);
                frame.setVisible(false);
            }
        };
        this.clockTimer = new Timer(timeDisplayed, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.start();
    }

    private void show(){
        panel.setPreferredSize(dimension);
        frame.setSize(dimension);
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        IconPanel startImage = new IconPanel();
    }
}
