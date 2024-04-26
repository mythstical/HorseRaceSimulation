package gameComponents;

import javax.swing.*;

public class GameNumberInput extends JSpinner {

    public GameNumberInput(double initialValue, double minValue, double maxValue, double stepSize) {
        super(new SpinnerNumberModel(initialValue, minValue, maxValue, stepSize));
        ((JSpinner.DefaultEditor) getEditor()).getTextField().setEditable(false);
    }
}
