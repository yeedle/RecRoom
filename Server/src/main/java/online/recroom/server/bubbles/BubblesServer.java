package online.recroom.server.bubbles;


import online.recroom.server.Player;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Set;
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
    private Set<Session> opponentsSession = new HashSet<>();
    private Game connectedGame;

    @OnOpen
    public void onOpen(Session session, @PathParam("gameId") long gameId) throws Exception {
        this.session = session;
        if (gameId == -1) {
//        TODO start new game
            connectedGame =
                    new Game(new BubblePlayer((String) session.getUserProperties().getOrDefault("player", "Anonymous")));
            session.getUserProperties().put("gameId", connectedGame.id);
        } else {
//            TODO join a game based on the game id
            if (pendingGames.containsKey(gameId)) {
                connectedGame = pendingGames.get(gameId);
            } else if (activeGames.containsKey(gameId)) {
                connectedGame = activeGames.get(gameId);
            } else {
//                Close connection and return error message.
            }
            connectedGame.addPlayer(new Player((String) session.getUserProperties().getOrDefault("player", "Anonymous")));
            session.getUserProperties().put("gameId", connectedGame.id);
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen() && ((Long) s.getUserProperties().get("gameId")) == gameId) {
                    opponentsSession.add(s);
                }
            }
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

}
