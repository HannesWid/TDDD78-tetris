package se.liu.tetris.lab1;

public class Exercise6
{
    public static boolean isPrime(int number) {
        boolean isPrimeNumber = true;
        for(int i = 2; i < number; i++) {
            if (number % i == 0) {
                isPrimeNumber = false;
                break;
            }
	}
        return isPrimeNumber;
    }
    public static void allPrimeNumbers() {
        for(int i = 2; i < 101; i++) {
            boolean isPrimeNumber = true;
            for(int e = 2; e < i; e++) {
                if (i % e == 0) {
                    isPrimeNumber = false;
                    break;
                }
            }
            if(isPrimeNumber){
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args) {
        boolean isPrimeNum = isPrime(4);
        System.out.println(isPrimeNum);
        allPrimeNumbers();
    }
}
