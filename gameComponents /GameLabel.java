package gameComponents;

import theme.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameLabel extends JLabel {
    public GameLabel(String text, int fontStyle, int fontSize, int horizontalAlignment) {
        initialize(fontStyle, fontSize, horizontalAlignment);
        setText(text);
    }

    public GameLabel(int fontStyle, int fontSize, int horizontalAlignment) {
        initialize(fontStyle, fontSize, horizontalAlignment);
    }

    private void initialize(int fontStyle, int fontSize, int horizontalAlignment) {
        setFont(new Font("Courier New", fontStyle, fontSize));
        setHorizontalAlignment(horizontalAlignment);
        setForeground(Color.PanelTextColor);
    }
}
