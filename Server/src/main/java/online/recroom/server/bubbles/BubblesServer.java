package online.recroom.server.bubbles;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yehuda Globerman on 5/15/2016.
 */
@ServerEndpoint(
        value = "/bubbles/{gameId}",
        decoders = {BubblePoppedMessageDecoder.class},
        encoders = {BubblePoppedMessageEncoder.class})
public class BubblesServer {
    private static ConcurrentHashMap<Long, Game> pendingGames = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Long, Game> activeGames = new ConcurrentHashMap<>();

    private Session session;
    private Game connectedGame;

    @OnOpen
    public void onOpen(Session session) throws Exception {
        this.session = session;
        if (!session.getRequestParameterMap().containsKey("gameId")) {
//        TODO start new game
            connectedGame =
                    new Game(new BubblePlayer(extractQueryParam("player")));
            connectedGame.getPlayersSessions().add(this.session);
        } else {
//            TODO join a game based on the game id
            long gameId = extractGameIdOfSession();
            String name = extractPlayerName();
            if (pendingGames.containsKey(gameId)) {
                connectedGame = pendingGames.get(gameId);
            } else if (activeGames.containsKey(gameId)) {
                connectedGame = activeGames.get(gameId);
            } else {
//                Close connection and return error message.
            }
            connectedGame.addPlayer(new BubblePlayer(name));
            connectedGame.getPlayersSessions().add(this.session);
        }
    }


    @OnMessage
    public void onMessage(BubblePoppedMessage message) {

    }

    @OnError
    public void onError(Throwable throwable) {

    }

    @OnClose
    public void onClose() {

    }

    private void sendGameStateOnJoin() {
//        TODO when player joins a game that has already begun, send them the state of the game
    }


    private void sendBubblePoppedMessage() {
//        TODO iterate through connected session and send the BubblePoppedMessage
    }

    private long extractGameIdOfSession() {
        return Long.parseLong(extractQueryParam("gameId"));
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
}
