package online.recroom.client.ticTacToe;



import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class TicTacToeGame
{
    UUID uuid = UUID.randomUUID();
    private final long id;
    private final String player1;
    private final String player2;
    private Player nextMove = Player.random();
    private Player[][] grid = new Player[3][3];
    private boolean over;
    private boolean draw;
    private Player winner;

    private TicTacToeGame(long id, String player1, String player2) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;

    }


    public enum Player {
        PLAYER1, PLAYER2;

        private static final Random random = new Random();

        private static Player random() {
            return Player.random.nextBoolean() ? PLAYER1 : PLAYER2;
        }
    }
}
