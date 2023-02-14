package State.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static Database.Database.GetLevelDMGPathFromDataBase;
import static Database.Database.GetLevelPathFromDataBase;
import static State.Utils.Lvls.lvls.*;

//Un tip de date lvls, pentru a salva proprietatile fiecarui nivel, dar si a face tranzitia intre ele.
public class Lvls {

    public enum lvls {
        level_1, level_2, level_3, level_5, level_6, level_7
    }

    public static lvls nextLevel(lvls level){
        switch(level){
            case level_1:
                return level_2;
            case level_2:
            default:
                return level_3;
        }
    }

    public static lvls nextLevelDMG(lvls level){
        switch(level){
            case level_5:
                return level_6;
            case level_6:
            default:
                return level_7;
        }
    }

    public static lvls previousLevel(lvls level){
        switch(level){
            case level_3:
                return level_2;
            default:
                return level_1;
        }
    }

    public static String GetLevelName(lvls level){
        switch(level){
            case level_1:
                return "Nivelul 1";
            case level_2:
                return "Nivelul 2";
            default:
                return "Nivelul final";
        }
    }

    public static String GetLevelWorld(lvls level){

        switch(level){
            case level_1:
                //return "Resource/Levels/Level1.txt";
                return GetLevelPathFromDataBase(1);
            case level_2:
                //return "Resource/Levels/Level2.txt";
                return GetLevelPathFromDataBase(2);
            case level_3:
            default:
                //return "Resource/Levels/Level3.txt";
                return GetLevelPathFromDataBase(3);
        }
        /*if(level == level_1)
            return nivel1;
        else
            return null;*/
    }

    public static String GetLevelWorldDMG(lvls level){
        /*if(level == level_5)
            return nivel1DMG;
        else
            return null;
    }*/

        switch(level){
            case level_5:
                //return "Resource/Levels/Level1DMG.txt";
                return GetLevelDMGPathFromDataBase(1);
            case level_6:
                //return "Resource/Levels/Level2DMG.txt";
                return GetLevelDMGPathFromDataBase(2);
            case level_7:
            default:
                //return "Resource/Levels/Level3DMG.txt";
                return GetLevelDMGPathFromDataBase(3);
        }
    }

}
