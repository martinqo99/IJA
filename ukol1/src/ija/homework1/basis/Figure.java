/**
 * Homework1: uloha c. 1 z IJA
 * Trida reprezentujici obecnou figurku
 */
package ija.homework1.basis;

/**
 *
 * @author xkolac12
 */
public abstract class Figure {
    
    protected Position position;
    
    public Figure(Position position){
        this.position = position;
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
            this.position = position;
            
            return true;
        }
        
        return false;
    }
    
    public abstract boolean canMove(Position position);    
}
