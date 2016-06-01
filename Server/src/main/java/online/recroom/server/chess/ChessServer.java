package online.recroom.server.chess;

import com.google.gson.Gson;
import online.recroom.messages.Message;
import online.recroom.messages.MessageDecoder;
import online.recroom.messages.MessageEncoder;
import online.recroom.server.chess.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by Yeedle on 4/11/2016 9:07 PM.
 */
@ServerEndpoint(
        value = "/chess/{gameID}/{userID}",
        decoders = {MessageDecoder.class},
        encoders = {MessageEncoder.class})
public class ChessServer
{
    private Session session;

    Controller controller;

    @OnOpen
    public void onOpen(Session session)
    {
        //TODO assign Player color to session
        this.session = session;
        controller = new Controller(this);
        controller.connectToGame(this.session, extractPlayerName());

    }

    @OnMessage
    public void onMessage(Message message) throws IllegalMoveException, IllegalCoordinateException, InvalidMoveException
    {
        Gson gson = new Gson();
        controller.updateGame(gson.fromJson(message.json, message.type));
    }

    @OnError
    public void onError(Throwable throwable)
    {

    }

    @OnClose
    public void onClose()
    {

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
}
