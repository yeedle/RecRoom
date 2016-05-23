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
    private List<BubblePlayer> players = new ArrayList<>();
    public final long id = idGenerator++;
    private final HashSet<Bubble> bubbles = new HashSet<>();

    public Game(BubblePlayer player1) {
        players.add(player1);
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
}
