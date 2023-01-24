package tictactoe;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // call the Menu method to start the game
        Menu();
    }

    static void Menu() {
        System.out.println("Input command:");
        Scanner scanner = new Scanner(System.in);
        // get input from the user
        String input = scanner.nextLine();
        GameType type = null;
        // use a switch statement to determine the game type based on the user's input
        switch (input) {
            case "start easy easy":
                type = GameType.EASY_EASY;
                break;
            case "start easy user":
                type = GameType.EASY_USER;
                break;
            case "start user user":
                type = GameType.USER_USER;
                break;
            case "start user easy":
                type = GameType.USER_EASY;
                break;
            case "start user medium":
                type = GameType.USER_MEDIUM;
                break;
            case "start medium user":
                type = GameType.MEDIUM_USER;
                break;
            case "start medium medium":
                type = GameType.MEDIUM_MEDIUM;
                break;
            case "start user hard":
                type = GameType.USER_HARD;
                break;
            case "start hard user":
                type = GameType.HARD_USER;
                break;
            case "start hard hard":
                type = GameType.HARD_HARD;
                break;
            case "exit":
                // exit the program
                System.exit(0);
                break;
            default:
                // if the input is invalid, print an error message and call the Menu method again
                System.out.println("Bad parameters!");
                Menu();
                return;
        }
        // create a new game with the selected game type and start it
        Game game = new Game(type);
        game.start();
    }
}
