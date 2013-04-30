/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package queen.basis;

import java.awt.*;

/**
 *
 * @author xkolac12 <xkolac12 at stud.fit.vutbr.cz>
 */
abstract public class Figure {
    
    private Desk desk;
    private Position position;
    private Color color;
    
    public Figure(Position position, Color color){
        this(null, position, color);
    }
    
    public Figure(Desk desk, Position position, Color color){
        this.desk = desk;
        this.position = position;
        this.color = color;  
    }
    

    
    public Position getPosition(){
        return this.position;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public boolean move(Position position){
        if(this.canMove(position)){
            this.position = position;
            return true;
        }
        else
            return false;
    }
    
    abstract public boolean canMove(Position position);    
}
