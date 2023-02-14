package KeyManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

import static State.State.hasGameStarted;

//Clasa care citeste input-ul de la tastatura, atat pentru a controla jucatorul, cat si pentru a introduce numele.
public class KeyManager implements KeyListener {

    public boolean[] keys;
    public boolean up, left, right, space, esc;
    public int number = 0;

    public static String buffer = "";

    public KeyManager(){
        keys = new boolean[256];
    }

    public void tick(){
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
        esc = keys[KeyEvent.VK_ESCAPE];
     }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if(up) {
            keys[KeyEvent.VK_W] = false;
            keys[KeyEvent.VK_UP] = false;
            number++;
        }

        if(!hasGameStarted){
            if(e.getKeyChar() >= 'A' && e.getKeyChar() <= 'Z' || e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z') {
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE && buffer.length() < 4)
                    buffer += (Character.toString(e.getKeyChar()));
                System.out.println(buffer);
            }
            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && buffer.length() > 0)
                buffer = buffer.substring(0, buffer.length() - 1);
        }

        System.out.println("Pressed !" + number);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        number = 0;
        System.out.println("Released!");
    }
}
