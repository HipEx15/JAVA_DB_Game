package State;

import AudioPlayer.AudioManager;
import Entity.Creature.Player;
import Entity.Entity;
import Game.Handler;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;

import static Database.Database.GetMusic;
import static Game.Assets.MenuBackground;
import static Game.Assets.Resume;
import static Utils.Utils.LoadFont;

//State-ul de pauza, care apare la apasarea tastei escape.
public class GamePauseState extends State{

    private UIManager uiManager;

    public GamePauseState(Handler handler) {
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        //handler.getGame().menuState.audioPlayer.loop_play();
        //handler.getGame().menuState.audioPlayer.loop_where_left();

        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 145, 240, 225, 45, () -> {
            isUIManagerActive = false;
            State.setState(handler.getGame().gameState);
        }, "Revenire"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 145, 315, 225, 45, () -> {
            isUIManagerActive = false;
            handler.getGame().gameState.timer.stop();
            handler.getGame().audioManager.stopMusic();
            handler.getGame().gameState.StartNewGame();
            State.setState(handler.getGame().gameState);
            //handler.getLevel().getEntityManager().Set_EnemyNumber(0);
            //Player.health = Player.getHealth();
        }, "Restart"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 113, 390, 155, 45, () -> {
            isUIManagerActive = false;
            hasGameStarted = false;
            handler.getGame().audioManager.stopMusic();
            handler.getGame().audioManager = new AudioManager(handler.getGame(), 2);
            handler.getGame().audioManager.playMusic();
            State.setState(handler.getGame().menuState);
            handler.getGame().gameState.timer.stop();
            //Player.health = 100;
            handler.getGame().gameState.dificulty = 0;
        }, "Meniu"));
        //handler.getGame().getWidth()/2 - 120, 475, 170, 45, ()
        //handler.getGame().getWidth()/2 - 165, 600, 365, 50, ()
        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 120, 465, 170, 45, () -> {
            isUIManagerActive = false;
            System.exit(0);
        }, "Iesire"));
    }

    @Override
    protected UIManager getUiManager() {
        return uiManager;
    }

    @Override
    public void tick() {
        if (isUIManagerActive){
            uiManager.tick();
        }
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Resume,0,0,null);
        //g.setFont(new Font("Volcanic Dungeon", Font.BOLD | Font.ITALIC, 40));

        LoadFont(g, 40);

        String title = "Temnita Vrajitorilor";

        int width = g.getFontMetrics().stringWidth(title);

        /*int R = (int) (Math.random( )*256);
        int G = (int)(Math.random( )*256);
        int B = (int)(Math.random( )*256);

        Color randomColor = new Color(R, G, B);
        g.setColor(randomColor);*/

        DrawText(title, handler.getWidth()/2-width/2, 100, g, 40);

        if (isUIManagerActive) {
            uiManager.render(g);
        }

        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.YELLOW);
        g.drawString(Integer.toString(handler.getGame().GetFps()), handler.getWidth()-30, 30);
    }
}
