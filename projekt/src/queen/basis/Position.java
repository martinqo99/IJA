/** 
 * 
 * @brief       Class represents position of figure	
 * @file	Position.java
 * @date	2013/02/28
 * @authors	Frantisek Kolacek <xkolac12 at stud.fit.vutbr.cz>
 *              Pavel Matyas <xmatya03 at stud.fit.vutbr.cz>
 */

package queen.basis;

/**
 *
 * @author xkolac12 <xkolac12 at stud.fit.vutbr.cz>
 */
final public class Position {
    
    private char column;
    private int row;
    
    Position(char column, int row){
        this.column = column;
        this.row = row;
    }
    
    /**
     *
     * @param position
     * @return
     */
    public boolean equals(Position position){
        if(this.column == position.getColumn() && this.row == position.getRow())
            return true;
        else
            return false;
    }    
    
    public Position nextPosition(int column, int row){
        return new Position((char)(this.column + column), this.row + row);
    }
    
    /**
     *
     * @return
     */
    public char getColumn(){
        return this.column;
    }
    
    /**
     *
     * @return
     */
    public int getRow(){
        return this.row;
    }    
}
