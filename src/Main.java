import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game;
        Player player = new Player();

        do {
            System.out.println("Välj storlek på spelplanen:");
            while (!scanner.hasNextInt()) {
                System.out.println("Det var inte ett heltal!\uD83E\uDD13 Försök igen:");
                scanner.next();
            }
                int boardSize = scanner.nextInt();
                Board gameBoard = new Board(boardSize);

                game = new Game(gameBoard, player);
                System.out.println("Welcome " + player.getName()
                        + ", to Minesweeper!\uD83D\uDCA5");

                game.start();
            }
            while (game.playAgain()) ;

        }
    }
