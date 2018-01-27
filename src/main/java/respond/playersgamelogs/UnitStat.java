package respond.playersgamelogs;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UnitStat", schema = "PUBLIC", catalog = "gamelogs")
public class UnitStat implements Serializable {
    @SerializedName("@abbreviation")
    String abbreviation;
    @SerializedName("#text")
    String text;
    @SerializedName("@category")
    String category;
    int id;
    Stats statLosses;
    Stats statGoals;


    public UnitStat() {
    }

    public UnitStat(String abbreviation, String text, String category) {

        this.abbreviation = abbreviation;
        this.text = text;
        this.category = category;
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

    @Basic
    @Column(name = "Abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Basic
    @Column(name = "Text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "Category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public Stats getStatLosses() {
        return statLosses;
    }

    public void setStatLosses(Stats statLosses) {
        this.statLosses = statLosses;
    }

    public Stats getStatGoals() {
        return statGoals;
    }

    public void setStatGoals(Stats statGoals) {
        this.statGoals = statGoals;
    }

    @Override
    public String toString() {
        return "UnitStat{" +
                "abbreviation='" + abbreviation + '\'' +
                ", text='" + text + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
