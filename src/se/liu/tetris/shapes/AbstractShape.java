package se.liu.tetris.shapes;

import java.awt.*;
import java.util.Objects;

public abstract class AbstractShape implements Shape
{

    protected int x;
    protected int y;
    protected Color color;

    protected AbstractShape(final int x, final int y, final Color color) {
	this.x = x;
	this.y = y;
	this.color = color;
    }

    @Override public int getX() {
	return x;
    }

    @Override public int getY() {
	return y;
    }

    @Override public Color getColor() {
	return color;
    }

    @Override public String toString() {
	return "AbstractShape{" + "x=" + x + ", y=" + y + ", color=" + color + '}';
    }

    @Override public boolean equals(final Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}
	final AbstractShape that = (AbstractShape) o;
	return color.equals(that.color);
    }

    @Override public int hashCode() {
	return Objects.hash(color);
    }
}
