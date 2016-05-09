package online.recroom.server.chess;

import org.junit.Test;

import static online.recroom.server.chess.pieces.Player.BLACK;
import static online.recroom.server.chess.pieces.Player.WHITE;
import static org.junit.Assert.assertTrue;

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