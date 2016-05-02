package online.recroom.server.chess;

/**
 * Created by Yeedle on 5/2/2016 3:03 PM.
 */
public class IllegalMoveException extends Throwable
{
    public IllegalMoveException()
    {
        super();
    }

    public IllegalMoveException(String error)
    {
        super(error);
    }
}
