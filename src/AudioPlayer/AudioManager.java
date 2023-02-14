package AudioPlayer;

import Game.Game;

import static Database.Database.GetMusic;

public class AudioManager {

    Game game;

    public Audio music;

    public AudioManager(Game game, int id)
    {
        this.music = new Audio(GetMusic(id));
        this.game = game;
    }

    public void playMusic()
    {
        music.loop();
    }
    public void stopMusic() { music.stop();}

    /*
    public void playGunShot() {
        if(game.setings.soundon == "on")
            new Audio(GetMusic(1)).play();
    }

    public void playDamageTacken()
    {
        if(game.setings.soundon == "on")
            new Audio("res/music/damageTacken.wav").play();
    }

    public void playPickUpDamage()
    {
        if(game.setings.soundon == "on")
            new Audio("res/music/pickUpDamage.wav").play();
    }

    public void playZombieDeath()
    {
        if(game.setings.soundon == "on")
            new Audio("res/music/zombieDeath.wav").play();
    }*/
}
