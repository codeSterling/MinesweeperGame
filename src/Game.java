import java.util.Scanner;

public class Game {
    private Board gameBoard;
    private Player player;
    private int boardSize;
    private double difficulty;
    private boolean gameOver;

    Scanner scanner = new Scanner(System.in);

    public Game(int boardSize, Player player) {
        this.boardSize = boardSize;
        this.player = player;
        this.gameOver = false;
    }
    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }


    public void start() {
        gameOver = false;
        double minePercentage = getMinePercentage();
        gameBoard = new Board(boardSize, minePercentage);
        playGame();
    }

    private double getMinePercentage() {
        return this.difficulty;
    }

        public int playGame() {
        while (!gameOver) {
            gameBoard.printBoard();
            System.out.print("Enter row and column (e.g., 1 2): ");


            try {
                int row = scanner.nextInt();

                int col = scanner.nextInt();
                //Ifall man träffar mina
                if (row >= 1 && row <= gameBoard.getBoardSize()
                        && col >= 1 && col <= gameBoard.getBoardSize()) {
                    row--;
                    col--;
                    if (gameBoard.getGameBoardElement(row, col) == '*') {
                        gameBoard.revealCell(row, col);
                        gameOver = true;

                        System.out.println("Game over! You hit a mine.");
                        gameBoard.showBoardWhenLooses();
                        gameBoard.printBoard();
                    } else {
                        // Visar med revealCell-metoden position med X
                        gameBoard.revealCell(row, col);
                        //Räknar antalet X för vinst
                        int nonMineCells = (gameBoard.getBoardSize() * gameBoard.getBoardSize())
                                - gameBoard.getNumberOfMines();
                        int markedCells = 0;

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

        return 0;
    }

    private int setDifficulty(int difficulty) {
        return 0;
    }

    public boolean playAgain () {
          while (true) {
            System.out.println("Do you want to play again? (Yes/No): ");
            String answer = scanner.next();
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

