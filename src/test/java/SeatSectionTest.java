import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Josh on 3/5/2016.
 */
public class SeatSectionTest {

    SeatSection sectA;

    @Test
    public void testHasEnoughSeats() throws Exception {
        assertTrue(sectA.hasEnoughSeats(25));
        assertFalse(sectA.hasEnoughSeats(100));
    }

    @Test
    public void testGetSeatsAvailable() throws Exception{
        assertEquals(100, sectA.getSeatsAvailable());
    }

    @Test
    public void testHoldSeats() throws Exception {
        System.out.println("Running seat lifetime and expiration tests");
        SeatHold seat1 = new SeatHold();
        seat1 = sectA.holdSeats(5);
        assertEquals(0, seat1.getHoldId());
        SeatHold seat2 = new SeatHold();
        seat2 = sectA.holdSeats(5);
        assertEquals(5, seat2.getHoldId());
        sectA.reserveSeats(seat1);
        TimeUnit.SECONDS.sleep(7);
        SeatHold seat3 = new SeatHold();
        seat3 = sectA.holdSeats(4);
        assertEquals(10, seat3.getHoldId());
        TimeUnit.SECONDS.sleep(4);
        SeatHold seat4 = sectA.holdSeats(1);
        assertEquals(5, seat4.getHoldId());
        SeatHold seat5 = sectA.holdSeats(6);
        assertEquals(14, seat5.getHoldId());
    }

    @Test
    public void testReserveSeats() throws Exception {
        SeatHold seat1 = new SeatHold();
        assertFalse(sectA.reserveSeats(seat1));
        seat1 = sectA.holdSeats(1);
        assertTrue(sectA.reserveSeats(seat1));
    }

    @Before
    public void setUp() throws Exception {
        sectA = new SeatSection(0,4,25);
    }

    @After
    public void tearDown() throws Exception {

    }
}
