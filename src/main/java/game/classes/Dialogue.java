package game.classes;

import java.util.Scanner;

/**
 * Die Dialogue Klasse realisiert den Dialog mit einem Benutzer zum Spielen von
 * Minesweeper innerhalb der Konsole.
 * 
 * @author Nico Bozarslan
 * @version 1.0
 */
public class Dialogue {

    // Spielobjekte
    private Scanner input = new Scanner(System.in);
    private World world1;

    // Hauptmenue
    private static final int QUIT = 0;
    private static final int NEWGAME = 1;
    private static final int RULES = 2;
    private static final int SWEEP = 2;
    private static final int PLANTFLAG = 3;

    // Neues Spiel Menue
    private static final int BACK = 0;
    private static final int SMALL = 1;
    private static final int MEDIUM = 2;
    private static final int LARGE = 3;

    // Schwierigkeitsgrad
    private static final int WORLD_SIZE_SMALL = 10;
    private static final int WORLD_SIZE_MEDIUM = 20;
    private static final int WORLD_SIZE_LARGE = 26;
    public static final int NUMBER_OF_EXTRA_FLAGS = 3;

    // Nachrichten
    private static final String INVALID_SELECTION = "Ungueltige Auswahl!";
    private static final String QUIT_MESSAGE = "\nMinesweeper TM. programmiert in Java von Nico Bozarslan im Rahmen des Moduls 'Rapid Game Development' an der HTW Saar.\n";
    private static final String ENTER_X = "Bitte X Koordinate eingeben: ";
    private static final String ENTER_Y = "Bitte Y Koordinate eingeben: ";
    private static final String WIN = "\nHERZLICHEN GLUECKWUNSCH, DU HAST GEWONNEN!";
    private static final String LOSS = "\nGAME OVER!";
    private static final String NOT_INITIALIZED_ERROR = "\nRaeume zuerst ein Feld um das Spiel zu starten!";
    private static final String OUT_OF_FLAGS_ERROR = "\nDu hast keine Flaggen mehr!";
    private static final String FLAG_ALREADY_PLANTED_ERROR = "\nAuf diesem Feld wurde bereits eine Flagge gesetzt!";
    private static final String FAILED_TO_CREATE_WORLD_ERROR = "Spielwelt konnte nicht erstellt werden (Dimension zu klein/gross).";
    private static final String OUTSIDE_OF_WORLD_BORDER_ERROR = "Eingegebene Koordinaten liegen ausserhalb des Spielfeldes.";
    private static final String NOT_A_LETTER_ERROR = "Bitte gebe einen Buchstaben ein.\n";
    private static final String NOT_A_NUMBER_ERROR = "Bitte gebe eine Zahl ein.\n";

    // Titel
    private static final String TITLE = "\n\n"
            + "#   #  #  #   #  #####  #####  #   #  #####  #####  ####   #####  #### \n"
            + "## ##  #  ##  #  #      #      #   #  #      #      #   #  #      #   #\n"
            + "# # #  #  # # #  ###    #####  # # #  ###    ###    ####   ###    #### \n"
            + "#   #  #  #  ##  #          #  ## ##  #      #      #      #      #  # \n"
            + "#   #  #  #   #  #####  #####  #   #  #####  #####  #      #####  #   #\n";

    // Regeln
    private static final String RULES_MESSAGE = "\nSPIELREGELN:\n\n"
            + "Zu Beginn des Spiels waehlst du die Groesse deines Spielfeldes aus.\n"
            + "Du hast die Wahl zwischen klein (" + WORLD_SIZE_SMALL + "*" + WORLD_SIZE_SMALL + "), mittel ("
            + WORLD_SIZE_MEDIUM + "*" + WORLD_SIZE_MEDIUM + ") und gross (" + WORLD_SIZE_LARGE + "*" + WORLD_SIZE_LARGE
            + ").\n" + "Die Schwierigkeit des Spiels steigt je groesser dein Spielfeld ist.\n\n"
            + "Waehle ein Feld innerhalb des Spielfeldes aus dass du aufdecken willst, \n"
            + "unter dem ersten aufgedeckten Feld ist niemals eine Mine versteckt.\n"
            + "Mit jedem weiteren aufgedeckten Feld wird der Bereich bis zur unmittelbaren\n"
            + "Nähe einer Mine sichtbar gemacht.\n"
            + "Wie viele Minen in einem Radius von einem Feld sich um ein Feld verstecken\n"
            + "wird durch die angezeigte Zahl deutlich.\n\n" + "Du bekommst insgesammt " + NUMBER_OF_EXTRA_FLAGS
            + " Flaggen mehr zur Verfuegung gestellt als es Minen\n"
            + "auf dem Spielfeld gibt mit denen du Minen kennzeichnen kannst.\n"
            + "Ziel des Spiels ist es alle Minen mittels der vorhandenen Flaggen\n"
            + "zu kennzeichnen ohne dabei ausversehen eine Mine aufzudecken\n\n"
            + "Das Spiel gilt als verloren sobald du eine Mine aufdeckst oder du keine\n" + "Fahnen mehr hast.";

    /**
     * Die readFunction Methode praesentiert dem Spieler die Optionen des Menues,
     * liest einen Integer-Wert über den Benutzerinput ein und gibt diesen zurueck.
     * 
     * @return Die Eingabe des Spielers als Integer.
     */
    private int readFunction() {

        System.out.println("\n");

        if (world1 == null) {
            System.out.print(NEWGAME + ": Neues Spiel\n");
            System.out.print(RULES + ": Spielregeln\n");
            System.out.print(QUIT + ": Spiel beenden ->\n");

            while (!input.hasNextInt())
                input.next();

            return input.nextInt();

        } else {
            System.out.print(NEWGAME + ": Neues Spiel\n");
            System.out.print(SWEEP + ": Feld raeumen\n");
            System.out.print(PLANTFLAG + ": Flagge setzen\n");
            System.out.print(QUIT + ": Spiel beenden ->\n");

            while (!input.hasNextInt())
                input.next();

            return input.nextInt();
        }
    }

    /**
     * Die newGame Methode verlangt einen Input vom Spieler mit dem sich die Groesse
     * des Spielfeldes bestimmen lassen soll und legt ein neues Spiel an.
     */
    private void newGame() {

        System.out.println("\n\nBitte waehlen sie die Groesse des Spielfeldes:");
        System.out.println(SMALL + ": Klein");
        System.out.println(MEDIUM + ": Mittel");
        System.out.println(LARGE + ": Gross");
        System.out.println(BACK + ": Zurueck");

        while (!input.hasNextInt())
            input.next();

        int selection = input.nextInt();

        switch (selection) {

            case BACK:
                break;
            case SMALL:
                try {
                    world1 = new World(WORLD_SIZE_SMALL, WORLD_SIZE_SMALL);
                    System.out.println(world1);
                } catch (Exception e) {
                    System.out.println(FAILED_TO_CREATE_WORLD_ERROR);
                }
                break;
            case MEDIUM:
                try {
                    world1 = new World(WORLD_SIZE_MEDIUM, WORLD_SIZE_MEDIUM);
                    System.out.println(world1);
                } catch (Exception e) {
                    System.out.println(FAILED_TO_CREATE_WORLD_ERROR);
                }
                break;
            case LARGE:
                try {
                    world1 = new World(WORLD_SIZE_LARGE, WORLD_SIZE_LARGE);
                    System.out.println(world1);
                } catch (Exception e) {
                    System.out.println(FAILED_TO_CREATE_WORLD_ERROR);
                }
                break;
            default:
                System.out.println(INVALID_SELECTION);
        }
    }

    /**
     * Die convertLetter Methode nimmt einen Character entgegen in Form eines
     * Buchstabens und wandelt diesen in die Zahl korrespondierend zu dessen
     * Position im Alphabet um.
     * 
     * @param letter: Der umzuwandelnde Buchstabe.
     * 
     * @return Die Position des Buchstabens im Alphabet als Integer.
     */
    private int convertLetter(char letter) {
        if (letter >= 'A' && letter <= 'Z') {
            letter = Character.toLowerCase(letter);
        }

        return letter - 'a' + 1;
    }

    /**
     * Die enterXCoordinate Methode nimmt eine X-Koordinate in Form eines
     * Buchstabens durch den Spielerinput entgegen und gibt diesen als Integer
     * wieder.
     * 
     * @return Die eingegebene Koordinate als Integer Wert.
     */
    private int enterXCoordinate() {

        char coordinateLetter = '0';
        int xCoordinate = -1;

        while (coordinateLetter == '0') {

            System.out.print(ENTER_X);

            while (!input.hasNext())
                input.next();

            coordinateLetter = input.next().charAt(0);

            if ((coordinateLetter >= 'a' && coordinateLetter <= 'z')
                    || (coordinateLetter >= 'A' && coordinateLetter <= 'Z')) {
                xCoordinate = convertLetter(coordinateLetter);
            } else {
                coordinateLetter = '0';
                System.out.print(NOT_A_LETTER_ERROR);
            }
        }

        return xCoordinate;
    }

    /**
     * Die enterYCoordinate Methode nimmt eine Y-Koordinate in Form einer Zahl durch
     * den Spielerinput entgegen und gibt diesen als Integer wieder.
     * 
     * @return Die eingegebene Koordinate als Integer Wert.
     */
    private int enterYCoordinate() {

        int yCoordinate = -1;

        while (yCoordinate == -1) {

            System.out.print(ENTER_Y);

            while (!input.hasNextInt()) {

                input.next();
                System.out.print(NOT_A_NUMBER_ERROR);
                System.out.print(ENTER_Y);
            }
            yCoordinate = input.nextInt();
        }

        return yCoordinate;
    }

    /**
     * Die executeFunction Methode nimmt eine Funktion in Form eines Integers
     * entgegen und fuehrt diese ueber einen Methodenaufruf aus.
     * 
     * @param function: Die auszufuehrende Funktion als Integer.
     */
    private void executeFunction(int function) {

        if (world1 == null) {

            switch (function) {

                case QUIT:
                    System.out.println(QUIT_MESSAGE);
                    break;

                case NEWGAME:
                    newGame();
                    break;

                case RULES:
                    System.out.println(RULES_MESSAGE);
                    break;

            }

        } else {

            switch (function) {

                case QUIT:
                    System.out.println(QUIT_MESSAGE);
                    break;

                case NEWGAME:
                    newGame();
                    break;

                case SWEEP:

                    int coordinateXsw = enterXCoordinate();
                    int coordinateYsw = enterYCoordinate();

                    System.out.print("\n");

                    try {
                        if (world1.getInitialisation() == false) {
                            world1.initialize(coordinateXsw, coordinateYsw);
                            System.out.println(world1);
                        } else {
                            if (world1.sweep(coordinateXsw, coordinateYsw) == 1) {
                                System.out.println(world1);
                            } else {
                                world1.uncoverAll();
                                System.out.println(world1);
                                System.out.println(LOSS);
                                world1 = null;
                            }
                        }

                    } catch (Exception e) {
                        System.out.println(OUTSIDE_OF_WORLD_BORDER_ERROR);
                    }
                    break;

                case PLANTFLAG:

                    if (world1.getInitialisation() == false) {
                        System.out.println(world1);
                        System.out.println(NOT_INITIALIZED_ERROR);

                    } else {

                        int coordinateXpf = enterXCoordinate();
                        int coordinateYpf = enterYCoordinate();

                        System.out.print("\n");

                        try {

                            if (world1.plantFlag(coordinateXpf, coordinateYpf) == 1) {
                                System.out.println(world1);

                            } else if (world1.plantFlag(coordinateXpf, coordinateYpf) == -1) {
                                System.out.println(world1);
                                System.out.println(FLAG_ALREADY_PLANTED_ERROR);
                                break;

                            } else {
                                System.out.println(OUT_OF_FLAGS_ERROR);
                                System.out.println(LOSS);
                                world1 = null;
                                break;
                            }

                            if (world1.checkForWin() == true) {
                                System.out.println(WIN);
                                world1 = null;
                            }

                        } catch (Exception e) {
                            System.out.println(OUTSIDE_OF_WORLD_BORDER_ERROR);
                        }
                    }
                    break;

                default:
                    System.out.println(INVALID_SELECTION);
            }
        }

    }

    /**
     * Die start Methode startet den Dialog mit dem Spieler und fuehrt diesen so
     * lange aus bis der Spieler diesen beendet.
     */
    public void start() {

        System.out.println(TITLE);

        int function = -42;

        while (function != QUIT) {
            try {
                function = readFunction();
                executeFunction(function);
            } catch (AssertionError e) {
                System.out.println(e);
            } catch (java.util.InputMismatchException e) {
                System.out.println(e);
                input.next();
            } catch (Exception e) {
                System.out.println("Irgendeine Ausnahme gefangen: " + e);
                e.printStackTrace(System.out);
            }
        }
    }
}
