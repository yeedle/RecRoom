package online.recroom.server.bubbles;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yehuda Globerman on 5/15/2016.
 */
@ServerEndpoint(
        value = "/bubbles/",
        decoders = {MessageDecoder.class},
        encoders = {MessageEncoder.class})
public class BubblesServer {
    private static Set<Game> pendingGames = new HashSet<>();
    private static Set<Game> activeGames = new HashSet<>();

    private Session session;
    private Game connectedGame;

    @OnOpen
    public void onOpen(Session session) throws Exception {
        this.session = session;
        String name = extractPlayerName();

        if (!isThereActiveGameWithRoom() && pendingGames.isEmpty()) {
            //        TODO start new game
            connectedGame =
                    new Game(new BubblePlayer(name));
            pendingGames.add(connectedGame);
        } else if (!isThereActiveGameWithRoom() && !pendingGames.isEmpty()) {
            connectedGame = getAPendingGame();
            pendingGames.remove(connectedGame);
            activeGames.add(connectedGame);
            connectedGame.addPlayer(new BubblePlayer(name));
        } else {
            connectedGame = getActiveGameThatHasRoom();
            connectedGame.addPlayer(new BubblePlayer(name));
        }
        connectedGame.getPlayersSessions().add(this.session);
    }


    @OnMessage
    public void onMessage(long bubbleId) {
//        TODO keep score and send message to all players, check if game is over
    }

    @OnError
    public void onError(Throwable throwable) {

    }

    @OnClose
    public void onClose() {

    }

    private boolean isThereActiveGameWithRoom() {
        for (Game g : activeGames) {
            if (g.getPlayersSessions().size() < 6)
                return true;
        }
        return false;
    }

    private Game getActiveGameThatHasRoom() throws Exception {
        if (!isThereActiveGameWithRoom())
            throw new Exception();
        for (Game g : activeGames) {
            if (g.getPlayersSessions().size() < 6) {
                return g;
            }
        }
        return null;
    }

    private Game getAPendingGame() {
        for (Game g : pendingGames) {
            return g;
        }
        return null;
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
