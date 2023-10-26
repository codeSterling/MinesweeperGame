import java.util.Scanner;

public class Player {
    private String name;
    private int wins;

    public Player() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter username");

        this.name = myObj.nextLine();
    }

    public String getName() {
        return name;
    }

    public void incrementWins() {
        this.wins++;
    }
    public int getWins() {
        return this.wins;
    }
}
