package g52103.humbug.controller;

import g52103.humbug.model.Action;
import static g52103.humbug.model.Action.HELP;
import static g52103.humbug.model.Action.MOVE;
import static g52103.humbug.model.Action.QUIT;
import static g52103.humbug.model.Action.RESTART;
import g52103.humbug.model.Direction;
import g52103.humbug.model.LevelStatus;
import g52103.humbug.model.Model;
import g52103.humbug.model.Position;
import g52103.humbug.view.text.InterfaceView;

/**
 * The controller dispply the board and the animal and updates the view.
 *
 * @author El Amrani Mounir (52103) 52103@etu.he2b.be
 */
public class Controller {

    private final Model game;
    private final InterfaceView view;

    /**
     * Constructor of a controller.
     *
     * @param game the game
     * @param view the view
     */
    public Controller(Model game, InterfaceView view) {
        this.game = game;
        this.view = view;
    }

    /**
     * Start the game with the given level and initialise the board and the
     * animals. Ask to the user a position and direction to move the animals.
     *
     * @param nLevel the given level
     */
    public void startGame(int nLevel) {
        view.displayRules();
        view.displayMessage("Veuillez chosir le niveau a chargé : ");
        nLevel = view.robustIntegerRead("Entrez un entier correcte.");
        game.startLevel(nLevel);

        while ((nLevel >= 1 && nLevel <= 48) || nLevel == 100) {
            view.displayNumberLevel(game.getCurrentLevel());

            while (game.getLevelStatus() == LevelStatus.IN_PROGRESS) {
                view.displayBoard(game.getBoard(), game.getAnimals());
                view.displayRemainingMoves(game.getRemainingMoves());
                Action actionChosen = view.askAction();

                switch (actionChosen) {
                    case MOVE:
                        Position pos = view.askPosition();
                        Direction direction = view.askDirection();
                        try {
                            game.move(pos, direction);
                        } catch (IllegalStateException | IllegalArgumentException e) {
                            view.displayError(e.getMessage());
                        }
                        break;
                    case RESTART:
                        game.startLevel(nLevel);
                        break;
                    case UNDO:
                        game.undo();
                        break;
                    case HELP:
                        view.displayRules();
                        break;
                    case QUIT:
                        game.setLevelStatus(LevelStatus.NOT_STARTED);
                        nLevel = 0;
                        break;
                    default:
                        break;
                }

                if (game.getLevelStatus() == LevelStatus.WIN) {
                    nLevel++;
                    if (nLevel == 49) {
                        nLevel = 100;
                    }
                    checkTheLevel(nLevel);
                } else if (game.getLevelStatus() == LevelStatus.FAIL) {
                    checkRemaingingsMoves(game.getRemainingMoves());
                    Action actionChoisir = view.askAction();
                    game.startLevel(nLevel);
                }
            }
        }

        if (game.getLevelStatus() == LevelStatus.WIN && nLevel > 100) {
            view.displayEndGame();
        }
    }

    /**
     * If the level is 49 pass to level 100.
     *
     * @param nLevel number of level
     * @return the next level
     */
    private void checkTheLevel(int nLevel) {
        if (nLevel > 100) {
            view.displayMessage("Le jeu est terminé. Vous avez gagné le dernier niveau.");

        } else {
            view.displayMessage("Bravo. Passage au prochain niveau ...");
            view.displayNumberLevel(nLevel);
            game.startLevel(nLevel);
        }
    }

    /**
     * If the remainings moves equals 0 display message.
     *
     * @param move integer
     */
    private void checkRemaingingsMoves(int move) {
        if (move == 0) {
            view.displayError("Plus de mouvement disponible.");
        } else {
            view.displayError("L'animal est tombé. La partie "
                    + "recommence.\n");
            view.displayNumberLevel(game.getCurrentLevel());
        }
    }
}
