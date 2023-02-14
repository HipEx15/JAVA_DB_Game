package Entity;

import java.awt.*;

import AudioPlayer.Audio;
import Entity.Creature.Player;
import Entity.StaticEntity.Lava;
import Game.*;

import static Database.Database.GetMusic;

//Crearea tutoror entitatilor, atat cele dinamice, jucator, inamic, dar si cele statice, insemnand capcanele si lava.
public abstract class Entity {

    public static int DEFAULT_POINTS = 0;
    public static int DEFAULT_DAMAGE = 1;
    public int counter=0;
    public static int Trying = 0;

    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean active = true;
    public static int points = DEFAULT_POINTS;
    public static int damage;

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0,0 ,width,height);
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public void hurt(int amount){
        System.out.println(Player.health);
        Player.health -= amount;
        new Audio(GetMusic(9)).play();
        if(Player.health <= 0) {
            active = false;
            die();
        }
    }

    public abstract void tick();

    public abstract void die();

    public abstract void render(Graphics g);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean active){
        this.active = active;
    }


}
