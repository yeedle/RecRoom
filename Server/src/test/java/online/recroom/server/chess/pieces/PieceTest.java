package online.recroom.server.chess.pieces;

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

}