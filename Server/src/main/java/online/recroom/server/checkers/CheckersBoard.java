package online.recroom.server.checkers;

/**
 * Created by theje on 4/28/2016.
 */
public class CheckersBoard {

    private static final int ROWS = 8;
    private static final int COLUMNS = 8;

    private Cell[][] board;

    private class Cell{
        public Piece piece;
        public boolean CrowningRow;
    }

    public Cell getCell(int row, int column) {
        return board[row][column];
    }

    public Piece getPiece(int row, int column)
    {
        return getCell(row, column).piece;
    }

    public boolean isValidMove(Piece p, int row, int column)
    {
        return false;
    }

    public void movePiece(Piece p, int row, int column)
    {

    }

    public boolean draw() {
        return false;
    }

    public CheckersPlayer calculateWinner()
    {
        return null;
    }

    private boolean playerHasMoves(CheckersPlayer player)
    {
        return false;
    }
}
