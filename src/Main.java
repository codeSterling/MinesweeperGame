import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game;
        Player player = new Player();

        do {
            do {
                System.out.println("Välj storlek på spelplanen (4-25):");
                if (!scanner.hasNextInt()) {
                    System.out.println("Det var inte ett heltal!🤪 Försök igen:");
                    scanner.next();
                }
            } while (!scanner.hasNextInt());

            int boardSize = scanner.nextInt();
            int boardSize = scanner.nextInt();
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



