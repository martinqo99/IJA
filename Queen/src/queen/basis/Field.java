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
public class Field {
    
    private Position position;
    private Color color;
    private Figure figure;
    
    public Field(Position position, Color color){
        this.position = position;
        this.color = color;
        this.figure = null;
    }
    
    public Field(Position position, Color color, Figure figure){
        this.position = position;
        this.color = color;
        this.figure = figure;    
    }
    
    public Position getPosition(){
        return this.position;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public Figure getFigure(){
        return this.figure;
    }
    
    public Figure setFigure(Figure figure){
        Figure tmp = this.figure;
        
        this.figure = figure;
        
        return tmp;
    }
    
    public Figure removeFigure(){
        Figure tmp = this.figure;
        
        this.figure = null;
        
        return tmp;
    }
    
}
