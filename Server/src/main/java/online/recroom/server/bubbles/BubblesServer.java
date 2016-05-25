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
        value = "/bubbles",
        decoders = {MessageDecoder.class},
        encoders = {MessageEncoder.class})
public class BubblesServer {
    public static final int PLAYER_LIMIT = 6;
    private static Set<Game> pendingGames = new HashSet<>();
    private static Set<Game> activeGames = new HashSet<>();

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
            session.getBasicRemote().sendObject(Message.createJoinedGameMessage(game.getArrayOfBubbles(), game.getArrayOfPlayers()));
//            Send message to all other players that a new player has joined

        } else if (!pendingGames.isEmpty()) {
            game = getAPendingGame();
            pendingGames.remove(game);
            activeGames.add(game);
            game.addPlayer(this.player);
            game.getPlayersSessions().add(this.session);
//            TODO send bubbles to both players
            for (Session s : game.getPlayersSessions()) {
                if (s != this.session) {
                    sendListOfBubblesToSession(s, game.getArrayOfBubbles(), game.getArrayOfPlayers());
                }
            }
            session.getBasicRemote().sendObject(Message.createGameStartedMessage(game.getArrayOfBubbles(), game.getArrayOfPlayers()));
        } else {
            //        TODO start new game
            game = new Game(this.player);
            pendingGames.add(game);
            game.getPlayersSessions().add(this.session);
            session.getBasicRemote().sendObject(Message.createGamePendingMessage());
        }
    }

    @OnMessage
    public void onMessage(long bubbleId) throws Exception {
//        TODO keep score and send message to all players, check if game is over
        if (game.wasBubblePopped(bubbleId))
            throw new Exception("Someone beat you to it, sorry");

        game.removeBubble(bubbleId);
        sendBubblePoppedMessage(bubbleId);
        player.incrementBubblesPopped();
        if (game.isOver())
            for (Session s : this.session.getOpenSessions()) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendObject(Message.createGameOverMessage(
                            game.leader().name, game.leader().getScore()));
                }
            }
    }

    @OnError
    public void onError(Throwable throwable) {

    }

    @OnClose
    public void onClose() {

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

    private static void sendListOfBubblesToSession(Session aSession, Bubble[] bubbles, BubblePlayer[] players) throws IOException, EncodeException {
//        TODO when player joins a game that has already begun, send them the state of the game
        RemoteEndpoint.Basic endpoint = aSession.getBasicRemote();
        endpoint.sendObject(Message.createGameStartedMessage(bubbles, players));
    }


    private void sendBubblePoppedMessage(long id) throws IOException, EncodeException {
//        TODO iterate through connected session and send the BubblePoppedMessage
        Message bubblePoppedMessage = Message.createBubblePoppedMessage(id);
        for (Session s : game.getPlayersSessions()) {
            RemoteEndpoint.Basic endpoint = s.getBasicRemote();
            endpoint.sendObject(bubblePoppedMessage);
        }
    }

    private String extractQueryParam(String key) {
        return this.session.getRequestParameterMap().get(key).get(0);
    }

    private String extractPlayerName() {
        if (session.getRequestParameterMap().containsKey("player")) {
            return extractQueryParam("player");
        } else {
            return "Anonymous";
        }
    }

    private long extractGameIdOfSession() {
        return Long.parseLong(extractQueryParam("gameId"));
    }
}
