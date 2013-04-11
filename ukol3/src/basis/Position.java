/** 
 * 
 * @brief	Trida reprezentujici pozici
 * @file	Position.java
 * @date	2013/04/07
 * @authors	Frantisek Kolacek <xkolac12 at stud.fit.vutbr.cz>
 *         	Petr Matyas <xmatya03 at stud.fit.vutbr.cz>
 */

package ukol3.src.basis;

public class Position {
    
    private char column;
    private int row;
    
    public Position(char column, int row){
        this.column = column;
        this.row = row;
    }
    
    public Position(){
        this('a', 1);
    }
    
    public boolean equals(Position position){
        if(this.column == position.getColumn() && this.row == position.getRow())
            return true;
        else
            return false;
    }
    
    public char getColumn(){
        return this.column;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public void setColumn(char column){
        this.column = column;
    }
    
    public void setRow(int row){
        this.row = row;
    }
}