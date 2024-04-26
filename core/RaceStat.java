import java.io.Serializable;

public class RaceStat implements Serializable {
    private final int distance;
    private final String winner;
    private final double timeDuration;
    private final Horse[] horses;
    private final int noOfTracks;

    public RaceStat(int distance, String winner, double timeDuration, Horse[] horses, int noOfTracks) {
        this.distance = distance;
        this.winner = winner;
        this.timeDuration = timeDuration;
        this.horses = horses;
        this.noOfTracks = noOfTracks;
    }

    public int getDistance() {
        return distance;
    }


    public String getWinner() {
        return winner;
    }


    public double getTimeDuration() {
        return timeDuration;
    }

    public Horse[] getHorses() {
        return horses;
    }

    public int getNoOfTracks() {
        return noOfTracks;
    }

}
