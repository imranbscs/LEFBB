package com.laeb.laebproject.model;

public class UpComingGames {
    public String nameTeam;
    public String gameLocation;
    public String gameTime;

    public UpComingGames(String nameTeam, String gameLocation, String gameTime) {
        this.nameTeam = nameTeam;
        this.gameLocation = gameLocation;
        this.gameTime = gameTime;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }
}
