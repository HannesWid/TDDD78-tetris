package se.liu.tetris.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeTest
{
    public static void main(String[] args) {
	final List<Shape> shapes = new ArrayList<>();
	Shape circle1 = new Circle(2,3, 4, Color.black);
	shapes.add(circle1);
	Shape circle2 = new Circle(6,9,5,Color.cyan);
	shapes.add(circle2);
	Shape circle3 = new Circle(1,8,7, Color.BLACK);
	shapes.add(circle3);
	Shape rectangle1 = new Rectangle(6,8,2,3,Color.cyan);
	shapes.add(rectangle1);
	Shape rectangle2 = new Rectangle(6,8,2,3,Color.cyan);
	shapes.add(rectangle2);
	Shape text1 = new Text(1,1,4,Color.black,"Hejsan");
	shapes.add(text1);

	for (final Shape shape : shapes) {
	    //shape.draw();
	}
    }
}
