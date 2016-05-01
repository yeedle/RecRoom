package online.recroom.server.checkers;

/**
 * Created by theje on 4/28/2016.
 */
public class KingPiece extends Piece implements IMovable {

    @Override
    public boolean checkMove(int row, int column) {
        return false;
    }

    @Override
    public void move(int row, int column) {

    }
}
