

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * KrusKalGen  method for the  generation of the maze through KrusKal method.
 */
public class KruskalGen {

    private Board board;
    private List<Wall> allEdges;
    private DisjointSetDataStruct disjointSetDataStruct;
    List<Cell> allCells = new ArrayList<>();
    private int counter =0;

    public int getCounter() {
        return counter;
    }

    public int getEdgeSize(){
        return allEdges.size();
    }

    /**
     * constructor for the Kruskal generation
     * @param board
     */
    public KruskalGen(Board board){
        this.board = board;
        allEdges = board.getAllEdges();
        disjointSetDataStruct = new DisjointSetDataStruct();

        int counter=0;
        for(int i = 0; i< Main.boardSize; i++){
            for(int j = 0; j< Main.boardSize; j++){
                board.getBoard()[i][j].setId(counter);
                disjointSetDataStruct.create_set
                        (board.getBoard()[i][j].getId());
                allCells.add(board.getBoard()[i][j]);
                counter++;
            }
        }
        Collections.shuffle(allEdges);

    }

    /**
     *  KrusKalMaze generaion  method  using the obard ,ceel
     *  and DisjointSetDataStruct class.
     */
    public void KruskalMaze(){

        if(counter < allEdges.size()){
            Wall current = allEdges.get(counter);
            if(disjointSetDataStruct.find_set(current.getCell1().getId()) !=
                    disjointSetDataStruct.find_set(current.getCell2().getId())){
                current.getCell1().removeWalls(current.getCell2());
                disjointSetDataStruct.union(current.getCell1().getId(),
                        current.getCell2().getId());
            }
        }
        counter++;




    }




}
