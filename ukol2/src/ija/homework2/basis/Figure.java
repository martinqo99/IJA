/**
 * Homework1: uloha c. 2 z IJA
 * Trida reprezentujici obecnou figurku
 */
package ija.homework2.basis;

/**
 *
 * @author xkolac12
 */
public abstract class Figure {
    
    protected Position position;
    
    public Figure(Position position){
        this.position = position;
        this.position.putFigure(this);
    }
    
    public Position getPosition(){
        return this.position;
    }
    
    public boolean isAtPosition(Position position){
        if(this.position.equals(position)){
            return true;
        }
        
        return false;
    }
    
    public boolean move(Position position){
        if(canMove(position)){
            this.position.removeFigure();
            
            this.position = position;
            
            this.position.putFigure(this);
            
            return true;
        }
        
        return false;
    }
    
    public abstract boolean canMove(Position position);    
}
