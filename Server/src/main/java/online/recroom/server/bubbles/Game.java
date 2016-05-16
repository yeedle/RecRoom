package online.recroom.server.bubbles;

import java.util.HashSet;

/**
 * Created by Yeedle on 5/11/2016 6:25 PM.
 */
public class Game
{
    private static long idGenerator = Long.MIN_VALUE;

    public final long id = idGenerator++;
    private HashSet<Bubble> bubbles = new HashSet<>();

    public void generateBubbles(int amount) {
        for (int i = 0; i < amount; i++) {
            bubbles.add(new Bubble());
        }
    }

    public void removeBubble(long id) {
        for (Bubble bubble : bubbles) {
            if (id == bubble.id) {
                bubbles.remove(bubble);
                break;
            }
        }
    }
}
