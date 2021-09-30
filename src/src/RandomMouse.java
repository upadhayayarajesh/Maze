

import javafx.animation.AnimationTimer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RandomMouse class for maze solving  using random Mouse algorithm
 */
public class RandomMouse {
    private Board board;
    public static Cell currentCellRandomMouse;
    private Cell endingCell;
    private Cell anotherMouse;

    /**
     * constructor for the randomMouse generation.
     * @param board
     */
    public RandomMouse(Board board){
        this.board = board;
        currentCellRandomMouse = Board.startingCell;
        endingCell = Board.endCell;
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long start = -1;
                if(now-start > 100_000_000){
                    if(currentCellRandomMouse !=endingCell){
                        solve();
                        board.repaintBoard();
                    }
                    else {
                        this.stop();
                    }
                }
            }
        };at.start();
    }

    /**
     *  this method  to return the path solving the maze.
     * @param cell
     * @return
     */
    public int returnPath(Cell cell){
        int rand =0;
        List<Integer> list = new ArrayList<>();
        boolean[] walls = cell.getWalls();
        for(int i=0; i<4;i++){
            if(!walls[i]){
                list.add(i);
            }
        }

        if(list.size()>0){
            Random random = new Random();
            rand = random.nextInt(list.size());
        }
        return list.get(rand);

    }
    public void solve(){
        int counter = returnPath(currentCellRandomMouse);
        if (counter == 0) {
            anotherMouse = board.getBoard()
                    [currentCellRandomMouse.getX() - 1]
                    [currentCellRandomMouse.getY()];
            anotherMouse.setPath(true);
            currentCellRandomMouse = anotherMouse;
        }
        else if (counter == 1) {
            anotherMouse = board.getBoard()
                    [currentCellRandomMouse.getX()]
                    [currentCellRandomMouse.getY()+1];
            anotherMouse.setPath(true);
            currentCellRandomMouse = anotherMouse;
        }
        else if (counter == 2) {
            anotherMouse = board.getBoard()
                    [currentCellRandomMouse.getX()+1]
                    [currentCellRandomMouse.getY()];
            anotherMouse.setPath(true);
            currentCellRandomMouse = anotherMouse;
        }
        else if (counter == 3) {
            anotherMouse = board.getBoard()
                    [currentCellRandomMouse.getX()]
                    [currentCellRandomMouse.getY()-1];
            anotherMouse.setPath(true);
            currentCellRandomMouse = anotherMouse;
        }

    }

}
