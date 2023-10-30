import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game;
        Player player = new Player();

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
            } while (!validInput);

            game = new Game(boardSize, player);

            System.out.println("Välj svårighetsgrad (1-3):");
            int difficultyChoice = scanner.nextInt();

            switch (difficultyChoice) {
                case 1:
                    // Sätt svårighetsgrad till enkelt
                    game.setDifficulty(0.1);  // 10% av rutorna har minor
                    break;
                case 2:
                    // Sätt svårighetsgrad till medel
                    game.setDifficulty(0.2);  // 20% av rutorna har minor
                    break;
                case 3:
                    // Sätt svårighetsgrad till svårt
                    game.setDifficulty(0.3);  // 30% av rutorna har minor
                    break;
                default:
                    System.out.println("Ogiltigt val. Sätter svårighetsgrad till enkelt.");
                    game.setDifficulty(0.1);  // 10% av rutorna har minor
                    break;
            }


            System.out.println("Welcome " + player.getName() + ", to Minesweeper!\uD83D\uDCA5");
            game.start();

        } while (game.playAgain());

    }
}



