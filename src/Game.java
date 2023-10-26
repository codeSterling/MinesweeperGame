import java.util.Scanner;

public class Game {
    private Board gameBoard;
    private Player player;
    private boolean gameOver;

    Scanner scanner = new Scanner(System.in);

    public Game(Board board, Player player) {
        this.gameBoard = board;
        this.gameOver = false;
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
                    System.out.println("Game over! You hit a mine.");
                    gameBoard.showBoardWhenLooses();
                } else {
                    // MarkCell-metoden för att placera "X"
                    // gameBoard.markCell(row, col);
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
                    System.out.println(markedCells);
                    if (markedCells == nonMineCells) {
                        gameOver = true;
                        System.out.println("Congratulations! You win!");
                        player.incrementWins();
                        System.out.println("You have won " + player.getWins() + " times!");
                    }
                }
            }
         else{
            System.out.println("Invalid input. Please try again.");
        }
    }

        gameBoard.printBoard(); // Visa hela spelplanen med eventuellt resultat
        System.out.println("Game over. Thank you for playing!");

}

    public boolean playAgain() {
        System.out.print("Do you want to play again? (Yes/No): ");
        String answer = scanner.next();
        return answer.equalsIgnoreCase("Yes");
    }
}