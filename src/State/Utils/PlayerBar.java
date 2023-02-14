package State.Utils;

import Entity.Creature.Enemy;
import Entity.Creature.Player;
import Entity.EntityManager;
import Game.Assets;
import Game.Display;
import Game.Handler;
import State.State;
import UI.UIManager;

import javax.swing.*;
import java.awt.*;

import static Utils.Utils.LoadFont;

//Afisarea tututor informatiilor legate de jucator pe ecran, atunci cand incepe jocul. Insemnand timp, puncte, atac, viata.
public class PlayerBar extends State {
    protected int player_health;
    protected int player_points;
    protected int player_damage;

    public PlayerBar(Handler handler) {
        super(handler);
    }

    @Override
    protected UIManager getUiManager() {
        return null;
    }

    @Override
    public void tick() {
        this.player_health = Player.health;
        this.player_points = handler.getLevel().getEntityManager().getPlayer().getPoints();
        this.player_damage = handler.getLevel().getEntityManager().getPlayer().getBulletdamage();
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.Heart,10,10,48,48,null);
        LoadFont(g, 21);
        g.setColor(new Color(255,127,0));
        g.drawString(Integer.toString(player_health), 80, 43);
        g.drawImage(Assets.DamageWand, -14, 50, 96, 96, null);
        g.drawString(Integer.toString(player_damage), 90, 105);
        g.drawImage(Assets.Enemies, 8, 145, 48, 48, null);
        g.drawString(Integer.toString(handler.getLevel().NumberOfEnemy), 90, 175);
        g.drawString("Puncte: ", 900, 50);
        g.drawString(Integer.toString(player_points), 1100, 50);

        String tmp = handler.getGame().gameState.getDdMinutes() + " : " + handler.getGame().gameState.getDdSeconds();
        g.drawString(tmp, handler.getWidth()/2, handler.getHeight() - 20);
    }
}
