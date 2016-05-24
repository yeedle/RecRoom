package online.recroom.client.bubbles;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Yehuda Globerman on 5/21/2016.
 */
public class MessageDecoder implements Decoder.Text<Message> {
    private static Gson gson = new Gson();

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Message decode(String s) throws DecodeException {
        System.out.println(s);
        return gson.fromJson(s, Message.class);
    }

    @Override
    public boolean willDecode(String s) {
        try {
            gson.fromJson(s, Message.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
