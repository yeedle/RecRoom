package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Color;

import static online.recroom.server.chess.pieces.Color.WHITE;

/**
 * Created by Yeedle on 5/2/2016 2:57 PM.
 */
public class Game
{
    Board board;
    Color turn;

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

    public boolean isTurnOf(Color color)
    {
        return turn.equals(color);
    }

    public boolean isNotTurnOf(Color color)
    {
        return !isTurnOf(color);
    }



}
