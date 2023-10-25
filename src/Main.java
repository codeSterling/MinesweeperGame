import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board gameBoard = new Board();

        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        System.out.println("Welcome to Minesweeper!");
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
                            if (gameBoard.gameBoard[r][c] == 'X') {
                                markedCells++;
                            }
                        }
                    }
                    if (markedCells == nonMineCells) {
                        gameOver = true;
                        System.out.println("Congratulations! You win!");
                    }
                }
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        gameBoard.printBoard(); // Visa hela spelplanen med eventuellt resultat
        System.out.println("Game over. Thank you for playing!");

        // Stäng scanner om den inte längre behövs
        scanner.close();

    }
}


