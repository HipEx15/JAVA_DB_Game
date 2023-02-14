package Entity.StaticEntity;

import Entity.Entity;
import Game.Handler;

//Crearea de entitati statice, capcanele si lava.
public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
}
