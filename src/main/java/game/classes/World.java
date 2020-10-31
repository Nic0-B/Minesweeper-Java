package game.classes;

import java.util.Random;
import java.lang.String;
import game.exceptions.*;

/**
 * Die World Klasse realisiert das Erstellen von neuen Spielen und beinhaltet
 * Methoden fuer Objekte auf dem Spielfeld.
 * 
 * @author Nico Bozarslan
 * @version 1.0
 */
public class World {

    private final int xSize;
    private final int ySize;
    private Object[][] worldMap; // Matrix die die Minen Objekte beeinhaltet.
    private Object[][] worldLayer; // Matrix die die Anzahl der jeweils Feld nahen Minen beeinhaltet.
    private Object[][] worldFog; // Matrix die den verdeckten Bereich des Spielfeldes beeinhaltet.
    private int numberOfMines;
    private int numberOfFlags;
    private boolean initialized;
    private static final int xMinBoundary = 5;
    private static final int yMinBoundary = 10;
    private static final int xMaxBoundary = 26;
    private static final int yMaxBoundary = 99;

    private static final String WORLD_TOO_LARGE = "Spieltwelt konnte nicht erstellt werden, Sie ist zu gross.";
    private static final String WORLD_TOO_SMALL = "Spieltwelt konnte nicht erstellt werden, Sie ist zu klein.";
    private static final String OUTSIDE_OF_WORLD_BORDER = "Fehler: Zugriff ausserhalb des Spielfeldes.";

    /**
     * Konstruktor der World Klasse. Hier wird ein World Objekt initialisiert und
     * die worldFog Matrix wird dabei vollstaendig mit Fog Objekten gefuellt um das
     * Spielfeld zu Anfang komplett zu verdecken.
     * 
     * @param xSize: Die horizontale Groesse des Spielfeldes.
     * @param ySize: Die vertikale Groesse des Spielfeldes.
     */
    public World(int xSize, int ySize) {

        if (xSize > xMaxBoundary || ySize > yMaxBoundary) {
            throw new InvalidWorldDimensionException(WORLD_TOO_LARGE);
        }

        if (xSize < xMinBoundary || ySize < yMinBoundary) {
            throw new InvalidWorldDimensionException(WORLD_TOO_SMALL);
        }

        this.xSize = xSize;
        this.ySize = ySize;
        this.worldMap = new Object[xSize][ySize];
        this.worldLayer = new Object[xSize][ySize];
        this.worldFog = new Object[xSize][ySize];
        this.initialized = false;
        this.numberOfMines = 0;
        this.numberOfFlags = 0;

        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                this.worldFog[j][i] = new Fog();
            }
        }
    }

    /**
     * Die getXSize Methode gibt die horizontale Groesse des Spielfeldes zurueck.
     * 
     * @return Die horizontale Groesse des Spielfeldes.
     */
    public int getXSize() {

        return this.xSize;
    }

    /**
     * Die getYSize Methode gibt die vertikale Groesse des Spielfeldes zurueck.
     * 
     * @return Die vertikale Groesse des Spielfeldes.
     */
    public int getYSize() {

        return this.ySize;
    }

    /**
     * Die getInitialisation Methode gibt zurueck ob das Spielfeld bereits durch den
     * ersten Zug initialisiert wurde.
     * 
     * @return TRUE wenn das Spielfeld bereits initialisiert wurde.
     */
    public boolean getInitialisation() {

        return this.initialized;
    }

    /**
     * Die uncoverFog Methode deckt einen Bereich des Spielfeldes rekursiv ausgehend
     * von den uebergebenen Koordinaten auf.
     * 
     * @param x: X-Wert der Koordinate.
     * @param y: Y-Wert der Koordinate.
     */
    private void uncoverFog(int x, int y) {

        if (x > xSize - 1 || y > ySize - 1) {
            throw new OutsideOfWorldBorderException(OUTSIDE_OF_WORLD_BORDER);
        }

        int xBorderLeft = 0;
        int xBorderRight = 0;
        int yBorderUp = 0;
        int yBorderDown = 0;

        if (y == 0) {
            yBorderUp = 1;
        }
        if (y == this.ySize - 1) {
            yBorderDown = -1;
        }
        if (x == 0) {
            xBorderLeft = 1;
        }
        if (x == this.xSize - 1) {
            xBorderRight = -1;
        }

        for (int i = 0 + xBorderLeft; i < 3 + xBorderRight; i++) {
            for (int j = 0 + yBorderUp; j < 3 + yBorderDown; j++) {
                if (!(this.worldLayer[x - 1 + i][y - 1 + j] instanceof Integer)
                        && this.worldFog[x - 1 + i][y - 1 + j] instanceof Fog) {
                    this.worldFog[x - 1 + i][y - 1 + j] = this.worldLayer[x - 1 + i][y - 1 + j];
                    uncoverFog(x - 1 + i, y - 1 + j);
                } else if (!(this.worldFog[x - 1 + i][y - 1 + j] instanceof Flag)) {
                    this.worldFog[x - 1 + i][y - 1 + j] = this.worldLayer[x - 1 + i][y - 1 + j];
                }
            }
        }
    }

    /**
     * Die uncoverAll Methode deckt das komplette Spielfeld auf indem sie den Inhalt
     * der worldMap in die worldFog Matrix schreibt.
     */
    public void uncoverAll() {
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                this.worldFog[j][i] = this.worldMap[j][i];
            }
        }
    }

    /**
     * Die sweep Methode raeumt ein gegebenes Feld anhand von Koordinaten auf dem
     * Spielfeld und deckt dabei den Bereich um das Feld auf.
     * 
     * @param x: X-Wert der Koordinate.
     * @param y: Y-Wert der Koordinate.
     * 
     * @return 0 wenn unter dem Feld eine Mine ist, 1 wenn nicht.
     */
    public int sweep(int x, int y) {

        if (x > xSize || y > ySize) {
            throw new OutsideOfWorldBorderException(OUTSIDE_OF_WORLD_BORDER);
        }

        if (this.worldMap[x - 1][y - 1] instanceof Mine) {
            initialized = false;
            return 0;
        } else {
            uncoverFog(x - 1, y - 1);
            return 1;
        }
    }

    /**
     * Die plantFlag Methode platziert auf einem gegebenem Feld anhand von
     * Koordinaten eine Flagge auf dem Spielfeld.
     * 
     * @param x: X-Wert der Koordinate.
     * @param y: Y-Wert der Koordinate.
     * 
     * @return -1 wenn auf dem Feld bereits eine Flagge plaziert wurde, 0 wenn keine
     *         Flagge plaziert wurde, 1 wenn eine Flagge plaziert wurde.
     */
    public int plantFlag(int x, int y) {

        if (x > xSize || y > ySize) {
            throw new OutsideOfWorldBorderException(OUTSIDE_OF_WORLD_BORDER);
        }

        if (this.numberOfFlags > 0 && !(this.worldFog[x - 1][y - 1] instanceof Flag)) {
            this.numberOfFlags--;
            this.worldFog[x - 1][y - 1] = new Flag();
            return 1;

        } else if (this.worldFog[x - 1][y - 1] instanceof Flag) {
            return -1;

        } else {
            return 0;
        }
    }

    /**
     * Die checkForWin Methode ueberprueft ob alle Minen auf dem Spielfeld mittels
     * Flaggen gekennzeichnet wurden und somit das Spiel gewonnen ist.
     * 
     * @return TRUE wenn alle Minen auf dem Spielfeld gekennzeichnet wurden.
     */
    public boolean checkForWin() {
        for (int i = 0; i < this.ySize; i++) {
            for (int j = 0; j < this.xSize; j++) {
                if (worldMap[j][i] instanceof Mine) {
                    if (worldFog[j][i] instanceof Flag) {
                        break;
                    } else {
                        return false;
                    }
                }
            }
        }
        initialized = false;
        return true;
    }

    /**
     * Die initialize Methode initialisiert das Spielfeld indem die Koordinaten des
     * ersten Zuges entgegen genommen werden. Dabei wird zuerst die worldMap Matrix
     * zufaellig mit Minen Objekten gefuellt. Danach wird mittels der verteilten
     * Minen die worldLayer Matrix mit der Anzahl der umliegenden Minen fuer jedes
     * Feld gefuellt.
     * 
     * @param x: X-Wert der Koordinate.
     * @param y: Y-Wert der Koordinate.
     */
    public void initialize(int x, int y) {

        if (x > xSize || y > ySize) {
            throw new OutsideOfWorldBorderException(OUTSIDE_OF_WORLD_BORDER);
        }

        for (int i = 0; i < this.ySize; i++) {

            for (int j = 0; j < this.xSize; j++) {

                if (i == y - 1 && j == x - 1) {

                    this.worldMap[j][i] = ' ';

                } else {

                    Random random = new Random();
                    int randomNumber = random.nextInt(10 - 1 + 1) + 1;

                    if (randomNumber == 10) {
                        this.worldMap[j][i] = new Mine();
                        this.numberOfMines++;
                    } else {
                        this.worldMap[j][i] = ' ';
                    }
                }
            }
        }

        for (int i = 0; i < this.ySize; i++) {

            for (int j = 0; j < this.xSize; j++) {

                int count = 0;
                int xBorderLeft = 0;
                int xBorderRight = 0;
                int yBorderUp = 0;
                int yBorderDown = 0;

                if (i == 0) {
                    yBorderUp = 1;
                }
                if (i == this.ySize - 1) {
                    yBorderDown = -1;
                }
                if (j == 0) {
                    xBorderLeft = 1;
                }
                if (j == this.xSize - 1) {
                    xBorderRight = -1;
                }

                for (int k = 0 + xBorderLeft; k < 3 + xBorderRight; k++) {
                    for (int l = 0 + yBorderUp; l < 3 + yBorderDown; l++) {
                        if (this.worldMap[j - 1 + k][i - 1 + l] instanceof Mine) {
                            count++;
                        }
                    }
                }

                if (count == 0) {
                    this.worldLayer[j][i] = ' ';
                } else {
                    this.worldLayer[j][i] = count;
                }

            }
        }
        this.numberOfFlags = this.numberOfMines + Dialogue.NUMBER_OF_EXTRA_FLAGS;

        uncoverFog(x - 1, y - 1);
        this.initialized = true;
    }

    /**
     * Die toString Methode gibt das World Objekt mit einem gegebenen Layout als
     * String wieder.
     * 
     * @return Das World Objekt als String.
     */
    public String toString() {

        String result = "";

        char alphabet = 'A';
        int number = 1;

        result = result + "|  | |";
        for (int i = 0; i < xSize; i++) {
            result = result + alphabet + "|";
            alphabet++;
        }
        result = result + " |\n";
        result = result + "|  |■|";
        for (int i = 0; i < xSize + 1; i++) {
            result = result + "■|";
        }
        result = result + "\n";

        for (int i = 0; i < this.ySize + 1; i++) {

            result = result + "|";

            for (int j = 0; j < this.xSize; j++) {
                if (j == 0 && i < 9) {
                    result = result + " ";
                }
                if (j == 0 && i == this.ySize) {
                    result = result + "  ";
                }
                if (j == 0 && i != this.ySize) {
                    result = result + number;
                    number++;
                }
                if (j == 0) {
                    result = result + "|■|";
                }
                if (i < this.ySize) {
                    result = result + this.worldFog[j][i] + "|";
                } else {
                    result = result + "■|";
                }
            }

            result = result + "■|";

            if (i == 3 && initialized == true) {
                result = result + "   In diesem level sind " + this.numberOfMines + " Minen vergraben.";
            }
            if (i == 4 && initialized == true) {
                result = result + "   Du hast noch " + this.numberOfFlags + " Flagge/n uebrig.";
            }
            if (i == 6 && initialized == true && this.numberOfFlags < this.numberOfMines + 3) {
                result = result + "   " + (this.numberOfMines + 3 - this.numberOfFlags)
                        + " Flagge/n wurden bereits gesetzt.";
            }

            result = result + "\n";
        }

        result = result.substring(0, result.length() - 1);

        return result;
    }

}
