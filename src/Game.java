import java.util.Scanner;

public class Game {
    private Board gameBoard;
    private boolean gameOver;

    Scanner scanner = new Scanner(System.in);

    public Game(Board board) {
        this.gameBoard = board;
        this.gameOver = false;
    }

    public void start() {
        gameOver = false;
        // Wincondition
        while (!gameOver) {
            gameBoard.printBoard();
            System.out.print("Enter row and column (e.g., 1 2): ");
            try {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                //Ifall man träffar mina
                if (row >= 1 && row <= gameBoard.boardSize && col >= 1 && col <= gameBoard.boardSize) {
                    row--;
                    col--;
                    if (gameBoard.gameBoard[row][col] == '*') {
                        gameBoard.revealCell(row, col);
                        gameOver = true;
                        System.out.println("Game over! You hit a mine.");
                        gameBoard.showBoardWhenLooses();
                        gameBoard.printBoard();
                    } else {
                        // Visar med revealCell-metoden position med X
                        gameBoard.revealCell(row, col);
                        //Räknar antalet X för vinst
                        int nonMineCells = (gameBoard.boardSize * gameBoard.boardSize) - gameBoard.numberOfMines;
                        int markedCells = 0;
                        for (int r = 0; r < gameBoard.boardSize; r++) {
                            for (int c = 0; c < gameBoard.boardSize; c++) {
                                if (gameBoard.revealed[r][c]) {
                                    markedCells++;
                                }
                            }
                        }
                        if (markedCells == nonMineCells) {
                            gameOver = true;
                            System.out.println("Congratulations! You win!");
                            gameBoard.printBoard();
                        }
                    }
                } else {
                    System.out.println("Invalid input. Please enter row and column within the valid range.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter row and column as integers, e.g., 1 2.");
                scanner.nextLine();
            }
        }

    }
        public boolean playAgain () {
            System.out.print("Do you want to play again? (Yes/No): ");
            String answer = scanner.next();
            return answer.equalsIgnoreCase("Yes");
        }
    }
