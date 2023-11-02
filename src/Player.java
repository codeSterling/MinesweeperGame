import java.util.Scanner;

public class Player {
    //private which stores name and winnings, can only be changed in the player class
    private String name;
    private int wins;

    private boolean firstGame=true;


    //the constructor for the Player class
    public Player() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Please enter your username: ");

        //reads in what is written and stores in the name field
        this.name = myObj.nextLine();
    }

    //Returns the player's name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFirstGame() {
        return firstGame;
    }

    public void setFirstGame(boolean firstGame) {
        this.firstGame = firstGame;
    }

    //method that increases the number of wins by 1.
    public void incrementWins() {
        this.wins++;
    }

    public int getWins() {
        return this.wins;
    }

}
