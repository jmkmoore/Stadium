import java.util.concurrent.TimeUnit;

/**
 * Created by Josh on 3/7/2016.
 */
public class Seat implements Runnable {

    private boolean heldLock = false;
    private boolean reserveLock = false;
    private int seatHoldID = -1;
    private final int lockTiming = 10;

    public Seat(){
        heldLock = false;
        reserveLock = false;
        seatHoldID = -1;
    }

    public void run() {
        if(heldLock && !reserveLock)
            try {
                TimeUnit.SECONDS.sleep(lockTiming);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        if(!reserveLock) {
            heldLock = false;
            seatHoldID = -1;
        }
    }

    public void holdSeat(int seatHoldId){
        this.seatHoldID = seatHoldId;
        heldLock = true;
    }

    public boolean isLocked(){
        return heldLock || reserveLock;
    }

    public boolean changeToReserve(int seatHoldID){
        if(heldLock && this.seatHoldID == seatHoldID)
        {
            reserveLock = true;
        }
        return reserveLock;
    }




}
