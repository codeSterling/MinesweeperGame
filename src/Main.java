import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Game game;
        do {
            Board gameBoard = new Board();
            game = new Game(gameBoard);
            System.out.println("Welcome to Minesweeper!");
            game.start();
        } while (game.playAgain());

    }
}
