package game;

import game.classes.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Die WorldTest Testklasse testet die Methoden der World Klasse auf Fehler.
 * 
 * @author Nico Bozarslan
 * @version 1.0
 */
public class WorldTest {

    private World worldSmall;
    private World worldMedium;
    private World worldLarge;

    /**
     * Diese Methode setzt das Testgeruest vor jedem durchzufuehrenden Test.
     */
    @BeforeEach
    public void setUp() {

        worldSmall = new World(10, 10);
        worldMedium = new World(20, 20);
        worldLarge = new World(26, 26);
    }

    /**
     * Diese Methode gibt das Testgeruest wieder frei.
     */
    @AfterEach
    public void tearDown() {
    }

    /**
     * Die testGetXSize Methode prueft die getXSize Methode der World Klasse ob
     * diese die Korrekte X-Groesse wiedergibt.
     */
    @Test
    public void testGetXSize() {

        assertEquals(10, worldSmall.getXSize());
        assertEquals(20, worldMedium.getXSize());
        assertEquals(26, worldLarge.getXSize());
    }

    /**
     * Die testGetYSize Methode prueft die getYSize Methode der World Klasse ob
     * diese die Korrekte Y-Groesse wiedergibt.
     */
    @Test
    public void testGetYSize() {

        assertEquals(10, worldSmall.getYSize());
        assertEquals(20, worldMedium.getYSize());
        assertEquals(26, worldLarge.getYSize());
    }

    /**
     * Die getInitialisationTest Methode prueft die getInitialisation Methode der
     * World Klasse ob diese den Korrekten Zustand der Initialisierung wiedergibt.
     */
    @Test
    public void getInitialisationTest() {

        assertEquals(false, worldSmall.getInitialisation());
        worldSmall.initialize(1, 1);
        assertEquals(true, worldSmall.getInitialisation());

        assertEquals(false, worldMedium.getInitialisation());
        worldMedium.initialize(1, 1);
        assertEquals(true, worldMedium.getInitialisation());

        assertEquals(false, worldLarge.getInitialisation());
        worldLarge.initialize(1, 1);
        assertEquals(true, worldLarge.getInitialisation());
    }

    /**
     * Die toStringTest Methode prueft die toString Methode der World Klasse ob
     * diese ein World Objekt richtig in der Konsole darstellt.
     */
    @Test
    public void toStringTest() {

        String expected = "|  | |A|B|C|D|E|F|G|H|I|J| |\n|  |■|■|■|■|■|■|■|■|■|■|■|■|\n| 1|■|☒|☒|☒|☒|☒|☒|☒|☒|☒|☒|■|\n| 2|■|☒|☒|☒|☒|☒|☒|☒|☒|☒|☒|■|\n| 3|■|☒|☒|☒|☒|☒|☒|☒|☒|☒|☒|■|\n| 4|■|☒|☒|☒|☒|☒|☒|☒|☒|☒|☒|■|\n| 5|■|☒|☒|☒|☒|☒|☒|☒|☒|☒|☒|■|\n| 6|■|☒|☒|☒|☒|☒|☒|☒|☒|☒|☒|■|\n| 7|■|☒|☒|☒|☒|☒|☒|☒|☒|☒|☒|■|\n| 8|■|☒|☒|☒|☒|☒|☒|☒|☒|☒|☒|■|\n| 9|■|☒|☒|☒|☒|☒|☒|☒|☒|☒|☒|■|\n|10|■|☒|☒|☒|☒|☒|☒|☒|☒|☒|☒|■|\n|  |■|■|■|■|■|■|■|■|■|■|■|■|";
        assertEquals(expected, worldSmall.toString());
    }

    /**
     * Die sweepTest Methode prueft die sweep Methode der World Klasse ob diese
     * korrekt ausgefuehrt wird.
     */
    @Test
    public void sweepTest() {

        worldSmall.initialize(5, 5);
        assertEquals(1, worldSmall.sweep(5, 5));

        worldMedium.initialize(5, 5);
        assertEquals(1, worldMedium.sweep(5, 5));

        worldLarge.initialize(5, 5);
        assertEquals(1, worldLarge.sweep(5, 5));
    }

    /**
     * Die plantFlagTest Methode prueft die plantFlag Methode der World Klasse ob
     * diese korrekt ausgefuehrt wird.
     */
    @Test
    public void plantFlagTest() {

        worldSmall.initialize(5, 5);
        assertEquals(1, worldSmall.plantFlag(5, 5));

        worldMedium.initialize(5, 5);
        assertEquals(1, worldMedium.plantFlag(5, 5));

        worldLarge.initialize(5, 5);
        assertEquals(1, worldLarge.plantFlag(5, 5));
    }

    /**
     * Die checkForWinTest Methode prueft die checkForWin Methode der World Klasse
     * ob diese den Zustand des Spielfeldes korrekt erkennen kann.
     */
    @Test
    public void checkForWinTest() {

        worldSmall.initialize(5, 5);
        assertEquals(false, worldSmall.checkForWin());

        worldMedium.initialize(5, 5);
        assertEquals(false, worldMedium.checkForWin());

        worldLarge.initialize(5, 5);
        assertEquals(false, worldLarge.checkForWin());
    }
}
