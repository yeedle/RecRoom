package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.IllegalMoveException;
import online.recroom.server.chess.Movement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yeedle on 5/23/2016 10:39 AM.
 */
public class BishopTest
{
    Board board;
    @Before
    public void setUp() throws Exception
    {
        board = new Board();
    }

    @Test
    public void basicMovesLegality() throws Exception
    {
        Bishop b = (Bishop) board.pieceInSquare(Coordinates.byColumnAndRow(5, 0));

        Movement movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(4, 1), Coordinates.byColumnAndRow(4, 2));
        board.execute(movement);

        movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(5, 0), Coordinates.byColumnAndRow(2, 3));
        boolean test2 = b.isLegalMove(movement, board);
        board.execute(movement);

       movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(2, 3), Coordinates.byColumnAndRow(5, 6));
        boolean test3 = b.isLegalMove(movement, board);
        board.execute(movement);


        assertTrue(test2);
        assertTrue(test3);
    }

    @Test (expected = IllegalMoveException.class)
    public void testingJumpingOverFails() throws Exception
    {
        Bishop b = (Bishop) board.pieceInSquare(Coordinates.byColumnAndRow(5, 0));

        //move pawn out of the way
        Movement movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(4, 1), Coordinates.byColumnAndRow(4, 2));
        board.execute(movement);

        movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(5, 0), Coordinates.byColumnAndRow(2, 3));
        boolean test2 = b.isLegalMove(movement, board);
        board.execute(movement);

       movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(2, 3), Coordinates.byColumnAndRow(6, 7));
        boolean test3 = b.isLegalMove(movement, board);
        board.execute(movement);


        assertFalse(test2);
        assertTrue(test3);
    }

}