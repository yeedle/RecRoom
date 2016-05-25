package online.recroom.client.ticTacToe.message;

/**
 * Created by Yehuda Globerman on 4/17/2016.
 */
public abstract class Message {
    private final String action;

    public Message(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }
}
