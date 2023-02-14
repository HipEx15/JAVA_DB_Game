package State;

import Entity.Creature.Player;
import Entity.Entity;
import Game.Handler;
import State.Utils.Lvls;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;

import static Database.Database.getfromDatabaseSaves;
import static Database.Database.saveInto;
import static State.Utils.Lvls.GetLevelName;
import static State.Utils.Lvls.nextLevel;

//State-ul dintre 2 nivele.
public class BetweenLevelsState extends State{

    private UIManager uiManager;

    public BetweenLevelsState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;
        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 225, handler.getGame().getHeight() - 100, 405, 50, () -> {
            isUIManagerActive = false;
            handler.getGame().audioManager.stopMusic();
            handler.getGame().gameState.startNextLevel();
            State.setState(handler.getGame().gameState);
        }, "Urmatorul nivel"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth() / 2 - 135, 545, 215, 45, () -> {
            isUIManagerActive = false;
            State.setState(handler.getGame().nextLevel);
            handler.getGame().gameState.HealthDatabase = Player.getHealth();
            handler.getGame().gameState.PointsDatabase = handler.getLevel().getEntityManager().getPlayer().getPoints();
            handler.getGame().gameState.DamageDatabase = handler.getLevel().getEntityManager().getPlayer().getBulletdamage();
            handler.getGame().gameState.TryingDatabase = Entity.Trying + 1;
            handler.getGame().gameState.MinutesDatabase = GameState.minutes;
            handler.getGame().gameState.SecondDatabase = GameState.seconds;

            if( Lvls.nextLevel(GameState.current_level) == Lvls.lvls.level_2 )
                handler.getGame().gameState.LevelDatabase = 2;
            else if( Lvls.nextLevel(GameState.current_level) == Lvls.lvls.level_3 )
                handler.getGame().gameState.LevelDatabase = 3;

            handler.getGame().gameState.NameDatabase = handler.getKeyManager().buffer;
            saveInto(handler.getGame().gameState.HealthDatabase, handler.getGame().gameState.PointsDatabase, handler.getGame().gameState.DamageDatabase, handler.getGame().gameState.TryingDatabase, handler.getGame().gameState.MinutesDatabase, handler.getGame().gameState.SecondDatabase, handler.getGame().gameState.LevelDatabase,  GameState.dificulty  ,handler.getGame().gameState.NameDatabase);
            //handler.getLevel().getEntityManager().Set_EnemyNumber(0);
        }, "Salvare"));
    }

    @Override
    protected UIManager getUiManager() {
        return uiManager;
    }

    @Override
    public void tick() {
        if (isUIManagerActive) {
            uiManager.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, handler.getWidth(), handler.getHeight());

        String tmp = GetLevelName(GameState.current_level) + " complet !";

        DrawText(tmp , handler.getWidth()/2 - 350, handler.getHeight()/2 - 50, g, 35);

        if (isUIManagerActive) {
            uiManager.render(g);
        }

        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.YELLOW);
        g.drawString(Integer.toString(handler.getGame().GetFps()), handler.getWidth()-30, 30);
    }
}
