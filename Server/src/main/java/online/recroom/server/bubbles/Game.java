package online.recroom.server.bubbles;

import javax.websocket.Session;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Yeedle on 5/11/2016 6:25 PM.
 */
public class Game implements Comparable<Game>
{
    private static long idGenerator = Long.MIN_VALUE;

    public final long id = idGenerator++;
    private final Set<Session> playersSessions = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private final PriorityBlockingQueue<BubblePlayer> players = new PriorityBlockingQueue<>();
    private final ConcurrentHashMap<Long, Bubble> bubbles = new ConcurrentHashMap<>();

    public Game(BubblePlayer player1) {
        players.add(player1);
        generateBubbles(40);
    }

    private void generateBubbles(int amount) {
        for (int i = 0; i < amount; i++) {
            Bubble b = new Bubble();
            bubbles.put(b.id, b);
        }
    }

    public boolean isBubblePopped(long id) {
        return !bubbles.containsKey(id);
    }

    public boolean isOver() {
        return bubbles.isEmpty();
    }

    public BubblePlayer getLeader() {
        return players.element();
    }

    public int getAmountOfPlayers() {
        return players.size();
    }

    public PriorityBlockingQueue<BubblePlayer> getPlayers() {
        return players;
    }

    public void addPlayer(BubblePlayer p) {
        players.add(p);
    }

    public void removePlayer(BubblePlayer p) {
        players.remove(p);
    }

    public ConcurrentHashMap<Long, Bubble> getBubbles() {
        return bubbles;
    }

    public void removeBubble(long id) {
        bubbles.remove(id);
    }

    public Set<Session> getPlayersSessions() {
        return playersSessions;
    }

    public void removeSession(Session s) {
        playersSessions.remove(s.hashCode());
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

    @Override
    public int compareTo(Game otherGame) {
        return this.players.size() - otherGame.players.size();
    }
}
