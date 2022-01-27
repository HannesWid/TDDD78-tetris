package se.liu.tetris.shapes;


import java.awt.*;

public class Circle extends AbstractShape
{
    private int radius;

    public Circle(final int x, final int y, final int radius, final Color color) {
	super(x, y, color);
	if (radius < 0) {
	    throw new IllegalArgumentException("Negativ radie!");
	}
	this.radius = radius;
    }

    @Override public String toString() {
	return "Circle{" + "x=" + getX() + ", y=" + getY() + ", radius=" + radius + ", color=" + getColor() + '}';
    }

    @Override public void draw(final Graphics g){
        int width = radius * 2;
        int height = radius * 2;
	g.setColor(color);
	g.drawOval(x, y, width, height); // calc. from radius!
    }

    public int getRadius() {
	return radius;
    }

}
