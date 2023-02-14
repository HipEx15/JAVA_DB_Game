package Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

//Clasa ce contine toate tile-urile din joc.
public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile[] tilesDMG = new Tile[256];

    //Tiles
/*
    public static Tile backGroundTile_0 = new BackgroundTile_NC(0);
    public static Tile backGroundTile_1 = new BackgroundTile_NC(1);
    public static Tile backGroundTile_2 = new BackgroundTile_NC(2);
    public static Tile backGroundTile_3 = new BackgroundTile_NC(3);
    public static Tile backGroundTile_4 = new BackgroundTile_NC(4);
    public static Tile backGroundTile_5 = new BackgroundTile_NC(5);
    public static Tile backGroundTile_6 = new BackgroundTile_NC(6);
    public static Tile backGroundTile_7 = new BackgroundTile_NC(7);
    public static Tile backGroundTile_8 = new BackgroundTile_NC(8);
    public static Tile backGroundTile_9 = new BackgroundTile_NC(9);
    public static Tile backGroundTile_10 = new BackgroundTile_NC(10);
    public static Tile backGroundTile_11 = new BackgroundTile_NC(11);
    public static Tile backGroundTile_12 = new BackgroundTile_NC(12);
    public static Tile backGroundTile_13 = new BackgroundTile_NC(13);
    public static Tile backGroundTile_14 = new BackgroundTile_NC(14);
    public static Tile backGroundTile_15 = new BackgroundTile_NC(15);
    public static Tile backGroundTile_16 = new BackgroundTile_NC(16);
    public static Tile backGroundTile_17 = new BackgroundTile_NC(17);
    public static Tile backGroundTile_18 = new BackgroundTile_NC(18);
    public static Tile backGroundTile_19 = new BackgroundTile_NC(19);
    public static Tile backGroundTile_20 = new BackgroundTile_NC(20);
    public static Tile backGroundTile_21 = new BackgroundTile_NC(21);
    public static Tile backGroundTile_22 = new BackgroundTile_NC(22);
    public static Tile backGroundTile_23 = new BackgroundTile_NC(23);
    public static Tile backGroundTile_24 = new BackgroundTile_NC(24);
    public static Tile backGroundTile_25 = new BackgroundTile_NC(25);
    public static Tile backGroundTile_26 = new BackgroundTile_NC(26);
    public static Tile backGroundTile_27 = new BackgroundTile_NC(27);
    public static Tile backGroundTile_28 = new BackgroundTile_NC(28);
    public static Tile backGroundTile_29 = new BackgroundTile_NC(29);
    public static Tile backGroundTile_30 = new BackgroundTile_NC(30);
    public static Tile backGroundTile_31 = new BackgroundTile_NC(31);

    public static Tile MapTile_32 = new MapTiles_C(32);
    public static Tile MapTile_33 = new MapTiles_C(33);
    public static Tile MapTile_34 = new MapTiles_C(34);
    public static Tile MapTile_35 = new MapTiles_C(35);
    public static Tile MapTile_36 = new MapTiles_C(36);
    public static Tile MapTile_37 = new MapTiles_C(37);
    public static Tile MapTile_38 = new MapTiles_C(38);
    public static Tile MapTile_39 = new MapTiles_C(39);
    public static Tile MapTile_40 = new MapTiles_C(40);
    public static Tile MapTile_41 = new MapTiles_C(41);
    public static Tile MapTile_42 = new MapTiles_C(42);
    public static Tile MapTile_43 = new MapTiles_C(43);
    public static Tile MapTile_44 = new MapTiles_C(44);
    public static Tile MapTile_45 = new MapTiles_C(45);
    public static Tile MapTile_46 = new MapTiles_C(46);
    public static Tile MapTile_47 = new MapTiles_C(47);
    public static Tile MapTile_48 = new MapTiles_C(48);
    public static Tile MapTile_49 = new MapTiles_C(49);
    public static Tile MapTile_50 = new MapTiles_C(50);
    public static Tile MapTile_51 = new MapTiles_C(51);
    public static Tile MapTile_52 = new MapTiles_C(52);
    public static Tile MapTile_53 = new MapTiles_C(53);
    public static Tile MapTile_54 = new MapTiles_C(54);
    public static Tile MapTile_55 = new MapTiles_C(55);
    public static Tile MapTile_56 = new MapTiles_C(56);
    public static Tile MapTile_57 = new MapTiles_C(57);
    public static Tile MapTile_58 = new MapTiles_C(58);
    public static Tile MapTile_59 = new MapTiles_C(59);
    public static Tile MapTile_60 = new MapTiles_C(60);
    public static Tile MapTile_61 = new MapTiles_C(61);
    public static Tile MapTile_62 = new MapTiles_C(62);
    public static Tile MapTile_63 = new MapTiles_C(63);
    public static Tile MapTile_64 = new MapTiles_C(64);
    public static Tile MapTile_65 = new MapTiles_C(65);
    public static Tile MapTile_66 = new MapTiles_C(66);
    public static Tile MapTile_67 = new MapTiles_C(67);
    public static Tile MapTile_68 = new MapTiles_C(68);
    public static Tile MapTile_69 = new MapTiles_C(69);
    public static Tile MapTile_70 = new MapTiles_C(70);
    public static Tile MapTile_71 = new MapTiles_C(71);
    public static Tile MapTile_72 = new MapTiles_C(72);
    public static Tile MapTile_73 = new MapTiles_C(73);
    public static Tile MapTile_74 = new MapTiles_C(74);
    public static Tile MapTile_75 = new MapTiles_C(75);
    public static Tile MapTile_76 = new MapTiles_C(76);
    public static Tile MapTile_77 = new MapTiles_C(77);
    public static Tile MapTile_78 = new MapTiles_C(78);
    public static Tile MapTile_79 = new MapTiles_C(79);
    public static Tile MapTile_80 = new MapTiles_C(80);
    public static Tile MapTile_81 = new MapTiles_C(81);
    public static Tile MapTile_82 = new MapTiles_C(82);
    public static Tile MapTile_83 = new MapTiles_C(83);
    public static Tile MapTile_84 = new MapTiles_C(84);
    public static Tile MapTile_85 = new MapTiles_C(85);
    public static Tile MapTile_86 = new MapTiles_C(86);
    public static Tile MapTile_87 = new MapTiles_C(87);
    public static Tile MapTile_88 = new MapTiles_C(88);
    public static Tile MapTile_89 = new MapTiles_C(89);
    public static Tile MapTile_90 = new MapTiles_C(90);
    public static Tile MapTile_91 = new MapTiles_C(91);
    public static Tile MapTile_92 = new MapTiles_C(92);
    public static Tile MapTile_93 = new MapTiles_C(93);
    public static Tile MapTile_94 = new MapTiles_C(94);
    public static Tile MapTile_95 = new MapTiles_C(95);
    public static Tile MapTile_96 = new MapTiles_C(96);
    public static Tile MapTile_97 = new MapTiles_C(97);
    public static Tile MapTile_98 = new MapTiles_C(98);
    public static Tile MapTile_99 = new MapTiles_C(99);
    public static Tile MapTile_100 = new MapTiles_C(100);
    public static Tile MapTile_101 = new MapTiles_C(101);
    public static Tile MapTile_102 = new MapTiles_C(102);
    public static Tile MapTile_103 = new MapTiles_C(103);

    public static Tile Damage_104 = new DamageTiles(104);
    public static Tile Damage_105 = new DamageTiles(105);
    public static Tile Damage_106 = new DamageTiles(106);
    public static Tile Damage_107 = new DamageTiles(107);
    public static Tile Damage_108 = new DamageTiles(108);
    public static Tile Damage_109 = new DamageTiles(109);
    public static Tile Damage_110 = new DamageTiles(110);
    public static Tile Damage_111 = new DamageTiles(111);
    public static Tile Damage_112 = new DamageTiles(112);
    public static Tile Damage_113 = new DamageTiles(113);
    public static Tile Damage_114 = new DamageTiles(114);
    public static Tile Damage_115 = new DamageTiles(115);
    public static Tile Damage_116 = new DamageTiles(116);
    public static Tile Damage_117 = new DamageTiles(117);
    public static Tile Damage_118 = new DamageTiles(118);
    public static Tile Damage_119 = new DamageTiles(119);
    public static Tile Damage_120 = new DamageTiles(120);
    public static Tile Damage_121 = new DamageTiles(121);*/

    public static void init(){
        int i;
        for(i=0; i < 32; ++i)
            tiles[i] = new BackgroundTile_NC(i);

        for(i=32; i < 68; ++i)
            tiles[i] = new MapTiles_C(i);

        for(i=0; i < 5; ++i)
            tilesDMG[i] = new DamageTile(i);
    }

    //WRONG
    //public static Tile[] backGroundTiles;
    //public static Tile mapTiles[] = mapTiles();

    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 64;
    protected BufferedImage texture;
    protected final int id;

    //WRONG
    /*
    public static void BGTiles(){
        for(int i=0; i < 32; ++i)
            backGroundTiles[i] = new BackgroundTile_NC(i);
    }*/
/*
    public static Tile[] mapTiles(){
        MapTiles_C[] tmp = new MapTiles_C[32];
        int i=32;
        while(i < 122) {
            tmp = new MapTiles_C[i];
            i++;
        }
        return tmp;
    }*/

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        if(id < 5)
            tilesDMG[id] = this;
        else
            tiles[id] = this;
    }

    public void tick(){
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isSolid(){
        return false;
    }

    public int getId() {
        return id;
    }

    public boolean isNotTraverseble() {
        return true;
    }

}
