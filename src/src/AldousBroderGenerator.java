
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class is for the generation of the maze from aldous method
 *
 */
public class AldousBroderGenerator {

    private Board board;
    public static Cell currentCell;
    private Random random;
    private Cell randomNeighbor;
    private List<Cell> visitedCells = new ArrayList<>();
    boolean stop = false;
    private int x;
    private int y;

    /**
     * this is the constructor class of maze generation by aldous method
     * @param board
     */
    public AldousBroderGenerator(Board board){
        random = new Random();
        this.board = board;
        currentCell = board.getBoard()
                [random.nextInt(board.getBoard().length-1)]
                [random.nextInt(board.getBoard().length-1)];
        visitedCells.add(currentCell);

    }

    /**
     *This method is to generate the maze.
     */

    public void aldousMaze(){
        randomNeighbor =
                board.neighbourCellCopy(currentCell.getX(), currentCell.getY());
        if(!visitedCells.contains(randomNeighbor)){
            currentCell.removeWalls(randomNeighbor);
            visitedCells.add(randomNeighbor);
        }
        currentCell = randomNeighbor;
    }

    /**
     * gets the visited cell list size
     * @return the integer value of visited cells list
     */
    public int getVisitedCellSize(){
        return visitedCells.size();
    }

    public Cell getCurrentCell(){
        return currentCell;
    }


}
