package online.recroom.server.bubbles;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yehuda Globerman on 5/15/2016.
 */
@ServerEndpoint(
        value = "/bubble",
        decoders = {MessageDecoder.class},
        encoders = {MessageEncoder.class})
public class BubblesServer {
    public static final int PLAYER_LIMIT = 6;
    private static Set<Game> pendingGames = new HashSet<>();
    private static Set<Game> activeGames = new HashSet<>();
    private static Set<Game> endedGames = new HashSet<>();

    private Session session;
    private Game game;
    private BubblePlayer player;

    @OnOpen
    public void onOpen(Session session) throws Exception {
        this.session = session;
        player = new BubblePlayer(extractPlayerName());

        if (isThereActiveAnGameWithRoom()) {
            game = getActiveGameThatHasRoom();
            game.addPlayer(this.player);
            game.getPlayersSessions().add(this.session);
//            TODO send bubbles to player that joined the game
            session.getBasicRemote().sendObject(Message.joinedGame(
                    game.getArrayOfBubbles(), game.getArrayOfPlayers()));
//           TODO Send message to all other players that a new player has joined
            broadcastPlayerJoinedMessage();
        } else if (!pendingGames.isEmpty()) {
            game = getAPendingGame();
            pendingGames.remove(game);
            activeGames.add(game);
            game.addPlayer(this.player);
            game.getPlayersSessions().add(this.session);
//            TODO send bubbles to both players
            startNewGame(game.getArrayOfBubbles(), game.getArrayOfPlayers());
        } else {
            //        TODO start new game
            game = new Game(this.player);
            pendingGames.add(game);
            game.getPlayersSessions().add(this.session);
            session.getBasicRemote().sendObject(Message.gamePending());
        }
    }

    @OnMessage
    public void onMessage(long bubbleId) throws Exception {
//        TODO keep score and send message to all players, check if game is over
        if (game.wasBubblePopped(bubbleId))
            throw new Exception("Someone beat you to it, sorry");

        game.removeBubble(bubbleId);
        broadcastBubblePoppedMessage(bubbleId);
        player.incrementBubblesPopped();
        if (game.isOver()) {
            broadcastGameOverMessage(game.leader());
            endedGames.add(game);
            activeGames.remove(game);
        }
    }

    @OnError
    public void onError(Throwable throwable) {

    }

    @OnClose
    public void onClose() throws IOException, EncodeException {
        game.removePlayer(this.player);
        game.removeSession(this.session);
        broadcastPlayerLeft(this.player.name);
        if (game.getPlayersSessions().size() == 1) {
            broadcastGameOverMessage(this.player);
            endedGames.add(game);
            activeGames.remove(game);
        } else if (game.getPlayersSessions().size() == 0) {
            endedGames.remove(game);
        }
    }

    private boolean isThereActiveAnGameWithRoom() {
        for (Game g : activeGames) {
            if (g.getPlayersSessions().size() < PLAYER_LIMIT)
                return true;
        }
        return false;
    }

    private Game getActiveGameThatHasRoom() throws Exception {
        if (!isThereActiveAnGameWithRoom())
            throw new Exception();
        for (Game g : activeGames) {
            if (g.getPlayersSessions().size() < PLAYER_LIMIT) {
                return g;
            }
        }
        throw new Exception();
    }

    private Game getAPendingGame() throws Exception {
        for (Game g : pendingGames) {
            return g;
        }
        throw new Exception();
    }

    private void startNewGame(Bubble[] bubbles, BubblePlayer[] players) throws IOException, EncodeException {
        broadcastMessage(Message.gameStarted(bubbles, players), true);
    }

    private void broadcastMessage(Message m, boolean includeMe) throws IOException, EncodeException {
        for (Session s : this.game.getPlayersSessions()) {
            if ((includeMe || s != this.session) && s.isOpen())
                s.getBasicRemote().sendObject(m);
        }
    }

    private void broadcastPlayerJoinedMessage() throws IOException, EncodeException {
        broadcastMessage(Message.playerJoined(this.player.name), false);
    }

    private void broadcastBubblePoppedMessage(long id) throws IOException, EncodeException {
//        iterate through connected session and send the BubblePoppedMessage
        broadcastMessage(Message.bubblePopped(id), true);
    }

    public void broadcastPlayerLeft(String playerName) throws IOException, EncodeException {
        broadcastMessage(Message.playerLeft(playerName), false);
    }

    public void broadcastGameOverMessage(BubblePlayer winner) throws IOException, EncodeException {
        broadcastMessage(Message.gameOver(winner.name, winner.getScore()), true);
    }

    private String extractQueryParam(String key) {
        return this.session.getRequestParameterMap().get(key).get(0);
    }

    private String extractPlayerName() {
        if (session.getRequestParameterMap().containsKey("username")) {
            return extractQueryParam("username");
        } else {
            return "Anonymous";
        }
    }

    private long extractGameIdOfSession() {
        return Long.parseLong(extractQueryParam("gameId"));
    }
}
