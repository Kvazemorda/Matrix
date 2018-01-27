package respond.fullgameschedule;

public class RootSchedule {
    FullGameSchedule fullgameschedule;

    public RootSchedule(FullGameSchedule fullGameSchedule) {
        this.fullgameschedule = fullGameSchedule;
    }

    public FullGameSchedule getFullGameSchedule() {
        return fullgameschedule;
    }

    public void setFullGameSchedule(FullGameSchedule fullGameSchedule) {
        this.fullgameschedule = fullGameSchedule;
    }

    @Override
    public String toString() {
        return "RootSchedule{" +
                "fullGameSchedule=" + fullgameschedule +
                '}';
    }
}
