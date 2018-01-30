package respond.playersgamelogs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Stats", schema = "PUBLIC", catalog = "gamelogs")
public class Stats implements Serializable {
    UnitStat Goals;
    UnitStat Assists;
    UnitStat Points;
    UnitStat HatTricks;
    UnitStat PlusMinus;
    UnitStat Shots;
    UnitStat ShotPercentage;
    UnitStat Penalties;
    UnitStat PenaltyMinutes;
    UnitStat PowerplayGoals;
    UnitStat PowerplayAssists;
    UnitStat PowerplayPoints;
    UnitStat ShorthandedGoals;
    UnitStat ShorthandedAssists;
    UnitStat ShorthandedPoints;
    UnitStat GameWinningGoals;
    UnitStat GameTyingGoals;
    UnitStat Hits;
    UnitStat Faceoffs;
    UnitStat FaceoffWins;
    UnitStat FaceoffLosses;
    UnitStat FaceoffPercent;
    UnitStat Losses;
    List<PlayerGameLogs> playerGameLogsesStats;
    int id;

    public Stats() {
    }

    public Stats(UnitStat goals, UnitStat assists, UnitStat points, UnitStat hatTricks, UnitStat plusMinus,
                 UnitStat shots, UnitStat shotPercentage, UnitStat penalties, UnitStat penaltyMinutes,
                 UnitStat powerplayGoals, UnitStat powerplayAssists, UnitStat powerplayPoints,
                 UnitStat shorthandedGoals, UnitStat shorthandedAssists, UnitStat shorthandedPoints,
                 UnitStat gameWinningGoals, UnitStat gameTyingGoals, UnitStat hits, UnitStat faceoffs,
                 UnitStat faceoffWins, UnitStat faceoffLosses, UnitStat faceoffPercent, UnitStat losses) {
        Goals = goals;
        Assists = assists;
        Points = points;
        HatTricks = hatTricks;
        PlusMinus = plusMinus;
        Shots = shots;
        ShotPercentage = shotPercentage;
        Penalties = penalties;
        PenaltyMinutes = penaltyMinutes;
        PowerplayGoals = powerplayGoals;
        PowerplayAssists = powerplayAssists;
        PowerplayPoints = powerplayPoints;
        ShorthandedGoals = shorthandedGoals;
        ShorthandedAssists = shorthandedAssists;
        ShorthandedPoints = shorthandedPoints;
        GameWinningGoals = gameWinningGoals;
        GameTyingGoals = gameTyingGoals;
        Hits = hits;
        Faceoffs = faceoffs;
        FaceoffWins = faceoffWins;
        FaceoffLosses = faceoffLosses;
        FaceoffPercent = faceoffPercent;
        Losses = losses;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "Goals=" + Goals +
                ", Assists=" + Assists +
                ", Points=" + Points +
                ", HatTricks=" + HatTricks +
                ", PlusMinus=" + PlusMinus +
                ", Shots=" + Shots +
                ", ShotPercentage=" + ShotPercentage +
                ", Penalties=" + Penalties +
                ", PenaltyMinutes=" + PenaltyMinutes +
                ", PowerplayGoals=" + PowerplayGoals +
                ", PowerplayAssists=" + PowerplayAssists +
                ", PowerplayPoints=" + PowerplayPoints +
                ", ShorthandedGoals=" + ShorthandedGoals +
                ", ShorthandedAssists=" + ShorthandedAssists +
                ", ShorthandedPoints=" + ShorthandedPoints +
                ", GameWinningGoals=" + GameWinningGoals +
                ", GameTyingGoals=" + GameTyingGoals +
                ", Hits=" + Hits +
                ", Faceoffs=" + Faceoffs +
                ", FaceoffWins=" + FaceoffWins +
                ", FaceoffLosses=" + FaceoffLosses +
                ", FaceoffPercent=" + FaceoffPercent +
                '}';
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public UnitStat getLosses() {
        return Losses;
    }

    public void setLosses(UnitStat losses) {
        Losses = losses;
    }

    public UnitStat getGoals() {
        return Goals;
    }

    public void setGoals(UnitStat goals) {
        Goals = goals;
    }

    public UnitStat getAssists() {
        return Assists;
    }

    public void setAssists(UnitStat assists) {
        Assists = assists;
    }

    public UnitStat getPoints() {
        return Points;
    }

    public void setPoints(UnitStat points) {
        Points = points;
    }


    public UnitStat getHatTricks() {
        return HatTricks;
    }

    public void setHatTricks(UnitStat hatTricks) {
        HatTricks = hatTricks;
    }

    public UnitStat getPlusMinus() {
        return PlusMinus;
    }

    public void setPlusMinus(UnitStat plusMinus) {
        PlusMinus = plusMinus;
    }

    public UnitStat getShots() {
        return Shots;
    }

    public void setShots(UnitStat shots) {
        Shots = shots;
    }

    public UnitStat getShotPercentage() {
        return ShotPercentage;
    }

    public void setShotPercentage(UnitStat shotPercentage) {
        ShotPercentage = shotPercentage;
    }

    public UnitStat getPenalties() {
        return Penalties;
    }

    public void setPenalties(UnitStat penalties) {
        Penalties = penalties;
    }


    public UnitStat getPenaltyMinutes() {
        return PenaltyMinutes;
    }

    public void setPenaltyMinutes(UnitStat penaltyMinutes) {
        PenaltyMinutes = penaltyMinutes;
    }


    public UnitStat getPowerplayGoals() {
        return PowerplayGoals;
    }

    public void setPowerplayGoals(UnitStat powerplayGoals) {
        PowerplayGoals = powerplayGoals;
    }


    public UnitStat getPowerplayAssists() {
        return PowerplayAssists;
    }

    public void setPowerplayAssists(UnitStat powerplayAssists) {
        PowerplayAssists = powerplayAssists;
    }


    public UnitStat getPowerplayPoints() {
        return PowerplayPoints;
    }

    public void setPowerplayPoints(UnitStat powerplayPoints) {
        PowerplayPoints = powerplayPoints;
    }


    public UnitStat getShorthandedGoals() {
        return ShorthandedGoals;
    }

    public void setShorthandedGoals(UnitStat shorthandedGoals) {
        ShorthandedGoals = shorthandedGoals;
    }


    public UnitStat getShorthandedAssists() {
        return ShorthandedAssists;
    }

    public void setShorthandedAssists(UnitStat shorthandedAssists) {
        ShorthandedAssists = shorthandedAssists;
    }


    public UnitStat getShorthandedPoints() {
        return ShorthandedPoints;
    }

    public void setShorthandedPoints(UnitStat shorthandedPoints) {
        ShorthandedPoints = shorthandedPoints;
    }


    public UnitStat getGameWinningGoals() {
        return GameWinningGoals;
    }

    public void setGameWinningGoals(UnitStat gameWinningGoals) {
        GameWinningGoals = gameWinningGoals;
    }


    public UnitStat getGameTyingGoals() {
        return GameTyingGoals;
    }

    public void setGameTyingGoals(UnitStat gameTyingGoals) {
        GameTyingGoals = gameTyingGoals;
    }


    public UnitStat getHits() {
        return Hits;
    }

    public void setHits(UnitStat hits) {
        Hits = hits;
    }


    public UnitStat getFaceoffs() {
        return Faceoffs;
    }

    public void setFaceoffs(UnitStat faceoffs) {
        Faceoffs = faceoffs;
    }


    public UnitStat getFaceoffWins() {
        return FaceoffWins;
    }

    public void setFaceoffWins(UnitStat faceoffWins) {
        FaceoffWins = faceoffWins;
    }


    public UnitStat getFaceoffLosses() {
        return FaceoffLosses;
    }

    public void setFaceoffLosses(UnitStat faceoffLosses) {
        FaceoffLosses = faceoffLosses;
    }


    public UnitStat getFaceoffPercent() {
        return FaceoffPercent;
    }

    public void setFaceoffPercent(UnitStat faceoffPercent) {
        FaceoffPercent = faceoffPercent;
    }

    @OneToMany(mappedBy="stats", cascade = CascadeType.ALL)
    public List<PlayerGameLogs> getPlayerGameLogsesStats() {
        return playerGameLogsesStats;
    }

    public void setPlayerGameLogsesStats(List<PlayerGameLogs> playerGameLogses) {
        this.playerGameLogsesStats = playerGameLogses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stats stats = (Stats) o;

        return id == stats.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}

