package gameComponents;

import core.Breed;

import javax.swing.*;

public class GameComboBox<T> extends JComboBox<T> {
    public GameComboBox(T[] values) {
        super(values);
    }
    public GameComboBox() {
        super();
    }
}
