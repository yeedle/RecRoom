package online.recroom.server.poolCheckers;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by theje on 4/28/2016.
 */
public class CheckersServer {
    private static ConcurrentHashMap<Long, Game> pendingGames = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Long, Game> activeGames = new ConcurrentHashMap<>();


}
