package respond.playersgamelogs;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "playerGameLogs", schema = "PUBLIC", catalog = "gamelogs")
public class PlayerGameLogs implements Serializable {
    int id;
    Game game;
    Player player;
    Team team;
    Stats stats;

    public PlayerGameLogs() {
    }

    public PlayerGameLogs(Game game, Player player, Team team, Stats stats) {
        this.game = game;
        this.player = player;
        this.team = team;
        this.stats = stats;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, insertable = false, unique = true)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="playerGameLogsesGame")
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }

    @ManyToOne
    @JoinColumn(name="playerGameLogsesPlayer")
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    @ManyToOne
    @JoinColumn(name="playerGameLogsesTeam")
    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    @ManyToOne
    @JoinColumn(name="playerGameLogsesStats")
    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "PlayerGameLogs{" +
                "game=" + game +
                ", player=" + player +
                ", team=" + team +
                ", stats=" + stats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerGameLogs that = (PlayerGameLogs) o;

        if (game != null ? !game.equals(that.game) : that.game != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (team != null ? !team.equals(that.team) : that.team != null) return false;
        return stats != null ? stats.equals(that.stats) : that.stats == null;

    }

    @Override
    public int hashCode() {
        int result = game != null ? game.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        result = 31 * result + (stats != null ? stats.hashCode() : 0);
        return result;
    }
}
