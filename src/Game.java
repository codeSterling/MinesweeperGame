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
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            //Ifall man träffar mina
            if (row >= 0 && row < gameBoard.boardSize && col >= 0 && col < gameBoard.boardSize) {
                if (gameBoard.gameBoard[row][col] == '*') {
                    gameBoard.revealCell(row, col);
                    gameOver = true;
                    System.out.println("Game over! You hit a mine\uD83D\uDE2D");
                    gameBoard.showBoardWhenLooses();
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
                    if (gameBoard.numberOfSquaresRevealed() == nonMineCells) {
                        gameOver = true;
                        System.out.println("Congratulations! You win!\uD83C\uDF89\uD83C\uDF89");
                    }
                }
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        gameBoard.printBoard();
        System.out.println("Thank you for playing!");

    }

    public boolean playAgain() {
        System.out.print("Do you want to play again? (Yes/No): ");
        String answer = scanner.next();
        return answer.equalsIgnoreCase("Yes");
    }
}
