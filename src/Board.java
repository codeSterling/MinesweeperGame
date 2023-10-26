import java.util.Random;

public class Board {
    Random rand = new Random();
    public char[][] gameBoard;
    int boardSize = 6;
    int numberOfMines = 10;
    boolean[][] revealed;

    public Board() {

        this.gameBoard = new char[boardSize][boardSize];
        this.revealed = new boolean[boardSize][boardSize];
        initializeBoard();
        placeMines();
        fillInNumberOfMines();
    }
    
    //Restore the board
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
        showBlanksNextToSquare(row, col);
    }

    public void printBoard() {
        System.out.println("Game Board:");
        System.out.print(" ");
        for (int col = 0; col < boardSize; col++) {
            System.out.print("   " + (col + 1));
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

    //Shows the bomb when hitting it
    public void showBoardWhenLooses() {
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                if(gameBoard[i][j] == '*') {
                    revealed[i][j] = true;
                }
            }
        }
    }

    public void showBlanksNextToSquare(int r, int c) {
        if (gameBoard[r][c] == '0') {
            for (int i = -1; i <= 1; i++) {
                if (r - i >= 0 && r - i < boardSize) {
                    if (c - 1 >= 0 && !revealed[r - i][c - 1]) {
                        revealed[r - i][c - 1] = true;
                        if (gameBoard[r - i][c - 1] == '0') {
                            showBlanksNextToSquare(r - i, c - 1);
                        }
                    }
                    if ( !revealed[r - i][c]) {
                        revealed[r - i][c] = true;
                        if (gameBoard[r - i][c] == '0') {
                            showBlanksNextToSquare(r - i, c);
                        }
                    }
                    if (c + 1 < boardSize && !revealed[r - i][c + 1]) {
                        revealed[r - i][c + 1] = true;
                        if (gameBoard[r - i][c + 1] == '0') {
                            showBlanksNextToSquare(r - i, c + 1);
                        }
                    }
                }
            }
        }
    }

    public int numberOfSquaresRevealed() {
        int numbersRevealed = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (revealed[i][j]) {
                    numbersRevealed++;
                }
            }
        }
        return numbersRevealed;
    }
}



