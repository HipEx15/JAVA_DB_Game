package Entity;

import Entity.Creature.Boss;
import Entity.Creature.Enemy;
import Entity.Creature.Enemy2;
import Entity.Creature.Player;
import Game.Handler;
import State.GameState;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private Enemy enemy;
    private Enemy2 enemy2;
    private Boss boss;
    private ArrayList<Entity> entities;

    public int[] vect2 = GameState.SetDificultyEnemies();

    //Rendering object behind player and vice versa
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b){
            if (a.getY() + a.getHeight() < b.getY() + b.getHeight()){
                return -1;
            }
            return 1;
        }
    };

    public EntityManager(Handler handler){

        this.handler = handler;
        entities = new ArrayList<Entity>();
    }

    public void tick(){

        Iterator<Entity> it = entities.iterator();

        while(it.hasNext()){
            Entity e = it.next();
            e.tick();
            if (!e.isActive()){
                it.remove();
            }
        }
        entities.sort(renderSorter);
    }

    public void render(Graphics g){
        for(Entity e : entities){
            e.render(g);
        }
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public void addPlayer(Player player){
        this.player = player;
        addEntity(player);
    }

    public void addEnemie(Enemy enemy){
        this.enemy = enemy;
        addEntity(enemy);
    }

    public void removeEnemie(Enemy enemy){
        entities.remove(enemy);
        handler.getLevel().NumberOfEnemy--;
    }

    public void spawnEnemie(float x, float y){
        addEnemie(new Enemy(handler, x, y, vect2[0], vect2[1], vect2[2]));
    }

    public void addEnemy2(Enemy2 enemy){
        this.enemy2 = enemy;
        addEntity(enemy2);
    }

    public void spawnEnemy2(float x, float y){
        addEnemy2(new Enemy2(handler, x, y, vect2[3], vect2[4], vect2[5]));
    }

    public void addBoss(Boss enemy){
        this.boss = enemy;
        addEntity(boss);
    }

    public void spawnBoss(float x, float y){
        addBoss(new Boss(handler, x, y, vect2[6], vect2[7], vect2[8]));
    }

    public void removePlayer(Player player){
        entities.remove(player);
    }


}
