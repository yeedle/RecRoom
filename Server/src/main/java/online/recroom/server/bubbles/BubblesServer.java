package online.recroom.server.bubbles;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Yehuda Globerman on 5/15/2016.
 */
public class BubblesServer {
    private static ConcurrentHashMap<Long, Game> pendingGames = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Long, Game> activeGames = new ConcurrentHashMap<>();

}
