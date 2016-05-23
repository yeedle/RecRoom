package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.IllegalMoveException;
import online.recroom.server.chess.Movement;
import online.recroom.server.poolCheckers.IllegalMovementException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yeedle on 5/23/2016 11:55 AM.
 */
public class KnightTest
{
    Board board;
    @Before
    public void setUp() throws Exception
    {
        board = new Board();
    }

    @Test
    public void isLegalMoveBasicMoveLegalityChecking() throws Exception
    {
        Knight k = (Knight) board.pieceInSquare(Coordinates.byColumnAndRow(6, 0));
        Movement movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(6, 0), Coordinates.byColumnAndRow(5, 2));
        boolean test1 = k.isLegalMove(movement, board);
        board.execute(movement);

        movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(5, 2), Coordinates.byColumnAndRow(3, 3));
        boolean test2 = k.isLegalMove(movement, board);
        board.execute(movement);

        movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(3,3), Coordinates.byColumnAndRow(4, 5));
        boolean test3 = k.isLegalMove(movement, board);
        board.execute(movement);

        movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(4,5), Coordinates.byColumnAndRow(3, 7));
        boolean test4 = k.isLegalMove(movement, board);
        board.execute(movement);

        assertTrue(test1);
        assertTrue(test2);
        assertTrue(test3);
        assertTrue(test4);
    }

    @Test (expected = IllegalMoveException.class)
    public void aBadKnightMove() throws Exception
    {
        Knight k = (Knight) board.pieceInSquare(Coordinates.byColumnAndRow(6, 0));
        Movement movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(6, 0), Coordinates.byColumnAndRow(5, 2));

        board.execute(movement);

        movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(5, 2), Coordinates.byColumnAndRow(3, 4));
        boolean test1 = k.isLegalMove(movement, board);
        board.execute(movement);

        assertFalse(test1);

    }

    @Test (expected = IllegalMoveException.class)
    public void movingAKnightToItsOwnColorWillBeLegalButWIllThrowError() throws Exception
    {
        Knight k = (Knight) board.pieceInSquare(Coordinates.byColumnAndRow(6, 0));
        Movement movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(6, 0), Coordinates.byColumnAndRow(4, 1));
        boolean test1 = k.isLegalMove(movement, board);
        board.execute(movement);


        assertTrue(test1);

    }

}