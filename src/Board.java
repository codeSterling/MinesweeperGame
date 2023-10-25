import java.util.Random;

public class Board {
    Random rand = new Random();
    public char[][] gameBoard;
    int boardSize = 6;
    int numberOfMines = 10;
    boolean boardFull = false;
    boolean hasWinner = false;
    boolean[][] revealed;

    public Board() {

        this.gameBoard = new char[boardSize][boardSize];
        this.revealed = new boolean[boardSize][boardSize];
        initializeBoard();
        placeMines();
        fillInNumberOfMines();
    }
    //Återställer planen
    public void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gameBoard[i][j] = ' ';
                revealed[i][j] = false;
            }
        }

    }

    public void placeMines() {
        for (int i = 0; i < numberOfMines; i++) {
            while (true) {
                int x = rand.nextInt(boardSize);
                int y = rand.nextInt(boardSize);
                if (gameBoard[x][y] != '*') {
                    gameBoard[x][y] = '*';
                    break;
                }
            }
        }
    }

    public void revealCell(int row, int col) {
        if (!revealed[row][col]) {
            revealed[row][col] = true;
        }
    }

    public void markCell(int row, int col) {
        if (!revealed[row][col]) {
            gameBoard[row][col] = 'X';
        }
    }

    public void printBoard() {
        System.out.println("Game Board:");
        System.out.print("  ");
        for (int col = 0; col < boardSize; col++) {
            System.out.print((col + 1) + "   ");
        }
        System.out.println();

        for (int row = 0; row < boardSize; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < boardSize; col++) {

                char cellValue = revealed[row][col] ? gameBoard[row][col] : ' ';
                System.out.print("| " + cellValue + " ");
            }
            System.out.println("|");
        }
    }

    public void fillInNumberOfMines() {
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                if(gameBoard[i][j] != '*') {
                    gameBoard[i][j] = (char) (countMines(i, j)+'0');
                }
            }
        }
    }

    public int countMines(int x, int y) {
        int numberOfMines = 0;
        for(int i = -1; i <=1 ; i++) {
            if(x - i >= 0 && x - i < boardSize) {
                if(y - 1 >= 0) {
                    if (gameBoard[x - i][y - 1] == '*') {
                        numberOfMines++;
                    }
                }
                if (gameBoard[x - i][y] == '*') {
                    numberOfMines++;
                }
                if(y + 1 < boardSize) {
                    if (gameBoard[x - i][y + 1] == '*') {
                        numberOfMines++;
                    }
                }
            }
        }
        return numberOfMines;
    }
}



