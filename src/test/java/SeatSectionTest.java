import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

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

    }

    @Test
    public void testReserveSeats() throws Exception {

    }

    @Before
    public void setUp() throws Exception {
        sectA = new SeatSection(0,4,25);
    }

    @After
    public void tearDown() throws Exception {

    }
}
