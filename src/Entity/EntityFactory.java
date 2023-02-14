package Entity;

import Entity.StaticEntity.*;
import Game.Handler;

public class EntityFactory {

    private Handler handler;

    public EntityFactory(Handler handler){
        this.handler = handler;
    }

    public Entity ProduceEntity(int code, float x, float y){
        return switch (code) {
            case 0 -> new TrapsDown(handler, x, y);
            case 1 -> new TrapsLeft(handler, x, y);
            case 2 -> new TrapsUp(handler, x, y);
            case 3 -> new TrapsRight(handler, x, y);
            case 4 -> new Lava(handler, x, y);
            default -> new Nothing(handler, x, y);
            /*case -1 :
                return new Enemy(handler, x, y, tank_1);
            case -2 :
                return new Enemy(handler, x, y, tank_2);
            case -3 :
                return new Enemy(handler, x, y, tank_3);
            case -4 :
                return new Enemy(handler, x, y, tank_4);
            case -5 :
            default:
                return new EnemyWithAnimations(handler, x, y, robot);*/
        };
    }
}
