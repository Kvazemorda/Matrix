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
    @Column(name = "ID", nullable = false, insertable = false)
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
}
