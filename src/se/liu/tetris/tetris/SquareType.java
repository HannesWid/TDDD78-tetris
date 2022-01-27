package se.liu.tetris.tetris;

import java.util.*;

public enum SquareType
{
    EMPTY, I, O, T, S, Z, J, L, OUTSIDE;

    public static void main(String[] args) {
	Random rnd = new Random();
	int i = 1;
        while(i<26){
	    int rndNumber = rnd.nextInt(8);
	    System.out.println(SquareType.values()[rndNumber]);
	    i+=1;
	}
    }
}
