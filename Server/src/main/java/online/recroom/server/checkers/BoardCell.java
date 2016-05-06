package online.recroom.server.checkers;

import online.recroom.server.checkers.pieces.Piece;

/**
 * Created by theje on 5/6/2016.
 */
public class BoardCell {
    public final CoOrdinates coOrdinates;
    public final boolean CrowningRow;
    public Piece piece;

    private BoardCell(CoOrdinates coOrdinates, boolean crowningRow) {
        this.coOrdinates = coOrdinates;
        CrowningRow = crowningRow;
    }

    public CoOrdinates getCoOrdinates() {
        return coOrdinates;
    }

    public boolean isCrowningRow() {
        return CrowningRow;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
