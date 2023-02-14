package Tile;

import Game.Assets;

import java.awt.image.BufferedImage;

//Tile-uri pentru fundal.
public class BackgroundTile_NC extends Tile {
    public BackgroundTile_NC(int id) {
        super(getAssetById(id), id);
    }

    private static BufferedImage getAssetById(int id) {
        int[] tmp = new int[32];
        for(int i=0; i < 32; ++i)
            tmp[i] = i;

        int index = 0;
        for(int i=0; i < 4; ++i){
            for(int j=0; j < 8; ++j){
                if(tmp[index++] == id)
                    return Assets.BackgroundTileNC[i][j];
            }
        }

        return Assets.BackgroundTileNC[3][7];

        //OLD
        /*return switch (id) {
            case 0 -> Assets.BackgroundTileNC[0][0];
            case 1 -> Assets.BackgroundTileNC[0][1];
            case 2 -> Assets.BackgroundTileNC[0][2];
            case 3 -> Assets.BackgroundTileNC[0][3];
            case 4 -> Assets.BackgroundTileNC[0][4];
            case 5 -> Assets.BackgroundTileNC[0][5];
            case 6 -> Assets.BackgroundTileNC[0][6];
            case 7 -> Assets.BackgroundTileNC[0][7];
            case 8 -> Assets.BackgroundTileNC[1][0];
            case 9 -> Assets.BackgroundTileNC[1][1];
            case 10 -> Assets.BackgroundTileNC[1][2];
            case 11 -> Assets.BackgroundTileNC[1][3];
            case 12 -> Assets.BackgroundTileNC[1][4];
            case 13 -> Assets.BackgroundTileNC[1][5];
            case 14 -> Assets.BackgroundTileNC[1][6];
            case 15 -> Assets.BackgroundTileNC[1][7];
            case 16 -> Assets.BackgroundTileNC[2][0];
            case 17 -> Assets.BackgroundTileNC[2][1];
            case 18 -> Assets.BackgroundTileNC[2][2];
            case 19 -> Assets.BackgroundTileNC[2][3];
            case 20 -> Assets.BackgroundTileNC[2][4];
            case 21 -> Assets.BackgroundTileNC[2][5];
            case 22 -> Assets.BackgroundTileNC[2][6];
            case 23 -> Assets.BackgroundTileNC[2][7];
            case 24 -> Assets.BackgroundTileNC[3][0];
            case 25 -> Assets.BackgroundTileNC[3][1];
            case 26 -> Assets.BackgroundTileNC[3][2];
            case 27 -> Assets.BackgroundTileNC[3][3];
            case 28 -> Assets.BackgroundTileNC[3][4];
            case 29 -> Assets.BackgroundTileNC[3][5];
            case 30 -> Assets.BackgroundTileNC[3][6];
            default -> Assets.BackgroundTileNC[3][7];
        };*/
        /*for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (Assets.BackgroundTileNC[i][j] == id)
                    return Assets.BackgroundTileNC[i][j];
            }
        }

        return Assets.BackgroundTileNC[3][7];*/
    }

    @Override
    public boolean isSolid(){
        return false;
    }

    @Override
    public boolean isNotTraverseble() {
        return false;
    }
}
