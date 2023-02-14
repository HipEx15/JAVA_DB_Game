package Database;

import Entity.Creature.Player;
import Entity.Entity;
import Game.Game;
import Game.Handler;
import KeyManager.KeyManager;
import State.GameState;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Metodele necesare pentru a scrie, citi si a afisa ce contine baza de date.

public class Database {

    public static Handler handler;

    public static String GetLevelPathFromDataBase(int level){
        Connection c = null;
        Statement stmt = null;
        String path = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TemnitaVrajitorilor.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM LevelPath WHERE Level=" + level + ";" );
            path = rs.getString("PathLevel");

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return path;
    }

    public static String GetLevelDMGPathFromDataBase(int level){
        Connection c = null;
        Statement stmt = null;
        String path = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TemnitaVrajitorilor.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM LevelPath WHERE Level=" + level + ";" );
            path = rs.getString("PathDMG");

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return path;
    }

    public static String GetMusic(int id){
        Connection c = null;
        Statement stmt = null;
        String path = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TemnitaVrajitorilor.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Music WHERE Id=" + id + ";" );
            path = rs.getString("Music");

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return path;
    }

    public static void insertScore(String Name, int Points, int Try, String Time, int Difficulty){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TemnitaVrajitorilor.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String current_date = returnCurrentDate();

            String sql =  "INSERT INTO Score (Name,Points,Try,Time,Date,Difficulty) " +
                    "VALUES ('" + Name + "'," +  Points + "," + Try + ",'" + Time + "','" + current_date + "'," + Difficulty + ");";

            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static String returnCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static void PrintFromDataBase(Graphics g){
        Connection c = null;
        Statement stmt = null;
        String[] path = new String[5];

        final String QUERY = "SELECT * FROM Score";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TemnitaVrajitorilor.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY + " ORDER BY Points DESC, Try ASC, Time ASC, Difficulty DESC;");
            int counter = 0;
            int y = 270;
            while ( rs.next() ) {
                String Name = rs.getString("Name");
                int Points = rs.getInt("Points");
                int Try = rs.getInt("Try");
                String Time = rs.getString("Time");
                int Difficulty = rs.getInt("Difficulty");

                path[0] = Name;
                path[1] = String.valueOf(Points);
                path[2] = String.valueOf(Try);
                path[3] = Time;
                path[4] = String.valueOf(Difficulty);

                if(counter < 4) {
                    counter++;
                    g.drawString(path[0], 67, y);
                    g.drawString(path[1], 300, y);
                    g.drawString(path[2], 630, y);
                    g.drawString(path[3], 805, y);
                    g.drawString(path[4], 1100, y);
                    y += 90;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static void saveInto(int Health, int Points, int Damage, int Trying, int Minutes, int Seconds, int Level, int Difficulty ,String Name){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TemnitaVrajitorilor.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            String current_date = returnCurrentDate();

            String sql =  "INSERT INTO Save (Health, Points, Damage, Trying, Minutes, Seconds, Level, Name, Date, Difficulty) " +
                    "VALUES (" + Health + "," +  Points + "," + Damage + "," + Trying + "," + Minutes + "," + Seconds + "," + Level + ",'" + Name + "','" + current_date + "'," + Difficulty +");";

            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public static String[] getfromDatabaseSaves(){
        Connection c = null;
        Statement stmt = null;
        String[] path = new String[4];

        final String QUERY = "SELECT * FROM Save";

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:TemnitaVrajitorilor.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery(QUERY + " ORDER BY Date DESC");

            int counter = 0;
            //int y = 270;

            while ( rs.next() && counter < 4) {
                int Health = rs.getInt("Health");
                int Points = rs.getInt("Points");
                int Damage = rs.getInt("Damage");
                int Trying = rs.getInt("Trying");
                int Minutes = rs.getInt("Minutes");
                int Seconds = rs.getInt("Seconds");
                int Level = rs.getInt("Level");
                String Name = rs.getString("Name");
                int Dificulty = rs.getInt("Difficulty");

                path[counter++] = "       " +String.valueOf(Name) + "     " + String.valueOf(Health) + "     " + String.valueOf(Points) + "     " + String.valueOf(Damage) + "     " + String.valueOf(Trying) + "     " + String.valueOf(Minutes) + " " + String.valueOf(Seconds) + "     " + String.valueOf(Level) + "     " + String.valueOf(Dificulty);

                System.out.println(path[counter-1]);
                //path[0] = String.valueOf(Health);
                //path[1] = String.valueOf(Points);
                //path[2] = String.valueOf(Damage);
                //path[3] = String.valueOf(Trying);
                //path[4] = String.valueOf(Minutes);
                //path[5] = String.valueOf(Seconds);
                //path[6] = String.valueOf(Level);
                //path[7] = String.valueOf(Name);

                /*if(counter < 4) {
                    counter++;
                    g.drawString(path[7], 100, y);
                    g.drawString(path[0], 300, y);
                    g.drawString(path[1], 500, y);
                    g.drawString(path[2], 700, y);
                    g.drawString(path[3], 900, y);
                    g.drawString(path[4], 1100, y);
                    g.drawString(path[5], 1300, y);
                    g.drawString(path[6], 1500, y);
                    y += 90;
                }*/

            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return path;
    }
}
