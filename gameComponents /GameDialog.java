package gameComponents;

import theme.Color;

import javax.swing.*;
import java.awt.*;

public class GameDialog extends JDialog {
    public GameDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        getContentPane().setBackground(Color.PanelBackgroundColor);
    }
}
