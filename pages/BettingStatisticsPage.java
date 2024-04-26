package pages;

import core.BetStatus;
import core.BettingStat;
import core.RaceStat;
import gameComponents.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BettingStatisticsPage extends GamePanel {

    private JTable table;
    public BettingStatisticsPage(GameFrame parentFrame) {
        super(parentFrame);
        setLayout(new GridBagLayout());

        // Page Title
        JLabel pageTitle = new GameLabel("Betting Statistics", Font.BOLD, 25, SwingConstants.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        add(pageTitle, gbc);


        // Race Statistics Table
        JScrollPane tableScrollPane = getBettingStatTable();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 100;
        add(tableScrollPane, gbc);

        // Navigation Button Panel
        GamePanel navigationButtonPanel = new GamePanel(new GridLayout(1,1));

        GameButton backButton = new GameButton("Back");
        registerButton(backButton, GamePanels.LANDING_PAGE);

        navigationButtonPanel.add(backButton);
        gbc.ipady = 10;
        gbc.gridy = 4;
        gbc.insets = new Insets(50,400,100,0);
        add(navigationButtonPanel, gbc);
    }

    private JScrollPane getBettingStatTable() {
        table = new GameTable(loadTableModel());
        JScrollPane tableScrollPane = new JScrollPane(table);
        return tableScrollPane;
    }

    public void loadData(){
        table.setModel(loadTableModel());
    }

    public DefaultTableModel loadTableModel(){
        String[] columnNames = {"Bet Horse", "Bet Amount", "Status"};
        String[][] rowData = new String[parentFrame.bettingStats.size()][5];
        for (int i = 0; i < parentFrame.bettingStats.size(); i++) {
            BettingStat bettingStat = parentFrame.bettingStats.get(i);
            rowData[i][0] = bettingStat.getBetHorse();
            rowData[i][1] = String.valueOf(bettingStat.getBetAmount());
            rowData[i][2] = String.valueOf(bettingStat.getStatus());
        }
        return new DefaultTableModel(rowData, columnNames);
    }
}
