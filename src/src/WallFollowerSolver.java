

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
public class WallFollowerSolver {
    // board array
    Cell[][] boardArray;
    //baord variable
    Board board;
    // to check the current cell from beginging.
    private Cell cell;
    // to check the current cell from ending
    private Cell cell1;
    // checking the nighbour of the begining cell.
    Cell neighbour;
    // checking the neighbour for the ending cell.
    Cell neighbour1;
    // animation timers for showing the path.
    AnimationTimer animationTimer;
    AnimationTimer animationTimer1;
    // wallFollower constructor.
    public WallFollowerSolver(Board board) {
        this.board = board;
        this.boardArray = board.getBoard();
        timerLogic();
    }
    // method for the animation timer logic.
    public   void timerLogic(){
        //starting  cell and ending cell.
        Cell begin = Board.startingCell;
        //Main.Main.Cell begin = this.boardArray[0][0];
        Cell end = Board.endCell;
        // if statement to  check for the starting of the path.
        if(!begin.walls[2]) { begin.setPath(2); }
        else  { begin.setPath(1); }
        // if- statement to check for the ending path.
        if(!end.walls[3]) { end.setPath(3); }else end.setPath(0);
        cell = begin;
        cell1 =end;
        // aniamtion timer for the path  of the begining  cell path.
        animationTimer =new AnimationTimer() {
            private long startTime = -1;
            @Override
            public void handle(long now) {
                if ((now - startTime) >=100_000_000) {
                    if(neighbour !=null && neighbour1 !=null &&
                            neighbour == neighbour1){ animationTimer1.stop(); }
                    if(cell !=end){ startSolve(Color.INDIGO);
                    }else {
                        animationTimer.stop();
                    }
                }
            }
        };

        // amimation timer for the endidng  path.
        animationTimer1 =new AnimationTimer() {
            private long startTime = -1;
            @Override
            public void handle(long now) {
                if ((now - startTime) >=100_000_000) {
                    if(cell1 !=begin){
                        endSolve(Color.GREEN);
                    }else {
                        animationTimer1.stop();
                    }
                }
            }
        };
        animationTimer.start();
        animationTimer1.start();

    }
    // method for the solving the maze form beginging and all
    // the if-statement  for checking the path of the cell.
    public void startSolve( Color color){
        // statring and ending corodinate of the begining cell.
        int stX= cell.getX();
        int stY= cell.getY();
        int endX= cell.getX()* Main.cellDimension;
        int endY= cell.getY()* Main.cellDimension;
        // checking the direction of the cell.
        if(cell.getDirection()==2){
            // checkiing for the  wall is true or not.
            if(!cell.walls[1]){
                board.pathShow(endX+6,endY+ Main.cellDimension-7,
                        endX+6,endY+ Main.cellDimension+6,color);
                cell.defaultPath();
                neighbour = boardArray[stX][stY+1];
                neighbour.setPath(1);
                neighbour.setPath(true);
                neighbour.directionRight(true);
                cell = neighbour;
            }
            else if(!cell.walls[2]) {
                board.pathShow(endX+6,endY+ Main.cellDimension-7,
                        endX+ Main.cellDimension+2,
                        endY+ Main.cellDimension-7,color);
                cell.defaultPath();
                neighbour = boardArray[stX+1][stY];
                neighbour.setPath(2);
                neighbour.setPath(true);
                neighbour.directionRight(true);
                cell = neighbour;
            }
            else {
                board.pathShow(endX+6,
                        endY+ Main.cellDimension-7,
                        endX+ Main.cellDimension-7,
                        endY+ Main.cellDimension-7,color);
                cell.setRotation();
            }
        }
        // checking the condition  for the positon if it is going
        // in other direction.
        else if(cell.getDirection()==1){
            // checking to see if the wall is true of not.
            if(!cell.walls[0]){
                board.pathShow(endX+6,endY+6,endX-6,
                        endY+6,color);
                cell.defaultPath();
                neighbour = boardArray[stX-1][stY];
                neighbour.setPath(0);
                neighbour.setPath(true);
                neighbour.directionRight(true);

                cell = neighbour;
            }
            else if(!cell.walls[1]) {
                board.pathShow(endX+6,endY+6,
                        endX+6,endY+ Main.cellDimension+2,color);
                cell.defaultPath();
                neighbour = boardArray[stX][stY+1];
                neighbour.setPath(1);
                neighbour.setPath(true);
                neighbour.directionRight(true);
                cell = neighbour;
            }
            else {
                board.pathShow(endX+6,endY+6,
                        endX+6,endY+ Main.cellDimension-7,color);
                cell.setRotation();

            }
        }
        // cheecking for thhe
        else if(cell.getDirection()==3){
            if(!cell.walls[2]){
                board.pathShow(endX+ Main.cellDimension-7,
                        endY+ Main.cellDimension-7,
                        endX+ Main.cellDimension+3,
                        endY+ Main.cellDimension-7,color);
                cell.defaultPath();
                neighbour = boardArray[stX+1][stY];
                neighbour.setPath(2);
                neighbour.setPath(true);
                neighbour.directionRight(true);


                cell = neighbour;
            }
            else if(!cell.walls[3]) {
                board.pathShow(endX+ Main.cellDimension-7,
                        endY+ Main.cellDimension-7,
                        endX+ Main.cellDimension-7,endY-6,color);
                cell.defaultPath();
                neighbour = boardArray[stX][stY-1];
                neighbour.setPath(3);
                neighbour.setPath(true);
                neighbour.directionRight(true);


                cell = neighbour;
            }
            else {
                board.pathShow(endX+ Main.cellDimension-7,
                        endY+ Main.cellDimension-7,
                        endX+ Main.cellDimension-7,endY+6,color);
                cell.setRotation();

            }
        }else if(cell.getDirection()==0){
            if(!cell.walls[3]){
                board.pathShow(endX+ Main.cellDimension-7,
                        endY-5,endX+ Main.cellDimension-7,
                        endY+6,color);
                cell.defaultPath();
                neighbour = boardArray[stX][stY-1];
                neighbour.setPath(3);
                neighbour.setPath(true);
                neighbour.directionRight(true);
                cell = neighbour;
            }
            else if(!cell.walls[0]) {
                board.pathShow(endX+ Main.cellDimension-7,
                        endY+6,endX-3,endY+6,color);
                cell.defaultPath();
                neighbour = boardArray[stX-1][stY];
                neighbour.setPath(0);
                neighbour.setPath(true);
                neighbour.directionRight(true);

                cell = neighbour;
            }
            else {
                board.pathShow(endX+ Main.cellDimension-7,
                        endY+6,endX+6,endY+6,color);
                cell.setRotation();

            }
        }
    }
    // method to solve the maze form the end.  it is similar to the
    // start Sove method
    // all the if-statement for checking the direciton fof the
    // neighbourhood cell.
    public void endSolve( Color color){
        int cellX= cell1.getX();
        int cellY= cell1.getY();
        int cell1X= cell1.getX()* Main.cellDimension;
        int cell1Y= cell1.getY()* Main.cellDimension;
        if(cell1.getDirection()==2){
            if(!cell1.walls[1]){
                board.pathShow(cell1X+6,cell1Y+ Main.cellDimension-7,
                        cell1X+6,cell1Y+ Main.cellDimension+6,color);
                cell1.defaultPath();
                neighbour1 = boardArray[cellX][cellY+1];
                neighbour1.setPath(1);
                neighbour1.setPath(true);
                neighbour1.setLeftPath(true);
                cell1 = neighbour1;
            }
            else if(!cell1.walls[2]) {
                board.pathShow(cell1X+6,cell1Y+ Main.cellDimension-7,
                        cell1X+ Main.cellDimension+2,
                        cell1Y+ Main.cellDimension-7,color);
                cell1.defaultPath();
                neighbour1 = boardArray[cellX+1][cellY];
                neighbour1.setPath(2);
                neighbour1.setPath(true);
                neighbour1.setLeftPath(true);
                cell1 = neighbour1;
            }
            else {
                board.pathShow(cell1X+6,cell1Y+ Main.cellDimension-7,
                        cell1X+ Main.cellDimension-7,
                        cell1Y+ Main.cellDimension-7,color);
                cell1.setRotation();
            }
        }
        else if(cell1.getDirection()==1){
            if(!cell1.walls[0]){
                board.pathShow(cell1X+6,cell1Y+6,cell1X-6,
                        cell1Y+6,color);
                cell1.defaultPath();
                neighbour1 = boardArray[cellX-1][cellY];
                neighbour1.setPath(0);
                neighbour1.setPath(true);
                neighbour1.setLeftPath(true);
                cell1 = neighbour1;
            }
            else if(!cell1.walls[1]) {
                board.pathShow(cell1X+6,cell1Y+6,cell1X+6,
                        cell1Y+ Main.cellDimension+2,color);
                cell1.defaultPath();
                neighbour1 = boardArray[cellX][cellY+1];
                neighbour1.setPath(1);
                neighbour1.setPath(true);
                neighbour1.setLeftPath(true);
                cell1 = neighbour1;
            }
            else {
                board.pathShow(cell1X+6,cell1Y+6,
                        cell1X+6,cell1Y+ Main.cellDimension-7,color);
                cell1.setRotation();
            }
        }
        else if(cell1.getDirection()==3){
            if(!cell1.walls[2]){
                board.pathShow(cell1X+ Main.cellDimension-7,
                        cell1Y+ Main.cellDimension-7,
                        cell1X+ Main.cellDimension+3,
                        cell1Y+ Main.cellDimension-7,color);
                cell1.defaultPath();
                neighbour1 = boardArray[cellX+1][cellY];
                neighbour1.setPath(2);
                neighbour1.setPath(true);
                neighbour1.setLeftPath(true);


                cell1 = neighbour1;
            }
            else if(!cell1.walls[3]) {
                board.pathShow(cell1X+ Main.cellDimension-7,
                        cell1Y+ Main.cellDimension-7,
                        cell1X+ Main.cellDimension-7,cell1Y-6,color);
                cell1.defaultPath();
                neighbour1 = boardArray[cellX][cellY-1];
                neighbour1.setPath(3);
                neighbour1.setPath(true);
                neighbour1.setLeftPath(true);


                cell1 = neighbour1;
            }
            else {
                board.pathShow(cell1X+ Main.cellDimension-7,
                        cell1Y+ Main.cellDimension-7,
                        cell1X+ Main.cellDimension-7,cell1Y+6,color);
                cell1.setRotation();

            }
        }else if(cell1.getDirection()==0){
            if(!cell1.walls[3]){
                board.pathShow(cell1X+ Main.cellDimension-7,
                        cell1Y-5,cell1X+ Main.cellDimension-7,
                        cell1Y+6,color);
                cell1.defaultPath();
                neighbour1 = boardArray[(cellX)][cellY-1];
                neighbour1.setPath(3);
                neighbour1.setPath(true);
                neighbour1.setLeftPath(true);

                cell1 = neighbour1;
            }
            else if(!cell1.walls[0]) {
                board.pathShow(cell1X+ Main.cellDimension-7,
                        cell1Y+6,cell1X-3,cell1Y+6,color);
                cell1.defaultPath();
                neighbour1 = boardArray[cellX-1][cellY];
                neighbour1.setPath(0);
                neighbour1.setPath(true);
                neighbour1.setLeftPath(true);
                cell1 = neighbour1;
            }
            else {
                board.pathShow(cell1X+ Main.cellDimension-7,
                        cell1Y+6,cell1X+6,cell1Y+6,color);
                cell1.setRotation();

            }
        }
    }

}