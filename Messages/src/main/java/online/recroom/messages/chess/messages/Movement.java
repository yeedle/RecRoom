package online.recroom.messages.chess.messages;

import online.recroom.messages.chess.POJOs.Coordinates;

/**
 * Created by Yeedle on 5/31/2016 8:14 PM.
 */
public class Movement
{
    public final Coordinates origin;
    public final Coordinates destination;

    public Movement(Coordinates origin, Coordinates destination)
    {
        this.origin = origin;
        this.destination = destination;
    }


}
