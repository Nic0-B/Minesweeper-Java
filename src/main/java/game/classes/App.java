package game.classes;

/**
 * Die App Klasse startet das Spiel.
 * 
 * @author Nico Bozarslan
 * @version 1.0
 */
public final class App {

    /**
     * Konstruktor der App Klasse.
     */
    private App() {
    }

    /**
     * Die main Methode erstellt einen neuen Dialog, fuehrt die start Methode aus
     * und startet somit das Spiel.
     *
     * @param args: Parameter fuer die main Methode.
     */
    public static void main(String[] args) {
        new Dialogue().start();
    }
}
