package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Player;

import javax.websocket.Session;

/**
 * Created by Yeedle on 5/31/2016 8:21 PM.
 */
public class Controller
{
    private Game game;
    private Player player;
    private String username;
    private ChessServer server;
    public Controller(ChessServer chessServer)
    {
        this.server = server;
    }

    public <T> void updateGame(T message) throws IllegalCoordinateException, InvalidMoveException, IllegalMoveException
    {
        if (message instanceof online.recroom.messages.chess.messages.Movement)
        {
            Coordinates origin = Coordinates.fromMessage(((online.recroom.messages.chess.messages.Movement) message).origin);
            Coordinates destination = Coordinates.fromMessage(((online.recroom.messages.chess.messages.Movement) message).destination);
            Movement movement = new Movement(player, origin, destination);
            game.move(movement);
        }

    }

    public void connectToGame(Session session, String username)
    {
        this.username = username;
       /* if (isThereActiveAnGameWithRoom()) {
            joinActiveGame(session);
        } else if (!PENDING_GAMES.isEmpty()) {
            joinPendingGame(session);
        } else {
            startNewGame(session);
         */
    }

}
