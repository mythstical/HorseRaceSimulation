package pages;

import core.*;
import gameComponents.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class HorseCustomizationPage extends GamePanel {

    private final GameComboBox<Breed> breedValue;
    private final GameTextInput horseNameValue;
    private final GameNumberInput confidenceValue;
    private final JLabel appearanceValue;
    private final GameComboBox<String> horseSelectionValue;

    private final HorseManager horseManager = HorseManager.getInstance();

    public HorseCustomizationPage(GameFrame parentFrame) {
        super(parentFrame);
        setLayout(new GridBagLayout());

        // Page Title
        JLabel pageTitle = new GameLabel("Horse Customization", Font.BOLD, 25, SwingConstants.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        add(pageTitle, gbc);

        // Input Panel
        GamePanel inputPanel = new GamePanel(new GridLayout(5,2,2,2));

        GameLabel horseSelectionLabel = new GameLabel("Select Horse", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel horseNameLabel = new GameLabel("Horse Name", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel breadLabel = new GameLabel("Breed", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel confidenceLabel = new GameLabel("Confidence", Font.PLAIN, 12, SwingConstants.LEFT);
        GameLabel appearanceLabel = new GameLabel("Appearance", Font.PLAIN, 12, SwingConstants.LEFT);

        horseSelectionValue = new GameComboBox<>();
        horseNameValue = new GameTextInput();
        breedValue = new GameComboBox<>(Breed.values());
        confidenceValue = new GameNumberInput(0.5,0,1,0.1);
        appearanceValue = new JLabel();

        for (int i = 0; i < this.parentFrame.horses.length; i++) {
            horseSelectionValue.addItem(this.parentFrame.horses[i].getName());
        }

        horseSelectionValue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedHorseIndex = horseSelectionValue.getSelectedIndex();
                if (selectedHorseIndex >= 0 && selectedHorseIndex < parentFrame.horses.length) {
                    Horse selectedHorse = parentFrame.horses[selectedHorseIndex];
                    breedValue.setSelectedItem(selectedHorse.getBreed());
                    confidenceValue.setValue(selectedHorse.getConfidence());
                    horseNameValue.setText(selectedHorse.getName());
                    ImageIcon imgIcon = new ImageIcon(horseManager.getHorseStaticImage(parentFrame.horses[selectedHorseIndex]));
                    appearanceValue.setIcon(imgIcon);
                }
            }
        });
        horseSelectionValue.setSelectedIndex(0);

        breedValue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImageIcon imgIcon = new ImageIcon(horseManager.getHorseStaticImgByBreed((Breed) breedValue.getSelectedItem()));
                appearanceValue.setIcon(imgIcon);
            }
        });


        inputPanel.add(horseSelectionLabel);
        inputPanel.add(horseSelectionValue);
        inputPanel.add(horseNameLabel);
        inputPanel.add(horseNameValue);
        inputPanel.add(breadLabel);
        inputPanel.add(breedValue);
        inputPanel.add(confidenceLabel);
        inputPanel.add(confidenceValue);
        inputPanel.add(appearanceLabel);
        inputPanel.add(appearanceValue);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipady = 10;
        gbc.insets = new Insets(0,100,10,100);
        add(inputPanel, gbc);

        // Navigation Button Panel
        GamePanel navigationButtonPanel = new GamePanel(new GridLayout(1,2));

        GameButton backButton = new GameButton("Back");
        GameButton saveButton = new GameButton("Save");
        registerBackButton(backButton);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(horseNameValue.getText(), "")){
                    validationWindow();
                } else{
                    int selectedHorseIndex = horseSelectionValue.getSelectedIndex();
                    if (selectedHorseIndex >= 0 && selectedHorseIndex < parentFrame.horses.length) {
                        Horse selectedHorse = parentFrame.horses[selectedHorseIndex];
                        selectedHorse.setBreed((Breed) breedValue.getSelectedItem());
                        selectedHorse.setConfidence((Double) confidenceValue.getValue());
                        selectedHorse.setName(horseNameValue.getText());

                        horseSelectionValue.removeAllItems();
                        for (int i = 0; i < parentFrame.horses.length; i++) {
                            horseSelectionValue.addItem(parentFrame.horses[i].getName());
                        }
                        horseSelectionValue.setSelectedIndex(selectedHorseIndex);
                    }
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

    private void validationWindow() {
        GameDialog validationDialog = new GameDialog(parentFrame, "Validation", true);
        validationDialog.setSize(500, 200);
        validationDialog.setLayout(new GridLayout(1, 1,10,5));
        GameLabel message = new GameLabel("Horse name cannot be empty", Font.PLAIN, 15, SwingConstants.CENTER);
        validationDialog.add(message);
        validationDialog.setVisible(true);
    }
}
