package online.recroom.client.chess.pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Yeedle on 5/23/2016 12:45 PM.
 */
public class Piece extends ImageView
{
    private Player color;
    private PieceType pieceType;

    public Piece(Player color, PieceType pieceType)
    {
        this.color = color;
        this.pieceType = pieceType;
        Image image = new Image(getClass().getResourceAsStream(color.toString()+"/"+pieceType.toString()+".png"));
        super.setImage(image);
        super.setPreserveRatio(true);
        super.setFitWidth(50);
        super.setFitHeight(50);
    }

}

