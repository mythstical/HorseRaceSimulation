package pages;

import core.Horse;
import core.RaceStat;
import gameComponents.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RaceDetailsPage extends GamePanel {

    private GameLabel trackDistanceValue;
    private GameLabel noOfTracksValue;
    private GameLabel timeSpentValue;
    private GameLabel winnerValue;
    private JTable table;

    public RaceDetailsPage(GameFrame parentFrame) {
        super(parentFrame);
        setLayout(new GridBagLayout());

        // Page Title
        JLabel pageTitle = new GameLabel("Race Details", Font.BOLD, 25, SwingConstants.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        add(pageTitle, gbc);


        // Horse Details Panel
        GamePanel raceDetailsPanel = new GamePanel(new GridLayout(4,2, 5, 5));
        GameLabel trackDistanceLabel = new GameLabel("Track Distance", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel noOfTracksLabel = new GameLabel("No. of Tracks", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel timeSpentLabel = new GameLabel("Time Spent", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel winnerLabel = new GameLabel("Winner", Font.PLAIN, 12, SwingConstants.LEFT);

        trackDistanceValue = new GameLabel(Font.BOLD, 15, SwingConstants.RIGHT);
        noOfTracksValue = new GameLabel( Font.BOLD, 15, SwingConstants.RIGHT);
        timeSpentValue = new GameLabel(Font.BOLD, 15, SwingConstants.RIGHT);
        winnerValue = new GameLabel(Font.BOLD, 15, SwingConstants.RIGHT);


        raceDetailsPanel.add(trackDistanceLabel);
        raceDetailsPanel.add(trackDistanceValue);

        raceDetailsPanel.add(noOfTracksLabel);
        raceDetailsPanel.add(noOfTracksValue);

        raceDetailsPanel.add(timeSpentLabel);
        raceDetailsPanel.add(timeSpentValue);

        raceDetailsPanel.add(winnerLabel);
        raceDetailsPanel.add(winnerValue);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 10;
        gbc.insets = new Insets(0,0,10,0);

        add(raceDetailsPanel, gbc);

        // Horse Statistics Table
        JScrollPane tableScrollPane = getHorseStatTable();
        JLabel tableTitle = new GameLabel("Horses Raced", Font.BOLD, 16, SwingConstants.LEFT);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipady = 0;
        add(tableTitle, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipady = 100;
        add(tableScrollPane, gbc);

        // Navigation Button Panel
        GamePanel navigationButtonPanel = new GamePanel(new GridLayout(1,1));

        GameButton backButton = new GameButton("Back");
        registerBackButton(backButton);

        navigationButtonPanel.add(backButton);
        gbc.ipady = 10;
        gbc.gridy = 4;
        gbc.insets = new Insets(50,400,100,0);
        add(navigationButtonPanel, gbc);
    }

    private JScrollPane getHorseStatTable() {
        String[] columnNames = {"Horse Name", "Horse Breed", "Confidence", "Average speed", "Distance Travelled", "Win count", "Lose Count"};
        String[][] rowData = {};
        this.table = new GameTable(rowData, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);
        return tableScrollPane;
    }

    public void setRaceStat(RaceStat raceStat){
        trackDistanceValue.setText(String.valueOf(raceStat.getDistance()));
        noOfTracksValue.setText(String.valueOf(raceStat.getNoOfTracks()));
        timeSpentValue.setText(String.valueOf(raceStat.getTimeDuration()));
        winnerValue.setText(String.valueOf(raceStat.getWinner()));
        String[] columnNames = {"Horse Name", "Horse Breed", "Confidence", "Average speed", "Distance Travelled", "Win count", "Lose Count"};
        String[][] rowData = new String[raceStat.getHorses().length][7];
        for (int i = 0; i < raceStat.getHorses().length; i++) {
            Horse horse = raceStat.getHorses()[i];
            rowData[i][0] = horse.getName();
            rowData[i][1] = horse.getBreed().value;
            rowData[i][2] = String.valueOf(horse.getConfidence());
            rowData[i][3] = String.valueOf(horse.getAvgSpeed());
            rowData[i][4] = String.valueOf(horse.getDistanceTravelled());
            rowData[i][5] = String.valueOf(horse.getRacesWon());
            rowData[i][6] = String.valueOf(horse.getRacesLost());

        }
        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
        table.setModel(tableModel);
    }
}
