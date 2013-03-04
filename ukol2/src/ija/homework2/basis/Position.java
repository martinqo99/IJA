/**
 * Homework1: uloha c. 2 z IJA
 * Trida reprezentujici pozici
 */
package ija.homework2.basis;

/**
 *
 * @author xkolac12
 */
public class Position {
    
    protected Desk desk;
    protected char column;
    protected int row;
    protected Figure figure;
    
    public Position(char column, int row){
        this.desk = null;
        this.column = column;
        this.row = row; 
        this.figure = null;
    }
    
    public Position(Desk desk, char column, int row){
        this.desk = desk;
        this.column = column;
        this.row = row;
        this.figure = null;
    }
    
    public Position nextPosition(int column, int row){
        return this.desk.getPositionAt((char)(this.column + column), this.row + row);
    }
    
   public Figure getFigure(){
       return this.figure;
   } 
   
   public Figure putFigure(Figure figure){
       Figure ptr = this.figure;
       
       this.figure = figure;
       
       return ptr;
   }
   
   public Figure removeFigure(){
       Figure ptr = this.figure;
       
       this.figure = null;
       
       return ptr;
   }

    public boolean equals(Position position){
        if(this.column == position.getColumn() && this.row == position.getRow())
            return true;
        else
            return false;
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
