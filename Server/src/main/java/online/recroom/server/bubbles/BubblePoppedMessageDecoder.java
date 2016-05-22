package online.recroom.server.bubbles;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Yehuda Globerman on 5/21/2016.
 */
public class BubblePoppedMessageDecoder implements Decoder.Text<BubblePoppedMessage> {
    private static Gson gson = new Gson();

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public BubblePoppedMessage decode(String s) throws DecodeException {
        return gson.fromJson(s, BubblePoppedMessage.class);
    }

    @Override
    public boolean willDecode(String s) {
        try {
            gson.fromJson(s, BubblePoppedMessage.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
