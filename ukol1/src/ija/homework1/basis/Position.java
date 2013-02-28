/**
 * Homework1: uloha c. 1 z IJA
 * Trida reprezentujici pozici
 */
package ija.homework1.basis;

/**
 *
 * @author xkolac12
 */
public class Position {
    
    protected char column;
    protected int row;    
    
    public Position(char column, int row){
        this.column = column;
        this.row = row;
    }

    public boolean equals(Position position){
        if(this.column == position.getColumn() && this.row == position.getRow())
            return true;
        else
            return false;
    }
    
    public Position nextPosition(int column, int row){
        return new Position((char)(this.column + column), this.row + row);
    }
    
    public boolean sameColumn(Position p){
        if(this.column == p.getColumn())
            return true;
        else
            return false;
    }
    
    public boolean sameRow(Position p){
        if(this.row == p.getRow())
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
}
