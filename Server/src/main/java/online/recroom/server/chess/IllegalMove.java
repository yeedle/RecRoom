package online.recroom.server.chess;

/**
 * Created by Yeedle on 5/2/2016 3:03 PM.
 */
public class IllegalMove extends Throwable
{
    public IllegalMove()
    {
        super();
    }

    public IllegalMove(String error)
    {
        super(error);
    }
}
