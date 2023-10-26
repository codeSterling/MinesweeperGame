import java.util.Scanner;

public class Player {
    private String name;

    public Player() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter your username: ");

        this.name = myObj.nextLine();
    }

    public String getName() {
        return name;
    }
}
