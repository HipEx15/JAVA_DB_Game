package Bullet;

import Entity.Creature.Player;
import Game.Handler;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class BulletManager {
    private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    Handler handler;

    public BulletManager(Handler handler){
        this.handler = handler;
    }

    public void tick(){
        Iterator<Bullet> it = bullets.iterator();

        while(it.hasNext()){
            Bullet b = it.next();
            b.tick();
            if (!b.isActive()){
                b.die();
                it.remove();
            }
        }

        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).tick();
        }}

    public void render(Graphics g){
        for(int i=0; i < bullets.size(); ++i){
            bullets.get(i).render(g);
        }
    }

    public void addBullet(Bullet block){
        bullets.add(block);
    }

    public void removeBullet(Bullet block){
        bullets.remove(block);
    }
}
