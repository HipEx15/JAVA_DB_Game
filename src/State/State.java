package State;

import Game.*;
import UI.UIManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static Utils.Utils.LoadFont;

//State-urile din joc.
public abstract class State {

    //GAME STATE MANAGER

    private static State currentState = null;
    boolean isUIManagerActive = false;
    public static boolean hasGameStarted = false;

    public static void setState(State state){
        if (!(state instanceof GameState)){
            state.setUIManagerActive(true);
        }
        state.SetUIManagerForMouseManager(state.getUiManager());
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }


    //ABSTRACT CLASS
    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public void setUIManagerActive(boolean UIManagerActive) {
        isUIManagerActive = UIManagerActive;
    }

    public void SetUIManagerForMouseManager(UIManager uiManager){
        handler.getMouseManager().setUIManager(uiManager);
    }

    protected abstract UIManager getUiManager();

    public abstract void tick();

    public abstract void render(Graphics g);

    public void DrawText(String string, int posX, int posY, Graphics g, int size){

        //g.setFont(new Font("Volcanic Dungeon", Font.BOLD | Font.ITALIC, size));
        LoadFont(g, size);

        g.setColor(Color.BLACK);
        g.drawString(string, posX + 5, posY + 5);

        g.setColor(Color.white);
        g.drawString(string, posX, posY);

    }
}
