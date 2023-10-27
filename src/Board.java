import java.util.Random;

public class Board {
    Random rand = new Random();

    private char[][] gameBoard;
    private int boardSize = 6;
    private int numberOfMines = 10;
    private boolean[][] revealed;


    String COLOR_RESET = "\u001B[0m";

    public Board() {

        this.gameBoard = new char[boardSize][boardSize];
        this.revealed = new boolean[boardSize][boardSize];
        initializeBoard();
        placeMines();
        fillInNumberOfMines();
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    public void setNumberOfMines(int numberOfMines) {
        this.numberOfMines = numberOfMines;
    }

    public char getGameBoardElement(int rowIndex, int colIndex) {
        return this.gameBoard[rowIndex][colIndex];
    }

    public void setGameBoardElement(int rowIndex, int colIndex, char newValue) {
        this.gameBoard[rowIndex][colIndex] = newValue;
    }

    public boolean getRevealedValue(int rowIndex, int colIndex) {
        return this.revealed[rowIndex][colIndex];
    }

    public void setRevealedValue(int rowIndex, int colIndex, boolean newValue) {
        this.revealed[rowIndex][colIndex] = newValue;
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

    public void revealCell(int r, int c) {
        if (!revealed[r][c]) {
            revealed[r][c] = true;
        }
        showBlanksNextToSquare(r, c);
    }

    public void printBoard() {
        System.out.println("Game Board:");
        System.out.print(" ");
        for (int c = 0; c < boardSize; c++) {
            System.out.print("   " + (c + 1));
        }
        System.out.println();

        for (int r = 0; r < boardSize; r++) {
            System.out.print((r + 1) + " ");
            for (int c = 0; c < boardSize; c++) {

                char cellValue = revealed[r][c] ? gameBoard[r][c] : ' ';
                System.out.print("| " + setColorsOnNumbers(cellValue) + cellValue + COLOR_RESET + " ");
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

    public String setColorsOnNumbers(char value) {
        switch (value) {
            case '1' -> {
                return "\u001B[34m";
            }
            case '2' -> {
                return "\u001B[32m";
            }
            case '3' -> {
                return "\u001B[31m";
            }
            case '4' -> {
                return "\u001B[35m";
            }
            case '5' -> {
                return "\u001B[33m";
            }
            case '6' -> {
                return "\u001B[36m";
            }
            default ->  {
                return "\u001B[37m";
            }
        }
    }
}