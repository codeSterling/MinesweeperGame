import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Game game;
        Player player = new Player();
        do {
            Board gameBoard = new Board();
            game = new Game(gameBoard, player);
            System.out.println("Welcome " + player.getName() + ", to Minesweeper!");
            game.start();
        } while (game.playAgain());

    }
}
