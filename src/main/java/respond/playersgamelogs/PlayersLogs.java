package respond.playersgamelogs;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayersLogs implements Serializable{
    private String lastUpdatedOn;
    private ArrayList<PlayerGameLogs> gamelogs;

    public PlayersLogs() {
    }

    public PlayersLogs(String lastUpdatedOn, ArrayList<PlayerGameLogs> gamelogs) {
        this.lastUpdatedOn = lastUpdatedOn;
        this.gamelogs = gamelogs;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public ArrayList<PlayerGameLogs> getGamelogs() {
        return gamelogs;
    }

    public void setGamelogs(ArrayList<PlayerGameLogs> gamelogs) {
        this.gamelogs = gamelogs;
    }

    @Override
    public String toString() {
        return "PlayersLogs{" +
                "lastUpdatedOn='" + lastUpdatedOn + '\'' +
                ", gamelogs=" + gamelogs +
                '}';
    }
}