package se.liu.tetris.lab1;

import javax.swing.*;

public class Exercise4
{
    public static void main(String[] args) {
	String input = JOptionPane.showInputDialog("Please input a value");
	int tabell = Integer.parseInt(input);
	for(int i = 1; i < 13; i++) {
	    int multipliedNumber = tabell * i;
	    System.out.println(i + " * "+ tabell + " = " + multipliedNumber);
	}
    }

}
