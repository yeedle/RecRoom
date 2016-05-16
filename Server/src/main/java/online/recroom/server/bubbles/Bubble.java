package online.recroom.server.bubbles;

import java.util.Random;

/**
 * Created by Yeedle on 5/11/2016 6:16 PM.
 */

public class Bubble {
    private static Random random = new Random();
    private static long idGenerator = Long.MIN_VALUE;

    public final long id = idGenerator++;
    public final double relativeXPosittion = random.nextDouble();
    public final double relativeYPosition = random.nextDouble();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bubble bubble = (Bubble) o;

        return id == bubble.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

