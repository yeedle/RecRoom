package online.recroom.server.ticTacToe;

import online.recroom.server.Player;
import online.recroom.server.ticTacToe.message.GamePendingMessage;
import online.recroom.server.ticTacToe.message.GameStartedMessage;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Yeedle on 5/29/2016 5:03 PM.
 */
public class Controller
{

    private static final ConcurrentLinkedQueue<TicTacToeGame> PENDING_GAMES = new ConcurrentLinkedQueue<>();
    private static final ConcurrentLinkedQueue<TicTacToeGame> ACTIVE_GAMES = new ConcurrentLinkedQueue<>();

    private TicTacToeServer server;
    private TicTacToeGame game;
    private Player player;

    public Controller(TicTacToeServer ticTacToeServer)
    {
        this.server = ticTacToeServer;
    }

    public void connectToGame(Session session, String playerName) throws Exception
    {
        this.player = new Player(playerName);
        if (PENDING_GAMES.isEmpty())
            startNewPendingGame(session);
        else
            joinPendingGame(session);
    }

    private void joinPendingGame(Session session) throws IOException, EncodeException
    {
        game = PENDING_GAMES.remove();
        ACTIVE_GAMES.add(game);
        game.addPlayer(this.player);
        game.getPlayersSessions().add(session);
        broadcastGameStartedMessage();
    }

    private void broadcastGameStartedMessage() throws IOException, EncodeException
    {
        server.sendMessage(new GameStartedMessage(game));
    }

    private void startNewPendingGame(Session session) throws IOException, EncodeException
    {
        game = new TicTacToeGame(this.player);
        PENDING_GAMES.add(game);
        game.getPlayersSessions().add(session);
        server.sendMessage(new GamePendingMessage("pending"));
    }




}
