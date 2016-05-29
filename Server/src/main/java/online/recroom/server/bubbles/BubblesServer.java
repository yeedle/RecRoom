package online.recroom.server.bubbles;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Yehuda Globerman on 5/15/2016.
 */
@ServerEndpoint(
        value = "/bubble",
        decoders = {MessageDecoder.class},
        encoders = {MessageEncoder.class})
public class BubblesServer {
    private static final int PLAYER_LIMIT = 6;
    private static final ConcurrentLinkedQueue<Game> PENDING_GAMES
            = new ConcurrentLinkedQueue<>();
    private static final PriorityBlockingQueue<Game> ACTIVE_GAMES
            = new PriorityBlockingQueue<>();

    private Controller controller;
    private Session session;
    private Game game;
    private BubblePlayer player;

    @OnOpen
    public void onOpen(Session session) throws Exception {
        this.session = session;
        player = new BubblePlayer(extractPlayerName());

        if (isThereActiveAnGameWithRoom()) {
            joinActiveGame();
        } else if (!PENDING_GAMES.isEmpty()) {
            joinPendingGame();
        } else {
            startNewGame(session);
        }
    }

    private void startNewGame(Session session) throws IOException, EncodeException {
        //        TODO start new game
        game = new Game(this.player);
        PENDING_GAMES.add(game);
        game.getPlayersSessions().add(this.session);
        session.getBasicRemote().sendObject(Message.gamePending());
    }

    private void joinPendingGame() throws IOException, EncodeException {
        game = PENDING_GAMES.remove();
        ACTIVE_GAMES.add(game);
        game.addPlayer(this.player);
        game.getPlayersSessions().add(this.session);
//            TODO send bubbles to both players
        startGame();
    }

    private void joinActiveGame() throws Exception {
        game = getActiveGameThatHasRoom();
        game.addPlayer(this.player);
        game.getPlayersSessions().add(this.session);
//            TODO send bubbles to player that joined the game
        this.session.getBasicRemote().sendObject(Message.joinedGame(
                game.getBubbles().values().toArray(new Bubble[game.getBubbles().size()]),
                game.getPlayers().toArray(new BubblePlayer[game.getAmountOfPlayers()])));
//          Send message to all other players that a new player has joined
        broadcastPlayerJoinedMessage();
    }

    @OnMessage
    public void onMessage(final long bubbleId) throws Exception {
//        TODO keep score and send message to all players, check if game is over
        if (game.isBubblePopped(bubbleId))
            throw new Exception("Someone beat you to it, sorry");

        game.removeBubble(bubbleId);
        broadcastBubblePoppedMessage(bubbleId);
        player.incrementBubblesPopped();
        if (game.isOver()) {
            broadcastGameOverMessage(game.getLeader());
            ACTIVE_GAMES.remove(game);

        }
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose() throws IOException, EncodeException {
        game.removePlayer(this.player);
        game.removeSession(this.session);
        broadcastPlayerLeft(this.player.name);
        if (game.getAmountOfPlayers() == 1) {
            broadcastGameOverMessage(game.getLeader());
            ACTIVE_GAMES.remove(game);
            for (Session s : game.getPlayersSessions()) {
                if (s.isOpen()) {
                    s.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Game over"));
                    game.removeSession(s);
                }
            }
        }
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

    private void startGame() throws IOException, EncodeException {
        Bubble[] bubbles = this.game.getBubbles().values().toArray(new Bubble[game.getBubbles().size()]);
        BubblePlayer[] players = this.game.getPlayers().toArray(new BubblePlayer[game.getAmountOfPlayers()]);
        broadcastMessage(Message.gameStarted(bubbles, players), true);
    }

    public void broadcastMessage(Message m, Set<Session> sessions, boolean includeMe) throws IOException, EncodeException {
        for (Session s : sessions) {
            if ((includeMe || s != this.session) && s.isOpen())
                s.getBasicRemote().sendObject(m);
        }
    }

    private void broadcastMessage(Message m, boolean includeMe) throws IOException, EncodeException {
        for (Session s : this.game.getPlayersSessions()) {
            if ((includeMe || s != this.session) && s.isOpen())
                s.getBasicRemote().sendObject(m);
        }
    }

    public void sendMessage(Message m) throws IOException, EncodeException {
        this.session.getBasicRemote().sendObject(m);
    }

    public void broadcastPlayerJoinedMessage() throws IOException, EncodeException {
        broadcastMessage(Message.playerJoined(this.player.name), false);
    }

    public void broadcastBubblePoppedMessage(long id) throws IOException, EncodeException {
//        iterate through connected session and send the BubblePoppedMessage
        broadcastMessage(Message.bubblePopped(id), true);
    }

    public void broadcastPlayerLeft(String playerName) throws IOException, EncodeException {
        broadcastMessage(Message.playerLeft(playerName), false);
    }

    public void broadcastGameOverMessage(BubblePlayer winner) throws IOException, EncodeException {
        broadcastMessage(Message.gameOver(winner.name, winner.getScore()), true);
    }

    public String extractQueryParam(String key) {
        return this.session.getRequestParameterMap().get(key).get(0);
    }

    private boolean isQueryParamEmpty(String key) {
        String name = this.session.getRequestParameterMap().get(key).get(0);
        return name == null || name.isEmpty();
    }

    private String extractPlayerName() {
        if (session.getRequestParameterMap().containsKey("username") && !isQueryParamEmpty("username")) {
            return extractQueryParam("username");
        } else {
            return "Anonymous";
        }
    }

    private long extractGameIdOfSession() {
        return Long.parseLong(extractQueryParam("gameId"));
    }
}
