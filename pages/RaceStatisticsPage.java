package pages;

import core.RaceStat;
import gameComponents.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaceStatisticsPage extends GamePanel {

    private JTable table;
    GameButtonColumn buttonColumn;
    public RaceStatisticsPage(GameFrame parentFrame) {
        super(parentFrame);
        setLayout(new GridBagLayout());

        // Page Title
        JLabel pageTitle = new GameLabel("Race Statistics", Font.BOLD, 25, SwingConstants.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        add(pageTitle, gbc);


        // Race Statistics Table
        JScrollPane tableScrollPane = getRaceStatTable();

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

    private JScrollPane getRaceStatTable() {

        table = new GameTable(loadTableModel());

        Action viewDetails = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                int row = Integer.valueOf( e.getActionCommand() );
                parentFrame.navigateToPanel(GamePanels.RACE_DETAILS_PAGE);
                ((RaceDetailsPage) parentFrame.gamePanels.get(GamePanels.RACE_DETAILS_PAGE)).setRaceStat(parentFrame.raceStats.get(row));
            }
        };

        buttonColumn = new GameButtonColumn(viewDetails, "More Details");
        buttonColumn.addToTable(table, 4);
        return new JScrollPane(table);
    }

    public DefaultTableModel loadTableModel(){
        String[] columnNames = {"Distance", "Winner", "Time Duration", "No. of Tracks", ""};
        String[][] rowData = new String[parentFrame.raceStats.size()][5];
        for (int i = 0; i < parentFrame.raceStats.size(); i++) {
            RaceStat raceStat = parentFrame.raceStats.get(i);
            rowData[i][0] = String.valueOf(raceStat.getDistance());
            rowData[i][1] = raceStat.getWinner();
            rowData[i][2] = String.valueOf(raceStat.getTimeDuration());
            rowData[i][3] = String.valueOf(raceStat.getNoOfTracks());
            rowData[i][4] = "More Details";
        }
        return new DefaultTableModel(rowData, columnNames);
    }

    public void loadData(){
        table.setModel(loadTableModel());
        buttonColumn.addToTable(table, 4);
    }
}
