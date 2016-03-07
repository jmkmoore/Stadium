import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Josh on 3/5/2016.
 */
public class SeatHoldTest {

    SeatHold testHold;

    @Before
    public void setUp() throws Exception {
        testHold = new SeatHold();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddSeat() throws Exception {
        testHold.addSeat(0,0,0);
        testHold.addSeat(0,1,0);
        testHold.addSeat(0,1,1);
        assertEquals(3, testHold.getNumOfSeats());
    }

    @Test
    public void testGetHoldId() throws Exception {
        testHold.addSeat(0,0,0);
        assertEquals(0, testHold.getHoldId());
        testHold.addSeat(1,0,0);
        assertFalse(10000 == testHold.getHoldId());
        testHold.addSeat(0,0,1);
        assertFalse(1 == testHold.getHoldId());
    }

    @Test
    public void testGetSection() throws Exception{
        SeatHold sectionTest = new SeatHold();
        sectionTest.addSeat(4, 10, 3);
        assertEquals(4, sectionTest.getSection());
        sectionTest = new SeatHold();
        sectionTest.addSeat(40,10,3);
        assertEquals(40, sectionTest.getSection());
    }

    @Test
    public void testGetRow() throws Exception {
        SeatHold sectionTest = new SeatHold();
        sectionTest.addSeat(4, 10, 3);
        assertEquals(10, sectionTest.getRow());
        sectionTest = new SeatHold();
        sectionTest.addSeat(40,90,3);
        assertEquals(90, sectionTest.getRow());
    }

    @Test
    public void testGetStartSeat() throws Exception {
        SeatHold sectionTest = new SeatHold();
        sectionTest.addSeat(4, 10, 3);
        assertEquals(3, sectionTest.getStartSeat());
        sectionTest = new SeatHold();
        sectionTest.addSeat(40,10,3);
        assertEquals(3, sectionTest.getStartSeat());
    }
}
