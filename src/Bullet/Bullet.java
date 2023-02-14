package Bullet;

import Entity.Creature.Creature;
import Entity.Creature.Player;
import Entity.Current_Direction;
import Entity.Entity;
import Game.Assets;
import Game.Handler;
import Tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Entity.Creature.Player.audio;

//Crearea proiectilelor pentru inamici si jucatori.
public class Bullet extends Entity {
    private int xMove, yMove;
    private final int speed = 3;
    private int firepower;
    private boolean enemy_fire = true;

    BufferedImage image;
    private Current_Direction current_direction;

    public Bullet(Handler handler, Current_Direction current_direction, float x, float y, int fire_power, boolean friendly_fire, BufferedImage[] buffer_images) {
        super(handler, x, y, 16, 12);
        this.firepower = fire_power;
        this.enemy_fire = friendly_fire;
        bounds.x = 14;
        bounds.y = 20;
        xMove = 0;
        yMove = 0;
        switch (current_direction) {
            case left:
                this.y += Creature.DEFAULT_CREATURE_HEIGHT / 2 - 40 ;
                this.x -= 45;
                image = buffer_images[1];
                xMove = -speed;
                break;
            case right:
                image = buffer_images[0];
                this.y += Creature.DEFAULT_CREATURE_HEIGHT / 2 - 40 ;
                this.x += Creature.DEFAULT_CREATURE_WIDTH;
                xMove = speed;
                break;
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (Entity e : handler.getLevel().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                e.hurt(firepower);
                return true;
            }
        }
        return false;
    }


    public void tick() {
        //x += xMove;
        //y += yMove;
        move();
        if (checkEntityCollisions(xMove, 0f)) {
            active = false;
        }
        if (checkEntityCollisions(0f, yMove)) {
            active = false;
        }
    }

    @Override
    public void die() {
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 48, 48, null);
        //g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
        //        (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
        //        bounds.width, bounds.height);

    }

    public void move() {
        if (!checkEntityCollisions(xMove, 0f)) {
            moveX();
        }
        if (!checkEntityCollisions(0f, yMove)) {
            moveY();
        }
    }

    public void moveX() {
        if (xMove > 0) {
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                active = false;
            }
        } else if (xMove < 0) {
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                active = false;
            }
        }
    }

    public void moveY() {
        if (yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += yMove;
            } else {
                active = false;
            }
        } else if (yMove > 0) {
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += yMove;
            } else {
                active = false;
            }
        }

    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getLevel().getTile(x, y).isNotTraverseble();
    }
}
