import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 3/5/2016.
 */


/**
 * An object that holds the
 */
public class SeatHold {
    public List<Integer> mySeats;
    public String email;

    public SeatHold(){
        mySeats = new ArrayList<Integer>();
    }


    //Assuming that seats rows and sections aren't more than 100
    public void addSeat(int sectionId, int rowId, int seatId){
        int holdId = 10000 * (sectionId) + 100 * (rowId) + (seatId);
        mySeats.add(holdId);
    }

    public int getHoldId(){
        if(mySeats.isEmpty())
            return -1;
        return mySeats.get(0);
    }

    public int getNumOfSeats(){
        return mySeats.size();
    }

    public int getSection(){
        return mySeats.get(0) / 10000;
    }

    public int getRow(){
        return mySeats.get(0) % 10000 / 100;
    }

    public int getStartSeat(){
        return mySeats.get(0) % 10000 % 100;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
