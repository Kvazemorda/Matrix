package respond.fullgameschedule;

import respond.playersgamelogs.Team;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "GAMEENTRY", schema = "PUBLIC", catalog = "gamelogs")
public class GameEntry {
    int id;
    int week;
    Date date;
    String time;
    Team awayTeam;
    Team homeTeam;
    String location;
    int idAwayTeam;
    int idHomeTeam;


    public GameEntry() {
    }

    public GameEntry(int id, int week, Date date, String time, Team awayTeam,
                     Team homeTeam, String location) {
        this.id = id;
        this.week = week;
        this.date = date;
        this.time = time;
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
        this.location = location;
        this.idAwayTeam = awayTeam.getID();
        this.idHomeTeam = homeTeam.getID();
    }
    @Id
    @Column(name = "ID", unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "week")
    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
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
    @JoinColumn(name="gameEntriesAway", nullable=false)
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
        setIdAwayTeam(awayTeam.getID());
    }


    @ManyToOne
    @JoinColumn(name="gameEntriesHome", nullable=false)
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
        setIdHomeTeam(homeTeam.getID());
    }

    @Basic
    @Column(name = "Location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Basic
    @Column(name = "idAwayTeam")
    public int getIdAwayTeam() {
        return idAwayTeam;
    }

    public void setIdAwayTeam(int idAwayTeam) {
        this.idAwayTeam = idAwayTeam;
    }

    @Basic
    @Column(name = "idHomeTeam")
    public int getIdHomeTeam() {
        return idHomeTeam;
    }

    public void setIdHomeTeam(int idHomeTeam) {
        this.idHomeTeam = idHomeTeam;
    }

    @Override
    public String toString() {
        return "GameEntry{" +
                "id=" + id +
                ", week=" + week +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", awayTeam=" + awayTeam +
                ", homeTeam=" + homeTeam +
                ", location='" + location + '\'' +
                '}';
    }
}
