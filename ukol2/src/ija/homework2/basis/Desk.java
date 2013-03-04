/**
 * Homework1: uloha c. 2 z IJA
 * Trida reprezentujici hraci desku
 */
package ija.homework2.basis;

/**
 *
 * @author xkolac12 <xkolac12 at stud.fit.vutbr.cz>
 */

final public class Desk {
    
    private int dimension;
    private Position desk[];
    
    public Desk(int dimension){
        this.dimension = dimension;
        
        this.desk = new Position[(this.dimension * this.dimension)];
        
        for(char column = 'a'; column < (char)('a' + this.dimension); column++)
            for(int row = 1; row <= this.dimension; row++)    
                this.desk[this.pos(column, row)] = new Position(this, column, row);
    }
    
    public Position getPositionAt(char column, int row){
        if(!this.isValidPosition(column, row))
            return null;
        
        return this.desk[this.pos(column, row)];
    }
    
    public Figure getFigureAt(char column, int row){
        if(!this.isValidPosition(column, row))
            return null;
        
        return this.desk[this.pos(column, row)].getFigure();
    }
    
    public boolean isValidPosition(char column, int row){
        column = Character.toLowerCase(column);
        
        if(column < 'a' || column >= (char)('a' + this.dimension))
            return false;
        
        if(row < 1 || row > this.dimension)
            return false;
        
        return true;
    }
    
    private int pos(char column, int row){
        column = Character.toLowerCase(column);
        
        column -= 'a';
        
        return ((row - 1) * this.dimension) + column;
    }
}
