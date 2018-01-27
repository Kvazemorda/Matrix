package respond.playersgamelogs;

import respond.fullgameschedule.GameEntry;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TEAM", schema = "PUBLIC", catalog = "gamelogs")
public class Team implements Serializable {
    int ID;
    String City;
    String Name;
    String Abbreviation;
    List<GameEntry> gameEntriesHome;
    List<GameEntry> gameEntriesAway;
    List<Game> gameHome;
    List<Game> gameAway;
    List<PlayerGameLogs> playerGameLogsesTeam;

    public Team(int ID, String city, String name, String abbreviation) {
        this.ID = ID;
        City = city;
        Name = name;
        Abbreviation = abbreviation;
    }

    public Team(int ID, String city, String name, String abbreviation, List<GameEntry> gameEntriesHome,
                List<GameEntry> gameEntriesAway) {
        this.ID = ID;
        City = city;
        Name = name;
        Abbreviation = abbreviation;
        this.gameEntriesHome = gameEntriesHome;
        this.gameEntriesAway = gameEntriesAway;
    }

    public Team() {
    }

    @Override
    public String toString() {
        return "Team{" +
                "ID=" + ID +
                ", City='" + City + '\'' +
                ", Name='" + Name + '\'' +
                ", Abbreviation='" + Abbreviation + '\'' +
                '}';
    }

    @Id
    @Column(name = "ID", insertable = false, updatable = false)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Basic
    @Column(name = "City")
    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Basic
    @Column(name = "Abbreviation")
    public String getAbbreviation() {
        return Abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        Abbreviation = abbreviation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return ID == team.ID;

    }
    @OneToMany(mappedBy="homeTeam")
    public List<GameEntry> getGameEntriesHome() {
        return gameEntriesHome;
    }

    public void setGameEntriesHome(List<GameEntry> gameEntries) {
        this.gameEntriesHome = gameEntries;
    }
    @OneToMany(mappedBy="awayTeam")
    public List<GameEntry> getGameEntriesAway() {
        return gameEntriesAway;
    }

    public void setGameEntriesAway(List<GameEntry> gameEntriesAway) {
        this.gameEntriesAway = gameEntriesAway;
    }

    @OneToMany(mappedBy="homeTeam")
    public List<Game> getGameHome() {
        return gameHome;
    }

    public void setGameHome(List<Game> gameHome) {
        this.gameHome = gameHome;
    }
    @OneToMany(mappedBy="awayTeam")
    public List<Game> getGameAway() {
        return gameAway;
    }

    public void setGameAway(List<Game> gameAway) {
        this.gameAway = gameAway;
    }

    @Override
    public int hashCode() {
        return ID;
    }

    @OneToMany(mappedBy="team")
    public List<PlayerGameLogs> getPlayerGameLogsesTeam() {
        return playerGameLogsesTeam;
    }

    public void setPlayerGameLogsesTeam(List<PlayerGameLogs> playerGameLogs) {
        this.playerGameLogsesTeam = playerGameLogs;
    }
}
