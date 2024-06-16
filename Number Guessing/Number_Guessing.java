import java.util.Random;
import java.util.Scanner;

public class Number_Guessing{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int rounds = 3; // You can change the number of rounds here
        int maxAttempts = 5; // You can change the max number of attempts per round here

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You have " + rounds + " rounds to play.");
        System.out.println("You will get " + maxAttempts + " attempts to guess the number in each round.");
        
        for (int round = 1; round <= rounds; round++) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + round + " starts!");
            System.out.println("I have chosen a number between 1 and 100. Try to guess it!");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You've guessed the number in " + attempts + " attempts.");
                    int points = maxAttempts - attempts + 1; // Higher points for fewer attempts
                    totalScore += points;
                    System.out.println("You earned " + points + " points.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The number was " + numberToGuess + ".");
            }
        }

        System.out.println("\nGame over! Your total score is: " + totalScore);
        scanner.close();
    }
}
