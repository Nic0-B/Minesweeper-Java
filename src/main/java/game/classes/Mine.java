package game.classes;

import game.interfaces.GameObject;

/**
 * Die Mine Klasse ist eine simple Klasse fuer Minen Objekte die lediglich die
 * Darstellung von Minen innerhalb des Spiels definiert.
 * 
 * @author Nico Bozarslan
 * @version 1.0
 */
public class Mine implements GameObject {

    private final String symbol = "#";

    /**
     * Konstruktor der Mine Klasse.
     */
    public Mine() {
    }

    /**
     * Die toString Methode gibt das Symbol das fuer Minen definiert ist als String
     * wieder.
     * 
     * @return Symbol fuer Mine.
     */
    public String toString() {

        return this.symbol;
    }
}
