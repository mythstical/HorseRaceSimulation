import java.io.Serializable;

public class BettingStat implements Serializable {
    private final double betAmount;
    private final String betHorse;
    private BetStatus status;

    public BettingStat(double betAmount, String betHorse, BetStatus status) {
        this.betAmount = betAmount;
        this.betHorse = betHorse;
        this.status = status;
    }

    public double getBetAmount() {
        return betAmount;
    }

    public String getBetHorse() {
        return betHorse;
    }

    public BetStatus getStatus() {
        return status;
    }
    public void setStatus(BetStatus status){
        this.status = status;
    }

}
