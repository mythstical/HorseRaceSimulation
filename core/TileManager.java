import gameComponents.GameGraphicPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GameGraphicPanel gameGraphicPanel;
    Tile[] tiles;

    public TileManager(GameGraphicPanel gameGraphicPanel){
        this.gameGraphicPanel = gameGraphicPanel;

        this.tiles = new Tile[10];
        getTilesImages();
    }

    public void getTilesImages(){
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass-track.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateUI(Graphics2D g2){
        for (int i = 0; i < gameGraphicPanel.maxScreenCol; i++){
            for (int j = 0; j < gameGraphicPanel.maxScreenRow - 1; j++){
                Tile tile = tiles[0];
                if( j % 2 == 0){
                    tile = tiles[1];
                }
                if( j == 0 || j ==  gameGraphicPanel.maxScreenRow - 2){
                    tile = tiles[2];
                }
                g2.drawImage(
                        tile.image,
                        i * gameGraphicPanel.tileSize,
                        j * gameGraphicPanel.tileSize,
                        gameGraphicPanel.tileSize,
                        gameGraphicPanel.tileSize,
                        null);
            }
        }


    }
}
