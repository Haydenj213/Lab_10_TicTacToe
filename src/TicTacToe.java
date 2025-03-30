import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];


    public static void main(String[] args) {
        // int rowMove = SafeInput.getRangedInt(in, "", 1, 3)
        // int colMove = SafeInput.getRangedInt(in, "", 1, 3)
        Scanner in = new Scanner(System.in);
        boolean playAgain = false;

        System.out.println("Hello! Welcome to Tic Tac Toe! Let's get right into playing!");

        do {
            clearBoard();

            String currentPlayer = "X";
            int moveCounter = 0;
            boolean gameEnded = false;


            while (!gameEnded) {
            display();

            int row, col;
            do {
                System.out.println("Player " + currentPlayer + ", it's your turn to make your move.");
                row = SafeInput.getRangedInt(in, "Enter row: ", 1, 3) - 1;
                col = SafeInput.getRangedInt(in, "Enter column: ", 1, 3) - 1;
            } while (!isValidMove(row, col));


            board[row][col] = currentPlayer;
            moveCounter++;


            if (moveCounter >= 5) {
                if (isWin(currentPlayer)) {
                    display();
                    System.out.println("Player " + currentPlayer + " wins the game! Nice Job!");
                    gameEnded = true;
                } else if (isTie()) {
                    display();
                    System.out.println("It's a tie! Play again to see who can win!");
                    gameEnded = true;
                }
            }

            if (!gameEnded) {
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
            }
        }

        playAgain = SafeInput.getYNConfirm(in, "Play again? (y/n)");

    } while (!playAgain);

        System.out.println("Thanks for playing Tic Tac Toe!");
    }

    public static void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], " ");
        }
    }

    private static void display() {
        System.out.println("Here is what the current board looks like: ");
        for (int i = 0; i < board.length; i++) {

            for (int j = 0; j < board[i].length; j++) {

                System.out.print(" " + board[i][j] + " ");
                if (j < board[i].length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();

            if (i < board.length - 1) {
                System.out.println("---|---|---");
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
            return false;
        }

        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {

        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int j = 0; j < board[0].length; j++) {
            boolean colWin = true;
            for (int i = 0; i < board.length; i++) {
                if (!board[i][j].equals(player)) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int i = 0; i < board.length; i++) {
            boolean rowWin = true;
            for (int j = 0; j < board[i].length; j++) {
                if (!Objects.equals(board[i][j], player)) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        boolean leftDiagonalWin = true, rightDiagonalWin = true;

        for (int i = 0; i < board.length; i++) {
            if (!board[i][i].equals(player)) {
                leftDiagonalWin = false;
            }
            if (!board[i][board.length - 1 - i].equals(player)) {
                rightDiagonalWin = false;
            }
        }
        return leftDiagonalWin || rightDiagonalWin;
    }

    private static boolean isTie() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }


}