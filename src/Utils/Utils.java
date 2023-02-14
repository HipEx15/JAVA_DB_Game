package Utils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//Metode pentru citirea din fisier, convertirea din String in Integer, dar si pentru a initializa font-ul pentru joc.

public class Utils {

    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }
        catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static void LoadFont(Graphics g, int size){
        try {
            g.setFont(Font.createFont(Font.TRUETYPE_FONT, new File("Resource/Volcanic Dungeon.ttf")).deriveFont(Font.BOLD | Font.ITALIC, size));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

}
