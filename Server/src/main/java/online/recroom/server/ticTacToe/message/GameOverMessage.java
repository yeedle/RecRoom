package online.recroom.server.ticTacToe.message;

/**
 * Created by Yehuda Globerman on 4/17/2016.
 */
public class GameOverMessage extends Message {
    private final boolean winner;

    public GameOverMessage(boolean winner) {
        super("gameOver");
        this.winner = winner;
    }

    public boolean isWinner() {
        return winner;
    }
}
