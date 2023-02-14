package Entity.Creature;

import AudioPlayer.Audio;
import AudioPlayer.AudioManager;
import Bullet.Bullet;
import Entity.Current_Direction;
import Entity.Entity;
import Game.*;
import Objects.Coin;
import State.*;
import Tile.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static Database.Database.GetMusic;

public class Player extends Creature{

    //Animations
    private Animation Idle_R;
    private Animation Idle_L;
    private Animation aFlyR;
    private Animation aFlyL;
    private Animation R_Attack;
    private Animation L_Attack;
    private Animation R_Die;

    boolean isLeft, isRight;

    private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
    private Current_Direction current_direction;

    public static int[] temp = GameState.SetDificulty();
    public static Audio audio = new Audio(GetMusic(1));

    public static int bulletdamage = temp[1];
    public static int health = temp[0];

    public Player(Handler handler, float x, float y, int Speed) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        //COLLISION COORD
        bounds.x = 16;
        bounds.y = 16;
        bounds.width = 48;
        bounds.height = 48;

        this.speed = Speed;

        //Animations
        Idle_R = new Animation(150, Assets.Player_Idle_R);
        Idle_L = new Animation(150, Assets.Player_Idle_L);
        aFlyR = new Animation(150, Assets.Player_Fly_R);
        aFlyL = new Animation(150, Assets.Player_Fly_L);
        R_Attack = new Animation(125, Assets.PlayerAttackR);
        L_Attack = new Animation(125, Assets.PlayerAttackL);

        current_direction = Current_Direction.right;

    }

    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for (Entity e : handler.getLevel().getEntityManager().getEntities()){
            if (e.equals(this)){
                continue;
            }

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                counter++;
                if(counter > 7) {
                    hurt(1);
                    counter = 0;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void addCoins(Coin coin) {
        if(coin.getId() == 9){
            addPoints(50);
        }
        AddHealthAndDamage(5);
    }

    @Override
    public void tick() {

        //Aniamtion
        Idle_R.tick();
        Idle_L.tick();
        //aLWalk.tick();
        //aRWalk.tick();
        R_Attack.tick();
        L_Attack.tick();
        aFlyR.tick();
        aFlyL.tick();

        System.out.println(x + " " + y);

        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        checkAttacks(this.bulletdamage);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up &&  handler.getKeyManager().number < 1)
            jumpStrength = 4.75f;


        yMove-= jumpStrength;
        jumpStrength -= Weight;
        if(yMove > 6)
            yMove = 3.25f;

        if(handler.getKeyManager().left) {
            xMove = -speed;
            current_direction = Current_Direction.left;
            isLeft = true;
            isRight = false;
        }
        if(handler.getKeyManager().right) {
            xMove = speed;
            current_direction = Current_Direction.right;
            isLeft = false;
            isRight = true;
        }

        //if(handler.getKeyManager().space)
        //    handler.getLevel().getBulletManager().addBullet(new Bullet(handler, current_direction, x + bounds.x , y + bounds.y ));
    }

    private void checkAttacks(int Damage){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown){
            return;
        }

        if (handler.getKeyManager().space) {
            audio = new Audio(GetMusic(1));
            audio.play();
            handler.getLevel().getBulletManager().addBullet(new Bullet(handler, current_direction, x, y, Damage, true, Assets.Shots1));
        }
        else{
            return;
        }

        attackTimer = 0;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame(){

       /*if(handler.getKeyManager().up && handler.getKeyManager().left)
            return aFlyL.getCurrentFrame();
        else if(handler.getKeyManager().up)
            return aFlyR.getCurrentFrame();
        else if(handler.getKeyManager().up && handler.getKeyManager().right)
            return aFlyR.getCurrentFrame();
        else if(handler.getKeyManager().left)
            return aFlyL.getCurrentFrame();
        else if(handler.getKeyManager().right)
            return aFlyR.getCurrentFrame();
        else if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT) &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT) &&
               !collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT) &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT))
            return aFlyR.getCurrentFrame();*/

        if(handler.getKeyManager().space && isRight && !isLeft)
            return R_Attack.getCurrentFrame();
        else if(handler.getKeyManager().space && !isRight && isLeft)
            return L_Attack.getCurrentFrame();

        if(isRight) {
            if (xMove < 0) {
                return aFlyL.getCurrentFrame();
            } else if (xMove > 0)
                return aFlyR.getCurrentFrame();
            else if (collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT) &&
                    collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT) &&
                    collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT) &&
                    collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT))
                return aFlyR.getCurrentFrame();
        }
        else{
            if (xMove > 0) {
                return aFlyR.getCurrentFrame();
            } else if (xMove < 0)
                return aFlyL.getCurrentFrame();
            else if (collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT) &&
                    collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT) &&
                    collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT) &&
                    collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT))
                return aFlyL.getCurrentFrame();
        }

        if (isLeft)
            return Idle_L.getCurrentFrame();
        else
            return Idle_R.getCurrentFrame();
    }

    @Override
    public void die() {
        System.out.println("You Lost !");
        Trying++;
        State.setState(handler.getGame().loseState);
        handler.getGame().audioManager.stopMusic();
        handler.getGame().audioManager = new AudioManager(handler.getGame(), 4);
        handler.getGame().audioManager.playMusic();
        handler.getLevel().getEntityManager().removePlayer(handler.getPlayer());
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int Point){
        points = Point;
    }

    public static void addPoints(int amount){
        points += amount;
    }

    public int getDamage(){
        return damage;
    }

    public static void AddHealthAndDamage(int amount) {
        addHealth(amount);
        bulletdamage += 1;
    }

    public int getBulletdamage() {
        return bulletdamage;
    }

    public void setBulletdamage(int bulletdamage) {
        Player.bulletdamage = bulletdamage;
    }

    public static void addHealth(int Health){
        health += Health;
    }

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int health) {
        Player.health = health;
    }
}
