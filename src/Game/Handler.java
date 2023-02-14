package Game;

import Entity.Creature.Player;
import KeyManager.KeyManager;
import Levels.*;
import MouseManager.MouseManager;
import State.GameCamera;

//Aceasta clasa imi permite accesarea diverselor metode din alte clase. Dese ori, cele mai frecvent folosite.

public class Handler {

    private Game game;
    private Levels level;
    private Player player;

    public Handler(Game game){
        this.game = game;
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Levels getLevel() {
        return level;
    }

    public Player getPlayer(){
        return player;
    }

    public void setLevel(Levels level) {
        this.level = level;
    }

}
