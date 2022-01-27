package se.liu.tetris.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircleTest
{
    public static void main(String[] args) {
	final List<Circle> circles = new ArrayList<>();
	Circle circle1 = new Circle(2,3, 4, Color.black);
	circles.add(circle1);
	Circle circle2 = new Circle(6,9,5,Color.cyan);
	circles.add(circle2);
	Circle circle3 = new Circle(1,8,7, Color.BLACK);
	circles.add(circle3);
	for (final Circle circle : circles) {
	    StringBuilder circleCords = new StringBuilder();
	    circleCords.append("X: ");
	    circleCords.append(circle.getX());
	    circleCords.append(" Y: ");
	    circleCords.append(circle.getY());
	    System.out.println(circleCords);
	}
    }
}
