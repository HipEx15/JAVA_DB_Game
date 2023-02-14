package Entity.StaticEntity;

import Game.Assets;
import Game.Handler;
import Tile.Tile;

import java.awt.*;

public class Nothing extends StaticEntity{

    public Nothing(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 0;
        bounds.height = 0;
    }

    @Override
    public void tick() {

    }

    @Override
    public void die() {

    }

    @Override
    public void render(Graphics g) {
    }
}
