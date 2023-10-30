import java.util.Scanner;

public class Game {
    private Board gameBoard;
    private Player player;
    private int boardSize;
    private double difficulty;
    private boolean gameOver;

    Scanner scanner = new Scanner(System.in);

    public Game(Player player) {
        this.player = player;
        this.gameOver = false;
        gameBoard = new Board();
    }

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
                    if (gameBoard.getGameBoardElement(row, col) == gameBoard.getBOMB_SYMBOL()) {
                        gameBoard.revealCell(row, col);
                        gameOver = true;

                        System.out.println("Game over! You hit a mine.");
                        gameBoard.showBoardWhenLooses();
                    } else {
                        // Visar med revealCell-metoden position med X
                        gameBoard.revealCell(row, col);
                        //Räknar antalet X för vinst
                        int nonMineCells = (gameBoard.getBoardSize() * gameBoard.getBoardSize())
                                - gameBoard.getNumberOfMines();


                        if (gameBoard.numberOfSquaresRevealed() == nonMineCells) {

                            gameOver = true;
                            System.out.println("Congratulations! You win!\uD83C\uDF89\uD83C\uDF89");
                            player.incrementWins();
                            System.out.println("You have won " + player.getWins() + " times!");
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

        gameBoard.printBoard();
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

