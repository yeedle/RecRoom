package online.recroom.server.bubbles;

/**
 * Created by Yeedle on 5/11/2016 6:16 PM.
 */

public class Bubble
{
    public static long idGenerator =0;
    public final long id = idGenerator++;
    private int x;
    private int y;

    public Bubble(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}

