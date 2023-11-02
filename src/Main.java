import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to \uD83D\uDCA5 Minesweeper \uD83D\uDCA5");
        System.out.println();
        Player player = new Player();
        Game game = new Game(player);
        game.printMenu();

    }


}



