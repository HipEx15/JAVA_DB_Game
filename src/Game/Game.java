package Game;

// Clasa game este clasa principala pentru joc.

import AudioPlayer.Audio;
import AudioPlayer.AudioManager;
import KeyManager.KeyManager;
import MouseManager.MouseManager;
import State.GameState;
import State.MenuState;
import State.*;
import Tile.Tile;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display display;
    private int width, height;
    public String title;
    private int fps;

    private boolean running = false;
    private Thread thread; // Thread este un mini-program care se executa independent

    private BufferStrategy bs; // Permite calculatorului sa deseneze diverse lucruri pe ecran.
    private Graphics g;

    //STATES
    public GameState gameState;
    public State menuState;
    public State loseState;
    public State winState;
    public State gamepauseState;
    public State nextLevel;
    public State storyState;
    public State topState;
    public State insertName;
    public State saveState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    //TEST CODE
    //private BufferedImage test;
    //private SpriteSheet sheet;
    public AudioManager audioManager = new AudioManager(this, 2);

    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager(handler);
    }

    private void init(){ // Initialize graphics of the game
        display = Display.getInstance(title,width,height);
        display.getFrame().addKeyListener(keyManager);

        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        //TEST CODE
        //test = ImageLoader.loadImage("/Textures/FinallyV3.png");
        //sheet = new SpriteSheet(test);
        Assets.init();

        handler = new Handler(this);
        gameCamera = GameCamera.getInstance(handler,0,0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        loseState = new LoseState(handler);
        winState = new WinState(handler);
        gamepauseState = new GamePauseState(handler);
        nextLevel = new BetweenLevelsState(handler);
        storyState = new StoryState(handler);
        topState = new TopState(handler);
        insertName = new InsertName(handler);
        saveState = new SaveState(handler);

        audioManager.playMusic();

        //State.setState(gameState);
        State.setState(menuState);
    }

    private void tick(){
        keyManager.tick();

        if(State.getState() != null)
            State.getState().tick();
    }



    private void render(){ // Rendering the objects to the screen
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen

        g.clearRect(0,0,width,height);

        //DRAWING


        //TEST CODE
        /*g.setColor(Color.RED);
        g.fillRect(10,50,50,70);
        g.setColor(Color.GREEN);
        g.fillRect(0,0,10,10);*/

        //g.drawImage(test,0,0,null);
        //g.drawImage(sheet.crop(192,0,64,64), 5, 5, null); // Cropping a tile and putting on X,Y coords
        //g.drawImage(Assets.Heart, 64, 64, null);
        //g.drawImage(Assets.Backgrounds[2][8], 128, 128, null);
        //g.drawImage(Assets.Objects_Dark[2][9], 256, 256, null);
        //g.drawImage(Assets.Objects_Light[2][9], 512, 512, null);

        if(State.getState() != null)
            State.getState().render(g);

        bs.show(); // Showing to the screen
        g.dispose();
    }

    public void run(){ // !! MUST HAVE TO IMPLEMENT THE THREAD (RUNNABLE)

        init();
        Tile.init();

        int FPS = 60;
        double timePerTick = (double)1000000000/FPS; // Time in nanoseconds (Maximum time to execute renderer)
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); // It is like a clock (return value in nanoseconds)
        long timer = 0;
        int ticks = 0;
        int GC = 250;

        while(running){

            now = System.nanoTime(); // Current time in nanoseconds
            delta += (now - lastTime) / timePerTick; // How much time we have untill we call update and render
            timer += now - lastTime; // Amount of nanoseconds of our last call for this block
            lastTime = now;

            //System.gc();

            GC--;
            if(GC == 0){
                System.gc();
                GC = 250;
            }

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + ticks);
                this.fps = ticks;
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public synchronized void start(){ // Starting the thread
        if(running) // Checking the game if is already running
            return;

        running = true;
        thread = new Thread(this);
        thread.start(); // Calling run method
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public synchronized void stop(){ // Stopping the thread
        if(!running) // Checking the game if is already stopped
            return;
        running = false;
        try {
            thread.join(); // Stopping the thread (safely)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int GetFps(){
        return fps;
    }

}
