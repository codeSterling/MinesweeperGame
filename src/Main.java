import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player player = new Player();
        Game game = new Game(player);

        do {
            int boardSize = 4;
            boolean validInput = false;

            // Loop until the user enters a valid size of the playing field
            while (!validInput) {
                System.out.print("Set board size (4-9): ");
                if (scanner.hasNextInt()) {
                    boardSize = scanner.nextInt();
                    if (boardSize >= 4 && boardSize <= 9) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid size. Set board size between 4 and 9.");
                    }
                } else {
                    System.out.println("Invalid size. Set board size between 4 and 9.");
                    scanner.next();
                }
            }

            game.setBoardSize(boardSize);
            int difficultyChoice = 0;
            boolean validDifficulty = false;
            while (!validDifficulty) {
                try {
                    System.out.println("Choose difficulty: 1.Easy 2.Medium 3.Hard:");
                    difficultyChoice = scanner.nextInt();

                switch (difficultyChoice) {
                    case 1:
                        // Set difficulty to easy
                        game.setDifficulty(0.1);  // 10% of the squares have mines
                        validDifficulty = true;
                        break;
                    case 2:
                        // Set difficulty to medium
                        game.setDifficulty(0.2);  // 20% of the squares have mines
                        validDifficulty = true;
                        break;
                    case 3:
                        // Set difficulty to hard
                        game.setDifficulty(0.3);  // 30% of the squares have mines
                        validDifficulty = true;
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid input. Set a number between 1 and 3");
                scanner.next();
            }
        }
            System.out.println("Welcome " + player.getName() + ", to Minesweeper!\uD83D\uDCA5");
            game.start();

        } while (game.playAgain());
    }
}



