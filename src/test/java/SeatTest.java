import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Josh on 3/7/2016.
 */
public class SeatTest {

   @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHoldSeat() throws Exception {
        Seat seat = new Seat();
        new Thread(seat).start();
        assertFalse(seat.isLocked());
        seat.holdSeat(10101);
        assertTrue(seat.isLocked());
    }

    @Test
    public void testChangeToReserve() throws Exception {
        System.out.println("Running seat lifetime tests");
        Seat reserveSeat = new Seat();
        new Thread(reserveSeat).start();
        reserveSeat.changeToReserve(10101);
        assertFalse(reserveSeat.isLocked());
        reserveSeat.holdSeat(10101);
        assertTrue(reserveSeat.isLocked());
        reserveSeat.changeToReserve(10101);
        reserveSeat.isLocked();
        TimeUnit.SECONDS.sleep(15);
        assertTrue(reserveSeat.isLocked());
    }

    @Test
    public void testIsLocked() throws Exception {
        System.out.println("Running lock duration tests");
        Seat freshSeat = new Seat();
        new Thread(freshSeat).start();
        freshSeat.holdSeat(10101);
        assertTrue(freshSeat.isLocked());
        TimeUnit.SECONDS.sleep(12);
        assertFalse(freshSeat.isLocked());
        freshSeat.holdSeat(10101);
        freshSeat.changeToReserve(10101);
        assertTrue(freshSeat.isLocked());
        TimeUnit.SECONDS.sleep(15);
        assertTrue(freshSeat.isLocked());
    }


}