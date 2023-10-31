import java.util.Random;

public class Board {
    Random rand = new Random();


    private Square[][] gameBoard;
    private int boardSize;
    private int numberOfMines;
    private static final char BOMB_SYMBOL = '*';
    private static final char FLAG_SYMBOL = 'F';
    String COLOR_RESET = "\u001B[0m";

    //Constructor for a class Board
    public Board() {
    }

    public void resetGameBoard(int boardSize, double difficulty) {
        this.boardSize = boardSize;
        this.gameBoard = new Square[boardSize][boardSize];
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

    public Square getGameBoard(int row, int col) {
        return this.gameBoard[row][col];
    }

//Restore the board
    public void initializeBoard() {  //Creates the board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gameBoard[i][j] = new Square();

            }
        }
    }

    public void placeMines() {
        for (int i = 0; i < numberOfMines; i++) {
            while (true) {
                int x = rand.nextInt(boardSize);
                int y = rand.nextInt(boardSize);
                if (!gameBoard[x][y].isHasBomb()) {  //Only adds mine if square doesnt have a bomb already.
                  //  gameBoard[x][y].setSymbol(BOMB_SYMBOL);
                    gameBoard[x][y].setHasBomb(true);
                    break;
                }
            }
        }
    }

    public void revealCell(int r, int c) {
        if (!gameBoard[r][c].isRevealed()) {
            gameBoard[r][c].setRevealed(true);
            showBlanksNextToSquare(r, c);  //Tries to open squares next to it.
        }

    }

    public String showCharOnSquare(int r, int c) {
        char selectedChar = ' ';
        if(gameBoard[r][c].isRevealed()) {  //Only shows if the square is revealed.
            if (gameBoard[r][c].isFlagged()) {
                selectedChar = FLAG_SYMBOL;

            } else if(gameBoard[r][c].isHasBomb()) {
                selectedChar = BOMB_SYMBOL;
            } else {
                selectedChar = (char) (gameBoard[r][c].getNumbersOfMinesNextTo() + '0');
            }
        }
         return setColorsOnChar(selectedChar) + selectedChar;
    }

    public void printBoard() {
        System.out.println("Game Board:");
        System.out.print(" ");
        for (int c = 0; c < boardSize; c++) {
            System.out.print("   " + (c + 1));              // column names to letters
        }
        System.out.println();

        for (int r = 0; r < boardSize; r++) {
            System.out.print((char) ('a' + r) + " ");       // row names to letters
            for (int c = 0; c < boardSize; c++) {
                System.out.print("| " + showCharOnSquare(r, c) + COLOR_RESET + " ");
            }
            System.out.println("|");
        }
    }

    public void fillInNumberOfMines() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (!gameBoard[i][j].isHasBomb()) {
                    gameBoard[i][j].setNumbersOfMinesNextTo(countMinesNextToSquare(i, j));
                }
            }
        }
    }

    public int countMinesNextToSquare(int r, int c) {
        int numberOfMines = 0;
        for (int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {  //This checks all square around original square
                int newR = r + i;
                int newC = c + j;
                if (squareExist(newR,newC) && gameBoard[newR][newC].isHasBomb()) {
                    numberOfMines++;
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
            for (int increaseR = -1; increaseR <= 1; increaseR++) {
                for (int increaseC = -1; increaseC <= 1; increaseC++) {
                    int newR = r + increaseR;
                    int newC = c + increaseC;
                    if( squareExist(newR, newC) && !gameBoard[newR][newC].isRevealed()) {
                        gameBoard[newR][newC].setRevealed(true);
                        if(gameBoard[newR][newC].getNumbersOfMinesNextTo() == 0) {
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

    public String setColorsOnChar(char value) {
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
}