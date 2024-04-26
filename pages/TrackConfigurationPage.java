package pages;

import core.Horse;
import gameComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackConfigurationPage extends GamePanel {

    private GameNumberInput trackCountValue;
    private GameNumberInput trackDistanceValue;
    private GamePanel trackPanel;
    private GameComboBox<String>[] gameComboBoxes;

    public TrackConfigurationPage(GameFrame parentFrame) {
        super(parentFrame);
        setLayout(new GridBagLayout());

        // Page Title
        JLabel pageTitle = new GameLabel("Track Configuration", Font.BOLD, 25, SwingConstants.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        add(pageTitle, gbc);

        // Input Panel
        GamePanel inputPanel = new GamePanel(new GridLayout(2,2,2,2));

        GameLabel trackCountLabel = new GameLabel("No. of tracks", Font.PLAIN, 10, SwingConstants.LEFT);
        GameLabel trackDistanceLabel = new GameLabel("Track distance", Font.PLAIN, 10, SwingConstants.LEFT);
        trackCountValue = new GameNumberInput(parentFrame.numberOfLanes, 2, 6, 1);
        trackDistanceValue = new GameNumberInput(parentFrame.raceDistance, 20, 100, 10);

        inputPanel.add(trackCountLabel);
        inputPanel.add(trackCountValue);
        inputPanel.add(trackDistanceLabel);
        inputPanel.add(trackDistanceValue);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 10;
        gbc.insets = new Insets(0,100,10,100);
        add(inputPanel, gbc);

        // Update Button
        GameButton updateButton = new GameButton("Update Tracks");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTrackPanel(false);
            }
        });
        gbc.gridy = 2;
        add(updateButton, gbc);


        // Core.Track Panel
        trackPanel = new GamePanel();
        trackPanel.setLayout(new BoxLayout(trackPanel, BoxLayout.Y_AXIS));
        JScrollPane trackScrollPane = new JScrollPane(trackPanel);
        trackScrollPane.setPreferredSize(new Dimension(500, 1000));

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipady = 100;
        add(trackScrollPane, gbc);

        // Navigation Button Panel
        GamePanel navigationButtonPanel = new GamePanel(new GridLayout(1,2));

        GameButton backButton = new GameButton("Back");
        GameButton saveButton = new GameButton("Save");

        registerBackButton(backButton);
        registerBackButton(saveButton);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.numberOfLanes = ((Double) trackCountValue.getValue()).intValue();
                parentFrame.raceDistance = ((Double) trackDistanceValue.getValue()).intValue();
                parentFrame.selectedHorses = new Horse[parentFrame.numberOfLanes];
                for (int y = 0; y < parentFrame.numberOfLanes; y++){
                    parentFrame.selectedHorses[y] = parentFrame.horses[gameComboBoxes[y].getSelectedIndex()];
                }
            }
        });

        navigationButtonPanel.add(backButton);
        navigationButtonPanel.add(saveButton);
        gbc.ipady = 10;
        gbc.gridy = 4;
        gbc.insets = new Insets(50,400,100,0);
        add(navigationButtonPanel, gbc);
    }

    private void updateTrackPanel(boolean isUpdate) {
        trackPanel.removeAll();
        int trackCount = ((Double) trackCountValue.getValue()).intValue();
        String[] horseNames = new String[parentFrame.horses.length];
        gameComboBoxes = new GameComboBox[trackCount];
        for (int x = 0; x < parentFrame.horses.length; x++){
            horseNames[x] = parentFrame.horses[x].getName();
        }
        for (int i = 0; i < trackCount; i++) {
            GamePanel trackRow = new GamePanel(new GridLayout(1,2));
            JLabel trackLabel = new GameLabel(" Track " + i + ":", Font.BOLD, 10, SwingConstants.LEFT);
            GameComboBox<String> horseComboBox = new GameComboBox<>(horseNames);
            if(isUpdate){
                horseComboBox.setSelectedItem(parentFrame.selectedHorses[i].getName());
            }
            trackRow.add(trackLabel);
            trackRow.add(horseComboBox);
            gameComboBoxes[i] = horseComboBox;
            trackPanel.add(trackRow);
        }
        trackPanel.revalidate();
        trackPanel.repaint();
    }

    public void loadData(){
        this.trackCountValue.setValue((double) parentFrame.numberOfLanes);
        this.trackDistanceValue.setValue((double) parentFrame.raceDistance);
        updateTrackPanel(true);
    }
}
