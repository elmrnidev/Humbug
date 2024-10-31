package elm.humbug.view.text;

import elm.humbug.model.*;
import elm.humbug.util.TerminalColor;
import java.util.Scanner;

/**
 * The view of the games. The view allows to display the game board. To request
 * a position and direction from the player using various methods.
 *
 * @author elm (52103) 52103@etu.he2b.be
 */
public class View implements InterfaceView {

    /**
     * Display the board of the game. For all square show the type and if the
     * position is null print a space.
     *
     * @param board the given board.
     */
    @Override
    public void displayBoard(Board board, Animal... animals) {

        String[][] theBoard = mergeAnimalBoard(board, animals);

        String line = "";
        displayLineSeparator(board.getNbColumn());

        for (int row = 0; row < board.getNbRow(); row++) {
            displayLineWithNorthWall(row, board);
            for (int col = 0; col < board.getNbColumn(); col++) {
                Position pos = new Position(row, col);

                if (board.isInside(pos)) {

                    if (board.getSquare(pos).hasWall(Direction.WEST) 
                            && board.getSquare(pos).hasWall(Direction.EAST)) {
                        line += "|" + TerminalColor.BG_GREEN + "·" 
                                + theBoard[row][col] 
                                + "·" 
                                + TerminalColor.DEFAULT;
                    } else if (board.getSquare(pos).hasWall(Direction.EAST)) {
                        line += "|" + TerminalColor.BG_GREEN + " " 
                                + theBoard[row][col] 
                                + "·" 
                                + TerminalColor.DEFAULT;
                    } else if (board.getSquare(pos).hasWall(Direction.WEST)) {
                        line += "|" + TerminalColor.BG_GREEN + "·" 
                                + theBoard[row][col] + " " 
                                + TerminalColor.DEFAULT;
                    } else {
                        line += "|" + TerminalColor.BG_GREEN + " " 
                                + theBoard[row][col] + " " 
                                + TerminalColor.DEFAULT;
                    }
                } else {
                    line += "|   ";
                }
            }

            line += "|";
            System.out.println(line);
            line = "";
            displayLineWithSouthWall(row, board);
            displayLineSeparator(board.getNbColumn());
        }
    }

    /**
     * Diplay the north line for all square and set the wall if is it.
     * 
     * @param row a integer
     * @param board the given board.
     */
    public void displayLineWithNorthWall(int row, Board board) {
        String line = "";
        for (int col = 0; col < board.getNbColumn(); col++) {
            Position pos = new Position(row, col);
            line += "| ";
            if (board.isInside(pos) && board.getSquare(pos)
                    .hasWall(Direction.NORTH)) {
                line += ". ";
            } else {
                line += "  ";
            }

        }
        line += "|";
        System.out.println(line);
    }

    /**
     * Diplay the south line for all square and set the wall if is it.
     * 
     * @param row a integer
     * @param board the given board.
     */
    public void displayLineWithSouthWall(int row, Board board) {
        String line = "";
        for (int col = 0; col < board.getNbColumn(); col++) {
            Position pos = new Position(row, col);
            line += "| ";
            if (board.isInside(pos) && board.getSquare(pos)
                    .hasWall(Direction.SOUTH)) {
                line += ". ";
            } else {
                line += "  ";
            }

        }
        line += "|";
        System.out.println(line);
    }
    
    /**
     * Display a little message for the end of the game
     */
    @Override
    public void displayEndGame(){
        System.out.println("----------------------------------");
        System.out.println("        Le jeu est terminé        ");
        System.out.println("----------------------------------");
    }
    
    /**
     * Display an error message received.
     *
     * @param message the given string
     */
    @Override
    public void displayError(String message) {
        System.out.println("Erreur : " + message);
    }

    
    /**
     * Display a line separator before the sqaure.
     *
     * @param length a integer
     */
    public void displayLineSeparator(int length) {
        if (length == 1) {
            System.out.print("-----");
        } else {
            for (int i = 0; i < length * 4 + 1; i++) {
                System.out.print("-");
            }
        }
        System.out.println("");

    }
    
    /**
     * Display the remaining moves.
     * 
     * @param nbRemainingMoves a integer
     */
    @Override
    public void displayRemainingMoves(int nbRemainingMoves) {
        System.out.println("Mouvement restant : " + nbRemainingMoves);
    }
    
    /**
     * Display the number of the current Level.
     * 
     * @param nLevel a integer
     */
    @Override
    public void displayNumberLevel(int nLevel) {
        System.out.println("----------------------------------------------");
        System.out.println("           Voici le niveau : " + nLevel);
        System.out.println("----------------------------------------------");
    }
    
    /**
     * Display the rules of the games.
     * 
     */
    @Override
    public void displayRules() {
        System.out.println("----------------------------------------------");
        System.out.println(TerminalColor.BG_RED_FG_WHITE
                +"         Binvenue sur le jeux Humbug          "
                +TerminalColor.DEFAULT);
        System.out.println("----------------------------------------------");
        System.out.println("**** Petit rappel des lettres des animaux ****");
        System.out.println("                                              ");
        System.out.println("A = L'araignée || E = Escargot || B = Bourdon ");
        System.out.println("S = Sauterlle  || P = Papillon || C = Cocinelle");
        System.out.println("                                              ");
        System.out.println("----------------------------------------------"); 
        System.out.println(TerminalColor.BG_RED_FG_WHITE 
                +"                                               "
                +TerminalColor.DEFAULT);
        System.out.println("----------------------------------------------");
    }

    /**
     * Display the given message.
     * 
     * @param message a string
     */
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    /**
     * Ask the position desired by the user.
     *
     * @return the position
     */
    @Override
    public Position askPosition() {
        System.out.println("Entrez une positon ou se trouve un animal.");
        System.out.print("Position en ligne : ");
        int row = robustIntegerRead("Veuillez entrer une ligne disponible.\n "
                +"Position en ligne : ");
        System.out.print("Position en colonne : ");
        int col = robustIntegerRead("Veuillez entrer une colonne disponible.\n "
                +" Position en colonne : ");
        Position position = new Position(row, col);
        System.out.println(position.toString());
        return position;
    }
    /**
     * Ask the action desired by the user.
     * 
     * @return the chosen action
     */
    @Override
    public Action askAction(){
        Scanner keyBoard = new Scanner(System.in);
        System.out.println("Veuillez entrer une action : Move = M,Restart = R, "
                + "Help = H, Quit = Q, Undo= U");

        char actionChosen = keyBoard.next().toLowerCase().charAt(0);

        while (actionChosen != 'm' && actionChosen != 'r' 
                && actionChosen != 'h' && actionChosen != 'q'&& actionChosen != 'u') {

            displayError(" Veuillez entrez la premiere lettre de l'action " 
                    + "ou le nom de l'action "
                    + actionChosen);
            actionChosen = keyBoard.next().toLowerCase().charAt(0);
        }

        switch (actionChosen) {
            case 'm':
                return Action.MOVE;
            case 'r':
                return Action.RESTART;
            case 'h':
                return Action.HELP;
            case 'q':
                return Action.QUIT;
            case 'u':
                return Action.UNDO;
            default:
                return null;
        }
    }
    
    /**
     * Robust read integer if is not a integer show the given message.
     *
     * @param message String message
     * @return level
     */
    @Override
    public int robustIntegerRead(String message){
        Scanner keyBoard = new Scanner(System.in);
            
        while(!keyBoard.hasNextInt()) {
            System.out.print(message);
            keyBoard.next();
            
        }
        return keyBoard.nextInt();
    }

    /**
     * Asks the direction desired by the user.
     *
     * @return the Direction
     */
    @Override
    public Direction askDirection() {
        Scanner keyBoard = new Scanner(System.in);
        System.out.println("Veuillez entrer une direction : N,S,E,W");

        char directionChosen = keyBoard.next().toLowerCase().charAt(0);

        while (directionChosen != 'n' && directionChosen != 's' 
                && directionChosen != 'e' && directionChosen != 'w') {

            displayError(" Veuillez entrez la premiere lettre de la direction " 
                    + "ou le nom de la direction"
                    + directionChosen);
            directionChosen = keyBoard.next().toLowerCase().charAt(0);
        }

        switch (directionChosen) {
            case 'n':
                return Direction.NORTH;
            case 's':
                return Direction.SOUTH;
            case 'w':
                return Direction.WEST;
            case 'e':
                return Direction.EAST;
            default:
                return null;
        }
    }

    /**
     * Browse the given animal and add on the board all animal.
     *
     * @param board the given board
     * @param animals the given array with animal
     * @return the board with animals
     */
    public String[][] mergeAnimalBoard(Board board, Animal... animals) {
        String[][] newBoard = new String[board.getNbRow()][board.getNbColumn()];

        for (int row = 0; row < board.getNbRow(); row++) {
            for (int col = 0; col < board.getNbColumn(); col++) {
                Position pos = new Position(row, col);
                
                if (board.isInside(pos)) {
                    if (board.getSquareType(pos) == SquareType.STAR) {
                        newBoard[row][col] = "*";
                    } else if (board.getSquareType(pos) == SquareType.GRASS) {
                        newBoard[row][col] = " ";
                    }
                }
            }
        }

        for (Animal animal : animals) {
            if (!animal.isOnStar() && animal.getPositionOnBoard() != null) {

                int row = animal.getPositionOnBoard().getRow();
                int col = animal.getPositionOnBoard().getColumn();

                String animalSymbol = animal.toString();

                newBoard[row][col] = animalSymbol;
            }
        }

        return newBoard;
    }

}
