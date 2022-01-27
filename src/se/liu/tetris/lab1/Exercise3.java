package se.liu.tetris.lab1;

public class Exercise3
{
    private final static int TABELL = 6;
    public static void main(String[] args) {
        for(int i = 1; i < 13; i++) {
            int multipliedNumber = TABELL * i;
	    System.out.println(i + " * "+TABELL + " = " + multipliedNumber);
	}
    }

}
