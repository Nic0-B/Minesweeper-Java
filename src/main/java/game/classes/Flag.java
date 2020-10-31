package game.classes;

import game.interfaces.GameObject;

/**
 * Die Flag Klasse ist eine simple Klasse fuer Flag Objekte die lediglich die
 * Darstellung von Flaggen innerhalb des Spiels definiert.
 * 
 * @author Nico Bozarslan
 * @version 1.0
 */
public class Flag implements GameObject {

    private final String symbol = "F";

    /**
     * Konstruktor der Flag Klasse.
     */
    public Flag() {
    }

    /**
     * Die toString Methode gibt das Symbol das fuer Flaggen definiert ist als
     * String wieder.
     * 
     * @return Symbol fuer Flagge.
     */
    public String toString() {

        return this.symbol;
    }
}
