package online.recroom.server.ticTacToe.message;

import online.recroom.server.ticTacToe.message.Message;

/**
 * Created by Yeedle on 5/29/2016 5:38 PM.
 */
public class GamePendingMessage extends Message
{
    public GamePendingMessage(String action)
    {
        super(action);
    }
}
