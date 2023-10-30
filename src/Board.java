import java.util.Random;

public class Board {
    Random rand = new Random();


    private Cell[][] gameBoard;
    private int boardSize;
    private int numberOfMines;
   // private boolean[][] revealed;

    private static final char BOMB_SYMBOL = '*';


    String COLOR_RESET = "\u001B[0m";



    //Constructor for a class Board
    
    public Board() {
    }


    public void resetGameBoard(int boardSize, double difficulty) {
        this.boardSize = boardSize;
        this.gameBoard = new Cell[boardSize][boardSize];
//        this.revealed = new boolean[boardSize][boardSize];
        this.numberOfMines = (int) (boardSize * boardSize * difficulty); //the number of mines that will be placed on the board.
                                                                        // multiplies the total size of the board by minePercentage

        initializeBoard();

        placeMines();
        fillInNumberOfMines();
    }

    public int getBoardSize() {
        return boardSize;
    }


    public int getNumberOfMines() {
        return numberOfMines;
    }

    public Cell getGameBoard(int row, int col) {
        return this.gameBoard[row][col];
    }

    public void setGameBoard(Cell[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
    //    public char getGameBoardElement(int rowIndex, int colIndex) {
//        return this.gameBoard[rowIndex][colIndex];
//    }

    public char getBOMB_SYMBOL() {
        return BOMB_SYMBOL;
    }
//Restore the board
    public void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gameBoard[i][j] = new Cell();

            }
        }
    }

    public void placeMines() {
        for (int i = 0; i < numberOfMines; i++) {
            while (true) {
                int x = rand.nextInt(boardSize);
                int y = rand.nextInt(boardSize);
                if (!gameBoard[x][y].isHasBomb()) {
                    gameBoard[x][y].setSymbol(BOMB_SYMBOL);
                    gameBoard[x][y].setHasBomb(true);
                    break;
                }
            }
        }
    }

    public void revealCell(int r, int c) {
        if (!gameBoard[r][c].isRevealed()) {
            gameBoard[r][c].setRevealed(true);
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

                char cellValue = gameBoard[r][c].isRevealed() ? gameBoard[r][c].getSymbol() : ' ';
                System.out.print("| " + setColorsOnNumbers(cellValue)
                        + cellValue + COLOR_RESET + " ");
            }
            System.out.println("|");
        }
    }

    public void fillInNumberOfMines() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (!gameBoard[i][j].isHasBomb()) {
                    gameBoard[i][j].setNumbersOfMinesNextTo(countMines(i, j));// = (char) (countMines(i, j) + '0');
                }
            }
        }
    }

    public int countMines(int x, int y) {
        int numberOfMines = 0;
        for (int i = -1; i <= 1; i++) {
            if (x - i >= 0 && x - i < boardSize) {
                if (y - 1 >= 0) {
                    if (gameBoard[x - i][y - 1].isHasBomb()) {
                        numberOfMines++;
                    }
                }
                if (gameBoard[x - i][y].isHasBomb()) {
                    numberOfMines++;
                }
                if (y + 1 < boardSize) {
                    if (gameBoard[x - i][y + 1].isHasBomb()) {
                        numberOfMines++;
                    }
                }
            }
        }
        return numberOfMines;
    }

    //Shows the bomb when hitting it
    public void showBoardWhenLooses() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (gameBoard[i][j].isHasBomb()) {
                    gameBoard[i][j].setRevealed(true);
                }
            }
        }
    }

    public void showBlanksNextToSquare(int r, int c) {
        if (gameBoard[r][c].getNumbersOfMinesNextTo() == 0) {
            for (int i = -1; i <= 1; i++) {
                if (r - i >= 0 && r - i < boardSize) {
                    if (c - 1 >= 0 && !gameBoard[r - i][c - 1].isRevealed()) {
                        gameBoard[r - i][c - 1].setRevealed(true);
                        if (gameBoard[r - i][c - 1].getNumbersOfMinesNextTo() == 0) {
                            showBlanksNextToSquare(r - i, c - 1);
                        }
                    }
                    if (!gameBoard[r - i][c].isRevealed()) {
                        gameBoard[r - i][c].setRevealed(true);
                        if (gameBoard[r - i][c].getNumbersOfMinesNextTo() == 0) {
                            showBlanksNextToSquare(r - i, c);
                        }
                    }
                    if (c + 1 < boardSize && !gameBoard[r - i][c + 1].isRevealed()) {
                        gameBoard[r - i][c + 1].setRevealed(true);
                        if (gameBoard[r - i][c + 1].getNumbersOfMinesNextTo() == 0) {
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
                if (gameBoard[i][j].isRevealed()) {
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
            case BOMB_SYMBOL -> {
                return "\u001B[31m";
            }
            default -> {
                return "\u001B[37m";
            }
        }
    }
}