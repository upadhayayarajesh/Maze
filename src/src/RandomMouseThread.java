


import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.io.FileInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class RandomMouseThread {
    private Board board;
    private Cell endingCell;
    ExecutorService executorService = Executors.newFixedThreadPool(1000);
    private Cell currentCellRandomWThread;

    /**
     * this constructor is for the randomMouseThread to manage the
     * thread of solving
     * using randomMouse class.
     * @param board
     */
    public RandomMouseThread(Board board){
        this.board = board;
        currentCellRandomWThread = Board.startingCell;
        endingCell = Board.endCell;
        Runnable threadStart = new RunMouse(currentCellRandomWThread,
                drawImage(currentCellRandomWThread));
        new Thread(threadStart).start();

    }

    /**
     *  this constructor uses a image for the mouse drawing.
     * @param cell
     * @return
     */
    public Rectangle drawImage(Cell cell) {

        Rectangle rect2 =
                new Rectangle(cell.getX()*Main.cellDimension,
                cell.getY()*Main.cellDimension,
                Main.cellDimension, Main.cellDimension);
        rect2.setFill(Color.YELLOW);
        return rect2;
//        ImageView imageView = null;
//        try {
//            Image img = new Image(new FileInputStream("/resources/kid.png"));
//            imageView = new ImageView(img);
//            imageView.setFitWidth(Main.cellDimension - 5);
//            imageView.setFitHeight(Main.cellDimension - 5);
//            imageView.setX(cell.getX() * Main.cellDimension);
//            imageView.setY(cell.getY() * Main.cellDimension);
//        } catch (Exception e) {
//
//        }
//        return imageView;
    }

    /**
     *  THis method is the main logic of thisclass finding the path
     *  using the current cell passed.
     * @param current
     */
    public void solve(Cell current) {
        int x=current.getX();
        int y=current.getY();
        if(current==endingCell){
            Platform.runLater(()->{
                board.getPane().getChildren().add(drawImage(current));
            });
            executorService.shutdownNow();
        }

        board.getBoard()[x][y].setTempFlagForRandomMouse();

        if( y+1<Main.boardSize &&!board.getBoard()[x][y].getWalls()[1] &&
                !board.getBoard()[x][y+1].isTempFlagForRandomMouse() &&
                !executorService.isShutdown()){
            Cell temp=board.getBoard()[x][y+1];
            Rectangle drawImage=drawImage(temp);
            Runnable thread = new RunMouse(temp,drawImage);
            executorService.execute(thread);
        }

        if(y-1>=0 &&!board.getBoard()[x][y].getWalls()[3] &&
                !board.getBoard()[x][y-1].isTempFlagForRandomMouse() &&
                !executorService.isShutdown()){
            Cell temp = board.getBoard()[x][y-1];
            Rectangle drawImage=drawImage(temp);
            Runnable thread = new RunMouse(temp,drawImage);
            executorService.execute(thread);
        }

        if(x-1>=0 &&!board.getBoard()[x][y].getWalls()[0] &&
                !board.getBoard()[x-1][y].isTempFlagForRandomMouse() &&
                !executorService.isShutdown()){
            Cell temp = board.getBoard()[x-1][y];
            Rectangle mouse=drawImage(temp);
            Runnable thread = new RunMouse(temp,mouse);
            executorService.execute(thread);
        }

        if( x+1<Main.boardSize &&!board.getBoard()[x][y].getWalls()[2] &&
                !board.getBoard()[x+1][y].isTempFlagForRandomMouse() &&
                !executorService.isShutdown()){
            Cell temp=board.getBoard()[x+1][y];
            Rectangle mouse=drawImage(temp);
            Runnable thread = new RunMouse(temp,mouse);
            executorService.execute(thread);
        }


    }

    class RunMouse implements Runnable{
    Cell start;
    Rectangle imageView;
    RunMouse(Cell start, Rectangle imageView){
        this.start=start;
        this.imageView=imageView;
    }
    @Override
    public void run() {
        Platform.runLater(() -> {
            board.getPane().getChildren().add(imageView);
        });
        try {
            Thread.sleep(40);
        } catch (InterruptedException ex) {
        }
        Platform.runLater(() -> {
            board.getPane().getChildren().remove(imageView);
        });
        solve(start);
    }
}
}


