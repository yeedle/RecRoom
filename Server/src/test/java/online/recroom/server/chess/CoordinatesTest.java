package online.recroom.server.chess;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yeedle on 5/2/2016 3:32 PM.
 */
public class CoordinatesTest
{
    @Test
    public void byColumnAndRow() throws Exception
    {
        Coordinates c = Coordinates.byColumnAndRow(1, 5);
        assertEquals(1, c.column());
        assertEquals(5, c.row());
    }

}