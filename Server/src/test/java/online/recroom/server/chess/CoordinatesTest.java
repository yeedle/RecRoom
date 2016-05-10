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

    @Test (expected = IllegalCoordinateException.class)
    public void testThrowsIllegalCoordinateExceptionTooLowRow() throws Exception
    {
        Coordinates.byColumnAndRow(0, -1);
    }

    @Test (expected = IllegalCoordinateException.class)
    public void testThrowsIllegalCoordinateExceptionTooLowColumn() throws Exception
    {
        Coordinates.byColumnAndRow(-1, 1);
    }

    @Test (expected = IllegalCoordinateException.class)
    public void testThrowsIllegalCoordinateExceptionTooHighRow() throws Exception
    {
        Coordinates.byColumnAndRow(0, 8);
    }

    @Test (expected = IllegalCoordinateException.class)
    public void testThrowsIllegalCoordinateExceptionTooHighColumn() throws Exception
    {
        Coordinates.byColumnAndRow(8, 1);
    }

}