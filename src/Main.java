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

            // Loopa tills användaren anger en giltig storlek på spelplanen
            while (!validInput) {
                System.out.print("Set boardsize (4-9): ");
                if (scanner.hasNextInt()) {
                    boardSize = scanner.nextInt();
                    if (boardSize >= 4 && boardSize <= 9) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid size. Set boardsize between 4 and 9.");
                    }
                } else {
                    System.out.println("Invalid size. Set boardsize between 4 and 9.");
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
                        // Sätt svårighetsgrad till enkelt
                        game.setDifficulty(0.1);  // 10% av rutorna har minor
                        validDifficulty = true;
                        break;
                    case 2:
                        // Sätt svårighetsgrad till medel
                        game.setDifficulty(0.2);  // 20% av rutorna har minor
                        validDifficulty = true;
                        break;
                    case 3:
                        // Sätt svårighetsgrad till svårt
                        game.setDifficulty(0.3);  // 30% av rutorna har minor
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



