import java.util.Scanner;

public class Game {
    private Board gameBoard;
    private Player player;
    private int boardSize;
    private double difficulty;
    private boolean gameOver;

    Scanner scanner = new Scanner(System.in);


    //Constructor

    public Game(Player player) {
        this.player = player;
        this.gameOver = false;
        gameBoard = new Board();
    }

    //a setter method. Sets the difficulty level of the game

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }


    private double getMinePercentage() {
        return this.difficulty;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }


    public void start() {
        gameOver = false;
        //Calculates percentage of mines in relation to the size of the game.
        double minePercentage = getMinePercentage();
        gameBoard.resetGameBoard(boardSize, minePercentage);
        playGame();
    }


    public void playGame() {

        while (!gameOver) {
            gameBoard.printBoard();
            System.out.print("Enter row and column (e.g., 1 2): ");


            try {
                int row = scanner.nextInt();

                int col = scanner.nextInt();
                scanner.nextLine();
                //Ifall man träffar mina
                if (row >= 1 && row <= gameBoard.getBoardSize()
                        && col >= 1 && col <= gameBoard.getBoardSize()) {
                    row--;
                    col--;
                    if (gameBoard.getGameBoard(row, col).isHasBomb()) {
                        gameBoard.revealCell(row, col);
                        gameOver = true;
                        gameBoard.showBoardWhenLooses();
                        gameBoard.printBoard();
                        System.out.println("Game over! You hit a mine.");

                    } else {
                        // Visar med revealCell-metoden position med X
                        gameBoard.revealCell(row, col);
                        //Räknar antalet X för vinst
                        int nonMineCells = (gameBoard.getBoardSize() * gameBoard.getBoardSize())
                                - gameBoard.getNumberOfMines();
                        if (gameBoard.numberOfSquaresRevealed() == nonMineCells) {
                            gameOver = true;
                            player.incrementWins();
                            gameBoard.printBoard();
                            System.out.println("Congratulations! You win!\uD83C\uDF89\uD83C\uDF89");
                            System.out.println("You have won " + player.getWins() + " times!");
                            System.out.println();

                        }


                    }

                } else {
                    System.out.println("Invalid input. " +
                            "Please enter row and column within the valid range.");

                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. " +
                        "Please enter row and column as integers, e.g., 1 2.");
                scanner.nextLine();

            }
        }
        System.out.println("Thank you for playing!");

    }


    public boolean playAgain() {
        while (true) {
            System.out.println("Do you want to play again? (Yes/No): ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Yes")) {
                return true;
            } else if (answer.equalsIgnoreCase("No")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Yes' or 'No'. ");
            }
        }
    }


}

