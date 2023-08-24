package clubSimulation;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  Andre the Barman. Andre serves drinks to
    patrons at the bar, patrons must not leave the bar before being served. Implement
    Andre as a separate thread.
    @author Fhatuwani Mokwenda
 */

public class Barman extends Thread{
    
    private ClubGrid barGrid;
    private int pos_x;
    private int pos_y;
    private boolean serving = true;

    public Barman (ClubGrid barGrid) {
        this.barGrid = barGrid;
        pos_x = 1;
        pos_y = barGrid.getBar_y();
    }

    public void stopServing() {
        serving = false;
    }

    public void moveAndre() {
        if (pos_x == barGrid.getMaxX() -1) {
            pos_x = 1; // move to the left
        }
        else {
            pos_x = barGrid.getMaxX() -1; // move to the right right side
        }
    }

    public int getPosX() {
        return pos_x;
    }

    public int getPosY() {
        return pos_y;
    }

    //private void serveDrink(GridBlock barBlock) throws InterruptedException {
       // barBlock.
    //}

    // public void run() {
    //     while (serving) {
    //         try{
    //             moveAndre();

    //             GridBlock barBlock = barGrid.whichBlock(pos_x, pos_y) ;
    //             if (barBlock.occupied()) {
                    
    //             }
    //         }

    //     }
    // }

}
