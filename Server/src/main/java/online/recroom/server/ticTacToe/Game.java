package online.recroom.server.ticTacToe;

import javax.websocket.Session;

/**
 * Created by Yehuda Globerman on 4/17/2016.
 */
public class Game {
    public long gameId;

    public Session player1;

    public Session player2;

    public TicTacToeGame ticTacToeGame;
}
