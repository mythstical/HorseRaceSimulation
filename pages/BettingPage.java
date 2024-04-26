package pages;

import core.BetStatus;
import core.BettingStat;
import core.Breed;
import core.Horse;
import gameComponents.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BettingPage extends GamePanel {

    private GameTable table;

    public BettingPage(GameFrame parentFrame) {
        super(parentFrame);
        setLayout(new GridBagLayout());

        // Page Title
        JLabel pageTitle = new GameLabel("Betting Center", Font.BOLD, 25, SwingConstants.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        add(pageTitle, gbc);

        // Horse Statistics Table
        JScrollPane tableScrollPane = getHorseStatTable();
        JLabel tableTitle = new GameLabel("Horses performances", Font.BOLD, 16, SwingConstants.LEFT);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,100,10,100);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 0;
        add(tableTitle, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipady = 100;
        add(tableScrollPane, gbc);

        // Horse Details Panel
        GamePanel bettingInputPanel = new GamePanel(new GridLayout(2,2, 5, 5));
        GameLabel horseLabel = new GameLabel("Horse", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel bettingAmountLabel = new GameLabel("Betting Amount", Font.PLAIN, 12, SwingConstants.LEFT);

        String[] horseNames = new String[parentFrame.horses.length];
        for (int x = 0; x < parentFrame.horses.length; x++){
            horseNames[x] = parentFrame.horses[x].getName();
        }

        GameComboBox<String> horseValue = new GameComboBox<>(horseNames);
        GameNumberInput bettingAmountValue = new GameNumberInput(10,10,1000,10);

        bettingInputPanel.add(horseLabel);
        bettingInputPanel.add(horseValue);

        bettingInputPanel.add(bettingAmountLabel);
        bettingInputPanel.add(bettingAmountValue);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipady = 10;

        add(bettingInputPanel, gbc);

        // Navigation Button Panel
        GamePanel navigationButtonPanel = new GamePanel(new GridLayout(1,2));
        GameButton betButton = new GameButton("Place a Bet!!");
        GameButton backButton = new GameButton("Back");

        betButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedHorseIndex = horseValue.getSelectedIndex();
                if (selectedHorseIndex >= 0 && selectedHorseIndex < parentFrame.horses.length) {
                    Horse selectedHorse = parentFrame.horses[selectedHorseIndex];
                    BettingStat newBet = new BettingStat((double) bettingAmountValue.getValue(), selectedHorse.getName(), BetStatus.PENDING);
                    parentFrame.bettingStats.add(newBet);
                    openBettingConfirmationWindow(newBet);
                }
            }
        });

        registerBackButton(backButton);
        registerBackButton(betButton);

        navigationButtonPanel.add(backButton);
        navigationButtonPanel.add(betButton);
        gbc.ipady = 10;
        gbc.gridy = 4;
        gbc.insets = new Insets(50,400,100,0);
        add(navigationButtonPanel, gbc);
    }

    private JScrollPane getHorseStatTable() {
        String[] columnNames = {"Horse Name", "Horse Breed", "Confidence", "Average speed", "Distance Travelled", "Win count", "Lose Count"};
        String[][] rowData = new String[this.parentFrame.horses.length][7];

        for (int i = 0; i < this.parentFrame.horses.length; i++) {
            Horse horse = this.parentFrame.horses[i];
            rowData[i][0] = horse.getName();
            rowData[i][1] = horse.getBreed().value;
            rowData[i][2] = String.valueOf(horse.getConfidence());
            rowData[i][3] = String.valueOf(horse.getAvgSpeed());
            rowData[i][4] = String.valueOf(horse.getDistanceTravelled());
            rowData[i][5] = String.valueOf(horse.getRacesWon());
            rowData[i][6] = String.valueOf(horse.getRacesLost());

        }
        table = new GameTable(loadTableModel());
        JScrollPane tableScrollPane = new JScrollPane(table);
        return tableScrollPane;
    }

    public void loadData(){
        DefaultTableModel tableModel = loadTableModel();
        table.setModel(tableModel);
    }

    public DefaultTableModel loadTableModel(){
        String[] columnNames = {"Horse Name", "Horse Breed", "Confidence", "Average speed", "Distance Travelled", "Win count", "Lose Count"};
        String[][] rowData = new String[this.parentFrame.horses.length][7];

        for (int i = 0; i < this.parentFrame.horses.length; i++) {
            Horse horse = this.parentFrame.horses[i];
            rowData[i][0] = horse.getName();
            rowData[i][1] = horse.getBreed().value;
            rowData[i][2] = String.valueOf(horse.getConfidence());
            rowData[i][3] = String.valueOf(horse.getAvgSpeed());
            rowData[i][4] = String.valueOf(horse.getTotalDistanceTravelled());
            rowData[i][5] = String.valueOf(horse.getRacesWon());
            rowData[i][6] = String.valueOf(horse.getRacesLost());

        }
        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
        return tableModel;
    }

    private void openBettingConfirmationWindow(BettingStat newBet) {
        GameDialog customizeHorsesDialog = new GameDialog(parentFrame, "Betting Confirmation", true);
        customizeHorsesDialog.setSize(500, 200);
        customizeHorsesDialog.setLayout(new GridLayout(3, 1,10,5));
        GameLabel bettingConfirmation = new GameLabel("You successfully placed a bet", Font.PLAIN, 15, SwingConstants.CENTER);
        GameLabel bettingHorse = new GameLabel(newBet.getBetHorse(), Font.BOLD, 25, SwingConstants.CENTER);
        GameLabel bettingValue = new GameLabel(newBet.getBetAmount()+ "", Font.BOLD, 20, SwingConstants.CENTER);
        customizeHorsesDialog.add(bettingConfirmation);
        customizeHorsesDialog.add(bettingHorse);
        customizeHorsesDialog.add(bettingValue);
        customizeHorsesDialog.setVisible(true);
    }


}
