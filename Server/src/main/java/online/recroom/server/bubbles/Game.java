package online.recroom.server.bubbles;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Yeedle on 5/11/2016 6:25 PM.
 */
public class Game
{
    private static long idGenerator = Long.MIN_VALUE;

    private final Set<Session> playersSessions = new HashSet<>();
    public final long id = idGenerator++;
    private final List<BubblePlayer> players = new ArrayList<>();
    private final HashSet<Bubble> bubbles = new HashSet<>();

    public Game(BubblePlayer player1) {
        players.add(player1);
        generateBubbles(40);
    }

    public void generateBubbles(int amount) {
        for (int i = 0; i < amount; i++) {
            bubbles.add(new Bubble());
        }
    }

    public Bubble[] getArrayOfBubbles() {
        Bubble[] bubbles = new Bubble[this.bubbles.size()];
        return this.bubbles.toArray(bubbles);
    }

    public void removeBubble(long id) {
        for (Bubble bubble : bubbles) {
            if (id == bubble.id) {
                bubbles.remove(bubble);
                break;
            }
        }
    }

    public boolean wasBubblePopped(long id) {
        boolean found = false;
        for (Bubble bubble : bubbles) {
            if (bubble.id == id) {
                found = true;
                break;
            }
        }
        return !found;
    }

    public void addPlayer(BubblePlayer p) {
        players.add(p);
    }

    public void removePlayer(BubblePlayer p) {
        players.remove(p);
    }

    private void generateBubbles() {
//        TODO generate the bubbles when the game starts
    }

    public Set<Session> getPlayersSessions() {
        return playersSessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return id == game.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
