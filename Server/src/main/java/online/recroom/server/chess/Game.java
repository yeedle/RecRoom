package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Player;

import static online.recroom.server.chess.pieces.Player.WHITE;

/**
 * Created by Yeedle on 5/2/2016 2:57 PM.
 */
public class Game
{
    Board board;
    Player turn;

    public Game()
    {
        board = new Board();
        turn = WHITE;
    }

    public void move(Movement movement) throws IllegalMoveException, InvalidMoveException
    {
        if(isNotTurnOf(movement.madeBy))
            throw new IllegalMoveException("Not your turn");

        board.execute(movement);
    }

    public boolean isTurnOf(Player player)
    {
        return turn.equals(player);
    }

    public boolean isNotTurnOf(Player player)
    {
        return !isTurnOf(player);
    }



}
