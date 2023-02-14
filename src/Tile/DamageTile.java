package Tile;

import Game.Assets;

import java.awt.image.BufferedImage;

//Tile-uri care scad viata.
public class DamageTile extends Tile{

    public DamageTile(int id) {
        super(getAssetById(id), id);
    }

    private static BufferedImage getAssetById(int id){
        int[] tmp = new int[5];
        int j = 0;
        for(int i=68; i < 73; ++i)
            tmp[j++] = i;

        int index = 0;
        for(int i=0; i < 5; ++i){
                if(tmp[index++] == id)
                    return Assets.Damage[i];
            }

        return Assets.Damage[4];
    }

    @Override
    public boolean isSolid(){
        return false;
    }

    @Override
    public boolean isNotTraverseble() {
        return true;
    }
}
