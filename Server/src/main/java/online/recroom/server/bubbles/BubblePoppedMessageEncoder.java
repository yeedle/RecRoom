package online.recroom.server.bubbles;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Yehuda Globerman on 5/21/2016.
 */
public class BubblePoppedMessageEncoder implements Encoder.Text<BubblePoppedMessage> {
    private static Gson gson = new Gson();

    @Override
    public String encode(BubblePoppedMessage message) throws EncodeException {
        return gson.toJson(message, BubblePoppedMessage.class);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
