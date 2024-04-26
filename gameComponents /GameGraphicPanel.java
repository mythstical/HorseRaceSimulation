package gameComponents;

import core.Horse;
import core.Race;
import core.Tile;
import core.TileManager;
import theme.Color;

import javax.swing.*;
import java.awt.*;

public class GameGraphicPanel extends JPanel {

    public final int originalTileSize = 16;
    public final int scale = 2;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 30;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    private Race race;
    private TileManager tileManager = new TileManager(this);

    public GameGraphicPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.PanelBackgroundColor);
        this.setDoubleBuffered(true);
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public void updateGraphicPanel() {
        this.repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        this.tileManager.updateUI(g2);
        if(this.race != null){
            this.race.updateUI(g2);
        }

    }

}
