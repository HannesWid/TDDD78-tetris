package se.liu.tetris.shapes;

import java.awt.*;

public interface Shape
{
    public void draw(final Graphics g);

    int getX();

    int getY();

    Color getColor();
}
