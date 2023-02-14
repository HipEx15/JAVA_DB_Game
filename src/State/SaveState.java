package State;

import Game.Handler;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import static Database.Database.PrintFromDataBase;
import static Database.Database.getfromDatabaseSaves;
import static Game.Assets.Resume;

//State-ul unde sunt afisate ultimele 4 salvari.
public class SaveState extends State{

    private UIManager uiManager;
    public String[] temp = new String[4];

    public SaveState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;
        temp = getfromDatabaseSaves();
        //temp = getfromDatabaseSaves();

        if(temp[0] != null) {
            uiManager.addObject(new UIButton(25, 215, 1200, 45, () -> {
                hasGameStarted = true;
                isUIManagerActive = false;
                handler.getGame().audioManager.stopMusic();
                handler.getGame().gameState.loadLevel(temp[0]);
                State.setState(handler.getGame().gameState);
            }, temp[0]));
        }

        if(temp[1] != null) {
            uiManager.addObject(new UIButton(25, 315, 1200, 45, () -> {
                hasGameStarted = true;
                isUIManagerActive = false;
                handler.getGame().audioManager.stopMusic();
                handler.getGame().gameState.loadLevel(temp[1]);
                State.setState(handler.getGame().gameState);
            }, temp[1]));
        }

        if(temp[2] != null) {
            uiManager.addObject(new UIButton(25, 415, 1200, 45, () -> {
                hasGameStarted = true;
                isUIManagerActive = false;
                handler.getGame().audioManager.stopMusic();
                handler.getGame().gameState.loadLevel(temp[2]);
                State.setState(handler.getGame().gameState);
            }, temp[2]));
        }

        if(temp[3] != null) {
            uiManager.addObject(new UIButton(25, 515, 1200, 45, () -> {
                hasGameStarted = true;
                isUIManagerActive = false;
                handler.getGame().audioManager.stopMusic();
                handler.getGame().gameState.loadLevel(temp[3]);
                State.setState(handler.getGame().gameState);
            }, temp[3]));
        }


        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 120, 600, 155, 45, () -> {
            isUIManagerActive = false;
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
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
            if (hasGameStarted){
                handler.getGame().gamepauseState.setUIManagerActive(true);
                State.setState(handler.getGame().gamepauseState);
            }
            else{
                handler.getGame().menuState.setUIManagerActive(true);
                State.setState(handler.getGame().menuState);
            }
        }
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Resume,0,0,null);

        String title1 = "Salvari";
        DrawText(title1, handler.getWidth()/2 - 170, 100, g, 30);

        g.setFont(new Font("Volcanic Dungeon", Font.BOLD, 12));
        g.setColor(Color.WHITE);

        String tmp1 = "NUME/";
        String tmp2 = "VIATA/";
        String tmp3 = "PUNCTE/";
        String tmp4 = "DAMAGE/";
        String tmp5 = "INCERCARI/";
        String tmp6 = "TIMP/";
        String tmp7 = "LEVEL/";
        String tmp8 = "DIFICULTATE [1 - USOR; 2 - MEDIU; 3 - GREU]";

        g.drawString(tmp1, 15, 180);
        g.drawString(tmp2, 95, 180);
        g.drawString(tmp3, 180, 180);
        g.drawString(tmp4, 290, 180);
        g.drawString(tmp5, 400, 180);
        g.drawString(tmp6, 550, 180);
        g.drawString(tmp7, 620, 180);
        g.drawString(tmp8, 710, 180);

        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.YELLOW);
        g.drawString(Integer.toString(handler.getGame().GetFps()), handler.getWidth()-30, 30);


        if (isUIManagerActive){
            uiManager.render(g);
        }
    }
}
