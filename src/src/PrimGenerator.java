

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *   another method for the generation of the maze for the prim algorithm.
 */
public class PrimGenerator {

    private Board board;
    private List<Wall> walls;
    private Cell current;
    private Random random;

    /**
     *  constructor for generation of the maze using prim algorithm.
     * @param board
     */
    public PrimGenerator(Board board){
        this.board = board;
        board.getAllEdges();
        current = board.getBoard()[0][0];
        current.setVisited(true);
        walls = current.getWallList();
        this.random = new Random();
        Collections.shuffle(walls);
    }

    /**
     * prim maze generation from this method.
     */
    public void primMaze(){
        if(!walls.isEmpty()){
            int rand = random.nextInt(walls.size());
            if(!walls.get(rand).getCell1().isVisited() ||
                    !walls.get(rand).getCell2().isVisited()){
                System.out.println("check");
                walls.get(rand).getCell1().
                        removeWalls(walls.get(rand).getCell2());
                if(!walls.get(rand).getCell1().isVisited()){
                    walls.get(rand).getCell1().setVisited(true);
                    current = walls.get(rand).getCell1();
                    walls.addAll(current.getWallList());
                }
                else if(!walls.get(rand).getCell2().isVisited()){
                    walls.get(rand).getCell2().setVisited(true);
                    current = walls.get(rand).getCell2();
                    walls.addAll(current.getWallList());
                }
            }
            walls.remove(rand);

        }
    }

}
