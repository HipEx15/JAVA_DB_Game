package Exceptions;

//Exceptie in cazul in care fisierul citit este gol.

public class EmptyWorldFileException extends Exception{
    public EmptyWorldFileException(){
        super("Empty World File");
        System.exit(0);
    }
}
