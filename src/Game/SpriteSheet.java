package Game;

import java.awt.image.BufferedImage;

//Clasa imi decupeaza o imagine mare, in tile-uri mai mici, de diverse dimensiuni.
public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x,y,width,height); // Return a new BufferedImage of the area specified [Returning the tile]
    }

}
