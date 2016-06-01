package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Player;

/**
 * Created by Yeedle on 5/31/2016 8:21 PM.
 */
public class Controller
{
    private Game game;
    private Player player;

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
}
