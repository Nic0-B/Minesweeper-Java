package game.classes;

import game.interfaces.GameObject;

/**
 * Die Fog Klasse ist eine simple Klasse fuer Fog Objekte die lediglich die
 * Darstellung von "Nebel" (d.h. der Bereich des Spielfeldes den der Spieler
 * noch nicht aufgedeckt hat) innerhalb des Spiels definiert.
 * 
 * @author Nico Bozarslan
 * @version 1.0
 */
public class Fog implements GameObject {

    private final String symbol = "☒";

    /**
     * Konstruktor der Fog Klasse.
     */
    public Fog() {
    }

    /**
     * Die toString Methode gibt das Symbol das fuer Nebel definiert ist als String
     * wieder.
     * 
     * @return Symbol fuer Nebel.
     */
    public String toString() {

        return this.symbol;
    }
}
