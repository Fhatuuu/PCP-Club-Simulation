package clubSimulation;

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
        pos_y = barGrid.getBar_y() + 1; // Andre is behind the bar area
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

    private void serveDrink(GridBlock barBlock) throws InterruptedException {
        barBlock.get(-1); // Acquire the lock for the bar block
        try {
            if (barBlock.occupied() && barBlock.isBar()) {
                System.out.println("Andre the Barman serves drink to patron at position: " + barBlock.getX() + " " + barBlock.getY());
                Thread.sleep(10);
            }
        } finally {
            barBlock.release(); // Release the lock for the bar block
        }
    }

    public void run() {

        while (serving) {
            try{
                GridBlock block = barGrid.whichBlock(pos_y, pos_x) ;
                if (block.get(-1)) {
                    serveDrink(block); 
                    block.release();
                }
                moveAndre();
                Thread.sleep(5);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

               
        }

    }
}


