/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */

package queen.basis;

import java.awt.*;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class Field {
    
    private Position position;
    private Color color;
    private Figure figure;
    
    /**
     *
     * @param position
     * @param color
     */
    public Field(Position position, Color color){
        this.position = position;
        this.color = color;
        this.figure = null;
    }
    
    /**
     *
     * @param position
     * @param color
     * @param figure
     */
    public Field(Position position, Color color, Figure figure){
        this.position = position;
        this.color = color;
        this.figure = figure;    
    }
    
    /**
     *
     * @return
     */
    public Position getPosition(){
        return this.position;
    }
    
    /**
     *
     * @return
     */
    public Color getColor(){
        return this.color;
    }
    
    /**
     *
     * @return
     */
    public Figure getFigure(){
        return this.figure;
    }
    
    /**
     *
     * @param figure
     * @return
     */
    public Figure setFigure(Figure figure){
        Figure tmp = this.figure;
        
        this.figure = figure;
        
        return tmp;
    }
    
    /**
     *
     * @return
     */
    public Figure removeFigure(){
        Figure tmp = this.figure;
        
        this.figure = null;
        
        return tmp;
    }    
}
