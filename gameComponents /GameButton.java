package gameComponents;

import theme.Color;
import javax.swing.*;
import java.awt.*;

public class GameButton extends JButton {
    public GameButton(String title) {
        setBackground(Color.ButtonBackgroundColor);
        setForeground(Color.ButtonTextColor);
        setFont(new Font("Courier New", Font.BOLD, 10));
        setText(title);
        setFocusable(false);
    }
}
