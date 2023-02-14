package State;

import AudioPlayer.AudioManager;
import Entity.Creature.Player;
import Entity.Entity;
import Game.Assets;
import Game.Handler;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;

import static Game.Assets.LostBackground;

//State-ul care apare atunci cand ai pierdut jocul.
public class LoseState extends State{

    private UIManager uiManager;
    public Rectangle Menu;

    public LoseState(Handler handler) {

        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 150, 525, 225, 45, () -> {
            isUIManagerActive = false;
            handler.getGame().gameState.timer.stop();
            handler.getGame().gameState.StartNewGame();
            State.setState(handler.getGame().gameState);
            //handler.getLevel().getEntityManager().Set_EnemyNumber(0);
        }, "Restart"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 120, 600, 155, 45, () -> {
            isUIManagerActive = false;
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
            Entity.Trying = 0;
            handler.getGame().audioManager.stopMusic();
            handler.getGame().audioManager = new AudioManager(handler.getGame(), 2);
            handler.getGame().audioManager.playMusic();
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
            handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
        }
    }

    @Override
    public void render(Graphics g) {

        handler.getGame().gameState.timer.stop();
        g.drawImage(LostBackground,0,0,null);

        if (isUIManagerActive){
            uiManager.render(g);
        }

        DrawText("Ai pierdut !", handler.getWidth()/2 - 275, handler.getHeight()/2 - 50, g, 40);

        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.YELLOW);
        g.drawString(Integer.toString(handler.getGame().GetFps()), handler.getWidth()-30, 30);

        System.out.println("Incercari " + Entity.Trying);
    }
}
