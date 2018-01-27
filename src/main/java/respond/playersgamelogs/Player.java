package respond.playersgamelogs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Player", schema = "PUBLIC", catalog = "gamelogs")
public class Player implements Serializable {
    int id;
    int ID;
    String LastName;
    String FirstName;
    int Number;
    String Position;
    String Height;
    int Weight;
    Date BirthDate;
    int Age;
    String BirthCity;
    String BirthCountry;
    boolean IsRookie;
    List<PlayerGameLogs> playerGameLogsesPlayer;

    public Player(int ID, String lastName, String firstName, int number, String position, String height,
                  int weight, Date birthDate, int age, String birthCity, String birthCountry, boolean isRookie) {
        this.ID = ID;
        LastName = lastName;
        FirstName = firstName;
        Number = number;
        Position = position;
        Height = height;
        Weight = weight;
        BirthDate = birthDate;
        Age = age;
        BirthCity = birthCity;
        BirthCountry = birthCountry;
        IsRookie = isRookie;
    }

    public Player() {
    }

    @Override
    public String toString() {
        return "Player{" +
                "ID=" + ID +
                ", LastName='" + LastName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", Number=" + Number +
                ", Position='" + Position + '\'' +
                ", Height='" + Height + '\'' +
                ", Weight=" + Weight +
                ", BirthDate=" + BirthDate +
                ", Age=" + Age +
                ", BirthCity='" + BirthCity + '\'' +
                ", BirthCountry='" + BirthCountry + '\'' +
                ", IsRookie=" + IsRookie +
                '}';
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



    @Basic
    @Column(name = "IDPlayer")
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Basic
    @Column(name = "LastName")
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    @Basic
    @Column(name = "FirstName")
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    @Basic
    @Column(name = "Number")
    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    @Basic
    @Column(name = "Position")
    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    @Basic
    @Column(name = "Height")
    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    @Basic
    @Column(name = "Weight")
    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }
    @Basic
    @Column(name = "Birthdate")
    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }
    @Basic
    @Column(name = "Age")
    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
    @Basic
    @Column(name = "BirthCity")
    public String getBirthCity() {
        return BirthCity;
    }

    public void setBirthCity(String birthCity) {
        BirthCity = birthCity;
    }
    @Basic
    @Column(name = "BirthCountry")
    public String getBirthCountry() {
        return BirthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        BirthCountry = birthCountry;
    }
    @Basic
    @Column(name = "Rookie")
    public boolean isRookie() {
        return IsRookie;
    }

    public void setRookie(boolean rookie) {
        IsRookie = rookie;
    }

    @OneToMany(mappedBy="player")
    public List<PlayerGameLogs> getPlayerGameLogsesPlayer() {
        return playerGameLogsesPlayer;
    }

    public void setPlayerGameLogsesPlayer(List<PlayerGameLogs> playerGameLogses) {
        this.playerGameLogsesPlayer = playerGameLogses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return ID == player.ID;

    }

    @Override
    public int hashCode() {
        return ID;
    }
}
