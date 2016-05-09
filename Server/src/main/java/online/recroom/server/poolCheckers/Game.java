package online.recroom.server.poolCheckers;

import online.recroom.server.poolCheckers.board.Board;
import online.recroom.server.poolCheckers.board.CoOrdinatesOutOfBoundsException;

/**
 * Created by theje on 4/28/2016.
 */
public class Game {
    private Player redPlayer;
    private Player blackPlayer;

    private final Board board;

    public Game(Board board) {
        this.board = board;
    }

    public void move(Movement movement) throws Exception {
        if (!board.isValidMove(movement))
            throw new IllegalMovementException();

        board.movePiece(movement);

    }

    public Player calculateWinner() throws CoOrdinatesOutOfBoundsException {
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

    public void forfeit(Player p) {

    }
}
