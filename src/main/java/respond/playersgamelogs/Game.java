package respond.playersgamelogs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Game", schema = "PUBLIC", catalog = "gamelogs")
public class Game implements Serializable {
    int id;
    Date date;
    String time;
    Team awayTeam;
    Team homeTeam;
    String location;
    List<PlayerGameLogs> playerGameLogsesGame;

    public Game() {
    }

    public Game(int id, Date date, String time, Team awayTeam, Team homeTeam, String location) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
        this.location = location;
    }
    @Id
    @Column(name = "ID", unique = true, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "Date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "Time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @ManyToOne
    @JoinColumn(name="gameAway", nullable=false)
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @ManyToOne
    @JoinColumn(name="gameHome", nullable=false)
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @Basic
    @Column(name = "Location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @OneToMany(mappedBy="game")
    public List<PlayerGameLogs> getPlayerGameLogsesGame() {
        return playerGameLogsesGame;
    }

    public void setPlayerGameLogsesGame(List<PlayerGameLogs> playerGameLogses) {
        this.playerGameLogsesGame = playerGameLogses;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", awayTeam=" + awayTeam +
                ", homeTeam=" + homeTeam +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return id == game.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
