package pages;

import core.Race;
import gameComponents.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePlayingPage extends GamePanel {

    public GameGraphicPanel gameGraphicPanel;
    public GamePlayingPage(GameFrame parentFrame) {

        super(parentFrame);
        setLayout(new GridBagLayout());

        // Game Graphic Panel
        gameGraphicPanel = new GameGraphicPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 950;
        gbc.ipady = 540;
        add(gameGraphicPanel, gbc);

        // Navigation Button Panel
        GamePanel navigationButtonPanel = new GamePanel(new GridLayout(1,5,5,5));

        GameButton startGameButton = new GameButton("Start Race");
        GameButton stopGameButton = new GameButton("Stop/Reset Race");
        GameButton configureTrackButton = new GameButton("Configure Track");
        GameButton bettingButton = new GameButton("Bet On horse!");
        GameButton backToMainMenuButton = new GameButton("Back to main menu");

        registerButton(configureTrackButton, GamePanels.TRACK_CONFIGURATION_PAGE);
        registerButton(bettingButton, GamePanels.BETTING_PAGE);
        registerButton(backToMainMenuButton, GamePanels.LANDING_PAGE);

        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.startRaceSimulation();
            }
        });

        stopGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.stopRaceSimulation();
            }
        });

        navigationButtonPanel.add(startGameButton);
        navigationButtonPanel.add(stopGameButton);
        navigationButtonPanel.add(configureTrackButton);
        navigationButtonPanel.add(bettingButton);
        navigationButtonPanel.add(backToMainMenuButton);
        gbc.gridy = 1;
        gbc.ipadx = 0;
        gbc.ipady = 10;
        gbc.insets = new Insets(50,50,100,50);
        add(navigationButtonPanel, gbc);
    }

    public void loadData(){
        GamePlayingPage gamePlayingPage = ((GamePlayingPage) parentFrame.gamePanels.get(GamePanels.GAME_PLAYING_PAGE));
        parentFrame.race = new Race(parentFrame.raceDistance, parentFrame.selectedHorses, gamePlayingPage.gameGraphicPanel, parentFrame);
        gamePlayingPage.gameGraphicPanel.setRace(parentFrame.race);
        parentFrame.race.gameGraphicPanel.updateGraphicPanel();
    }
}
