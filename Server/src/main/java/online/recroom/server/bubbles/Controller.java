package online.recroom.server.bubbles;

import online.recroom.messages.Message;

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
//            send bubbles to madeBy that joined the game
        bubblesServer.sendMessage(Message.joinedGame(getBubblesAsArray(), getPlayersAsArray()));

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
        bubblesServer.sendMessage(Message.gamePending());
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
        bubblesServer.broadcastMessage(Message.gameStarted(getBubblesAsArray(), getPlayersAsArray()),
                game.getPlayersSessions(), true);
    }

    private void broadcastPlayerJoinedMessage() throws IOException, EncodeException {
        bubblesServer.broadcastMessage(Message.playerJoined(this.player.name),
                game.getPlayersSessions(), false);
    }

    private void broadcastPlayerLeft(String playerName) throws IOException, EncodeException {
        bubblesServer.broadcastMessage(Message.playerLeft(playerName), game.getPlayersSessions(), false);
    }

    private void broadcastBubblePoppedMessage(long id) throws IOException, EncodeException {
        bubblesServer.broadcastMessage(Message.bubblePopped(id), game.getPlayersSessions(), true);
    }

    private void broadcastGameOverMessage() throws IOException, EncodeException {
        BubblePlayer winner = game.getLeader();
        bubblesServer.broadcastMessage(Message.gameOver(winner.name, winner.getScore()),
                game.getPlayersSessions(), true);
    }

    private online.recroom.messages.bubble.POJOs.Bubble[] getBubblesAsArray() {
        Bubble[] bubbles =
                this.game.getBubbles().values().toArray(new Bubble[game.getBubbles().size()]);
        online.recroom.messages.bubble.POJOs.Bubble[] messageBubbles =
                new online.recroom.messages.bubble.POJOs.Bubble[bubbles.length];
        for (int i = 0; i < bubbles.length; i++) {
            messageBubbles[i] =
                    new online.recroom.messages.bubble.POJOs.Bubble(bubbles[i].id,
                            bubbles[i].relativeXPosition,
                            bubbles[i].relativeYPosition,
                            bubbles[i].deltaX,
                            bubbles[i].deltaY,
                            bubbles[i].relativeRadius);
        }
        return messageBubbles;
    }

    private online.recroom.messages.bubble.POJOs.BubblePlayer[] getPlayersAsArray() {
        BubblePlayer[] players = this.game.getPlayers().toArray(new BubblePlayer[game.getPlayers().size()]);
        online.recroom.messages.bubble.POJOs.BubblePlayer[] messagePlayers =
                new online.recroom.messages.bubble.POJOs.BubblePlayer[players.length];
        for (int i = 0; i < players.length; i++) {
            messagePlayers[i] =
                    new online.recroom.messages.bubble.POJOs.BubblePlayer(players[i].name, players[i].getScore());
        }
        return messagePlayers;
    }
}
