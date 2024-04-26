package pages;

import gameComponents.*;
import theme.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HorseDetailsPage extends GamePanel {

    public HorseDetailsPage(GameFrame parentFrame) {
        super(parentFrame);
        setLayout(new GridBagLayout());

        // Page Title
        JLabel pageTitle = new GameLabel("Horse Details", Font.BOLD, 25, SwingConstants.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        add(pageTitle, gbc);


        // Horse Details Panel

        GamePanel horseDetailsPanel = new GamePanel(new GridLayout(9,2, 5, 5));
        GameLabel nameLabel = new GameLabel("Horse Name", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel symbolLabel = new GameLabel("Symbol", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel confidenceLabel = new GameLabel("Confidence", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel breedLabel = new GameLabel("Breed", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel coatColorLabel = new GameLabel("Coat Color", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel distanceTravelledLabel = new GameLabel("Distance Travelled", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel winCountLabel = new GameLabel("Win Count", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel lossCountLabel = new GameLabel("Lose Count", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel avgSpeedLabel = new GameLabel("Average Speed", Font.PLAIN, 12, SwingConstants.LEFT);

        GameLabel nameValue = new GameLabel(Font.BOLD, 15, SwingConstants.RIGHT);
        GameLabel symbolValue = new GameLabel( Font.BOLD, 15, SwingConstants.RIGHT);
        GameLabel confidenceValue = new GameLabel(Font.BOLD, 15, SwingConstants.RIGHT);
        GameLabel breedValue = new GameLabel(Font.BOLD, 15, SwingConstants.RIGHT);
        GameLabel coatColorValue = new GameLabel( Font.BOLD, 15, SwingConstants.RIGHT);
        GameLabel distanceTravelledValue = new GameLabel(Font.BOLD, 15, SwingConstants.RIGHT);
        GameLabel winCountValue = new GameLabel( Font.BOLD, 15, SwingConstants.RIGHT);
        GameLabel lossCountValue = new GameLabel( Font.BOLD, 15, SwingConstants.RIGHT);
        GameLabel avgSpeedValue = new GameLabel( Font.BOLD, 15, SwingConstants.RIGHT);

        nameValue.setText("Dummy name");

        horseDetailsPanel.add(nameLabel);
        horseDetailsPanel.add(nameValue);

        horseDetailsPanel.add(symbolLabel);
        horseDetailsPanel.add(symbolValue);

        horseDetailsPanel.add(confidenceLabel);
        horseDetailsPanel.add(confidenceValue);

        horseDetailsPanel.add(breedLabel);
        horseDetailsPanel.add(breedValue);

        horseDetailsPanel.add(coatColorLabel);
        horseDetailsPanel.add(coatColorValue);

        horseDetailsPanel.add(distanceTravelledLabel);
        horseDetailsPanel.add(distanceTravelledValue);

        horseDetailsPanel.add(winCountLabel);
        horseDetailsPanel.add(winCountValue);

        horseDetailsPanel.add(lossCountLabel);
        horseDetailsPanel.add(lossCountValue);

        horseDetailsPanel.add(avgSpeedLabel);
        horseDetailsPanel.add(avgSpeedValue);

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 10;
        gbc.insets = new Insets(0,250,10,250);

        add(horseDetailsPanel, gbc);

        // Navigation Button Panel
        GamePanel navigationButtonPanel = new GamePanel(new GridLayout(1,1));

        GameButton backButton = new GameButton("Back");

        navigationButtonPanel.add(backButton);
        gbc.ipady = 10;
        gbc.gridy = 4;
        gbc.insets = new Insets(50,400,100,0);
        add(navigationButtonPanel, gbc);
    }

    private static JScrollPane getRaceStatTable() {
        JTable table = new GameTable(20, 5);

        Action viewDetails = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                ((DefaultTableModel)table.getModel()).removeRow(modelRow);
            }
        };

        GameButtonColumn buttonColumn = new GameButtonColumn(viewDetails, "View Details");
        buttonColumn.addToTable(table, 2);
        JScrollPane tableScrollPane = new JScrollPane(table);
        return tableScrollPane;
    }
}
