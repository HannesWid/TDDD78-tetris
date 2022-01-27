package se.liu.tetris.lab1;

import javax.swing.*;

public class Exercise2
{
    public static int sumFor(int min, int max)
    {
        int returnValue = 0;
        for(int i = min; i < max+1; i++) {
            returnValue += i;
        }
        return returnValue;
    }
    public static int sumWhile(int min, int max) {
        int returnValue = 0;
        while(min < max+1) {
            returnValue += min;
            min += 1;
        }
        return returnValue;
    }
    public static void main(String[] args)
    {
        final int min = 10;
        final int max = 13;
        String whileOrFor = JOptionPane.showInputDialog("Please type in 'while' or 'for'");
        switch(whileOrFor) {
            case "while":
                System.out.println(sumWhile(min, max));
                break;
            case "for":
                System.out.println(sumFor(min, max));
                break;
            default:
                System.out.println("Wrong input");
                break;
        }
    }
}
