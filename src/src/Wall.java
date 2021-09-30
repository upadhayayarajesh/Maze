
public class Wall {
    private Cell cell1;
    private Cell cell2;

    public Wall(Cell cell1, Cell cell2){
        this.cell1 = cell1;
        this.cell2 = cell2;
    }


    /**
     * string representation of object
     * @return string representation
     */
    public String toString(){
        return cell1 + " | " + cell2;
    }

    /**
     * gets one of the cell separated by wall inside board
     * @return firstcell between cells separated by wall
     */
    public Cell getCell1() {
        return cell1;
    }

    /**
     * gets the other cell separated by wall inside board
     * @return other cell between cells separated by wall
     */
    public Cell getCell2() {
        return cell2;
    }


}
