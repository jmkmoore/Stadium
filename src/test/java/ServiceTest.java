import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Josh on 3/6/2016.
 */
public class ServiceTest {

    public Service stadium;
    public Optional<Integer> testedSection;

    @Test
    public void testSetNumberOfSections() throws Exception {

    }

    @Test
    public void testBuildSection() throws Exception {
        stadium.buildSection(0, 4, 25);
    }

    @Test
    public void testNumSeatsAvailable() throws Exception {
        //Test entire stadium count with empty
        testedSection = Optional.empty();
        assertEquals(5100, stadium.numSeatsAvailable(testedSection));
        testedSection = Optional.of(0);
        assertEquals(100, stadium.numSeatsAvailable(testedSection));
        testedSection = Optional.of(1);
        assertEquals(2000, stadium.numSeatsAvailable(testedSection));
        Optional<Integer> minLevel = Optional.ofNullable(0);
        Optional<Integer> maxLevel = Optional.ofNullable(3);
        SeatHold testSeat = stadium.findAndHoldSeats(1, minLevel, maxLevel, "jmkmoore@gmail.com");
        assertEquals(99, stadium.numSeatsAvailable(Optional.of(0)));

        testSeat = stadium.findAndHoldSeats(10, Optional.of(2),Optional.of( 6), "jmkmoore@gmail.com");
        assertEquals(1490, stadium.numSeatsAvailable(Optional.of(2)));
        TimeUnit.SECONDS.sleep(15);
        assertEquals(1500, stadium.numSeatsAvailable(Optional.of(2)));
    }

    @Test
    public void testReserveSeats() throws Exception {
        Service freshStadium = new Service(5);
        freshStadium.buildSection(0, 3, 15);
        freshStadium.buildSection(1, 2, 10);
        freshStadium.buildSection(2, 5, 20);
        freshStadium.buildSection(3, 10, 20);
        freshStadium.buildSection(4, 25, 20);
        SeatHold seat = freshStadium.findAndHoldSeats(5, Optional.of(0), Optional.of(3), "jmkmoore@gmail.com");

        String confirmCode = freshStadium.reserveSeats(0, "jmkmoore@gmail.com");
        assertEquals(("jmkmoore@gmail.com"+0).hashCode() +"", confirmCode);

        String badReserve = freshStadium.reserveSeats(0, "fake");
        assertFalse(("fake".hashCode()+"").equals(badReserve));
    }

    @Test
    public void testFindAndHoldSeats() throws Exception {
        Optional<Integer> minLevel = Optional.ofNullable(0);
        Optional<Integer> maxLevel = Optional.ofNullable(3);
        SeatHold testSeat = stadium.findAndHoldSeats(1, minLevel, maxLevel, "jmkmoore@gmail.com");
        assertEquals(0, testSeat.getHoldId());
        assertEquals("jmkmoore@gmail.com", testSeat.email);
    }

    @Before
    public void setUp() throws Exception {
        stadium = new Service(4);
        stadium.buildSection(0, 4, 25);
        stadium.buildSection(1, 20, 100);
        stadium.buildSection(2, 15, 100);
        stadium.buildSection(3, 15, 100);
    }

    @After
    public void tearDown() throws Exception {

    }

}