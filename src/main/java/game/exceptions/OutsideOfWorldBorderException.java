package game.exceptions;

/**
 * Die OutsideOfWorldBorderException Klasse realisiert das Erstellen von neuen
 * Spielen und beinhaltet Methoden fuer Objekte auf dem Spielfeld.
 * 
 * @author Nico Bozarslan
 * @version 1.0
 */
public class OutsideOfWorldBorderException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

    public OutsideOfWorldBorderException(String exception) {
        super(exception);
    }
}
