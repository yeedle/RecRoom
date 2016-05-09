package online.recroom.server.poolCheckers.board;

import online.recroom.server.poolCheckers.IllegalMovementException;
import online.recroom.server.poolCheckers.Movement;
import online.recroom.server.poolCheckers.pieces.BlackPiece;
import online.recroom.server.poolCheckers.pieces.Piece;
import online.recroom.server.poolCheckers.pieces.RedPiece;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Yehuda Globerman on 4/28/2016.
 */
public class Board {

    public static final int ROWS = 8;
    public static final int COLUMNS = 8;

    private final Color colorWeArePlayingOn = Color.BLACK;

    private Cell[][] board = new Cell[ROWS][COLUMNS];
    ;

    private LinkedList<Movement> movements;
    private HashSet<Piece> blackPieces;
    private HashSet<Piece> redPieces;

    public Board() throws CoOrdinatesOutOfBoundsException {
        initializeBoard();
    }

    private void initializeBoard() throws CoOrdinatesOutOfBoundsException {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (row % 2 == 0) {
                    if (column % 2 == 0) {
                        board[row][column] = new Cell(this, new CoOrdinates(row, column), Color.WHITE);
                    } else {
                        Cell tempCell = new Cell(this, new CoOrdinates(row, column), Color.BLACK, new RedPiece());
                        tempCell.getPiece().setCellPieceIsIn(tempCell);
                        board[row][column] = tempCell;
                    }
                } else {
                    if (column % 2 == 0) {
                        Cell tempCell = new Cell(this, new CoOrdinates(row, column), Color.BLACK, new RedPiece());
                        tempCell.getPiece().setCellPieceIsIn(tempCell);
                        board[row][column] = tempCell;
                    } else {
                        board[row][column] = new Cell(this, new CoOrdinates(row, column), Color.WHITE);
                    }
                }
            }
        }
        for (int row = 3; row < 5; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (row % 2 == 0) {
                    if (column % 2 == 0) {
                        board[row][column] = new Cell(this, new CoOrdinates(row, column), Color.WHITE);
                    } else {
                        board[row][column] = new Cell(this, new CoOrdinates(row, column), Color.BLACK);
                    }
                }
            }
        }

        for (int row = 5; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (row % 2 == 0) {
                    if (column % 2 == 0) {
                        board[row][column] = new Cell(this, new CoOrdinates(row, column), Color.WHITE);
                    } else {
                        Cell tempCell = new Cell(this, new CoOrdinates(row, column), Color.BLACK, new BlackPiece());
                        tempCell.getPiece().setCellPieceIsIn(tempCell);
                        board[row][column] = tempCell;
                    }
                } else {
                    if (column % 2 == 0) {
                        Cell tempCell = new Cell(this, new CoOrdinates(row, column), Color.BLACK, new BlackPiece());
                        tempCell.getPiece().setCellPieceIsIn(tempCell);
                        board[row][column] = tempCell;
                    } else {
                        board[row][column] = new Cell(this, new CoOrdinates(row, column), Color.WHITE);
                    }
                }
            }
        }
    }

    public Color getColorWeArePlayingOn() {
        return colorWeArePlayingOn;
    }

    private Cell getCell(int row, int column) {
        return board[row][column];
    }

    public Cell getCell(CoOrdinates coOrdinates) {
        return getCell(coOrdinates.row, coOrdinates.column);
    }

    public Piece getPiece(CoOrdinates coOrdinates)
    {
        return getCell(coOrdinates).getPiece();
    }

    public boolean isValidMove(Movement proposedMovement)
    {
        return getPiece(proposedMovement.origin).isDestinationValid(getCell(proposedMovement.destination));
    }

    public void movePiece(Movement movement) throws Exception {
        if (!isValidMove(movement)) {
            throw new IllegalMovementException();
        } else {
            if (movement.piece.isCaptureMove(getCell(movement.destination))) {
                capturePiece(movement.piece, movement.origin, movement.destination);
            }
            movement.piece.setCellPieceIsIn(getCell(movement.destination));
            getCell(movement.origin).clearCell();
            getCell(movement.destination).setPiece(movement.piece);
        }
    }

    private void capturePiece(Piece captor, CoOrdinates origin, CoOrdinates destination) throws Exception {
        if (!captor.isCaptureMove(getCell(destination))) {
            throw new Exception();
        }
        Cell tempCell;
        if (destination.row == (origin.row + 2)) {
            if (destination.column == (origin.column + 2)) {
                tempCell = getCell(new CoOrdinates(origin.row + 1, origin.column + 1));
            } else {
                tempCell = getCell(new CoOrdinates(origin.row + 1, origin.column - 1));
            }
        } else {
            if (destination.column == (origin.column + 2)) {
                tempCell = getCell(new CoOrdinates(origin.row - 1, origin.column + 1));
            } else {
                tempCell = getCell(new CoOrdinates(origin.row - 1, origin.column - 1));
            }
        }
        if (tempCell.getPiece().color == online.recroom.server.poolCheckers.pieces.Color.BLACK) {
            blackPieces.remove(tempCell.getPiece());
        } else {
            redPieces.remove(tempCell.getPiece());
        }
        tempCell.clearCell();
    }

    private boolean colorHasLegalMoves(online.recroom.server.poolCheckers.pieces.Color pieceColor) throws CoOrdinatesOutOfBoundsException {
        if (pieceColor == online.recroom.server.poolCheckers.pieces.Color.BLACK) {
            return blackHasMoves();
        } else {
            return redHasMoves();
        }
    }

    private boolean blackHasMoves() throws CoOrdinatesOutOfBoundsException {
        for (Piece piece : blackPieces) {
            if (piece.hasMoves()) {
                return true;
            }
        }
        return false;
    }

    private boolean redHasMoves() throws CoOrdinatesOutOfBoundsException {
        for (Piece piece : redPieces) {
            if (piece.hasMoves()) {
                return true;
            }
        }
        return false;
    }
}
