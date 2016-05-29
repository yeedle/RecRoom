package online.recroom.server.bubbles;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Yehuda Globerman on 5/27/2016.
 */
public class Controller {
    private static final int PLAYER_LIMIT = 6;
    private static final ConcurrentLinkedQueue<Game> PENDING_GAMES
            = new ConcurrentLinkedQueue<>();
    private static final PriorityBlockingQueue<Game> ACTIVE_GAMES
            = new PriorityBlockingQueue<>();

    private BubblesServer bubblesServer;
    private Game game;
    private BubblePlayer player;


    public void setEndpoint(BubblesServer bubblesServer) {
        this.bubblesServer = bubblesServer;
    }

    public void connectToGame(Session session, String playerName) throws Exception {
        this.player = new BubblePlayer(playerName);
        if (isThereActiveAnGameWithRoom()) {
            joinActiveGame(session);
        } else if (!PENDING_GAMES.isEmpty()) {
            joinPendingGame(session);
        } else {
            startNewGame(session);
        }
    }

    private void joinActiveGame(Session session) throws Exception {
        game = getActiveGameThatHasRoom();
        game.addPlayer(this.player);
        game.getPlayersSessions().add(session);
//            send bubbles to player that joined the game
        session.getBasicRemote().sendObject(Message.joinedGame(
                game.getBubbles().values().toArray(new Bubble[game.getBubbles().size()]),
                game.getPlayers().toArray(new BubblePlayer[game.getAmountOfPlayers()])));
//          Send message to all other players that a new player has joined
        bubblesServer.broadcastPlayerJoinedMessage();
    }

    private void joinPendingGame(Session session) throws IOException, EncodeException {
        game = PENDING_GAMES.remove();
        ACTIVE_GAMES.add(game);
        game.addPlayer(this.player);
        game.getPlayersSessions().add(session);
//            TODO send bubbles to both players
        broadcastGameStartedMessage();
    }

    private void startNewGame(Session session) throws IOException, EncodeException {
        //        TODO start new game
        game = new Game(this.player);
        PENDING_GAMES.add(game);
        game.getPlayersSessions().add(session);
        bubblesServer.sendMessage(Message.gamePending());
    }

    private boolean isThereActiveAnGameWithRoom() {
//        using priority queue, which gives me smallest game
        return !ACTIVE_GAMES.isEmpty() && ACTIVE_GAMES.element().getAmountOfPlayers() < PLAYER_LIMIT;
    }

    private Game getActiveGameThatHasRoom() throws Exception {
        if (!isThereActiveAnGameWithRoom())
            throw new Exception();
        return ACTIVE_GAMES.peek();
    }

    public void popBubble(long id) {

    }

    public void broadcastGameStartedMessage() throws IOException, EncodeException {
        bubblesServer.broadcastMessage(Message.gameStarted(getBubblesAsArray(), getPlayersAsArray()),
                game.getPlayersSessions(), true);
    }

    public void broadcastPlayerJoinedMessage() throws IOException, EncodeException {
        bubblesServer.broadcastMessage(Message.playerJoined(this.player.name),
                game.getPlayersSessions(), false);
    }

    public void broadcastPlayerLeft(String playerName) throws IOException, EncodeException {
        bubblesServer.broadcastMessage(Message.playerLeft(playerName), game.getPlayersSessions(), false);
    }

    public void broadcastBubblePoppedMessage(long id) throws IOException, EncodeException {
//        iterate through connected session and send the BubblePoppedMessage
        bubblesServer.broadcastMessage(Message.bubblePopped(id), game.getPlayersSessions(), true);
    }

    public void broadcastGameOverMessage() throws IOException, EncodeException {
        BubblePlayer winner = game.getLeader();
        bubblesServer.broadcastMessage(Message.gameOver(winner.name, winner.getScore()),
                game.getPlayersSessions(), true);
    }

    private Bubble[] getBubblesAsArray() {
        return this.game.getBubbles().values().toArray(new Bubble[game.getBubbles().size()]);
    }

    private BubblePlayer[] getPlayersAsArray() {
        return this.game.getPlayers().toArray(new BubblePlayer[game.getPlayers().size()]);
    }
}
