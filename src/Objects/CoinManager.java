package Objects;

import Game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class CoinManager {

    private Handler handler;
    private ArrayList<Coin> coins;

    public CoinManager(Handler handler){
        this.handler = handler;
        this.coins = new ArrayList<Coin>();
    }

    public void tick(){
        Iterator<Coin> it = coins.iterator();
        while(it.hasNext()){
            Coin i = it.next();
            i.tick();
            if (i.isPickedUp()){
                it.remove();
            }
        }
    }

    public void render(Graphics g){
        for (Coin i : coins){
            i.render(g);
        }
    }

    public void addItem(Coin i){
        i.setHandler(handler);
        coins.add(i);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Coin> getItems() {
        return coins;
    }

    public void setItems(ArrayList<Coin> items) {
        this.coins = items;
    }
}
