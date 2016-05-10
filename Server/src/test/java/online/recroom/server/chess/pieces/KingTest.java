package online.recroom.server.chess.pieces;

import online.recroom.server.chess.Board;
import online.recroom.server.chess.Coordinates;
import online.recroom.server.chess.Movement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yeedle on 5/10/2016 10:06 AM.
 */
public class KingTest
{

    private Board board;

    @Before
    public void setup() throws Exception
    {
        board = new Board();
    }

    @Test
    public void isLegalMove() throws Exception
    {

    }

    @Test
    public void isNotInCheck() throws Exception
    {

    }

    @Test
    public void diagonalThreatOnKing() throws Exception
    {
        Movement movement = new Movement(Player.WHITE, Coordinates.byColumnAndRow(3, 1), Coordinates.byColumnAndRow(3, 3));
        board.execute(movement);
        movement = new Movement(Player.BLACK, Coordinates.byColumnAndRow(1, 6), Coordinates.byColumnAndRow(1, 5));
        board.execute(movement);
        movement = new Movement(Player.BLACK, Coordinates.byColumnAndRow(2, 7), Coordinates.byColumnAndRow(0, 5));
        board.execute(movement);
        King king = (King)board.pieceInSquare(Coordinates.byColumnAndRow(4, 0));
        Boolean isNotInCheck = king.isInCheck(board);
        assertFalse(isNotInCheck);
    }

}