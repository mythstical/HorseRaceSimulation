import java.io.Serializable;
import java.util.List;

public class GameData implements Serializable {
    private final Horse[] horses;
    private final List<RaceStat> raceStats;
    private final List<BettingStat> bettingStats;
    private final Horse[] selectedHorses;
    private final int numberOfLanes;
    private final int raceDistance;

    public GameData(Horse[] horses, List<RaceStat> raceStats, List<BettingStat> bettingStats, Horse[] selectedHorses, int numberOfLanes, int raceDistance) {
        this.horses = horses;
        this.raceStats = raceStats;
        this.bettingStats = bettingStats;
        this.selectedHorses = selectedHorses;
        this.numberOfLanes = numberOfLanes;
        this.raceDistance = raceDistance;
    }

    public Horse[] getHorses() {
        return horses;
    }

    public List<RaceStat> getRaceStats() {
        return raceStats;
    }

    public List<BettingStat> getBettingStats() {
        return bettingStats;
    }

    public Horse[] getSelectedHorses() {
        return selectedHorses;
    }

    public int getNumberOfLanes() {
        return numberOfLanes;
    }

    public int getRaceDistance() {
        return raceDistance;
    }
}
