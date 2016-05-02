package online.recroom.server.chess;

/**
 * Created by Yeedle on 5/2/2016 3:46 PM.
 */
public class InvalidMoveException extends Exception
{

    public InvalidMoveException()
    {
        super();
    }
    public InvalidMoveException(String s)
    {
        super(s);
    }
}
