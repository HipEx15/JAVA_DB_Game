package State;

import AudioPlayer.AudioManager;
import Game.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import UI.*;

import static Game.Assets.MenuBackground;
import static Utils.Utils.LoadFont;

//State-ul de meniu, de unde putem accesa celelalte submeniuri create.
public class MenuState extends State{

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 120, 240, 170, 45, () -> {
            isUIManagerActive = false;
            hasGameStarted = false;
            handler.getKeyManager().buffer = "";
            State.setState(handler.getGame().insertName);
        }, "Start"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 165, 315, 265, 45, () -> {
            isUIManagerActive = false;
            State.setState(handler.getGame().topState);
        }, "Clasament"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 145, 390, 225, 45, () -> {
            isUIManagerActive = false;
            State.setState(handler.getGame().storyState);
        }, "Poveste"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 145, 465, 225, 45, () -> {
            isUIManagerActive = false;
            handler.getGame().saveState = new SaveState(handler);
            State.setState(handler.getGame().saveState);
        }, "Salvari"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 120, 540, 170, 45, () -> {
            isUIManagerActive = false;
            System.exit(0);
            handler.getGame().audioManager.stopMusic();
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

        g.drawImage(MenuBackground,0,0,null);
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
