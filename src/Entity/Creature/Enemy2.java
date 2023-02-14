package Entity.Creature;

import Bullet.Bullet;
import Entity.Current_Direction;
import Entity.Entity;
import Game.Animation;
import Game.Assets;
import Game.Handler;
import Objects.Coin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static Entity.Creature.Player.AddHealthAndDamage;

public class Enemy2 extends Enemy{

    protected long lastMoveTimer, moveCoolDown = 2000, moveTimer = moveCoolDown;
    private long lastAttackTimer, attackCooldown = 1500, attackTimer = attackCooldown;
    private Current_Direction current_direction;

    public int health;
    public int speed;

    private Animation Idle;
    private Animation Walk_R;
    private Animation Walk_L;

    public Enemy2(Handler handler, float x, float y, int Health, int Damage, int Speed) {
        super(handler, x, y, Health, Damage, Speed);
        bounds.x = 16;
        bounds.y = 16;
        bounds.width = 48;
        bounds.height = 48;

        this.speed = Speed;
        this.health = Health;
        this.damage = Damage;

        current_direction = Current_Direction.right;

        Idle = new Animation(150, Assets.Enemie2_Idle);
        Walk_R = new Animation(150, Assets.Enemie2_R);
        Walk_L = new Animation(150, Assets.Enemie2_L);

    }

    protected void checkAttacks(int damage){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown){
            return;
        }
        handler.getLevel().getBulletManager().addBullet(new Bullet(handler, current_direction, x  , y , damage, true,Assets.Shots3));

        attackTimer = 0;
    }

    @Override
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for (Entity e : handler.getLevel().getEntityManager().getEntities()){
            if (e.equals(this)){
                continue;
            }

            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                return true;
            }
        }
        return false;
    }

    @Override
    public void addCoins(Coin coin) {

    }

    public void hurt(int amount){
        this.health -= amount;
        if(health <= 0) {
            die();
        }
    }

    private void getInput(){

        Random rand = new Random();
        int n = rand.nextInt(2);
        if (n == 0){
            checkAttacks(damage);

        }
        moveTimer += System.currentTimeMillis() - lastMoveTimer;
        lastMoveTimer = System.currentTimeMillis();
        if (moveTimer < moveCoolDown){
            return;
        }
        System.out.println("Generez miscare2");
        xMove = 0;
        yMove = 0;
        n = rand.nextInt(100);
        if (n > 50) {
            xMove = -speed;
            current_direction = Current_Direction.left;
        } else{
            xMove = speed;
            current_direction = Current_Direction.right;
        }
        moveTimer = 0;
    }

    @Override
    public void tick() {
        getInput();
        move();

        Idle.tick();
        Walk_R.tick();
        Walk_L.tick();
    }

    @Override
    public void die() {
        handler.getLevel().getEntityManager().removeEnemie(this);
        Player.addPoints(100);
        AddHealthAndDamage(25);
    }

    @Override
    public void render(Graphics g) {
        drawHealth(g);
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame(){

        if (xMove < 0)
            return Walk_L.getCurrentFrame();
        else if (xMove > 0)
            return Walk_R.getCurrentFrame();

        return Idle.getCurrentFrame();

    }

    protected void drawHealth(Graphics g){
        Font fnt1 = new Font("arial", Font.BOLD, 15);
        String text = Integer.toString(health);
        int text_width = g.getFontMetrics().stringWidth(text);
        g.setColor(Color.ORANGE);
        g.setFont(fnt1);
        g.drawString(text, (int) (x - handler.getGameCamera().getxOffset() + bounds.x + bounds.width/2 - text_width/2), (int) (y - handler.getGameCamera().getyOffset()));
    }
}
