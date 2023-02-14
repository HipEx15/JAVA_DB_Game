package Levels;

import Bullet.BulletManager;
import Entity.Creature.*;
import Entity.EntityFactory;
import Entity.EntityManager;
import Exceptions.EmptyWorldFileException;
import Exceptions.UnknownTileException;
import Objects.Coin;
import Objects.CoinManager;
import State.GameState;
import State.Utils.Lvls;
import Tile.*;
import Utils.Utils;
import Game.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

import static State.Utils.Lvls.GetLevelWorld;
import static State.Utils.Lvls.GetLevelWorldDMG;

//Clasa care se ocupa de initializarea hartii si a tuturor entitatilor.
public class Levels {

    private Handler handler;
    private int width, height;// Size of map
    private int spawnX, spawnY;
    private int[][] tiles;
    private int[][] tilesDMG;
    public int NumberOfEnemy;

    //private int x_Enemy[] = {448, 657, 738, 1736, 2416, 1783, 2422};
    //private int y_Enemy[] = {566, 950, 182, 440, 630, 823, 1018};

    public Vector<Integer> spawnEnemyX;
    public Vector<Integer> spawnEnemyY;

    private EntityFactory entityFactory;
    private CoinManager coinManager;
    private BulletManager bulletManager;

    //Entities
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Levels(Handler handler, Lvls.lvls path1, Lvls.lvls path2){
        this.handler = handler;
        bulletManager = new BulletManager(handler);
        entityManager = new EntityManager(handler);
        entityFactory = new EntityFactory(handler);
        coinManager = new CoinManager(handler);

        loadLevel(GetLevelWorld(path1));
        loadDMG(GetLevelWorldDMG(path2));

        //entityManager.addEnemie(new Enemy(handler, 448, 566));
    }

    public void setLevel(String path1, String path2) {

        int[] vect1 = GameState.SetDificulty();

        bulletManager = new BulletManager(handler);
        entityManager = new EntityManager(handler);
        entityFactory = new EntityFactory(handler);
        coinManager = new CoinManager(handler);

        loadLevel(path1);
        loadDMG(path2);

        entityManager.addPlayer(new Player(handler, 100, 100, vect1[2]));

        if (handler.getLevel().NumberOfEnemy == 7) {
            int i;
            for(i=0; i < NumberOfEnemy; ++i)
                entityManager.spawnEnemie(spawnEnemyX.get(i), spawnEnemyY.get(i));
        } else if (handler.getLevel().NumberOfEnemy == 12) {
            for(int i=0; i < NumberOfEnemy/2; ++i)
                entityManager.spawnEnemie(spawnEnemyX.get(i), spawnEnemyY.get(i));
            for(int i=NumberOfEnemy/2; i < NumberOfEnemy; ++i)
                entityManager.spawnEnemy2(spawnEnemyX.get(i), spawnEnemyY.get(i));
        } else if (handler.getLevel().NumberOfEnemy == 13) {

            for(int i=0; i < NumberOfEnemy/2; ++i)
                entityManager.spawnEnemie(spawnEnemyX.get(i), spawnEnemyY.get(i));
            for(int i=NumberOfEnemy/2; i < NumberOfEnemy - 1; ++i)
                entityManager.spawnEnemy2(spawnEnemyX.get(i), spawnEnemyY.get(i));

            entityManager.spawnBoss(spawnEnemyX.elementAt(spawnEnemyX.size() - 1), spawnEnemyY.elementAt(spawnEnemyY.size() - 1));
        }
        else{
            for(int i=0; i < NumberOfEnemy/2; ++i)
                entityManager.spawnEnemie(spawnEnemyX.get(i), spawnEnemyY.get(i));
            for(int i=NumberOfEnemy/2; i < NumberOfEnemy; ++i)
                entityManager.spawnEnemy2(spawnEnemyX.get(i), spawnEnemyY.get(i));
        }

        //Enemy2 Enemies = new Enemy2(handler, 1119, 565);
        //Boss Enemies = new Boss(handler, 1119, 565);
        //entityManager.addEnemie(Enemies);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick(){
        entityManager.tick();
        bulletManager.tick();
        coinManager.tick();
    }

    public void render(Graphics g){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset()/ Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset()/ Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for(int y=yStart; y < yEnd; ++y) {
            for(int x=xStart; x < xEnd; ++x){
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }

        }
        //Entities
        coinManager.render(g);
        entityManager.render(g);
        bulletManager.render(g);
    }

    public void loadDMG(String path){

        //Creature.EnemyNumber = Entity.DEFAULT_ENEMY;

        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");

        try{
            if(path == null)
                throw new EmptyWorldFileException();
        } catch (EmptyWorldFileException e) {
            System.err.println(e.getMessage());
        }

        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        NumberOfEnemy = Utils.parseInt(tokens[2]);
        spawnEnemyX = new Vector<Integer>(NumberOfEnemy);
        spawnEnemyY = new Vector<Integer>(NumberOfEnemy);
        int tmp = 3;
        for(int i=0; i < NumberOfEnemy; ++i)
            spawnEnemyX.add(Utils.parseInt(tokens[tmp++]));
        for(int i=0; i < NumberOfEnemy; ++i)
            spawnEnemyY.add(Utils.parseInt(tokens[tmp++]));

        System.out.println(spawnEnemyX);
        System.out.println(spawnEnemyY);

        tilesDMG = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int temp = Utils.parseInt(tokens[(x + y * width) + 3 + NumberOfEnemy * 2]);
                if(temp == 9)
                    coinManager.addItem(Coin.coin.createNew(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT));
                else
                entityManager.addEntity(entityFactory.ProduceEntity(temp, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT));

                tilesDMG[x][y] = temp;
                }
            }
    }

    public Tile getTile(int x, int y){
        if(x <  0 || y < 0 || x >= width || y >= height)
            return Tile.tiles[29];

        Tile T = Tile.tiles[tiles[x][y]];
        if(T == null)
            return Tile.tiles[29];
        return T;
    }

    private void loadLevel(String path) {

       try{
           if(path == null)
               throw new EmptyWorldFileException();
       } catch (EmptyWorldFileException e) {
           System.err.println(e.getMessage());
       }

        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                try{
                    if(Utils.parseInt(tokens[(x + y * width) + 4]) > 69)
                        throw new UnknownTileException();
                    else
                        tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);

                } catch (UnknownTileException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getSpawnX(){
        return spawnX;
    }

    public int getSpawnY(){
        return spawnY;
    }

    public BulletManager getBulletManager() {
        return bulletManager;
    }

    public void setBulletManager(BulletManager bulletManager) {
        this.bulletManager = bulletManager;
    }

}
