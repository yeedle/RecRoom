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
    public void setUpNewBoard()
    {
        board = new Board();
    }

    @Test
    public void testWhitePawnIn_a1_AfterInitBoard() throws Exception
    {
        Piece piece = board.pieceInSquare(0, 1);
        assert(piece instanceof Pawn);
        assertEquals(Color.WHITE, piece.getColor());
    }

    @Test
    public void testBlackPawnIn_a7_afterInitBoard()
    {
        Piece piece = board.pieceInSquare(0, 6);
        assert(piece instanceof Pawn);
        assertEquals(Color.BLACK, piece.getColor());
    }

    @Test
    public void testBlackRookIn_a8_afterInitBoard()
    {
        Piece piece = board.pieceInSquare(0, 7);
        assertTrue(piece instanceof Rook);
        assertEquals(Color.BLACK, piece.getColor());
    }

    @Test
    public void testWhiteKnightIn_b1_afterInitBoard()
    {
        Piece piece = board.pieceInSquare(1, 0);
        assertTrue(piece instanceof Knight);
        assertEquals(Color.WHITE, piece.getColor());
    }
    @Test
    public void testBlackBishopIn_c8_afterInitBoard()
    {
        Piece piece = board.pieceInSquare(2, 7);
        assertTrue(piece instanceof Bishop);
        assertEquals(Color.BLACK, piece.getColor());
    }

    @Test
    public void testQueensIn_dX_afterInitBoard()
    {
        Piece blackQueen = board.pieceInSquare(3, 7);
        Piece whiteQueen = board.pieceInSquare(3, 0);
        assertTrue(blackQueen instanceof Queen);
        assertTrue(whiteQueen instanceof Queen);
        assertEquals(Color.WHITE, whiteQueen.getColor());
        assertEquals(Color.BLACK, blackQueen.getColor());
    }

    @Test
    public void testKingsIn_eX_afterInitBoard()
    {
        Piece blackKing = board.pieceInSquare(4, 7);
        Piece whiteKing = board.pieceInSquare(4, 0);
        assertTrue(blackKing instanceof King);
        assertTrue(whiteKing instanceof King);
        assertEquals(Color.WHITE, whiteKing.getColor());
        assertEquals(Color.BLACK, blackKing.getColor());
    }


}