package online.recroom.server.chess;

import online.recroom.server.chess.pieces.Color;
import online.recroom.server.chess.pieces.Pawn;
import online.recroom.server.chess.pieces.Piece;
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
        board.initBoard();
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
        Piece piece = board.pieceInSquare(0, 6);
        assert(piece instanceof Rook);
        assertEquals(Color.BLACK, piece.getColor());
    }

}