package online.recroom.server.bubbles;


import online.recroom.messages.Message;
import online.recroom.messages.MessageDecoder;
import online.recroom.messages.MessageEncoder;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;


@ServerEndpoint(
        value = "/bubblesgame",
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
    public void onMessage(final Message bubbleId) throws Exception {
        controller.updateGame(bubbleId);
    }

    @OnClose
    public void onClose() throws IOException, EncodeException {
        controller.endSession(this.session);
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
        throwable.printStackTrace();
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
