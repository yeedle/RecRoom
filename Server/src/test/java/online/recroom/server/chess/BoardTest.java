package online.recroom.server.chess;

import online.recroom.server.chess.pieces.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yeedle on 5/2/2016 12:49 PM.
 */
public class BoardTest
{
    Board board;

    @Before
    public void setUpNewBoard() throws Exception
    {
        board = new Board();
    }

    @Test
    public void testWhitePawnIn_a1_AfterInitBoard() throws Exception
    {
        Piece piece = board.pieceInSquare(Coordinates.byColumnAndRow(0, 1));
        assert(piece instanceof Pawn);
        assertEquals(Player.WHITE, piece.player());
    }

    @Test
    public void testBlackPawnIn_a7_afterInitBoard() throws Exception
    {
        Piece piece = board.pieceInSquare(Coordinates.byColumnAndRow(0, 6));
        assert(piece instanceof Pawn);
        assertEquals(Player.BLACK, piece.player());
    }

    @Test
    public void testBlackRookIn_a8_afterInitBoard() throws Exception
    {
        Piece piece = board.pieceInSquare(Coordinates.byColumnAndRow(0, 7));
        assertTrue(piece instanceof Rook);
        assertEquals(Player.BLACK, piece.player());
    }

    @Test
    public void testWhiteKnightIn_b1_afterInitBoard() throws Exception
    {
        Piece piece = board.pieceInSquare(Coordinates.byColumnAndRow(1,0));
        assertTrue(piece instanceof Knight);
        assertEquals(Player.WHITE, piece.player());
    }
    @Test
    public void testBlackBishopIn_c8_afterInitBoard() throws Exception
    {
        Piece piece = board.pieceInSquare(Coordinates.byColumnAndRow(2, 7));
        assertTrue(piece instanceof Bishop);
        assertEquals(Player.BLACK, piece.player());
    }

    @Test
    public void testQueensIn_dX_afterInitBoard() throws Exception
    {
        Piece blackQueen = board.pieceInSquare(Coordinates.byColumnAndRow(3, 7));
        Piece whiteQueen = board.pieceInSquare(Coordinates.byColumnAndRow(3, 0));
        assertTrue(blackQueen instanceof Queen);
        assertTrue(whiteQueen instanceof Queen);
        assertEquals(Player.WHITE, whiteQueen.player());
        assertEquals(Player.BLACK, blackQueen.player());
    }

    @Test
    public void testKingsIn_eX_afterInitBoard() throws Exception
    {
        Piece blackKing = board.pieceInSquare(Coordinates.byColumnAndRow(4, 7));
        Piece whiteKing = board.pieceInSquare(Coordinates.byColumnAndRow(4, 0));
        assertTrue(blackKing instanceof King);
        assertTrue(whiteKing instanceof King);
        assertEquals(Player.WHITE, whiteKing.player());
        assertEquals(Player.BLACK, blackKing.player());
    }

    @Test
    public void testExecuteAMove()
    {

    }


}