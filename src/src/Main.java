
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main.Main class for the main method and maze
 * generation  and solver of the maze formed from different method
 */
public class Main extends Application{
    public static int windowSize;
    public static int inputCellSize;
    public static int cellDimension;
    public static int boardSize;
    public static String generator;
    public static String solver;


    public static Board board;

    public void start(Stage stage){
        createAndShow(stage, generator, solver);
    }

    /**
     *  creating and solving the maze as given input.
     * @param primaryStage
     * @param generator
     * @param solver
     */
    public void createAndShow(Stage primaryStage, String generator,
                              String solver){
        cellDimension= inputCellSize ;
        boardSize = (windowSize/cellDimension);
        board = new Board();

        if((generator.equalsIgnoreCase("dfs") ||
                generator.equalsIgnoreCase("kruskal")
        || generator.equalsIgnoreCase("aldous")) &&
                (solver.equalsIgnoreCase("wall_thread") ||
                solver.equalsIgnoreCase("tremaux") ||
                solver.equalsIgnoreCase("wall") ||
                solver.equalsIgnoreCase("mouse") ||
                solver.equalsIgnoreCase("mouse_thread"))){
            board.paintBoard();
            DFSGenerator dfsGenerator = new DFSGenerator(board);
            KruskalGen kruskalGen = new KruskalGen(board);
            AldousBroderGenerator aldousBroderGenerator =
                    new AldousBroderGenerator(board);

            AnimationTimer at = new AnimationTimer() {
                private long start=0;
                @Override
                public void handle(long now) {
                    if(now -start>=1000){
                        if(generator.equalsIgnoreCase("dfs")){
                            Board.isDFSActive = true;
                            dfsGenerator.DFSMaze();
                            board.repaintBoard();
                        }
                        else if(generator.equalsIgnoreCase
                                ("kruskal")){
                            kruskalGen.KruskalMaze();
                            board.repaintBoard();
                        }
                        else if(generator.equalsIgnoreCase
                                ("aldous")){
                            Board.isAldousActive=true;
                            aldousBroderGenerator.aldousMaze();
                            board.repaintBoard();
                        }

                    }

                    if(solver.equalsIgnoreCase("wall_thread")){
                        if(generator.equalsIgnoreCase("dfs")){
                            if(dfsGenerator.getStack().isEmpty()){
                                new WallFollowerSolver(board);
                                this.stop();
                            }

                        }

                        else if(generator.equalsIgnoreCase
                                ("kruskal")){
                            if(kruskalGen.getEdgeSize() ==
                                    kruskalGen.getCounter()){
                                new WallFollowerSolver(board);
                                this.stop();
                            }
                        }

                        else if(generator.equalsIgnoreCase
                                ("aldous")){
                            if(aldousBroderGenerator.getVisitedCellSize() ==
                            boardSize * boardSize){
                                new WallFollowerSolver(board);
                                this.stop();
                            }
                        }
                    }



                    else if(solver.equalsIgnoreCase("wall")){
                        if(generator.equalsIgnoreCase("dfs")){
                            if(dfsGenerator.getStack().isEmpty()){
                                new WallFollowerNoThread(board);
                                this.stop();
                            }

                        }

                        else if(generator.equalsIgnoreCase
                                ("kruskal")){
                            if(kruskalGen.getEdgeSize() ==
                                    kruskalGen.getCounter()){
                                new WallFollowerNoThread(board);
                                this.stop();
                            }
                        }

                        else if(generator.equalsIgnoreCase
                                ("aldous")){
                            if(aldousBroderGenerator.getVisitedCellSize() ==
                                    boardSize * boardSize){
                                new WallFollowerNoThread(board);
                                this.stop();
                            }
                        }
                    }




                    else if(solver.equalsIgnoreCase("tremaux")){
                        if(generator.equalsIgnoreCase("dfs")){
                            if(dfsGenerator.getStack().isEmpty()){
                                new Tremaux(board);
                                this.stop();
                            }

                        }

                        else if(generator.equalsIgnoreCase
                                ("kruskal")){
                            if(kruskalGen.getEdgeSize() ==
                                    kruskalGen.getCounter()){
                                new Tremaux(board);
                                this.stop();
                            }
                        }

                        else if(generator.equalsIgnoreCase
                                ("aldous")){
                            if(aldousBroderGenerator.getVisitedCellSize() ==
                                    boardSize * boardSize){
                                new Tremaux(board);
                                this.stop();
                            }
                        }
                    }

                    else if(solver.equalsIgnoreCase("mouse")){
                        if(generator.equalsIgnoreCase("dfs")){
                            if(dfsGenerator.getStack().isEmpty()){
                                Board.randomMouseFlag = true;
                                new RandomMouse(board);
                                this.stop();
                            }

                        }

                        else if(generator.equalsIgnoreCase
                                ("kruskal")){
                            if(kruskalGen.getEdgeSize() ==
                                    kruskalGen.getCounter()){
                                Board.randomMouseFlag = true;
                                new RandomMouse(board);
                                this.stop();
                            }
                        }

                        else if(generator.equalsIgnoreCase
                                ("aldous")){
                            if(aldousBroderGenerator.getVisitedCellSize() ==
                                    boardSize * boardSize){
                                new RandomMouse(board);
                                this.stop();
                            }
                        }
                    }

                    else if(solver.equalsIgnoreCase
                            ("mouse_thread")){
                        if(generator.equalsIgnoreCase("dfs")){
                            if(dfsGenerator.getStack().isEmpty()){
                                new RandomMouseThread(board);
                                this.stop();
                            }

                        }

                        else if(generator.equalsIgnoreCase
                                ("kruskal")){
                            if(kruskalGen.getEdgeSize() ==
                                    kruskalGen.getCounter()){
                                new RandomMouseThread(board);
                                this.stop();
                            }
                        }

                        else if(generator.equalsIgnoreCase
                                ("aldous")){
                            if(aldousBroderGenerator.getVisitedCellSize() ==
                                    boardSize * boardSize){
                                new RandomMouseThread(board);
                                this.stop();
                            }
                        }
                    }
                    start=now;
                }
            };
            at.start();

            primaryStage.setTitle("Maze");
            primaryStage.setScene(new Scene(board.getPane(),
                    windowSize+40,windowSize+40));
            primaryStage.show();


        }
        else {
            System.out.println("Not a valid option");
            System.out.println("Generators present: DFS, Kruskal ,Aldous");
            System.out.println("Solver present: Wall, wall_thread, mouse," +
                    "mouse_thread, tremaux");
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        windowSize = Integer.parseInt(args[0]);
        inputCellSize = Integer.parseInt(args[1]);
        generator = args[2];
        solver = args[3];
        launch(args);

    }

}
