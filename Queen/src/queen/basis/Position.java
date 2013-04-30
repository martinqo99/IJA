/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package queen.basis;

/**
 *
 * @author xkolac12 <xkolac12 at stud.fit.vutbr.cz>
 */
public class Position {
    
    private char column;
    private int row;
    
    public Position(char column, int row){
        this.column = column;
        this.row = row;
    }
    
    public char getColumn(){
        return this.column;
    }
    
    public int getRow(){
        return this.row;
    }
    
    @Override
    public String toString(){
        return Character.toString(column) + Integer.toString(row);
    }
    
}
