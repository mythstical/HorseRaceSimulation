package gameComponents;

import theme.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    protected GameFrame parentFrame;
    public GamePanel(LayoutManager layout) {
        super(layout);
        initialize();
    }
    public GamePanel(){
        initialize();
    }

    public GamePanel(GameFrame parentFrame){
        this.parentFrame = parentFrame;
        initialize();
    }

    public void initialize(){
        setBackground(Color.PanelBackgroundColor);
    }

    public void registerButton(JButton button, GamePanels panel){
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               parentFrame.navigateToPanel(panel);
            }
        });
    }

    public void registerBackButton(JButton button){
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentFrame.navigateToPanel(parentFrame.previousPanel);
            }
        });
    }

    public void loadData(){}

}
