package gameComponents;

import core.*;
import pages.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GameFrame extends JFrame {
    public int numberOfLanes = 3;
    public int raceDistance = 50;
    public Horse[] horses = new Horse[10];
    public Horse[] selectedHorses = new Horse[numberOfLanes];
    private Thread simulation;
    public final Map<GamePanels, GamePanel> gamePanels = new HashMap<>();
    private GameData gameData;
    public List<RaceStat> raceStats;
    public List<BettingStat> bettingStats;
    public GamePanels activePanel = null;
    public GamePanels previousPanel = null;
    private final JPanel mainPanel = new JPanel();

    public Race race;

    public GameFrame() {
        setTitle("Horse Racing Simulation");
        loadGameData();
        gamePanels.put(GamePanels.LANDING_PAGE, new LandingPage(this));
        gamePanels.put(GamePanels.HORSE_DETAILS_PAGE, new HorseDetailsPage(this));
        gamePanels.put(GamePanels.GAME_PLAYING_PAGE, new GamePlayingPage(this));
        gamePanels.put(GamePanels.RACE_STATISTIC_PAGE, new RaceStatisticsPage(this));
        gamePanels.put(GamePanels.TRACK_CONFIGURATION_PAGE, new TrackConfigurationPage(this));
        gamePanels.put(GamePanels.HORSE_CUSTOMIZATION_PAGE, new HorseCustomizationPage(this));
        gamePanels.put(GamePanels.RACE_DETAILS_PAGE, new RaceDetailsPage(this));
        gamePanels.put(GamePanels.BETTING_PAGE, new BettingPage(this));
        gamePanels.put(GamePanels.BETTING_STAT_PAGE, new BettingStatisticsPage(this));


        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(layout);
        mainPanel.add(gamePanels.get(GamePanels.LANDING_PAGE));
        activePanel = GamePanels.LANDING_PAGE;
        setSize(980, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(mainPanel);
        setVisible(true);
    }

    public void navigateToPanel(GamePanels panel){
        mainPanel.removeAll();
        mainPanel.add(gamePanels.get(panel));
        gamePanels.get(panel).loadData();
        previousPanel = activePanel;
        activePanel = panel;
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void loadGameData(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("GameData.ser"));
            gameData  = (GameData) objectInputStream.readObject();
            horses = gameData.getHorses();
            bettingStats = gameData.getBettingStats();
            raceStats = gameData.getRaceStats();
            selectedHorses = gameData.getSelectedHorses();
            numberOfLanes = gameData.getNumberOfLanes();
            raceDistance = gameData.getRaceDistance();

        } catch (Exception e){
            if ( e instanceof FileNotFoundException){
                horses = new Horse[10];
                for (int i = 0; i < 10; i ++){
                    horses[i] = new Horse('A', "Horse " + i, 0.5, Breed.ARABIAN_HORSE, HorseColor.BAY);
                }
                raceStats = new ArrayList<>();
                bettingStats = new ArrayList<>();
                for (int k = 0; k < numberOfLanes; k++){
                    selectedHorses[k] = horses[k];
                }
            } else {
                e.printStackTrace();
            }
        }
    }

    private void openHorseWinnerWindow() {
        GameDialog customizeHorsesDialog = new GameDialog(this, "Introducing the Winner", true);
        customizeHorsesDialog.setSize(300, 200);
        customizeHorsesDialog.setLayout(new GridLayout(3, 1,10,5));

        GameLabel winnerLabel = new GameLabel("Winner", Font.PLAIN, 15, SwingConstants.CENTER);
        GameLabel winnerValue = new GameLabel(raceStats.get(raceStats.size()-1).getWinner(), Font.BOLD, 25, SwingConstants.CENTER);
        customizeHorsesDialog.add(winnerLabel);
        customizeHorsesDialog.add(winnerValue);
        if (!bettingStats.isEmpty()){
            GameLabel betStatusValue = new GameLabel("You " + bettingStats.get(bettingStats.size() - 1).getStatus() + " the bet!!", Font.PLAIN, 20, SwingConstants.CENTER);
            customizeHorsesDialog.add(betStatusValue);
        }

        customizeHorsesDialog.setVisible(true);
    }

    public void startRaceSimulation() {
        if ( simulation == null){
            simulation = new Thread(new Runnable() {
                public void run() {
                    race.startRace();
                }
            });

            simulation.start();
        }
    }

    public void stopRaceSimulation() {
        if ( simulation != null){
            simulation.interrupt();
            simulation = null;
        }
        race.stopRace();
        race.gameGraphicPanel.updateGraphicPanel();
    }

    public void onRaceStop(RaceStat raceStat){
        this.raceStats.add(raceStat);
        if (!this.bettingStats.isEmpty()){
            BettingStat latestBet = this.bettingStats.get(this.bettingStats.size() - 1);
            if (Objects.equals(raceStat.getWinner(), latestBet.getBetHorse())) {
                latestBet.setStatus(BetStatus.WIN);
            } else {
                latestBet.setStatus(BetStatus.LOSE);
            }
        }
        openHorseWinnerWindow();
    }
}
