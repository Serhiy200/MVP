package model;

public class GameFields {
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    private String gameName;

    private String playerName;

    private String nickName;

    private String number;

    private String teamName;

    private String goals;

    public GameFields(String gameName, String playerName, String nickName, String number, String teamName, String goals) {
        this.gameName = gameName;
        this.playerName = playerName;
        this.nickName = nickName;
        this.number = number;
        this.teamName = teamName;
        this.goals = goals;
    }
}
