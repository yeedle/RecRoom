package online.recroom.server.bubbles;

import online.recroom.server.Player;

import java.util.HashSet;

/**
 * Created by Yeedle on 5/11/2016 6:25 PM.
 */
public class Game
{
    private static long idGenerator = Long.MIN_VALUE;

    private HashSet<Player> players = new HashSet();
    public final long id = idGenerator++;
    private final HashSet<Bubble> bubbles = new HashSet<>();

    public Game(Player p) {
        players.add(p);
    }

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

    public void addPlayer(Player p) {
        players.add(p);
    }

    public Player removePlayer(Player p) {
        return players.remove(p) ? p : null;
    }
}
