package online.recroom.server.chess;

/**
 * Created by Yeedle on 5/2/2016 3:46 PM.
 */
public class InvalidMove extends Throwable
{

    public InvalidMove()
    {
        super();
    }
    public InvalidMove(String s)
    {
        super(s);
    }
}
