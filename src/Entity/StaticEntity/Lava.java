package Entity.StaticEntity;

import Game.Assets;
import Game.Handler;
import Tile.*;

import java.awt.*;

public class Lava extends StaticEntity{

    public Lava(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        //COLLISION COORD
        bounds.x = 0;
        bounds.y = 32;
        bounds.width = 64;
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
        g.drawImage(Assets.Damage[4], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
//        g.setColor(Color.RED);
//        g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//               bounds.width, bounds.height);
    }
}
