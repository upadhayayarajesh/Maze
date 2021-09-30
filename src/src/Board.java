
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * board  class to create the board using the cell
 * it uses the pane to draw GUI
 *
 */
public class Board {
    // to draw gui
    private Pane pane;
    // collection of cell as a array to represent the board.
    private Cell[][] board = new Cell[Main.boardSize][Main.boardSize];
    //  to check for the neighbour of the current cell
    private List<Cell> allNeighbors = new ArrayList<>();
    // to  set the starting cell
    public static Cell startingCell;
    // to check for the ending cell
    public static Cell endCell;
    // random variable to use random
    private Random random = new Random();
    // to check the random   wall of the cell
    private int forRandomWall = random.nextInt(4);
    private int randomCellFirst = random.nextInt(Main.boardSize);
    private int radomCellSecond = random.nextInt(Main.boardSize);
    // random mouse counter for the mous and DFS and Aldous generation.
    public static boolean randomMouseFlag = false;
    public static boolean isDFSActive = false;
    public static boolean isAldousActive = false;

    /**
     * board constructor for the board  creation of the  board.
     */
    public Board(){
        this.pane = new Pane();
        pane.setLayoutX(20);
        pane.setLayoutY(20);
        for(int i = 0; i<Main.boardSize; i++){
            for(int j = 0; j<Main.boardSize; j++){
                board[i][j]= new Cell(i,j);
            }
        }

//        System.out.println(forRandomWall);
//        System.out.println(randomCellFirst);
//        System.out.println(radomCellSecond);

        if(forRandomWall == 0){
            startingCell = board[0][randomCellFirst];
            endCell = board[Main.boardSize-1][radomCellSecond];
        }
        else if(forRandomWall == 1){
            startingCell = board[Main.boardSize-1][randomCellFirst];
            endCell = board[0][radomCellSecond];
        }
        else if(forRandomWall == 2){
            startingCell = board[randomCellFirst][0];
            endCell = board[radomCellSecond][Main.boardSize-1];
        }
        else{
            startingCell = board[randomCellFirst][Main.boardSize-1];
            endCell = board[radomCellSecond][0];
        }




    }

    /**
     * paintBoard to paint board with cell.
     */
    public void paintBoard(){
        for(int i = 0; i<Main.boardSize; i++){
            for(int j = 0; j<Main.boardSize; j++){
                pane.getChildren().add(board[i][j].allWalls());
            }
        }

    }

    ImageView imageView1;

    /**
     * repaintBoard for  updating the board in generation and solver.
     */
    public void repaintBoard(){
        pane.getChildren().clear();
        for(int i = 0; i<Main.boardSize; i++){
            for(int j = 0; j<Main.boardSize; j++){
                pane.getChildren().add(board[i][j].allWalls());
            }
        }


        Rectangle rect = new Rectangle(startingCell.getX()*Main.cellDimension,
                startingCell.getY()*Main.cellDimension,
                Main.cellDimension, Main.cellDimension);
        rect.setFill(Color.LIGHTBLUE);
        pane.getChildren().add(rect);

        Rectangle rect1 = new Rectangle(endCell.getX()*Main.cellDimension,
                endCell.getY()*Main.cellDimension,
                Main.cellDimension, Main.cellDimension);
        rect1.setFill(Color.GRAY);
        pane.getChildren().add(rect1);

        System.out.println(randomMouseFlag);
        if(randomMouseFlag){
            Rectangle rectt = new Rectangle(RandomMouse.currentCellRandomMouse.getX()*Main.cellDimension,
                    RandomMouse.currentCellRandomMouse.getY()*Main.cellDimension,
                    Main.cellDimension, Main.cellDimension);
            rectt.setFill(Color.YELLOW);
            pane.getChildren().add(rectt);

        }



//        try{
//            Image img = new Image(new FileInputStream("/resources/start.png"));
//            ImageView imageView = new ImageView(img);
//            imageView.setFitWidth(Main.cellDimension-5);
//            imageView.setFitHeight(Main.cellDimension-5);
//            imageView.setX(startingCell.getX()*Main.cellDimension);
//            imageView.setY(startingCell.getY()*Main.cellDimension);
//            pane.getChildren().add(imageView);
//
//            Image img1 = new Image(new FileInputStream("/resources/end.png"));
//            imageView1 = new ImageView(img1);
//            //ImageView imageView1 = new ImageView(img1);
//            imageView1.setFitWidth(Main.cellDimension-5);
//            imageView1.setFitHeight(Main.cellDimension-5);
//            imageView1.setX(endCell.getX()*Main.cellDimension);
//            imageView1.setY(endCell.getY()*Main.cellDimension);
//            pane.getChildren().add(imageView1);
//
//            if(randomMouseFlag){
//                Image img2 = new Image(new FileInputStream("/resources/kid.png"));
//                ImageView imageView2 = new ImageView(img2);
//                imageView2.setFitWidth(Main.cellDimension-5);
//                imageView2.setFitHeight(Main.cellDimension-5);
//                imageView2.setX(RandomMouse.currentCellRandomMouse.getX()*
//                        Main.cellDimension);
//                imageView2.setY(RandomMouse.currentCellRandomMouse.getY()*
//                        Main.cellDimension);
//                pane.getChildren().add(imageView2);
//            }
//        }
//        catch (Exception e){
//            //e.printStackTrace();
//        }
        if(isDFSActive){
            Rectangle rectangle = new Rectangle(
                    DFSGenerator.currentCell.getX()*Main.cellDimension,
                    DFSGenerator.currentCell.getY()*Main.cellDimension,
                    Main.cellDimension, Main.cellDimension);
            rectangle.setFill(Color.BLACK);
            pane.getChildren().add(rectangle);
        }
        if(isAldousActive){
            Rectangle rectangle = new Rectangle(
                    AldousBroderGenerator.currentCell.getX()*
                            Main.cellDimension,
                    AldousBroderGenerator.currentCell.getY()*
                            Main.cellDimension,
                    Main.cellDimension, Main.cellDimension);
            rectangle.setFill(Color.BLACK);
            pane.getChildren().add(rectangle);
        }


    }

    /**
     * drawOuterLines for drawing the border of the GUI board.
     */
    public void drawOuterLines(){
        int corner = Main.cellDimension*Main.boardSize;
        Line topLine = new Line(0,0,corner,0);
        topLine.setStrokeWidth(5);
        Line bottomLine = new Line(0,corner,corner,corner);
        bottomLine.setStrokeWidth(5);
        Line leftLine = new Line(0,0,0,corner);
        leftLine.setStrokeWidth(5);
        Line rightLine = new Line(corner,0,corner,corner);
        rightLine.setStrokeWidth(5);

        pane.getChildren().addAll(topLine,bottomLine,leftLine,rightLine);

    }

    /**
     *
     * @param x cell x  position
     * @param y cell y position
     * @return cell  neighbour of the given corodinate.
     */
    public Cell neighbourCellCopy(int x, int y) {
        ArrayList<Cell> arrayList = new ArrayList<>();
        if (x + 1 < Main.boardSize) {
            arrayList.add(board[x+1][y]);

        }
        if ((x -1) >-1) {
            arrayList.add(board[x-1][y]);
        }
        if ((y+1) < Main.boardSize) {
            arrayList.add(board[x][y+1]);
        }
        if((y-1) >-1) {
            arrayList.add(board[x][y-1]);

        }
        if(arrayList.size()==0){
            return null;
        }else {
            Random random = new Random();
            int rand = random.nextInt(arrayList.size());
            return arrayList.get(rand);
        }


    }

    /**
     *
     * @param current cell  to find the path o
     * @return c moved cell from the given cell.
     */
    public Cell getNeighborPath(Cell current){
        ArrayList<Cell> allPath = new ArrayList<>();
        if((current.getY()-1)>=0 && !current.getWalls()[3] &&
                !board[current.getX()][current.getY()-1].isFirstVisit()){
            allPath.add(board[current.getX()][current.getY()-1]);
        }
        if(current.getX()+1<board.length && !current.getWalls()[2] &&
                !board[current.getX()+1][current.getY()].isFirstVisit()){
            allPath.add(board[current.getX()+1][current.getY()]);
        }
        if(current.getY()+1<board.length && !current.getWalls()[1] &&
                !board[current.getX()][current.getY()+1].isFirstVisit()){
            allPath.add(board[current.getX()][current.getY()+1]);
        }
        if(current.getX()-1>=0 && !current.getWalls()[0] &&
                !board[current.getX()-1][current.getY()].isFirstVisit()){
            allPath.add(board[current.getX()-1][current.getY()]);
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

    /**'
     *
     * @param x xcoordinate
     * @param y yCoordinate
     * @return cell negihbour
     */
    public Cell neighbourCell(int x, int y) {
        ArrayList<Cell> arrayList = new ArrayList<>();
        if (x + 1 < Main.boardSize) {
            if(board[x+1][y].isVisited()==false){
                arrayList.add(board[x+1][y]);
                allNeighbors.add(board[x+1][y]);
            }
        }
        if ((x -1) >-1) {
            if(board[x-1][y].isVisited()==false){
                arrayList.add(board[x-1][y]);
                allNeighbors.add(board[x-1][y]);
            }
        }
        if ((y+1) < Main.boardSize) {
            if(board[x][y+1].isVisited()==false){
                arrayList.add(board[x][y+1]);
                allNeighbors.add(board[x][y+1]);
            }
        }
        if((y-1) >-1) {
            if(board[x][y-1].isVisited()==false){
                arrayList.add(board[x][y-1]);
                allNeighbors.add(board[x][y-1]);
            }
        }
        if(arrayList.size()==0){
            return null;
        }else {
            Random random = new Random();
            int rand = random.nextInt(arrayList.size());
            return arrayList.get(rand);
        }


    }

    public Pane getPane() {
        return pane;
    }

    /**
     *
     * @return list of the edges of the  board wall tracker
     */
    public List<Wall> getAllEdges(){
        List<Wall> walls = new ArrayList<>();
        List<Cell> temp = new ArrayList<>();
        List<Cell> temp1 = new ArrayList<>();

        for(int i = 0; i<Main.boardSize; i++){
            for(int j = 0; j<Main.boardSize; j++){
                temp.add(board[i][j]);
                temp1.add(board[j][i]);
            }
        }

        int counter =0;
        for(int i = 0; i<Main.boardSize; i++){
            List<Cell> partition =
                    temp.subList(counter, counter+Main.boardSize);
            List<Cell> partition1 =
                    temp1.subList(counter, counter+Main.boardSize);
            counter+= Main.boardSize;
            for(int j=0; j<partition.size();j++){
                if(j+1<partition.size()){
                    partition.get(j).addWallList(partition.get(j+1));
                    partition1.get(j).addWallList(partition1.get(j+1));
                    Wall tempWall = new Wall(partition.get(j),
                            partition.get(j+1));
                    Wall tempWall1 = new Wall(partition1.get(j),
                            partition1.get(j+1));
                    walls.add(tempWall);
                    walls.add(tempWall1);

                }
            }
        }

        return walls;
    }

    /**
     * this method is to represent the wall to the given cell and neigbour cell
     * @param first  playing cell.
     * @param neighbor neighbour cell of the playing cell.
     */
    public void addWalls(Cell first, Cell neighbor){
        int x1 = first.getX()- neighbor.getX();
        int y1 = first.getY()-neighbor.getY();
        boolean[] previousWallsfirst = first.getWalls();
        boolean[] previousWallsSecond = neighbor.getWalls();
        if(x1==1){
            boolean[] temp1 = {true, previousWallsfirst[1],
                    previousWallsfirst[2],
            previousWallsfirst[3]};
            boolean[] temp2 = {previousWallsSecond[0], previousWallsSecond[1],
            true,previousWallsSecond[3]};
            first.setWalls(temp1);
            neighbor.setWalls(temp2);

        }
        else if(x1==-1){
            boolean[] temp2 = {true, previousWallsfirst[1],
                    previousWallsfirst[2],
                    previousWallsfirst[3]};
            boolean[] temp1 = {previousWallsSecond[0], previousWallsSecond[1],
                    true,previousWallsSecond[3]};
            first.setWalls(temp1);
            neighbor.setWalls(temp2);

        }
        if(y1==1){

            boolean[] temp1 = {previousWallsfirst[1], previousWallsfirst[2],
                    previousWallsfirst[3], true};
            boolean[] temp2 = {previousWallsSecond[0], previousWallsSecond[1],
                    true,previousWallsSecond[3]};
            first.setWalls(temp1);
            neighbor.setWalls(temp2);
        }
         else if(y1==-1){

            boolean[] temp2 = {previousWallsfirst[1], true,
                    previousWallsfirst[2],
                    previousWallsfirst[3]};
            boolean[] temp1 = {previousWallsSecond[0], previousWallsSecond[1],
                    previousWallsSecond[3], true};
            first.setWalls(temp2);
            neighbor.setWalls(temp1);
        }
    }

    public List<Cell> getAllNeighbors() {
        return allNeighbors;
    }

    public Cell[][] getBoard(){
        return board;
    }

    /**
     * this class is to  show the gui if the  path
     * @param stX starting of x
     * @param stY starting of y
     * @param enX ending of the x
     * @param enY ending of the t
     * @param color to set the color of the path
     */
    public void pathShow(int stX, int stY, int enX, int enY, Color color){
        Line line=new Line(stX,stY,enX,enY);
        line.setStroke(color);
        line.setStrokeWidth(5);
        Main.board.getPane().getChildren().add(line);
    }

    public ImageView getEndCellImage(){
        return imageView1;
    }

}
