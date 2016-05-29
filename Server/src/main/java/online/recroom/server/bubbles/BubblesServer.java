package online.recroom.server.bubbles;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;


@ServerEndpoint(
        value = "/bubble",
        decoders = {MessageDecoder.class},
        encoders = {MessageEncoder.class})
public class BubblesServer {

    private Controller controller;
    private Session session;

    @OnOpen
    public void onOpen(final Session session) throws Exception {
        this.session = session;
        controller = new Controller(this);
        controller.connectToGame(this.session, extractPlayerName());
    }

    @OnMessage
    public void onMessage(final long bubbleId) throws Exception {
        controller.popBubble(bubbleId);
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose() throws IOException, EncodeException {
        controller.closeSession(this.session);
    }

    public void broadcastMessage(Message m, Set<Session> sessions, boolean includeMe) throws IOException, EncodeException {
        for (Session s : sessions) {
            if ((includeMe || s != this.session) && s.isOpen())
                s.getBasicRemote().sendObject(m);
        }
    }

    public void sendMessage(Message m) throws IOException, EncodeException {
        this.session.getBasicRemote().sendObject(m);
    }

    public void closeSessions(Set<Session> sessions, CloseReason closeReason) throws IOException {
        for (Session s : sessions) {
            if (s.isOpen()) {
                s.close(closeReason);
            }
        }
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
