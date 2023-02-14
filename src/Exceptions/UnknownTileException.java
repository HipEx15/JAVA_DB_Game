package Exceptions;

//Exceptie in cazul in care intalnesc un tile cu un id nedifinit.

public class UnknownTileException extends Exception {
    public UnknownTileException() {
        super("Unknown Tile");
        System.exit(0);
    }
}
