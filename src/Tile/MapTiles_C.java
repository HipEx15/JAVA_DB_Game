package Tile;

import Game.Assets;

import java.awt.image.BufferedImage;

//Tile-urile pentru diversele platforme din joc.
public class MapTiles_C extends Tile {
    public MapTiles_C(int id) {
        super(getAssetById(id), id);
    }

    private static BufferedImage getAssetById(int id) {

        int[] tmp = new int[36];
        int j = 0;
        for(int i=32; i < 68; ++i)
            tmp[j++] = i;

        int index = 0;
        for(int i=0; i < 4; ++i){
            for(j=0; j < 9; ++j){
                if(tmp[index++] == id)
                    return Assets.MapTilesC[i][j];
            }
        }

        return Assets.MapTilesC[3][8];

        //OLD

         /* return switch (id) {
            case 32 -> Assets.MapTilesC[0][0];
            case 33 -> Assets.MapTilesC[0][1];
            case 34 -> Assets.MapTilesC[0][2];
            case 35 -> Assets.MapTilesC[0][3];
            case 36 -> Assets.MapTilesC[0][4];
            case 37 -> Assets.MapTilesC[0][5];
            case 38 -> Assets.MapTilesC[0][6];
            case 39 -> Assets.MapTilesC[0][7];
            case 40 -> Assets.MapTilesC[0][8];

            case 41 -> Assets.MapTilesC[1][0];
            case 42 -> Assets.MapTilesC[1][1];
            case 43 -> Assets.MapTilesC[1][2];
            case 44 -> Assets.MapTilesC[1][3];
            case 45 -> Assets.MapTilesC[1][4];
            case 46 -> Assets.MapTilesC[1][5];
            case 47 -> Assets.MapTilesC[1][6];
            case 48 -> Assets.MapTilesC[1][7];
            case 49 -> Assets.MapTilesC[1][8];

            case 50 -> Assets.MapTilesC[2][0];
            case 51 -> Assets.MapTilesC[2][1];
            case 52 -> Assets.MapTilesC[2][2];
            case 53 -> Assets.MapTilesC[2][3];
            case 54 -> Assets.MapTilesC[2][4];
            case 55 -> Assets.MapTilesC[2][5];
            case 56 -> Assets.MapTilesC[2][6];
            case 57 -> Assets.MapTilesC[2][7];
            case 58 -> Assets.MapTilesC[2][8];

            case 59 -> Assets.MapTilesC[3][0];
            case 60 -> Assets.MapTilesC[3][1];
            case 61 -> Assets.MapTilesC[3][2];
            case 62 -> Assets.MapTilesC[3][3];
            case 63 -> Assets.MapTilesC[3][4];
            case 64 -> Assets.MapTilesC[3][5];
            case 65 -> Assets.MapTilesC[3][6];
            case 66 -> Assets.MapTilesC[3][7];
            case 67 -> Assets.MapTilesC[3][8];

            case 68 -> Assets.MapTilesC[4][0];
            case 69 -> Assets.MapTilesC[4][1];
            case 70 -> Assets.MapTilesC[4][2];
            case 71 -> Assets.MapTilesC[4][3];
            case 72 -> Assets.MapTilesC[4][4];
            case 73 -> Assets.MapTilesC[4][5];
            case 74 -> Assets.MapTilesC[4][6];
            case 75 -> Assets.MapTilesC[4][7];
            case 76 -> Assets.MapTilesC[4][8];

            case 77 -> Assets.MapTilesC[5][0];
            case 78 -> Assets.MapTilesC[5][1];
            case 79 -> Assets.MapTilesC[5][2];
            case 80 -> Assets.MapTilesC[5][3];
            case 81 -> Assets.MapTilesC[5][4];
            case 82 -> Assets.MapTilesC[5][5];
            case 83 -> Assets.MapTilesC[5][6];
            case 84 -> Assets.MapTilesC[5][7];
            case 85 -> Assets.MapTilesC[5][8];

            case 86 -> Assets.MapTilesC[6][0];
            case 87 -> Assets.MapTilesC[6][1];
            case 88 -> Assets.MapTilesC[6][2];
            case 89 -> Assets.MapTilesC[6][3];
            case 90 -> Assets.MapTilesC[6][4];
            case 91 -> Assets.MapTilesC[6][5];
            case 92 -> Assets.MapTilesC[6][6];
            case 93 -> Assets.MapTilesC[6][7];
            case 94 -> Assets.MapTilesC[6][8];

            case 95 -> Assets.MapTilesC[7][0];
            case 96 -> Assets.MapTilesC[7][1];
            case 97 -> Assets.MapTilesC[7][2];
            case 98 -> Assets.MapTilesC[7][3];
            case 99 -> Assets.MapTilesC[7][4];
            case 100 -> Assets.MapTilesC[7][5];
            case 101 -> Assets.MapTilesC[7][6];
            case 102 -> Assets.MapTilesC[7][7];
            default -> Assets.MapTilesC[7][8];
        };*/
    }

    @Override
    public boolean isSolid(){
        return true;
    }

    @Override
    public boolean isNotTraverseble() {
        return true;
    }
}
