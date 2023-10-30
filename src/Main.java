import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Player player = new Player();
        Game game = new Game(player);

        do {
            do {
                System.out.println("Choose size on Gameboard: 3-9:");
                if (!scanner.hasNextInt()) {
                    System.out.println("Number requested \uD83E\uDD13Try again:");
                    scanner.next();
                }
            } while (!scanner.hasNextInt());

            int boardSize = scanner.nextInt();
            game.setBoardSize(boardSize);
            //game = new Game(boardSize, player);

            System.out.println("Choose difficulty: 1.Easy 2.Medium 3.Hard:");
            int difficultyChoice = scanner.nextInt();

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


            System.out.println("Welcome " + player.getName() + ", to Minesweeper!\uD83D\uDCA5");
            game.start();

        } while (game.playAgain());

    }
}



