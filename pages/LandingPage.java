package pages;

import gameComponents.*;

import javax.swing.*;
import java.awt.*;

public class LandingPage extends GamePanel {

    private final GameButton startButton;
    private final GameButton raceStatisticsButton;
    private final GameButton customizeHorseButton;
    private final GameButton bettingStatButton;
    private final GameButton quitGameButton;

    public LandingPage(GameFrame parentFrame) {
        super(parentFrame);
        setLayout(new GridLayout(4,1));
        JLabel gameTitle = new GameLabel("Horse Game", Font.BOLD, 35, SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon(getClass().getResource("horse-icon.png"));
        JLabel titleIcon = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
        titleIcon.setVerticalAlignment(JLabel.BOTTOM);
        add(titleIcon);
        add(gameTitle);
        GamePanel buttonPanel = new GamePanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.ipady = 10;


        startButton = new GameButton("Start Game");
        raceStatisticsButton = new GameButton("Race Statistics");
        customizeHorseButton = new GameButton("Customize Horse");
        bettingStatButton = new GameButton("Betting Statistics");
        quitGameButton = new GameButton("Quit Game");

        registerButton(startButton, GamePanels.GAME_PLAYING_PAGE);
        registerButton(raceStatisticsButton, GamePanels.RACE_STATISTIC_PAGE);
        registerButton(customizeHorseButton, GamePanels.HORSE_CUSTOMIZATION_PAGE);
        registerButton(bettingStatButton, GamePanels.BETTING_STAT_PAGE);


        buttonPanel.add(startButton, gbc);
        buttonPanel.add(raceStatisticsButton, gbc);
        buttonPanel.add(bettingStatButton, gbc);
        buttonPanel.add(customizeHorseButton, gbc);
        buttonPanel.add(quitGameButton, gbc);
        add(buttonPanel);
    }
}
