package online.recroom.client.ticTacToe.message;

import online.recroom.client.ticTacToe.Move;

/**
 * Created by Yehuda Globerman on 4/17/2016.
 */
public class OpponentMadeMoveMessage extends Message {
    private final Move move;

    public OpponentMadeMoveMessage(Move move) {
        super("opponentMadeMove");
        this.move = move;
    }

    public Move getMove() {
        return move;
    }
}
