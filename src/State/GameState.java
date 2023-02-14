package State;

import AudioPlayer.AudioManager;
import Entity.Creature.Player;
import Entity.Entity;
import Game.*;
import KeyManager.KeyManager;
import Levels.Levels;
import State.Utils.PlayerBar;
import UI.UIManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.*;

import static State.Utils.Lvls.*;
import static State.Utils.Lvls.lvls.*;

//State-ul unde se desfasoara jocul.

public class GameState extends State{

    private Levels Level;
    private PlayerBar playerBar;
    public static lvls current_level = level_1;
    public static lvls current_level_dmg = level_5;
    public static int dificulty = 0;
    public static int[] CaracteristicsPlayer = new int[3];
    public static int[] CaracteristicsEnemies = new int[9];

    public int HealthDatabase, PointsDatabase, DamageDatabase, TryingDatabase, MinutesDatabase, SecondDatabase, LevelDatabase;
    public String NameDatabase;

    public Timer timer;

    public int getSeconds() {
        return seconds;
    }

    public static void setSeconds(int seconds) {
        GameState.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public static void setMinutes(int minutes) {
        GameState.minutes = minutes;
    }

    public static int seconds=0, minutes=0;
    public String ddSeconds="00", ddMinutes="00";
    public DecimalFormat decimalFormat = new DecimalFormat("00");

    //private Lava lava;

    public GameState(Handler handler){
        super(handler);
        Level = new Levels(handler,level_1, level_5);
        handler.setLevel(Level);
        this.playerBar = new PlayerBar(handler);
        //player = new Player(handler, handler.getLevel().getSpawnX() , handler.getLevel().getSpawnY());
        //lava = new Lava(handler,64,1152);
    }

    public int GetMusicByLevel(lvls current_level){
        switch(current_level){
            case level_1:
                return 5;
            case level_2:
                return 6;
            case level_3:
            default:
                return 7;
        }
    }

    public void setDdSeconds(String ddSeconds) {
        this.ddSeconds = ddSeconds;
    }

    public void setDdMinutes(String ddMinutes) {
        this.ddMinutes = ddMinutes;
    }

    public void StartNewGame(){

        handler.getGame().audioManager.stopMusic();
        handler.getGame().audioManager = new AudioManager(handler.getGame(), 5);
        handler.getGame().audioManager.playMusic();

        Player.health = CaracteristicsPlayer[0];
        seconds = 0;
        minutes = 0;
        ddMinutes = "00";
        ddSeconds = "00";

        normalTimer();
        timer.start();

        current_level = level_1;
        current_level_dmg = level_5;

        Player.bulletdamage = CaracteristicsPlayer[1];
        Entity.points = Entity.DEFAULT_POINTS;

        String path1 = GetLevelWorld(current_level);
        String path2 = GetLevelWorldDMG(current_level_dmg);

        Level.setLevel(path1,path2);

    }

    public void loadLevel(String temp){

        temp = temp.replaceAll("( )+", " ");
        String[] str = temp.split(" ");
        System.out.println(Arrays.toString(str));

        handler.getGame().audioManager.stopMusic();
        handler.getGame().audioManager = new AudioManager(handler.getGame(), GetMusicByLevel(current_level) + 1);
        handler.getGame().audioManager.playMusic();

        ddMinutes = "00";
        ddSeconds = "00";

        Player.health = Integer.parseInt((str[2]));
        Entity.points = Integer.parseInt((str[3]));
        Player.bulletdamage = Integer.parseInt((str[4]));
        Entity.Trying = Integer.parseInt((str[5]));
        GameState.setMinutes(Integer.parseInt((str[6])));
        GameState.setSeconds(Integer.parseInt((str[7])));
        KeyManager.buffer = str[1];

        GameState.dificulty = Integer.parseInt(((str[9])));


        normalTimer();
        timer.start();

        if(Integer.parseInt((str[8])) == 2) {
            current_level = level_2;
            current_level_dmg = level_6;
            String path1 = GetLevelWorld(current_level);
            String path2 = GetLevelWorldDMG(current_level_dmg);
            Level.setLevel(path1, path2);
        }
        else if(Integer.parseInt((str[8])) == 3){
            current_level = level_3;
            current_level_dmg = level_7;
            String path1 = GetLevelWorld(current_level);
            String path2 = GetLevelWorldDMG(current_level_dmg);
            Level.setLevel(path1, path2);
        }
    }

    public void startNextLevel(){

        handler.getGame().audioManager.stopMusic();
        handler.getGame().audioManager = new AudioManager(handler.getGame(), GetMusicByLevel(current_level) + 1);
        handler.getGame().audioManager.playMusic();

        current_level = nextLevel(current_level);
        current_level_dmg = nextLevelDMG(current_level_dmg);

        String path1 = GetLevelWorld(current_level);
        String path2 = GetLevelWorldDMG(current_level_dmg);

        Level.setLevel(path1,path2);

    }

    @Override
    protected UIManager getUiManager() {
        return null;
    }

    @Override
    public void tick() {
        /*if(Entity)
            State.setState(handler.getGame().winState);*/

        if(handler.getLevel().NumberOfEnemy == 0) {
            //timer.stop();
            //State.setState(handler.getGame().winState);
            if (current_level == level_3) {
                timer.stop();
                handler.getGame().audioManager.stopMusic();
                handler.getGame().audioManager = new AudioManager(handler.getGame(), 3);
                handler.getGame().audioManager.playMusic();
                State.setState(handler.getGame().winState);
            } else {
                State.setState(handler.getGame().nextLevel);
            }
        }

        if (handler.getKeyManager().esc) {
            handler.getGame().gamepauseState.setUIManagerActive(true);
            State.setState(handler.getGame().gamepauseState);
        }
        Level.tick();
        playerBar.tick();

        System.out.println("AICI");
        System.out.println(HealthDatabase);
        System.out.println(PointsDatabase);
        System.out.println(DamageDatabase);
        System.out.println(TryingDatabase);
        System.out.println(MinutesDatabase);
        System.out.println(SecondDatabase);
        System.out.println(LevelDatabase);
        System.out.println(NameDatabase);

        //player.tick();
        //lava.tick();
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(MenuBackground,0,0,null);
        Level.render(g);
        playerBar.render(g);
        //player.render(g);
        //lava.render(g);

        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.YELLOW);
        g.drawString(Integer.toString(handler.getGame().GetFps()), handler.getWidth()-30, 30);
    }

    public void normalTimer(){
        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                seconds++;
                ddSeconds = decimalFormat.format(seconds);
                ddMinutes = decimalFormat.format(minutes);

                System.out.println("Timer: " + minutes + " : " + seconds);

                if(seconds == 60){
                    seconds = 0;
                    minutes++;
                    System.out.println("Timer" + minutes + " : " + seconds);
                }

            }
        });
    }

    public String getDdSeconds(){
        return ddSeconds;
    }

    public String getDdMinutes(){
        return ddMinutes;
    }

    public static int[] SetDificulty(){

        if(dificulty == 2){
            CaracteristicsPlayer[0] = 75; // Viata
            CaracteristicsPlayer[1] = 10; // Damage
            CaracteristicsPlayer[2] = 2; // Viteza
        }
        else if(dificulty == 3){
            CaracteristicsPlayer[0] = 50; // Viata
            CaracteristicsPlayer[1] = 5; // Damage
            CaracteristicsPlayer[2] = 1; // Viteza
        }
        else{
            CaracteristicsPlayer[0] = 100; // Viata
            CaracteristicsPlayer[1] = 15; // Damage
            CaracteristicsPlayer[2] = 3; // Viteza
        }
        return CaracteristicsPlayer;
    }

    public static int[] SetDificultyEnemies(){

        if(dificulty == 2){
            //Inamic1
            CaracteristicsEnemies[0] = 250; // Viata
            CaracteristicsEnemies[1] = 10; // Damage
            CaracteristicsEnemies[2] = 2; // Viteza

            //Inamic2
            CaracteristicsEnemies[3] = 750; // Viata
            CaracteristicsEnemies[4] = 25; // Damage
            CaracteristicsEnemies[5] = 2; // Viteza

            //Boss
            CaracteristicsEnemies[6] = 3500; // Viata
            CaracteristicsEnemies[7] = 50; // Damage
            CaracteristicsEnemies[8] = 2; // Viteza
        }
        else if(dificulty == 3){
            //Inamic1
            CaracteristicsEnemies[0] = 500; // Viata
            CaracteristicsEnemies[1] = 15; // Damage
            CaracteristicsEnemies[2] = 3; // Viteza

            //Inamic2
            CaracteristicsEnemies[3] = 1000; // Viata
            CaracteristicsEnemies[4] = 75; // Damage
            CaracteristicsEnemies[5] = 3; // Viteza

            //Boss
            CaracteristicsEnemies[6] = 4000; // Viata
            CaracteristicsEnemies[7] = 150; // Damage
            CaracteristicsEnemies[8] = 3; // Viteza
        }
        else{
            //Inamic1
            CaracteristicsEnemies[0] = 150; // Viata
            CaracteristicsEnemies[1] = 5; // Damage
            CaracteristicsEnemies[2] = 1; // Viteza

            //Inamic2
            CaracteristicsEnemies[3] = 500; // Viata
            CaracteristicsEnemies[4] = 15; // Damage
            CaracteristicsEnemies[5] = 1; // Viteza

            //Boss
            CaracteristicsEnemies[6] = 3000; // Viata
            CaracteristicsEnemies[7] = 30; // Damage
            CaracteristicsEnemies[8] = 2; // Viteza
        }
        return CaracteristicsEnemies;
    }
}
