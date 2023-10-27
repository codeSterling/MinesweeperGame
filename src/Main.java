import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Game game;
        Player player = new Player();
        do {
            Board gameBoard = new Board();
            game = new Game(gameBoard);
            System.out.println("Welcome " + player.getName() + " to Minesweeper!\uD83D\uDCA5");
            game.start();
        } while (game.playAgain());

    }
}
