package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Incarcarea tuturor imaginilor folosite in cadrul jocului.

public class Assets {

    private static final int width = 64, height = 64;

    public static BufferedImage[][] BackgroundTileNC, MapTilesC, BigEnemie;
    public static BufferedImage[]  Player_Idle_R, Player_Idle_L, Player_Fly_R, Player_Fly_L, Damage, Shots1, Shots2, Shots3, Shots4, Enemie1_Idle, Enemie1_L, Enemie1_R, Enemie2_Idle, Enemie2_L, Enemie2_R, Enemie3_Idle, Enemie3_L, Enemie3_R, PlayerAttackR, PlayerAttackL;
    public static BufferedImage Heart, MenuBackground, Coin, DamageWand, Enemies, LostBackground, WinBackground, Resume;

    public static void init(){
        SpriteSheet backgroundTileNC = new SpriteSheet(ImageLoader.loadImage("/Textures/Background_NC.png"));
        SpriteSheet MapC = new SpriteSheet(ImageLoader.loadImage("/Textures/MapTiles_C.png"));
        SpriteSheet damage = new SpriteSheet(ImageLoader.loadImage("/Textures/Damagev2.png"));
        SpriteSheet heart = new SpriteSheet(ImageLoader.loadImage("/Textures/Heart.png"));
        SpriteSheet coin = new SpriteSheet(ImageLoader.loadImage("/Textures/Coin.png"));
        SpriteSheet magicwand = new SpriteSheet(ImageLoader.loadImage("/Textures/DamageWand.png"));
        SpriteSheet enemies = new SpriteSheet(ImageLoader.loadImage("/Textures/Enemies.png"));

        SpriteSheet playerIdleR = new SpriteSheet(ImageLoader.loadImage("/Textures/Player_Idle_R.png"));
        SpriteSheet playerIdleL = new SpriteSheet(ImageLoader.loadImage("/Textures/Player_Idle_L.png"));
        SpriteSheet playerFlyR = new SpriteSheet(ImageLoader.loadImage("/Textures/Player_Fly_R.png"));
        SpriteSheet playerFlyL = new SpriteSheet(ImageLoader.loadImage("/Textures/Player_Fly_L.png"));
        SpriteSheet playerAttackR = new SpriteSheet(ImageLoader.loadImage("/Textures/Player_Atack_R.png"));
        SpriteSheet playerAttackL = new SpriteSheet(ImageLoader.loadImage("/Textures/Player_Atack_L.png"));

        SpriteSheet shots1 = new SpriteSheet(ImageLoader.loadImage("/Textures/ShootsPlayer.png"));
        SpriteSheet shots2 = new SpriteSheet(ImageLoader.loadImage("/Textures/ShootsEnemie1.png"));
        SpriteSheet shots3 = new SpriteSheet(ImageLoader.loadImage("/Textures/ShootsEnemie2.png"));
        SpriteSheet shots4 = new SpriteSheet(ImageLoader.loadImage("/Textures/ShootsEnemieBOSS.png"));

        SpriteSheet enemie_Idle_1 = new SpriteSheet(ImageLoader.loadImage("/Textures/Enemie1Idle.png"));
        SpriteSheet enemie_L_1 = new SpriteSheet(ImageLoader.loadImage("/Textures/Enemie1FlyL.png"));
        SpriteSheet enemie_R_1 = new SpriteSheet(ImageLoader.loadImage("/Textures/Enemie1FlyR.png"));

        SpriteSheet enemie_Idle_2 = new SpriteSheet(ImageLoader.loadImage("/Textures/Enemie2Idle.png"));
        SpriteSheet enemie_L_2 = new SpriteSheet(ImageLoader.loadImage("/Textures/Enemie2FlyL.png"));
        SpriteSheet enemie_R_2 = new SpriteSheet(ImageLoader.loadImage("/Textures/Enemie2FlyR.png"));

        SpriteSheet enemie_Idle_3 = new SpriteSheet(ImageLoader.loadImage("/Textures/Enemie3Idle.png"));
        SpriteSheet enemie_L_3 = new SpriteSheet(ImageLoader.loadImage("/Textures/Enemie3FlyL.png"));
        SpriteSheet enemie_R_3 = new SpriteSheet(ImageLoader.loadImage("/Textures/Enemie3FlyR.png"));

        Heart = heart.crop(0,0,width,height);
        Coin = coin.crop(0,0,width,height);
        DamageWand = magicwand.crop(0,0, width, height);
        Enemies = enemies.crop(0,0, width, height);

        MenuBackground =  ImageLoader.loadImage("/States/MenuState.png");
        LostBackground =  ImageLoader.loadImage("/States/Lost.png");
        WinBackground = ImageLoader.loadImage("/States/Win.png");
        Resume = ImageLoader.loadImage("/States/Resume.png");

        BackgroundTileNC = new BufferedImage[4][8];
        for (int i = 0; i < BackgroundTileNC.length; i++){
            for (int j = 0; j < BackgroundTileNC[0].length; j++){
                BackgroundTileNC[i][j] = backgroundTileNC.crop(width * j, height * i, width, height);
            }
        }

        MapTilesC = new BufferedImage[4][9];
        for (int i = 0; i < MapTilesC.length; i++){
            for (int j = 0; j < MapTilesC[0].length; j++){
                MapTilesC[i][j] = MapC.crop(width * j, height * i, width, height);
            }
        }

        /*Damage = new BufferedImage[2][9];
        for (int i = 0; i < Damage.length; i++){
            for (int j = 0; j < Damage[0].length; j++){
                Damage[i][j] = damage.crop(width * j, height * i, width, height);
            }
        }*/

        Damage = new BufferedImage[5];
        for (int i = 0; i < Damage.length; i++){
            Damage[i] = damage.crop(width * i, 0, width, height);
        }


        Player_Idle_R = new BufferedImage[5];
        for(int i = 0; i < Player_Idle_R.length; ++i){
            Player_Idle_R[i] = playerIdleR.crop(width * i , 0, width, height);
        }

        Player_Idle_L = new BufferedImage[5];
        for(int i = 0; i < Player_Idle_L.length; ++i){
            Player_Idle_L[i] = playerIdleL.crop(width * i , 0, width, height);
        }

        Player_Fly_R = new BufferedImage[4];
        for(int i = 0; i < Player_Fly_R.length; ++i){
            Player_Fly_R[i] = playerFlyR.crop(width * i , 0, width, height);
        }

        Player_Fly_L = new BufferedImage[4];
        for(int i = 0; i < Player_Fly_L.length; ++i){
            Player_Fly_L[i] = playerFlyL.crop(width * i , 0, width, height);
        }

        Shots1 = new BufferedImage[2];
        for (int i = 0; i < Shots1.length; i++){
            Shots1[i] = shots1.crop(width * i, 0, width, height);
        }

        Shots2 = new BufferedImage[2];
        for (int i = 0; i < Shots2.length; i++){
            Shots2[i] = shots2.crop(width * i, 0, width, height);
        }

        Shots3 = new BufferedImage[2];
        for (int i = 0; i < Shots3.length; i++){
            Shots3[i] = shots3.crop(width * i, 0, width, height);
        }

        Shots4 = new BufferedImage[2];
        for (int i = 0; i < Shots4.length; i++){
            Shots4[i] = shots4.crop(width * i, 0, width, height);
        }

        Enemie1_Idle = new BufferedImage[5];
        for(int i = 0; i < Enemie1_Idle.length; ++i){
            Enemie1_Idle[i] = enemie_Idle_1.crop(width * i , 0, width, height);
        }

        Enemie1_R = new BufferedImage[4];
        for(int i = 0; i < Enemie1_R.length; ++i){
            Enemie1_R[i] = enemie_R_1.crop(width * i , 0, width, height);
        }

        Enemie1_L = new BufferedImage[4];
        for(int i = 0; i < Enemie1_L.length; ++i){
            Enemie1_L[i] = enemie_L_1.crop(width * i , 0, width, height);
        }

        Enemie2_Idle = new BufferedImage[5];
        for(int i = 0; i < Enemie2_Idle.length; ++i){
            Enemie2_Idle[i] = enemie_Idle_2.crop(width * i , 0, width, height);
        }

        Enemie2_R = new BufferedImage[4];
        for(int i = 0; i < Enemie2_R.length; ++i){
            Enemie2_R[i] = enemie_R_2.crop(width * i , 0, width, height);
        }

        Enemie2_L = new BufferedImage[4];
        for(int i = 0; i < Enemie2_L.length; ++i){
            Enemie2_L[i] = enemie_L_2.crop(width * i , 0, width, height);
        }

        Enemie3_Idle = new BufferedImage[5];
        for(int i = 0; i < Enemie3_Idle.length; ++i){
            Enemie3_Idle[i] = enemie_Idle_3.crop(width * i , 0, width, height);
        }

        Enemie3_R = new BufferedImage[4];
        for(int i = 0; i < Enemie3_R.length; ++i){
            Enemie3_R[i] = enemie_R_3.crop(width * i , 0, width, height);
        }

        Enemie3_L = new BufferedImage[4];
        for(int i = 0; i < Enemie3_L.length; ++i){
            Enemie3_L[i] = enemie_L_3.crop(width * i , 0, width, height);
        }

        PlayerAttackR = new BufferedImage[3];
        for(int i = 0; i < PlayerAttackR.length; ++i){
            PlayerAttackR[i] = playerAttackR.crop(width * i , 0, width, height);
        }

        PlayerAttackL = new BufferedImage[3];
        for(int i = 0; i < PlayerAttackL.length; ++i){
            PlayerAttackL[i] = playerAttackL.crop(width * i , 0, width, height);
        }

    }

    public static String GetAssetsTilesAndBackgroundsFromDataBase(int id){
        Connection c = null;
        Statement stmt = null;
        String path = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TemnitaVrajitorilor.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Assets WHERE Id=" + id + ";" );
            path = rs.getString("Tiles");

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return path;
    }

}