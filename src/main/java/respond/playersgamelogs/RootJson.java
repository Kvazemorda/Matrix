package respond.playersgamelogs;

import java.io.Serializable;

public class RootJson implements Serializable{
    PlayersLogs playergamelogs;

    public RootJson() {
    }

    public RootJson(PlayersLogs playergamelogs) {
        this.playergamelogs = playergamelogs;
    }

    public PlayersLogs getPlayergamelogs() {
        return playergamelogs;
    }

    public void setPlayergamelogs(PlayersLogs playergamelogs) {
        this.playergamelogs = playergamelogs;
    }

    @Override
    public String toString() {
        return "RootJson{" +
                "playergamelogs=" + playergamelogs +
                '}';
    }
}
