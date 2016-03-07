/**
 * Created by Josh on 3/5/2016.
 */
public class SeatSection {
    public Seat[][] myRows;
    public int myId;
    private int numSeatsAvailable;
    private int numSeatsHeld;

    public int getSeatsAvailable(){
        numSeatsAvailable = 0;
        numSeatsHeld = 0;
        for(Seat[] row: myRows){
            for(Seat seat: row){
                if(!seat.isLocked()){
                    numSeatsAvailable++;
                }
                else
                    numSeatsHeld++;
            }
        }
        return numSeatsAvailable;
    }

    public SeatSection(int sectionId, int numRows, int seatsPerRow){
        myId = sectionId;
        myRows = new Seat[numRows][seatsPerRow];
        numSeatsAvailable = numRows * seatsPerRow;
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < seatsPerRow; j++){
                myRows[i][j] = new Seat();
            }
        }
    }

    public boolean hasEnoughSeats(int requestSeatNum){
        return requestSeatNum < numSeatsAvailable;
    }

    public SeatHold holdSeats(int numSeats){
        int possibleSeatCount = 0;
        SeatHold hold = new SeatHold();
        if(numSeats < 0)
            return null;
        if(numSeats > numSeatsAvailable)
            return null;
        for(int row = 0; row < myRows.length; row++) {
            for(int seat = 0; seat < myRows[row].length; seat++) {
                if (!myRows[row][seat].isLocked()) {
                    possibleSeatCount++;
                }else {
                    possibleSeatCount = 0;
                }
                if (possibleSeatCount == numSeats) {
                    if (possibleSeatCount == 1) {
                        hold.addSeat(myId, row, seat);
                        myRows[row][seat].holdSeat(hold.getHoldId());
                        new Thread(myRows[row][seat]).start();
                        numSeatsHeld++;
                        numSeatsAvailable--;
                    } else {
                        for (int j = seat - (numSeats - 1); j <= seat; j++) {
                            hold.addSeat(myId, row, j);
                            myRows[row][j].holdSeat(hold.getHoldId());
                            new Thread(myRows[row][j]).start();
                            numSeatsHeld++;
                            numSeatsAvailable--;
                         }
                    }
                    return hold;
                }
            }
        }
        return hold;
    }

    public boolean reserveSeats(SeatHold reservation){
        int row = reservation.getRow();
        int startSeat = reservation.getStartSeat();
        int numOfSeats = reservation.getNumOfSeats();
        boolean ableToReserve = false;
        for(int i = startSeat; i < startSeat + numOfSeats; i++)
            ableToReserve = myRows[row][i].changeToReserve(reservation.getHoldId());
        return ableToReserve;
    }

}
