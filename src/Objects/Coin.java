package Objects;

import AudioPlayer.Audio;
import Entity.Creature.Enemy;
import Entity.Entity;
import Game.Assets;
import Game.Handler;
import Tile.Tile;

import javax.management.openmbean.OpenMBeanConstructorInfoSupport;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Database.Database.GetMusic;

//Crearea monedelor care sunt colectabile.
public class Coin {

    public static Coin[] coins = new Coin[256];
    public static Coin coin = new Coin(Assets.Coin, 9);

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    protected Handler handler;
    protected BufferedImage texture;
    protected final int id;
    protected int x;
    protected int y;

    protected int count;
    boolean pickedUp = false;

    protected Rectangle bounds;

    public static Audio audio = new Audio(GetMusic(8));

    public Coin(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        count = 1;
        bounds = new Rectangle(x,y, 64,64);

        coins[id] = this;
    }

    public synchronized void tick(){
        if (handler.getLevel().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
            audio = new Audio(GetMusic(8));
            audio.play();
            pickedUp = true;
            handler.getLevel().getEntityManager().getPlayer().addCoins(this);
        }
    }

    public void render(Graphics g){
        if (handler == null){
            return;
        }
        render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, 64, 64, null);
    }

    public Coin createNew(int x, int y){
        Coin i = new Coin(texture, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y){
        this.x = x + Tile.TILE_WIDTH/2 - bounds.width/2;
        this.y = y + Tile.TILE_HEIGHT/2 - bounds.height/2;
        bounds.x = this.x;
        bounds.y = this.y;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }


}
