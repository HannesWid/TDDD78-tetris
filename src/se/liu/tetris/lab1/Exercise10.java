package se.liu.tetris.lab1;

public class Exercise10
{
    public static void main(String[] args) {
	int number = 16777217;
	double decimal = number;
	int integerAgain = (int)decimal;
	int big = 2147483647;
	long bigger = (long)big+1;
	System.out.println(number);
	System.out.println(decimal);
	System.out.println(integerAgain);
	System.out.println(big);
	System.out.println(bigger);
    }
}
