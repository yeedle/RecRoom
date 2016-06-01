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

            controller = new Controller();

    }

    @OnMessage
    public void onMessage(Message message)
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
}
