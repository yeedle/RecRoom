package online.recroom.server.bubbles;

/**
 * Created by Yeedle on 5/11/2016 6:16 PM.
 */

public class Bubble {
    private static long idGenerator = Long.MIN_VALUE;
    private final static int MINIMUM_PIXEL_DELTA = 5;

    public final long id = idGenerator++;
    public final double relativeXPosition = Math.random();
    public final double relativeYPosition = Math.random();
    public final double deltaX = Math.random() * MINIMUM_PIXEL_DELTA + 1;
    public final double deltaY = Math.random() * MINIMUM_PIXEL_DELTA + 1;
    public final double relativeRadius = 5.0;

  
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

