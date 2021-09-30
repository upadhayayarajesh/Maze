

import java.util.List;
import java.util.Stack;

/**
 * DFSGenerator method  to  generator for generating
 * the  maze for the first depth search.
 */
public class DFSGenerator {

    private Stack<Cell> stack = new Stack<>();
    private Board board;
    public static Cell currentCell;
    private Cell neighborCell;
    private List<Cell> currentCells;

    /**
     * DFGenerator for the maze generation
     * @param board
     */

    public DFSGenerator(Board board){
        this.board=board;
        currentCell = Board.startingCell;
        //currentCell =board.getBoard()[0][0];
        currentCell.setVisited(true);
        stack.push(currentCell);
    }


    /**
     * DFSMaze method main logic for generation using stack
     */
    public void DFSMaze(){
        if(!stack.empty()){
            currentCell=stack.pop();

            //stack.pop();
        }
        currentCell.setVisited(true);
        stack.push(currentCell);
        neighborCell = board.neighbourCell(currentCell.getX(), currentCell.getY());

        if(neighborCell != null){
            if(!neighborCell.isVisited()){
                currentCell.removeWalls(neighborCell);
                neighborCell.setVisited(true);
                stack.push(neighborCell);
            }
        }
        else stack.pop();
        //currentCell = neighborCell;




    }


    public Stack<Cell> getStack() {
        return stack;
    }

    public List<Cell> getCurrentCells() {
        return currentCells;
    }
}
