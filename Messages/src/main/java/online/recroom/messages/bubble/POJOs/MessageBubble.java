package online.recroom.messages.bubble.POJOs;

/**
 * Created by Yehuda Globerman on 5/30/2016.
 */
public class MessageBubble {
    public final long id;
    public final double relativeXPosition;
    public final double relativeYPosition;
    public final double deltaX;
    public final double deltaY;
    public final double relativeRadius;


    public MessageBubble(long id, double relativeXPosition, double relativeYPosition, double deltaX, double deltaY, double relativeRadius) {
        this.id = id;
        this.relativeXPosition = relativeXPosition;
        this.relativeYPosition = relativeYPosition;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.relativeRadius = relativeRadius;
    }
}
