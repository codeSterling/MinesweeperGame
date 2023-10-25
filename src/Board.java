import java.util.Random;

public class Board {
    Random rand = new Random();
    private char[][] gameBoard;
    int boardSize = 6;
    int numberOfMines = 10;
    boolean boardFull = false;
    boolean hasWinner = false;
    boolean[][] revealed;

    public Board() {
    this.gameBoard = new char[boardSize][boardSize];
    this.revealed = new boolean[boardSize][boardSize];
    placeMines();
    fillInNumberOfMines();
    }

    public void placeMines() {
        for( int i = 0; i < numberOfMines; i++) {
            while(true) {
                int x = rand.nextInt(0, boardSize);
                int y = rand.nextInt(0, boardSize);
                if (gameBoard[x][y] != '*') {
                    gameBoard[x][y] = '*';
                    break;
                }
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print("| " + gameBoard[i][j] + " ");
            }
            System.out.println("|");
            if (i < boardSize-1) {
                System.out.println("--------------------------");
            }
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
