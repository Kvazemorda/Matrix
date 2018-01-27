package respond.fullgameschedule;

import java.util.List;

public class FullGameSchedule {
    String lastUpdatedOn;
    List<GameEntry> gameentry;

    public FullGameSchedule() {
    }

    public FullGameSchedule(String lastUpdatedOn, List<GameEntry> gameentry) {
        this.lastUpdatedOn = lastUpdatedOn;
        this.gameentry = gameentry;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public List<GameEntry> getGameentry() {
        return gameentry;
    }

    public void setGameentry(List<GameEntry> gameentry) {
        this.gameentry = gameentry;
    }

    @Override
    public String toString() {
        return "FullGameSchedule{" +
                "lastUpdatedOn=" + lastUpdatedOn +
                ", gameentry=" + gameentry +
                '}';
    }
}
