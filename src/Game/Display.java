package Game;

import State.GameCamera;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.tools.Tool;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Display {

    private JFrame frame; //Afiseaza fereastra pe ecran
    private Canvas canvas; // Panza pe care desenam

    private String title; // Titlul ferestrei
    private int width, height; // Dimensiunea ferestrei

    private static volatile Display instance;

    private Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    public static Display getInstance(String title, int width, int height){
        if(instance == null){
            synchronized (Display.class){
                if(instance == null){
                    instance = new Display(title, width, height);
                }
            }
        }
        return instance;
    }

    //Initialize JFrame

    private void createDisplay(){
        frame = new JFrame(title); // Titlu JFrame
        frame.setSize(width,height); // Dimensiune JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Implementarea butonului de exit(cel din dreapta sus)
        frame.setResizable(false); // Redimensionarea ferestrei sa fii imposibila.
        frame.setLocationRelativeTo(null); // Fereastra sa fie de fiecare data pozitionata pe centrul ecranului.
        frame.setVisible(true);

        //frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageIO.read(new File("/Textures/MagicWand.png")), new Point(0, 0), "Cursor"));
        frame.setIconImage(new ImageIcon(ImageLoader.loadImage("/Textures/Icon.png")).getImage());

        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = ImageLoader.loadImage("/Textures/MagicWand.png");
        Cursor cursor = t.createCustomCursor(i, new Point(0,0), "none");
        frame.setCursor(cursor);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height)); // Crearea panzei
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        canvas.setFocusable(false);

        frame.add(canvas); // Adaugarea panzei la JFrame.
        frame.pack();
    }

    public Canvas getCanvas(){ // Getting the canvas (because is private)
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }

}
