package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yeedle on 5/10/2016 9:20 PM.
 */
public class PieceTest
{
    @Test
    public void testIsInstanceOf() throws Exception
    {
        King king = new King(Player.BLACK);
        boolean kingIsInstanceOfKing = king.isInstanceOf(King.class);
        assertTrue(kingIsInstanceOfKing);
    }

    @Test
    public void testIsNotInstanceOf() throws Exception
    {
        King king = new King(Player.BLACK);
        boolean kingIsNotInstanceOfEmpty = king.isNotInstanceOf(Empty.class);
        assertTrue(kingIsNotInstanceOfEmpty);
    }

    @Test
    public void testIsEmpty() throws Exception
    {
        Board board = new Board();
        Empty empty = (Empty)board.pieceInSquare(Coordinates.byColumnAndRow(2, 2));
        boolean emptyIsEmpty = empty.isEmpty();
        assertTrue(emptyIsEmpty);
    }

}