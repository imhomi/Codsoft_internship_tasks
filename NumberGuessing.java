package package1;

import java.util.Scanner;
import java.util.Random;

public class NumberGuessing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalScore = 0;
        int round = 1;

        System.out.println(" Welcome to 'Guess Mania'! ");
        System.out.println("You're the mighty Guesser, and your job is to guess the super-secret Mystery Number!");
        System.out.println("Let's see if you can outwit the number gods and claim victory!");

        while (true) {
            int mysteryNumber = random.nextInt(100) + 1; 
            int maxAttempts = 7;
            int attempts = 0;
            boolean hasWon = false;

            System.out.println("\n Round " + round + ": The Mystery Number awaits your guess... ");
            
           
            while (attempts < maxAttempts) {
                System.out.print("Take a wild guess, Guesser (1-100): ");
                int guess = scanner.nextInt();
                attempts++;	

                if (guess == mysteryNumber) {
                    System.out.println("BINGO! You've cracked the code, mighty Guesser!");
                    hasWon = true;
                    totalScore += maxAttempts - attempts + 1; 
                    break;
                } else if (guess < mysteryNumber) {
                    System.out.println(" Whoops, too low! You need to aim higher!");
                } else {
                    System.out.println(" Oh no, too high! Cool it down a little.");
                }
            }

            if (!hasWon) {
                System.out.println("❌ Oops! You used up all your guesses. The Mystery Number was: " + mysteryNumber);
            }

            
            System.out.println(" Your score for this round: " + (hasWon ? (maxAttempts - attempts + 1) : 0));
            System.out.println(" Total score so far: " + totalScore);

            
            System.out.print(" Wanna give it another shot, Guesser? (yes/no): ");
            String playAgain = scanner.next();
            if (!playAgain.equalsIgnoreCase("yes")) {
                System.out.println(" Thanks for playing Guess Mania! Your final score is: " + totalScore);
                break;
            }

            round++; 
        }

        scanner.close();
    }
}
