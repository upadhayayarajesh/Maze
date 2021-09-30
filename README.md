1. How to Run
    The Maze.jar file takes four command line arguments. First, it takes the window size,
    then each cell size, and then the name of generator algorithm name and then the name of
    solver algorithm. There are three algorithms implementation for generating the maze:
    DFS, Kruskal, and Aldous. There are three algorithms implementation for solving the maze:
    Wall follower with multithreading and without multithreading, Random mouse with
    multithreading and without multithreading, and Tremaux.
    Example Command to run Jar file:
        java -jar Maze 400 20 dfs/kruskal/aldous wall/wall_thread/mouse/mouse_thread/tremaux

2. GUI Brief Explanation
    The GUI for the generator and solver is very simple. The light blue rectangle represents
    the starting point of the maze whereas the gray rectangle represents the ending point of
    the maze. The black rectangle that traverses while solving the maze is all the cell
    traversed while solving the maze. The yellow rectangle which can be seen in random mouse
    with and without thread also acts in similar manner to black rectangle.


3. Classes explanation
    All the generator algorithms classes and solver algorithms classes are implemented
    successfully with some minor bugs which is listed in number 4.

    One of classes named DisjointDataSetStructure is taken from online and the source
    code's path as well as author is mentioned as a comment inside the class.

    All the generator and solvers have Board Object, Cell Object. Also, all the generators
    and solvers with few exception where multithreading was needed, share common
    AnimationTimer which is in the Main class to make the codes as simple and as
    compact as possible.

    The Wall class is specifically used for Kruskal generator class which basically
    represent a wall inside the maze excluding all the outer edges.

    Other than those all the generators and solvers have their own specific class
    where all the logic and algorithms are implemented, with the help from predefined
    methods which can be accessed from Board Object as well as Cell object.




4. Some minor bugs
    Please ignore the black rectangle after the maze has been generated and solved. It sort of
    sticks around the maze and doesn't gets cleared. Sometimes it will be in the starting position,
    sometimes on a random cell which is actually the last cell that is visited while generating the
    maze. It all depends on what generator and solver you're using to generate and solve the maze.

    Sometimes in wall follower solver with multithreading one of the thread seems to get stuck in
    the middle. However, if you play individual thread which is implemented in wall follower without
    multi threading it works perfectly fine. It's just a small bug in the threading part that
    needs to be fixed and the problem in rare.

    Also, in a very rare occasion in the random mouse solver with multithreading, after the generator
    and solver has been implemented successfully, it shows java.util.concurrent.RejectedExecutionException
    which needs to be handled. We were not quite sure what the exception was.




