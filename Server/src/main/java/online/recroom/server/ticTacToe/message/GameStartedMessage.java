package online.recroom.server.ticTacToe.message;

import online.recroom.server.ticTacToe.TicTacToeGame;

/**
 * Created by Yehuda Globerman on 4/17/2016.
 */
public class GameStartedMessage extends Message {
    private final TicTacToeGame game;

    public GameStartedMessage(TicTacToeGame game) {
        super("gameStarted");
        this.game = game;
    }

    public TicTacToeGame getGame() {
        return game;
    }
}
