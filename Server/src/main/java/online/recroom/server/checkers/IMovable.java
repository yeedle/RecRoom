package online.recroom.server.checkers;

/**
 * Created by theje on 4/28/2016.
 */
public interface IMovable {
    public boolean checkMove(int row, int column);

    public void move(int row, int column);
}
