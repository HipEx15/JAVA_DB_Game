import Game.*;

public class Launcher {

    public static void main(String[] args){
        Game game = new Game("Temnita Vrajitorilor", 1280, 720);
        game.start(); // Starting the game (the game loop)
    }

}
