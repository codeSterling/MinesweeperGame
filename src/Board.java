import java.util.Random;

public class Board {
    Random rand = new Random();
    private Square[][] gameBoard;
    private int boardSize;
    private int numberOfMines;
    public static final char BOMB_SYMBOL = '*';
    public static final char FLAG_SYMBOL = '§';
    String COLOR_RESET = "\u001B[0m";

    //Constructor for a class Board
    public Board() {
    }

    public void resetGameBoard(int boardSize, double difficulty) {
        this.boardSize = boardSize;
        this.gameBoard = new Square[boardSize][boardSize];
        this.numberOfMines = (int) (boardSize * boardSize * difficulty);
        //the number of mines that will be placed on the board.
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

    public Square getGameBoard(int row, int col) {
        return this.gameBoard[row][col];
    }

    //Restore the board
    public void initializeBoard() {  //Creates the board
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                gameBoard[row][col] = new Square();
            }
        }
    }

    public void placeMines() {
        for (int i = 0; i < numberOfMines; i++) {
            while (true) {
                int row = rand.nextInt(boardSize);
                int col = rand.nextInt(boardSize);
                if (!gameBoard[row][col].isHasBomb()) { //Only adds mine if square doesn't have a bomb already.
                    gameBoard[row][col].setHasBomb(true);
                    break;
                }
            }
        }
    }

    public void revealCell(int row, int col) {
        if (!gameBoard[row][col].isRevealed()) {
            gameBoard[row][col].setRevealed(true);
            showBlanksNextToSquare(row, col);  //Tries to open squares next to it, will only do if it's 0 bombs next to square.
        }
    }

    public String showCharOnSquare(int row, int col) {
        char selectedChar = ' ';
        if (gameBoard[row][col].isRevealed()) {  //Only shows if the square is revealed.
            if (gameBoard[row][col].isFlagged()) {
                selectedChar = FLAG_SYMBOL;
            } else if (gameBoard[row][col].isHasBomb()) {
                selectedChar = BOMB_SYMBOL;
            } else {
                selectedChar = (char) (gameBoard[row][col].getNumbersOfMinesNextTo() + '0');
            }
        }
        return setColorsOnChar(selectedChar) + selectedChar; //returns tha color and char on that square
    }

    public void printBoard() {
        System.out.println("Game Board:");
        System.out.print(" ");
        for (int col = 0; col < boardSize; col++) {
            System.out.print("   " + (col + 1));              // column names to letters
        }
        System.out.println();

        for (int row = 0; row < boardSize; row++) {
            System.out.print((char) ('a' + row) + " ");       // row names to letters
            for (int c = 0; c < boardSize; c++) {
                System.out.print("| " + showCharOnSquare(row, c) + COLOR_RESET + " ");
            }
            System.out.println("|");
        }
    }

    public void fillInNumberOfMines() { //check alls squares to see how many bombs it's next to it.
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (!gameBoard[row][col].isHasBomb()) {
                    gameBoard[row][col].setNumbersOfMinesNextTo(countMinesNextToSquare(row, col));
                }
            }
        }
    }

    public int countMinesNextToSquare(int row, int col) {
        int numberOfMines = 0;
        for (int increaseRow = -1; increaseRow <= 1; increaseRow++) {
            for (int increaseCol = -1; increaseCol <= 1; increaseCol++) {  //This checks all square around original square
                int newR = row + increaseRow;
                int newC = col + increaseCol;
                if (squareExist(newR, newC) && gameBoard[newR][newC].isHasBomb()) {
                    numberOfMines++;
                }
            }
        }
        return numberOfMines;
    }

    //Shows the bomb when hitting it
    public void showBoardWhenLooses() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (gameBoard[row][col].isHasBomb()) {
                    gameBoard[row][col].setRevealed(true);
                }
            }
        }
    }

    public void showBlanksNextToSquare(int row, int col) {
        if (gameBoard[row][col].getNumbersOfMinesNextTo() == 0 && !gameBoard[row][col].isHasBomb()) {
            for (int increaseRow = -1; increaseRow <= 1; increaseRow++) {
                for (int increaseCol = -1; increaseCol <= 1; increaseCol++) {
                    int newR = row + increaseRow;
                    int newC = col + increaseCol;
                    if (squareExist(newR, newC) && !gameBoard[newR][newC].isRevealed()) {
                        gameBoard[newR][newC].setRevealed(true);
                        if (gameBoard[newR][newC].getNumbersOfMinesNextTo() == 0) {
                            showBlanksNextToSquare(newR, newC);
                        }
                    }
                }
            }
        }
    }

    public boolean squareExist(int row, int col) {
        return (row >= 0 && row < boardSize && col >= 0 && col < boardSize);
    }


    public int numberOfSquaresRevealed() { //Used to check win-condition
        int numbersRevealed = 0;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (gameBoard[row][col].isRevealed() && !gameBoard[row][col].isFlagged()) { // only adds if it's not a flag on that square
                    numbersRevealed++;
                }
            }
        }
        return numbersRevealed;
    }

    public String setColorsOnChar(char value) { //used to get what color the char should be.
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
                return "\u001B[91m";
            }
            case FLAG_SYMBOL -> {
                return "\u001B[95m";
            }
            default -> {
                return "\u001B[37m";
            }
        }
    }


    public void setFlagg(int row, int col) {
        gameBoard[row][col].setFlagged(!gameBoard[row][col].isFlagged());
        gameBoard[row][col].setRevealed(!gameBoard[row][col].isRevealed());
    }
}