package online.recroom.server.bubbles;

import com.google.gson.Gson;
import online.recroom.messages.Message;
import online.recroom.messages.bubble.POJOs.MessageBubble;
import online.recroom.messages.bubble.POJOs.MessageBubblePlayer;
import online.recroom.messages.bubble.enums.BubbleMessages;
import online.recroom.messages.bubble.messages.*;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;



public class Controller {
    private static final int PLAYER_LIMIT = 6;
    private static final ConcurrentLinkedQueue<Game> PENDING_GAMES
            = new ConcurrentLinkedQueue<>();
    private static final PriorityBlockingQueue<Game> ACTIVE_GAMES
            = new PriorityBlockingQueue<>();

    private Gson gson = new Gson();

    private BubblesServer bubblesServer;
    private Game game;
    private BubblePlayer player;

    public Controller(BubblesServer bs) {
        this.bubblesServer = bs;
    }

    public void connectToGame(Session session, String playerName) throws Exception {
        this.player = new BubblePlayer(playerName);
        if (isThereActiveAnGameWithRoom()) {
            joinActiveGame(session);
        } else if (!PENDING_GAMES.isEmpty()) {
            joinPendingGame(session);
        } else {
            startNewGame(session);
        }
    }

    private void joinActiveGame(final Session session) throws Exception {
        game = getActiveGameThatHasRoom();
        game.addPlayer(this.player);
        game.getPlayersSessions().add(session);
//            send bubbles to player that joined the game
        bubblesServer.sendMessage(new Message(BubbleMessages.GAME_STARTED, gson.toJson(new GameStarted(getBubblesAsArray(), getPlayersAsArray(), true))));
//          Send message to all other players that a new player has joined
        broadcastPlayerJoinedMessage();
    }

    private void joinPendingGame(final Session session) throws IOException, EncodeException {
        game = PENDING_GAMES.remove();
        ACTIVE_GAMES.add(game);
        game.addPlayer(this.player);
        game.getPlayersSessions().add(session);
//            send bubbles to both players
        broadcastGameStartedMessage();
    }

    private void startNewGame(final Session session) throws IOException, EncodeException {
        game = new Game(this.player);
        PENDING_GAMES.add(game);
        game.getPlayersSessions().add(session);
        bubblesServer.sendMessage(new Message(BubbleMessages.GAME_PENDING, gson.toJson(new GamePending())));
    }

    private boolean isThereActiveAnGameWithRoom() {
//        using priority queue, which gives me smallest game
        return !ACTIVE_GAMES.isEmpty() && ACTIVE_GAMES.element().getAmountOfPlayers() < PLAYER_LIMIT;
    }

    private Game getActiveGameThatHasRoom() throws Exception {
        if (!isThereActiveAnGameWithRoom())
            throw new Exception();
        return ACTIVE_GAMES.peek();
    }

    public void popBubble(final long bubbleId) throws IOException, EncodeException {
//        TODO keep score and send message to all players, check if game is over
        if (game.isBubblePopped(bubbleId)) {

        } else {
            game.removeBubble(bubbleId);
            broadcastBubblePoppedMessage(bubbleId);
            player.incrementBubblesPopped();
            if (game.isOver()) {
                closeGame(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Game Over"));
            }
        }
    }

    public void endSession(final Session session) throws IOException, EncodeException {
        game.removePlayer(this.player);
        game.removeSession(session);
        broadcastPlayerLeft(this.player.name);
        if (game.getAmountOfPlayers() == 1) {
            closeGame(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "All players have left"));
        }
    }

    private void closeGame(CloseReason closeReason) throws IOException, EncodeException {
        broadcastGameOverMessage();
        ACTIVE_GAMES.remove(game);
        bubblesServer.closeSessions(game.getPlayersSessions(), closeReason);
        game.getPlayersSessions().clear();
    }

    private void broadcastGameStartedMessage() throws IOException, EncodeException {
        bubblesServer.broadcastMessage(new Message(BubbleMessages.GAME_STARTED,
                        gson.toJson(new GameStarted(getBubblesAsArray(), getPlayersAsArray(), false))),
                game.getPlayersSessions(), true);
    }

    private void broadcastPlayerJoinedMessage() throws IOException, EncodeException {
        PlayerJoined playerJoinedMessage =
                new PlayerJoined(new MessageBubblePlayer(this.player.name, this.player.getScore()));

        Message message = new Message(BubbleMessages.PLAYER_JOINED, gson.toJson(playerJoinedMessage));

        bubblesServer.broadcastMessage(message, game.getPlayersSessions(), false);
    }

    private void broadcastPlayerLeft(String playerName) throws IOException, EncodeException {
        MessageBubblePlayer player = new MessageBubblePlayer(this.player.name, this.player.getScore());
        Message message = new Message(BubbleMessages.PLAYER_LEFT, gson.toJson(player, PlayerLeft.class));
        bubblesServer.broadcastMessage(message, game.getPlayersSessions(), false);
    }

    private void broadcastBubblePoppedMessage(long id) throws IOException, EncodeException {
        BubblePoppedMessage bubblePoppedMessage = new BubblePoppedMessage(id);
        Message message = new Message(BubbleMessages.BUBBLE_POPPED, gson.toJson(bubblePoppedMessage, BubblePoppedMessage.class));
        bubblesServer.broadcastMessage(message, game.getPlayersSessions(), true);
    }

    private void broadcastGameOverMessage() throws IOException, EncodeException {
        BubblePlayer winner = game.getLeader();
        MessageBubblePlayer player = new MessageBubblePlayer(winner.name, winner.getScore());
        GameOver gameOver = new GameOver(player, winner.getScore());
        Message message = new Message(BubbleMessages.GAME_OVER, gson.toJson(gameOver, GameOver.class));
        bubblesServer.broadcastMessage(message, game.getPlayersSessions(), true);
    }

    private MessageBubble[] getBubblesAsArray() {
        Bubble[] bubbles =
                this.game.getBubbles().values().toArray(new Bubble[game.getBubbles().size()]);
        MessageBubble[] messageBubbles =
                new MessageBubble[bubbles.length];
        for (int i = 0; i < bubbles.length; i++) {
            messageBubbles[i] =
                    new MessageBubble(bubbles[i].id,
                            bubbles[i].relativeXPosition,
                            bubbles[i].relativeYPosition,
                            bubbles[i].deltaX,
                            bubbles[i].deltaY,
                            bubbles[i].relativeRadius);
        }
        return messageBubbles;
    }

    private MessageBubblePlayer[] getPlayersAsArray() {
        BubblePlayer[] players =
                this.game.getPlayers().toArray(new BubblePlayer[game.getPlayers().size()]);
        MessageBubblePlayer[] messagePlayers =
                new MessageBubblePlayer[players.length];
        for (int i = 0; i < players.length; i++) {
            messagePlayers[i] =
                    new MessageBubblePlayer(players[i].name, players[i].getScore());
        }
        return messagePlayers;
    }
}
