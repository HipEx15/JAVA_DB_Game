package State;

import Game.Handler;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static Database.Database.PrintFromDataBase;
import static Game.Assets.Resume;

//State-ul pentru afisarea clasamentului, primii 4 cei mai buni.
public class TopState extends State{

    private UIManager uiManager;

    public TopState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 120, 600, 155, 45, () -> {
            isUIManagerActive = false;
            if (hasGameStarted){
                handler.getGame().gamepauseState.setUIManagerActive(true);
                State.setState(handler.getGame().gamepauseState);
            }
            else{
                handler.getGame().menuState.setUIManagerActive(true);
                State.setState(handler.getGame().menuState);
            }
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

        String title1 = "Clasament";
        DrawText(title1, handler.getWidth()/2 - 190, 100, g, 30);

        g.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
        g.setColor(Color.WHITE);

        String tmp1 = "NUME";
        String tmp2 = "PUNCTE";
        String tmp3 = "INCERCARI";
        String tmp4 = "TIMP";
        String tmp5 = "DIFICULTATE";

        g.drawString(tmp1, 50, 180);
        g.drawString(tmp2, 275, 180);
        g.drawString(tmp3, 550, 180);
        g.drawString(tmp4, 825, 180);
        g.drawString(tmp5, 1000, 180);

        PrintFromDataBase(g);

        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.YELLOW);
        g.drawString(Integer.toString(handler.getGame().GetFps()), handler.getWidth()-30, 30);


        if (isUIManagerActive){
            uiManager.render(g);
        }
    }
}
