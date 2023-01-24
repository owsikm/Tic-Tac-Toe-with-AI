package tictactoe;


import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Random;
import java.util.Scanner;

public class Game {

    static String[][] board = new String[3][3];
    static int rounds;
    static int counter_;
    static int gameLength = 0;
    private static GameType type;


    Game(GameType type) {
        Game.type = type;
        board = new String[3][3];
    }

    static void start() {
        if (gameLength==0){
            createBoard();
        }
        while (true) {
            switch (type) {
                case USER_USER:
                    // User move
                    userMove();
                    checkStatus();
                    break;
                case EASY_EASY:
                    System.out.println("Making move level \"easy\"");
                    // Computer move (easy level)
                    computerMoveEasy();
                    checkStatus();
                    break;
                case MEDIUM_MEDIUM:
                    System.out.println("Making move level \"medium\"");
                    // Computer move (medium level)
                    computerMoveMedium();
                    checkStatus();
                    break;
                case HARD_HARD:
                    System.out.println("Making move level \"hard\"");
                    // Computer move (hard level)
                    computerMoveHard();
                    checkStatus();
                    break;
                case EASY_USER:
                    if (gameLength % 2 == 0) {
                        System.out.println("Making move level \"easy\"");
                        // Computer move (easy level)
                        computerMoveEasy();
                    } else {
                        // User move
                        userMove();
                    }
                    checkStatus();
                    break;
                case USER_EASY:
                    if (gameLength % 2 == 0) {
                        // User move
                        userMove();
                    } else {
                        System.out.println("Making move level \"easy\"");
                        // Computer move (easy level)
                        computerMoveEasy();
                    }
                    checkStatus();
                    break;
                case USER_MEDIUM:
                    if (gameLength % 2 == 0) {
                        // User move
                        userMove();
                        checkStatus();
                    } else {
                        System.out.println("Making move level \"medium\"");
                        // Computer move (medium level)
                        computerMoveMedium();
                        checkStatus();
                    }
                    checkStatus();
                    break;
                case MEDIUM_USER:
                    if (gameLength % 2 == 0) {
                        System.out.println("Making move level \"medium\"");
                        // Computer move (medium level)
                        computerMoveMedium();
                        checkStatus();
                    } else {
                        // User move
                        userMove();
                        checkStatus();
                    }
                    checkStatus();
                    break;
                case USER_HARD:
                    if (gameLength % 2 == 0) {
                        // User move
                        userMove();
                        checkStatus();
                    } else {
                        System.out.println("Making move level \"hard\"");
                        // Computer move (hard level)
                        computerMoveHard();
                        checkStatus();
                    }
                    checkStatus();
                    break;
                case HARD_USER:
                    if (gameLength % 2 == 0) {
                        System.out.println("Making move level \"hard\"");
                        // Computer move (hard level)
                        computerMoveHard();
                        checkStatus();
                    } else {
                        // User move
                        userMove();
                        checkStatus();
                    }
                    checkStatus();
                    break;
            }
        }
    }

    static void createBoard() {
        // Create a 3x3 board and initialize all elements to " "
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = " ";
            }
        }
        // Print the board
        printBoard();
    }

    static void printBoard() {
        // Print the top border of the board
        System.out.println("---------");
        // Iterate through each row of the board
        for (int i = 0; i < 3; i++) {
            // Print the left border of the board
            System.out.print("| ");
            // Iterate through each element in the row
            for (int j = 0; j < 3; j++) {
                // Print the element
                System.out.print(board[i][j] + " ");
            }
            // Print the right border of the board
            System.out.println("|");
        }
        // Print the bottom border of the board
        System.out.println("---------");

    }


    public static boolean isNumeric(String str) {
        // Use the ParsePosition class to check if a string can be parsed as a number
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(str, pos);
        // If the entire string has been parsed as a number, the index will be equal to the length of the string
        return str.length() != pos.getIndex();
    }

    static void userMove() {
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        scanner.reset();
        System.out.println("Enter the coordinates: ");
        String str = scanner.nextLine();
        try {
            String[] parts = str.split(" ");
            // check if entered value is not in correct format
            if (str.length() > 3 || isNumeric(parts[0]) || isNumeric(parts[1])) {
                System.out.println("You should enter numbers!");
                userMove();
            }
            int ri = Integer.parseInt(parts[0]) - 1;
            int ci = Integer.parseInt(parts[1]) - 1;
            // check if entered coordinates are not in the correct range
            if (ri < 0 || ri > 3 || ci < 0 || ci > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                userMove();
            }
            // check if the selected cell is already occupied
            if (board[ri][ci].equals(" ")) {
                whosTurn(ri, ci);
                rounds++;
                printBoard();
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                userMove();
            }
        } catch (Exception e) {
            System.out.println("Wrong format or input type!");
            userMove();
        }
    }

    public static void computerMoveEasy() {
        // Create a random object
        Random random = new Random();
        // Generate random x and y coordinates
        int x = random.nextInt(3);
        int y = random.nextInt(3);

        // Check if the selected cell is empty
        if (board[x][y].equals(" ")) {
            // Make the move
            whosTurn(x, y);
            counter_--;
            // Print the board
            printBoard();
        } else {
            // Try again if the selected cell is already occupied
            computerMoveEasy();
        }
        // check for win or draw
        checkStatus();
    }

    static void whosTurn(int i, int j) {
        // Check if it is X's turn
        if (gameLength % 2 == 0) {
            board[i][j] = "X";
        } else {
            // If not, it is O's turn
            board[i][j] = "O";
        }
    }


    static void computerMoveMedium() {
        // Check if the computer can win in the next move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    whosTurn(i, j);
                    if (isWin()) {
                        printBoard();
                        return;
                    }
                    board[i][j] = " ";
                }
            }
        }
        // Check if the player can win in the next move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    if (gameLength % 2 == 0) {
                        board[i][j] = "O";
                    } else {
                        board[i][j] = "X";
                    }
                    if (isWin()) {
                        whosTurn(i, j);
                        printBoard();
                        return;
                    }
                    board[i][j] = " ";
                }
            }
        }
        // If no winning move is available, make a random move
        computerMoveEasy();
        printBoard();
    }

    private static void computerMoveHard() {

        // initialize best move as -1
        int bestRow = -1;
        int bestCol = -1;

        // initialize best score as the lowest possible value
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {

                    // put the computer symbol in the empty cell
                    if (gameLength % 2 == 0) {
                        board[i][j] = "X";
                    } else {
                        board[i][j] = "O";
                    }

                    // call the minimax function to get the score of the move
                    int score = minimax(false);

                    // undo the move
                    board[i][j] = " ";

                    // update the best move if necessary
                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = i;
                        bestCol = j;
                    }
                }
            }
        }
        // make the best move
        if (gameLength % 2 == 0) {
            board[bestRow][bestCol] = "X";
        } else {
            board[bestRow][bestCol] = "O";
        }
        printBoard();
    }


    private static int minimax(boolean isMaximizing) {
        // check if there is a winner or a tie
        String winner = checkWinner();
        String currentSign;
        String nextSign;

        if (gameLength % 2 == 0) {
            currentSign = "X";
            nextSign = "O";
        } else {
            currentSign = "O";
            nextSign = "X";
        }

        if (winner != null) {
            if (winner.equals(currentSign)) {
                return 1;
            } else if (winner.equals(nextSign)) {
                return -1;
            } else {
                return 0;
            }
        }

        if (isMaximizing) {
            // initialize best score as the lowest possible value
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals(" ")) {

                        // put the computer symbol in the empty cell
                        if (gameLength % 2 == 0) {
                            board[i][j] = "X";
                        } else {
                            board[i][j] = "O";
                        }

                        // call the minimax recursively for the opponent's move
                        int score = minimax(false);

                        // update the best score if necessary
                        bestScore = Math.max(score, bestScore);

                        // undo the move
                        board[i][j] = " ";
                    }
                }
            }
            return bestScore;
        } else {
            // initialize worst score as the highest possible value
            int worstScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].equals(" ")) {

                        // put the opponent symbol in the empty cell
                        if (gameLength % 2 == 0) {
                            board[i][j] = "O";
                        } else {
                            board[i][j] = "X";
                        }

                        // call the minimax recursively for the computer's move
                        int score = minimax(true);

                        // update the worst score if necessary
                        worstScore = Math.min(score, worstScore);

                        // undo the move
                        board[i][j] = " ";
                    }
                }
            }
            return worstScore;
        }
    }


    private static String checkWinner() {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals(" ")) {
                return board[i][0];
            }
        }

        // check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals(" ")) {
                return board[0][i];
            }
        }

        // check diagonals
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals(" ")) {
            return board[0][0];
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals(" ")) {
            return board[0][2];
        }

        // check if the board is full
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    return null;
                }
            }
        }

        return "T";
    }


    static boolean isWin() {
        // Check for horizontal wins
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals(" ")) {
                return true;
            }
        }
        // Check for vertical wins
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals(" ")) {
                return true;
            }
        }
        // Check for diagonal wins
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals(" ")) {
            return true;
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals(" ")) {
            return true;
        }
        return false;
    }

    static void checkStatus() {
        // Check if X wins
        if (gameLength % 2 == 0 && isWin()) {
            System.out.println("X wins");
            System.exit(0);
        }
        // Check if O wins
        else if (gameLength % 2 != 0 && isWin()) {
            System.out.println("O wins");
            System.exit(0);
        }
        // Check for a draw
        else if (gameLength >= 8) {
            System.out.println("Draw");
            System.exit(0);
        }
        // Increase the round count
        gameLength++;
        // Start the next round
        start();
    }
}
