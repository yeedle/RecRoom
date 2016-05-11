package online.recroom.server.bubbles;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Yeedle on 5/11/2016 6:20 PM.
 */
public class Frame
{
    private Collection<Bubble> bubbles;

    public Frame()
    {
        bubbles = new HashSet<>();
    }

    public void generateBubble()
    {
        int x = randomXorY();
        int y = randomXorY();
        bubbles.add(new Bubble(x, y));
    }

    public int randomXorY()
    {
        return 0;
    }

}
