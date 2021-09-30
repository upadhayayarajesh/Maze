

import javafx.animation.AnimationTimer;
import java.io.FileNotFoundException;
import java.util.Stack;

/**
 * this class is  for the solving the maze using TreMaux algorithm
 */
public class Tremaux {
    private Board board;
    private Stack<Cell> path=new Stack<>();
    private Cell current;
    private Cell nextCell, endCell;
    private int count;
    public Tremaux(Board board){
        this.board=board;
        current = Board.startingCell;
        //current=board.getBoard()[0][0];
        path.push(current);
        endCell = Board.endCell;
        AnimationTimer timer = new AnimationTimer() {
            private long startTime = -1;
            @Override
            public void handle(long now) {
                if ((now - startTime) >=1_000_000) {
                    if(current!=endCell){
                        solve();
                        board.repaintBoard();
                    }
                    else {
                        this.stop();
                    }
                    startTime = now;
                }
            }
        };
        timer.start();
    }
    public void solve(){
        count++;
        if(!path.empty()) current=path.pop();
        nextCell = current.getNeighborPath(board);
        current.setFirstVisit(true);
        path.push(current);
        if(nextCell!=null){
            path.push(nextCell);
            current=nextCell;
        }
        else {
            Cell temp =path.pop();
            temp.setAnotherVisit(true);
        }
    }

}
