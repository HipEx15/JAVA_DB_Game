package UI;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Utils.Utils.LoadFont;

//Clasa destinata randarii butoanelor din cadrul jocului.

public class UIButton extends UIObject{
    private Rectangle rect;
    private ClickListener clicker;
    private String text;

    public UIButton(float x, float y, int width, int height, ClickListener clicker, String text) {
        super(x, y, width, height);
        this.clicker = clicker;
        this.text = text;
    }



    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        // g2d = (Graphics2D) g;

        LoadFont(g, 20);

        if (hover){
            //g2d.draw(bounds);
            g.setColor(Color.ORANGE);
            g.drawString(text, bounds.x + 20, bounds.y + 30);

        } else{
            g.setColor(Color.WHITE);
            g.drawString(text, bounds.x + 20, bounds.y + 30);
            //g2d.draw(bounds);
        }

    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
