import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HorseManager {

    private static HorseManager horseManager;

    private final Map<Breed, BufferedImage[]> horseBreadImgMapper;

    public HorseManager(){
        horseBreadImgMapper = new HashMap<>();
        getHorseImages();
    }

    public static HorseManager getInstance(){
        if (horseManager == null){
            horseManager = new HorseManager();
        }
        return horseManager;
    }

    private void getHorseImages(){
        try {
            BufferedImage[] arabianHorseImages = new BufferedImage[3];
            arabianHorseImages[0] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_a1.png"));
            arabianHorseImages[1] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_a2.png"));
            arabianHorseImages[2] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_a3.png"));
            horseBreadImgMapper.put(Breed.ARABIAN_HORSE, arabianHorseImages);

            BufferedImage[] mustangHorseImages = new BufferedImage[3];
            mustangHorseImages[0] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_b1.png"));
            mustangHorseImages[1] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_b2.png"));
            mustangHorseImages[2] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_b3.png"));
            horseBreadImgMapper.put(Breed.MUSTANG, mustangHorseImages);

            BufferedImage[] shireHorseImages = new BufferedImage[3];
            shireHorseImages[0] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_c1.png"));
            shireHorseImages[1] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_c2.png"));
            shireHorseImages[2] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_c3.png"));
            horseBreadImgMapper.put(Breed.SHIRE_HORSE, shireHorseImages);

            BufferedImage[] bretonHorseImages = new BufferedImage[3];
            bretonHorseImages[0] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_d1.png"));
            bretonHorseImages[1] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_d2.png"));
            bretonHorseImages[2] = ImageIO.read(getClass().getResourceAsStream("/horses/horse_d3.png"));
            horseBreadImgMapper.put(Breed.BRETON_HORSE, bretonHorseImages);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage getHorseImage(Horse horse){
       return horseBreadImgMapper.get(horse.getBreed())[horse.getImgFrameChanger()];
    }
    public BufferedImage getHorseStaticImage(Horse horse){
        return horseBreadImgMapper.get(horse.getBreed())[0];
    }

    public BufferedImage getHorseStaticImgByBreed(Breed breed){
        return horseBreadImgMapper.get(breed)[0];
    }

}
