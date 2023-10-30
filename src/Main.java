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
                System.out.print("Välj storlek på spelplanen (4-9): ");
                if (scanner.hasNextInt()) {
                    boardSize = scanner.nextInt();
                    if (boardSize >= 4 && boardSize <= 9) {
                        validInput = true;
                    } else {
                        System.out.println("Ogiltig storlek. Ange en storlek mellan 4 och 9.");
                    }
                } else {
                    System.out.println("Ogiltig inmatning. Ange en siffra mellan 4 och 9.");

                    scanner.next();
                }
            }



            game.setBoardSize(boardSize);
            

            System.out.println("Choose difficulty: 1.Easy 2.Medium 3.Hard:");
            int difficultyChoice = 0;
            boolean validDifficulty = false;
            difficultyChoice = scanner.nextInt();

            switch (difficultyChoice) {
                case 1:
                    // Sets difficulty to easy
                    game.setDifficulty(0.1);  // 10% of the squares have mines
                    break;
                case 2:
                    // Sets difficulty to medium
                    game.setDifficulty(0.2);  // 20% of the squares have mines
                    break;
                case 3:
                    // Sets difficulty to hard
                    game.setDifficulty(0.3);  // 30% of the squares have mines
                    break;
                default:
                    System.out.println("Invalid choice. Sets difficulty to easy.");
                    game.setDifficulty(0.1);  // 10% of the squares have mines
                    break;
            }


            while (!validDifficulty) {
                try {
                    System.out.println("Välj svårighetsgrad (1-3):");
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
                        System.out.println("Ogiltigt val. Försök igen.");
                }
            } catch(InputMismatchException e) {
                System.out.println("Ogiltig inmatning. Ange en siffra mellan 1 och 3.");
                scanner.next();
            }
        }
            System.out.println("Welcome " + player.getName() + ", to Minesweeper!\uD83D\uDCA5");
            game.start();

        } while (game.playAgain());
    }
}



