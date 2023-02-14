package State;

import Entity.Creature.Player;
import Game.Handler;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;
import java.util.Objects;

import static Game.Assets.Resume;
import static Utils.Utils.LoadFont;

//State-ul pentru inserarea numelui unui jucator si inceperea jocului.
public class InsertName extends State{

    private UIManager uiManager;

    public InsertName(Handler handler) {
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 150, 590, 225, 45, () -> {

            if(Objects.equals(handler.getKeyManager().buffer, "")){
                hasGameStarted = false;
                isUIManagerActive = true;
                State.setState(handler.getGame().insertName);
            }
            else{
                hasGameStarted = true;
                isUIManagerActive = false;
                handler.getGame().audioManager.stopMusic();
                handler.getGame().gameState.StartNewGame();
                Player.health = GameState.CaracteristicsPlayer[0];
                State.setState(handler.getGame().gameState);
            }
        }, "Continua"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 110, 400, 155, 45, () -> {
            isUIManagerActive = false;
            handler.getGame().gameState.dificulty = 1;
            State.setState(handler.getGame().insertName);
        }, "Usor"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 115, 450, 155, 45, () -> {
            isUIManagerActive = false;
            handler.getGame().gameState.dificulty = 2;
            State.setState(handler.getGame().insertName);
        }, "Mediu"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 110, 500, 155, 45, () -> {
            isUIManagerActive = false;
            handler.getGame().gameState.dificulty = 3;
            State.setState(handler.getGame().insertName);
        }, "Greu"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 120, 650, 155, 45, () -> {
            isUIManagerActive = false;
            handler.getGame().menuState.setUIManagerActive(true);
            hasGameStarted = false;
            handler.getGame().gameState.dificulty = 0;
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

        DrawText("Introduceti numele: ", handler.getWidth()/2 - 280, handler.getHeight() - 500, g, 25);

        g.setColor(Color.white);
        g.fillRect(handler.getWidth()/2 - 210, handler.getHeight()/2 - 43, 370, 70);
        g.setColor(Color.BLACK);
        g.fillRect(handler.getWidth()/2 - 200, handler.getHeight()/2 - 40, 350, 64);
        DrawText(handler.getKeyManager().buffer, handler.getWidth()/2 - 190, handler.getHeight()/2, g , 25);
    }
}
