package online.recroom.server.poolCheckers;

import online.recroom.server.poolCheckers.board.Board;
import online.recroom.server.poolCheckers.board.CoOrdinatesOutOfBoundsException;

/**
 * Created by theje on 4/28/2016.
 */
public class Game {
    private static long idGenerator = Long.MIN_VALUE;
    public final long id;
    private CheckersPlayer redPlayer;
    private CheckersPlayer blackPlayer;
    private CheckersPlayer currentTurn;
    private final Board board;

    public Game(Board board) {
        this.board = board;
        id = idGenerator;
        idGenerator++;
        currentTurn = redPlayer;
    }

    public CheckersPlayer getRedPlayer() {
        return redPlayer;
    }

    public void setRedPlayer(CheckersPlayer redPlayer) {
        this.redPlayer = redPlayer;
    }

    public CheckersPlayer getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(CheckersPlayer blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public CheckersPlayer getCurrentTurn() {
        return currentTurn;
    }

    private void setCurrentTurn(CheckersPlayer currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void changeCurrentTurn() {
        setCurrentTurn((currentTurn == redPlayer) ? blackPlayer : redPlayer);
    }

    public Board getBoard() {
        return board;
    }

    public void move(Movement movement) throws Exception {
        if (!board.isValidMove(movement))
            throw new IllegalMovementException();
        if (movement.madeBy != getCurrentTurn().color)
            throw new NotYourTurnException();

        board.movePiece(movement);
        draw();
        calculateWinner();
        changeCurrentTurn();
    }

    public CheckersPlayer calculateWinner() throws CoOrdinatesOutOfBoundsException {
        if (!draw()) {
            if (board.blackHasNoPiecesLeft() || board.blackHasNoMoves()) {
                return redPlayer;
            } else if (board.redHasNoPiecesLeft() || board.redHasNoMoves()) {
                return blackPlayer;
            }
        }
        return null;
    }

    public boolean draw() throws CoOrdinatesOutOfBoundsException {
        return board.redHasNoMoves() && board.blackHasNoMoves();
    }

    public void forfeit(CheckersPlayer p) {

    }
}
