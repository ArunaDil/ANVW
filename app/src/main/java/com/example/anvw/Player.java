package com.example.anvw;

public class Player {
    private int playerID;
    private String playerName;
    private int playerScore;
    private int levelScore;
    private int currentLevel;
    private int totalScore;
    private static Player playerSingleton;
    private static int isLevelComplete=0;

    public static int getIsLevelComplete() {
        return isLevelComplete;
    }

    public static void setIsLevelComplete() {
        if(isLevelComplete==0){
            isLevelComplete=1;
        }
        else if(isLevelComplete==1){
            isLevelComplete=0;
        }

    }

    private Player() {
        super();
    }

    public static Player getInstance() {
        if (playerSingleton == null) {
            synchronized (Player.class) {
                playerSingleton = new Player();

            }
        }

        return playerSingleton;
    }

    public Player(int playerID, String playerName, int playerScore, int levelScore, int currentLevel, int totalScore) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerScore = playerScore;
        this.levelScore = levelScore;
        this.currentLevel = currentLevel;
        this.totalScore = totalScore;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public int getLevelScore() {
        return levelScore;
    }

    public void setLevelScore(int levelScore) {
        this.levelScore = levelScore;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

}
