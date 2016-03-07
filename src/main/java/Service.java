import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Josh on 3/5/2016.
 */
public class Service implements  TicketService{

    private SeatSection[] stadium;
    private HashMap<Integer, SeatHold> heldSeats;

    public Service(int numSections)
    {
        stadium = new SeatSection[numSections];
        heldSeats = new HashMap<Integer, SeatHold>();
    }

    public void setNumberOfSections(int numSections){
        stadium = new SeatSection[numSections];
    }

    public void buildSection(int section, int numRows, int numSeats){
        stadium[section] = new SeatSection(section, numRows, numSeats);
    }

    /**
     * The number of seats in the requested level that are neither held nor reserved
     *
     * @param venueLevel a numeric venue level identifier to limit the search
     * @return the number of tickets available on the provided level
     */
    public int numSeatsAvailable(Optional<Integer> venueLevel){
        int seatsAvailable = 0;
        if(venueLevel.isPresent()){
            seatsAvailable = stadium[venueLevel.get()].getSeatsAvailable();
        }else{
            for(SeatSection section: stadium){
                seatsAvailable += section.getSeatsAvailable();
            }
        }
        return seatsAvailable;
    }

    /**
     * Commit seats held for a specific customer
     *
     * @param seatHoldId the seat hold identifier
     * @param customerEmail the email address of the customer to which the seat hold
    is assigned
     * @return a reservation confirmation code
     */
    public String reserveSeats(int seatHoldId, String customerEmail){
        SeatHold held = heldSeats.get(seatHoldId);
        if(customerEmail.equals(held.email) && stadium[held.getSection()].reserveSeats(held)){
                return (customerEmail + seatHoldId).hashCode() + "";
        }else {
            return "unable to reserve seats";
        }
    }

    /**
     * Find and hold the best available seats for a customer
     *
     * @param numSeats the number of seats to find and hold
     * @param minLevel the minimum venue level
     * @param maxLevel the maximum venue level
     * @param customerEmail unique identifier for the customer
     * @return a SeatHold object identifying the specific seats and related
    information
     */
    public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel,
                                     Optional<Integer> maxLevel, String customerEmail){
        SeatHold newHold;
        int firstSection;
        int lastSection;

        if(minLevel.isPresent())
            firstSection = minLevel.get();
        else
            firstSection = 0;

        if (maxLevel.isPresent())
            lastSection = maxLevel.get();
        else
            lastSection = stadium.length;

        for(int i = firstSection; i <= lastSection; i++) {
            if (stadium[i].hasEnoughSeats(numSeats)) {
                newHold = stadium[i].holdSeats(numSeats);
                if (newHold != null) {
                    newHold.setEmail(customerEmail);
                    heldSeats.put(newHold.getHoldId(), newHold);
                    return newHold;
                }
            }
        }
        return null;
    }
}
