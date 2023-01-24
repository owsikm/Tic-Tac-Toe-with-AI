package tictactoe;

/**
 * Enumeration for the different types of games that can be played
 */
enum GameType {
    /**
     * Two users play against each other
     */
    USER_USER,
    /**
     * Easy difficulty AI plays against another easy difficulty AI
     */
    EASY_EASY,
    /**
     * Easy difficulty AI plays against a user
     */
    EASY_USER,
    /**
     * A user plays against an easy difficulty AI
     */
    USER_EASY,
    /**
     * A user plays against a medium difficulty AI
     */
    USER_MEDIUM,
    /**
     * A medium difficulty AI plays against a user
     */
    MEDIUM_USER,
    /**
     * Two medium difficulty AIs play against each other
     */
    MEDIUM_MEDIUM,
    /**
     * A user plays against a hard difficulty AI
     */
    USER_HARD,
    /**
     * A hard difficulty AI plays against a user
     */
    HARD_USER,
    /**
     * Two hard difficulty AIs play against each other
     */
    HARD_HARD

}
