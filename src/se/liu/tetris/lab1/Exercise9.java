package se.liu.tetris.lab1;

import javax.swing.*;

public class Exercise9
{
    public static double findRoot(double x) {
        double guess = x;
	for(int i = 1; i < 11; i++) {
	    guess -= (guess * guess - x) / (2 * guess);
	}
        return guess;
    }
    public static void main(String[] args) {
	double x = Double.parseDouble(JOptionPane.showInputDialog("Please input a value"));
	System.out.println("Roten ur " + x + " är " + findRoot(x));
    }
}
