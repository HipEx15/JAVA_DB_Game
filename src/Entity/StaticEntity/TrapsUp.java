package Entity.StaticEntity;

import Game.Assets;
import Game.Handler;
import Tile.Tile;

import java.awt.*;

public class TrapsUp extends StaticEntity{

    public TrapsUp(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        //COLLISION COORD
        bounds.x = 12;
        bounds.y = 32;
        bounds.width = 48;
        bounds.height = 32;
    }

    @Override
    public void tick() {

    }

    @Override
    public void die() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.Damage[2], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
