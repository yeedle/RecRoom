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
}
