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
    @Before
    public void setup() throws Exception
    {
        Board board = new Board();

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
        Movement movement = new Movement(Player.BLACK, Coordinates.byColumnAndRow(3, 1), Coordinates(3, 3), )
    }

}