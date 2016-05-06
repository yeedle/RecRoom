package online.recroom.server.chess;

import online.recroom.server.checkers.Color;
import org.junit.Test;

import static online.recroom.server.chess.pieces.Color.BLACK;
import static online.recroom.server.chess.pieces.Color.WHITE;
import static org.junit.Assert.*;

/**
 * Created by Yeedle on 5/2/2016 2:59 PM.
 */
public class GameTest
{

    @Test
    public void testCheckInitialTurn()
    {
        Game g = new Game();
        assertTrue(g.isTurnOf(WHITE));
        assertTrue(g.isNotTurnOf(BLACK));
    }
}