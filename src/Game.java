import java.util.*;

public class Game {
    private Board gameBoard;
    private Player player;
    private int boardSize;
    private double difficulty;
    private boolean gameOver;
    private long startTime;
    private long stopTime;
    private ArrayList<HighScoreEntry> highScore = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);


    //Constructor

    public Game(Player player) {
        this.player = player;
        this.gameOver = false;
        gameBoard = new Board();
    }

    //A setter method, sets the difficulty level of the game

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }


    private double getMinePercentage() {
        return this.difficulty;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }


    public void start() {
        player.setFirstGame(false);
        gameOver = false;
        //Calculates percentage of mines in relation to the size of the game.
        double minePercentage = getMinePercentage();
        gameBoard.resetGameBoard(boardSize, minePercentage);
        this.startTime = System.currentTimeMillis();
        playGame();
    }

    public void playGame() {
        int nonMineCells = (gameBoard.getBoardSize() * gameBoard.getBoardSize()) - gameBoard.getNumberOfMines();

        while (!gameOver) {

            gameBoard.printBoard();
            System.out.println("Number of mines on the board: " + gameBoard.getNumberOfMines());
            System.out.println();
            System.out.print("Please enter row and column (e.g., a 2): To add or remove flag, type § ");
            String input = scanner.next();
            if (input.charAt(0) == Board.FLAG_SYMBOL) {
                flagMethod();
            } else {

                try {
                    String rowInput = input;
                    int col = scanner.nextInt();
                    scanner.nextLine();
                    int row = rowInput.toLowerCase().charAt(0) - 96;
                    if (rowInput.matches(".*\\d.*")) {      // a regex looks if the string contains a number
                        System.out.println("Invalid input. That's a number. Please enter a letter as row!");
                    } else if (rowInput.length() > 1) {
                        System.out.println("Invalid input. You have written more than a letter. Try again!");

                    } else {
                        row--;
                        col--;
                        if (gameBoard.squareExist(row, col) && gameBoard.getGameBoard(row, col).isRevealed()) {
                            System.out.println("Square already open, try another.");
                        } else if (gameBoard.squareExist(row, col)) {
                            if (gameBoard.getGameBoard(row, col).isHasBomb() && !gameBoard.getGameBoard(row, col).isFlagged()) { //If you hit a mine that isnt flagged
                                gameBoard.revealCell(row, col);
                                gameOver = true;
                                gameBoard.showBoardWhenLooses();
                                gameBoard.printBoard();
                                System.out.println("Game over! You hit a mine.");

                            } else if (gameBoard.squareExist(col, row)) {
                                //  Shows revealCell method position with X
                                gameBoard.revealCell(row, col);
                                //Counts the number of X for winning
                                if (gameBoard.numberOfSquaresRevealed() == nonMineCells) {
                                    gameOver = true;
                                    player.incrementWins();
                                    this.stopTime = System.currentTimeMillis();
                                    long time = gameElapsedTime();
                                    addToHighScoreAndSort(time, difficulty);

                                    gameBoard.printBoard();
                                    System.out.println("Congratulations! You win!\uD83C\uDF89\uD83C\uDF89");
                                    System.out.println("You have won " + player.getWins() + " times!");
                                    System.out.println("Your time was " + time + " seconds");

                                    //printHighScore(highScore);
                                    System.out.println();
                                }
                            }
                        } else {
                            System.out.println("Index out of bounds, please try again.");
                        }
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. " +
                            "Please enter row and column within the valid range.");
                    scanner.nextLine();
                }
            }
        }
        System.out.println("Thank you for playing!");
    }

    public boolean playAgain() {
        while (true) {
            System.out.println("Do you want to play again? (Yes/No): ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Yes")) {
                return true;
            } else if (answer.equalsIgnoreCase("No")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Yes' or 'No'. ");
            }
        }
    }

    public long gameElapsedTime() {
        return (stopTime - startTime) / 1000; //Returns time in seconds
    }

    public void addToHighScoreAndSort(long time, double difficulty) {
        highScore.add(new HighScoreEntry(player.getName(), difficulty, time));
        Collections.sort(highScore, Comparator.comparingLong(HighScoreEntry::getTime));  //Sorts highscore by time
    }

    public void printHighScore(ArrayList<HighScoreEntry> highScore) {
        if(highScore.isEmpty()) {
            System.out.println("Highscore is empty");
        } else {
            System.out.println();
            System.out.println("Top three times :");
            for (int i = 0; i < Math.min(3, highScore.size()); i++) { //Only shows the first three of sorted highscore list, or all if highscore is less than 3.
                System.out.println("Name: " + highScore.get(i).getName() + ". Time: " + highScore.get(i).getTime() + " seconds. Difficulty: " + highScore.get(i).getDifficulty() + ".");
            }
        }
    }

    public void flagMethod() { //Method to add flag and remove flag from board, including check for error- input.
        System.out.print("Please enter row and column to flag (e.g., a 2):");

        try {
            String rowInput = scanner.next();
            int col = scanner.nextInt();
            scanner.nextLine();
            int row = rowInput.toLowerCase().charAt(0) - 96;
            if (rowInput.matches(".*\\d.*")) {      // a regex looks if the string contains a number
                System.out.println("Invalid input. That's a number. Please enter a letter as row!");
            } else if (rowInput.length() > 1) {
                System.out.println("Invalid input. You have written more than a letter. Try again!");
            } else {
                row--;
                col--;
                if (gameBoard.squareExist(row, col) && gameBoard.getGameBoard(row, col).isRevealed() && !gameBoard.getGameBoard(row, col).isFlagged()) {
                    System.out.println("Square already open, try another.");
                } else if (gameBoard.squareExist(row, col)) {
                    gameBoard.setFlagg(row, col);
                } else {
                    System.out.println("Index out of bounds, please try again.");
                }
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. " +
                    "Please enter row and column within the valid range.");
            scanner.nextLine();
        }

    }

    public void printMenu() {
        String answer;
        while(true) {
            System.out.println();
            System.out.println("Do you want to:");
            System.out.println(player.isFirstGame() ? "1. Play the game" : "1. Play again");
            System.out.println("2. Show highscore");
            System.out.println("3. Change name");
            System.out.println("4. Exit");

            answer = scanner.nextLine();

            switch (answer.toLowerCase()) {
                case "1", "play again", "play the game" -> {
                    startUpGame();
                }
                case "2", "show highscore" -> {
                    printHighScore(highScore);
                }
                case "3", "change name" -> {
                    System.out.println("Enter your username:");
                    player.setName(scanner.nextLine());
                }
                case "4", "exit" -> {
                    System.out.println("Thank you for playing!");
                    System.exit(0);

                }
                default -> {
                    System.out.println("Wrong input, try again.");
                }
            }
        }

    }

    public void startUpGame() {

            int boardSize = 4;
            boolean validInput = false;

            // Loop until the user enters a valid size of the playing field
            while (!validInput) {
                System.out.print("Set board size (4-9): ");
                if (scanner.hasNextInt()) {
                    boardSize = scanner.nextInt();
                    if (boardSize >= 4 && boardSize <= 9) {
                        validInput = true;
                    } else {
                        System.out.println("Invalid size. Set board size between 4 and 9.");
                    }
                } else {
                    System.out.println("Invalid size. Set board size between 4 and 9.");
                    scanner.next();
                }
            }

            setBoardSize(boardSize);
            int difficultyChoice = 0;
            boolean validDifficulty = false;
            while (!validDifficulty) {
                try {
                    System.out.println("Choose difficulty: 1.Easy 2.Medium 3.Hard:");
                    difficultyChoice = scanner.nextInt();

                    switch (difficultyChoice) {
                        case 1:
                            // Set difficulty to easy
                            setDifficulty(0.1);  // 10% of the squares have mines
                            validDifficulty = true;
                            break;
                        case 2:
                            // Set difficulty to medium
                            setDifficulty(0.2);  // 20% of the squares have mines
                            validDifficulty = true;
                            break;
                        case 3:
                            // Set difficulty to hard
                            setDifficulty(0.3);  // 30% of the squares have mines
                            validDifficulty = true;
                            break;
                        default:
                            System.out.println("Invalid input. Try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Set a number between 1 and 3");
                    scanner.next();
                }
            }
            System.out.println("Welcome " + player.getName() + ".");
            start();

        }


}

