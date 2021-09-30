

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * cell class to represent the cell  for the generating and solveing the maze
 *  x and y corodinate  property
 *  wall property for every cell
 *  visited or not   tracker
 *  wallList for the wall created or not.
 */
public class Cell {
    private int x,y;
    public boolean[] walls = {true, true, true, true};
    private boolean isVisited = false;
    private int id;
    private List<Wall> wallList = new ArrayList<>();
    private boolean firstVisit = false;
    private boolean anotherVisit = false;
    private boolean tempFlagForRandomMouse = false;
    private boolean isPath;
    private boolean rightPath;
    private boolean leftPath;

    /**
     * cell constructor
     * @param x
     * @param y
     */

    public Cell(int x, int y){
        this.x = x;
        this.y =y;
    }

    /**
     *
     * @return boolean of the visited or not
     */
    public boolean isVisited() {
        return isVisited;
    }

    /**
     *  to remove the walls
     * @param neighbor
     */

    public void removeWalls(Cell neighbor){
        int x1 = x- neighbor.x;
        int y1 = y-neighbor.y;
        if(x1==1){
            neighbor.walls[2]=false;
            walls[0]=false;
        }
        else if(x1==-1){
            walls[2]=false;
            neighbor.walls[0]=false;
        }
        if(y1==1){
            walls[3]=false;
            neighbor.walls[1]=false;
        }
        else if(y1==-1){
            walls[1]=false;
            neighbor.walls[3]=false;
        }

    }

    /**
     *  to create all the walls of the cells
     * @return group representing the wall of the cell
     */
    public Group allWalls(){
        Group g= new Group();
        int x1 = x* Main.cellDimension;
        int y1 = y* Main.cellDimension;


        if(firstVisit){
            Rectangle rect = new Rectangle(x1,y1,
                    Main.cellDimension, Main.cellDimension);
            rect.setFill(Color.GREEN);
            g.getChildren().add(rect);
        }



        if(anotherVisit){
            Rectangle rect = new Rectangle(x1,y1,
                    Main.cellDimension, Main.cellDimension);
            rect.setFill(Color.RED);
            g.getChildren().add(rect);
        }
        if(walls[0]){
            Line left = new Line(x1,y1+ Main.cellDimension,x1,y1);
            left.setStrokeWidth(5);
            g.getChildren().add(left);
        }
        if(walls[1]){
            Line bottom = new Line(x1+ Main.cellDimension,
                    y1+ Main.cellDimension,x1,
                    y1+ Main.cellDimension);
            bottom.setStrokeWidth(5);
            g.getChildren().add(bottom);

        }
        if(walls[2]){
            Line right = new Line(x1+ Main.cellDimension,y1,
                    x1+ Main.cellDimension,y1+ Main.cellDimension);
            right.setStrokeWidth(5);
            g.getChildren().add(right);
        }
        if(walls[3]){
            Line top = new Line(x1,y1,x1+ Main.cellDimension,y1);
            top.setStrokeWidth(5);
            g.getChildren().add(top);
        }

        return g;

    }

    /**
     * getNeighborPath method for the getting the path of the cell
     * @param board
     * @return
     */
    public Cell getNeighborPath(Board board){
        ArrayList<Cell> allPath = new ArrayList<>();
        if((y-1)>=0 && !walls[3] && !board.getBoard()[x][y-1].isFirstVisit()){
            allPath.add(board.getBoard()[x][y-1]);
        }
        if(x+1<board.getBoard().length && !walls[2] &&
                !board.getBoard()[x+1][y].isFirstVisit()){
            allPath.add(board.getBoard()[x+1][y]);
        }
        if(y+1<board.getBoard().length && !walls[1] &&
                !board.getBoard()[x][y+1].isFirstVisit()){
            allPath.add(board.getBoard()[x][y+1]);
        }
        if(x-1>=0 && !walls[0] && !board.getBoard()[x-1][y].isFirstVisit()){
            allPath.add(board.getBoard()[x-1][y]);
        }
        if(allPath.size() > 0){
            Random random = new Random();
            int rand = random.nextInt(allPath.size());
            return allPath.get(rand);
        }
        else{
            return null;
        }

    }


    /**
     * getter and setter method
     * for  using  the character property of the cell.
     */
    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public void addWallList(Cell cell) {
        wallList.add(new Wall(this, cell));
    }

    public List<Wall> getWallList() {
        return wallList;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setWalls(boolean[] walls){
        this.walls = walls;
    }

    public String toString(){
        return x + " " +y;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public boolean[] getWalls() {
        return walls;
    }

    public void setFirstVisit(boolean firstVisit) {
        this.firstVisit = firstVisit;
    }

    public void setTopWall(){
        walls[3] = true;

    }

    public void setTempFlagForRandomMouse(){
        tempFlagForRandomMouse = true;
    }

    public boolean isTempFlagForRandomMouse() {
        return tempFlagForRandomMouse;
    }

    public boolean isFirstVisit() {
        return firstVisit;
    }

    public boolean isAnotherVisit() {
        return anotherVisit;
    }

    public void setAnotherVisit(boolean anotherVisit) {
        this.anotherVisit = anotherVisit;
    }

    public boolean [] path ={false,false,false,false};
    public void setLeftPath(boolean initialState){
        this.leftPath=initialState;
    }

    public void directionRight(boolean initialState){
        this.rightPath=initialState;
    }
    //    public Group getG() {
//        return g;
//    }



    public void setPath(boolean path){
        isPath = path;
    }

    public void setPath(int dir){
        if(dir==0){ this.path[0]=true;
        } else if(dir==1){ this.path[1]=true;
        } else if(dir==2){ this.path[2]=true;
        } else if(dir==3){ this.path[3]=true; }
    }

    /**
     *  method to get the direction of the solver.
     * @return
     */
    public int getDirection(){
        if (this.path[0]){ return 0; }
        else if(this.path[1]){ return 1; }
        else if (this.path[2]){ return 2; }
        else if(this.path[3]){ return 3; }
        else return -1;
    }
    public void defaultPath(){
        this.path[0]=false;
        this.path[1]=false;
        this.path[2]=false;
        this.path[3]=false;
    }

    public void setRotation(){
        int dir=getDirection();
        path[dir]=false;
        if(dir<3){ path[dir+1]=true;
        }else { path[0]=true; }
    }

//    public boolean [] path ={false,false,false,false};
}
