package Entity.StaticEntity;

import Game.Assets;
import Game.Handler;
import Tile.Tile;

import java.awt.*;

public class TrapsLeft extends StaticEntity{

    public TrapsLeft(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        //COLLISION COORD
        bounds.x = 0;
        bounds.y = 12;
        bounds.width = 32;
        bounds.height = 48;
    }

    @Override
    public void tick() {

    }

    @Override
    public void die() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.Damage[1], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
