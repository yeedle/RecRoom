package online.recroom.server.ticTacToe;

import com.google.gson.Gson;
import online.recroom.server.ticTacToe.Move;
import online.recroom.server.ticTacToe.TicTacToeGame;

/**
 * Created by Yehuda Globerman on 4/17/2016.
 */
public class Json
{
   private String json;
    private Class type;

    private <T> Json(String json, Class<T> type)
    {
        this.json = json;
        this.type = type;
    }

    public enum Type{
        GAME_OVER, GAME_STARTED, OPPONENT_MOVED, GAME_IS_DRAW, GAME_FORFEITED
    }

    public static <T> Json factory(T obj)
    {
        Gson gson = new Gson();
        String json = gson.toJson(obj, obj.getClass());
        return new Json(json, obj.getClass());
    }
}
