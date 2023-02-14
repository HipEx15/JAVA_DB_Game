package State;

import AudioPlayer.AudioManager;
import Entity.Creature.Player;
import Entity.Entity;
import Game.Game;
import Game.Handler;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Database.Database.insertScore;
import static Database.Database.returnCurrentDate;
import static Game.Assets.WinBackground;

//State-ul care apare atunci cand jucatorul a reusit sa castige jocul.
public class WinState extends State{

    private UIManager uiManager;
    private boolean access_to_database = false;

    public WinState(Handler handler) {

        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 120, 600, 155, 45, () -> {
            isUIManagerActive = false;
            handler.getGame().menuState.setUIManagerActive(true);
            hasGameStarted = false;

            State.setState(handler.getGame().menuState);
            handler.getKeyManager().buffer = "";
            Entity.Trying = 0;
            handler.getGame().audioManager.stopMusic();
            handler.getGame().audioManager = new AudioManager(handler.getGame(), 2);
            handler.getGame().audioManager.playMusic();
            access_to_database = false;
        }, "Meniu"));
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
        if (handler.getKeyManager().esc){
            Entity.Trying = 0;
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
            access_to_database = false;
        }
    }

    @Override
    public void render(Graphics g) {

        System.out.println(Entity.Trying);
        g.drawImage(WinBackground,0,0,null);

        if (isUIManagerActive){
            uiManager.render(g);
        }

        DrawText("Ai castigat !", handler.getWidth()/2 - 275, handler.getHeight()/2 - 50, g, 40);

        DrawText("Scor: ", handler.getWidth()/2 - 165, handler.getHeight()/2 + 25, g, 20);

        g.setColor(Color.BLACK);
        g.drawString( String.valueOf(Entity.points),handler.getWidth()/2 + 30 + 5, handler.getHeight()/2 + 25 + 5);
        g.setColor(Color.WHITE);
        g.drawString( String.valueOf(Entity.points),handler.getWidth()/2 + 30, handler.getHeight()/2 + 25);

        DrawText("Timpul: ", handler.getWidth()/2 - 165, handler.getHeight()/2 + 100, g, 20);
        String tmp = handler.getGame().gameState.getDdMinutes() + " : " + handler.getGame().gameState.getDdSeconds();
        g.setColor(Color.BLACK);
        g.drawString( tmp,handler.getWidth()/2 + 30 + 5, handler.getHeight()/2 + 100 + 5);
        g.setColor(Color.WHITE);
        g.drawString( tmp,handler.getWidth()/2 + 30, handler.getHeight()/2 + 100);

        DrawText("Incercari: ", handler.getWidth()/2 - 165, handler.getHeight()/2 + 175, g, 20);
        String tmp2 = String.valueOf(Entity.Trying);
        g.setColor(Color.BLACK);
        g.drawString( tmp2,handler.getWidth()/2 + 125 + 5, handler.getHeight()/2 + 175 + 5);
        g.setColor(Color.WHITE);
        g.drawString( tmp2,handler.getWidth()/2 + 125, handler.getHeight()/2 + 175);

        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.YELLOW);
        g.drawString(Integer.toString(handler.getGame().GetFps()), handler.getWidth()-30, 30);

        String Temp = handler.getGame().gameState.getDdMinutes() + " : " + handler.getGame().gameState.getDdSeconds();

        if(!access_to_database){
            insertScore(handler.getKeyManager().buffer, Player.points, Entity.Trying, Temp, GameState.dificulty);
            access_to_database = true;
        }
    }
}
