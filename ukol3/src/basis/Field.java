/** 
 * 
 * @brief	Trida reprezentujici bitevni pole
 * @file	Field.java
 * @date	2013/04/07
 * @authors	Frantisek Kolacek <xkolac12 at stud.fit.vutbr.cz>
 *         	Petr Matyas <xmatya03 at stud.fit.vutbr.cz>
 */

package ukol3.src.basis;

public class Field {
    
    private Position position;
    private Color color; 
    private Figure figure;
    
    public Field(Position position, Color color){
        this.position = position;
        this.color = color;
        this.figure = null;
    }
    
    public Field(char column, int row, Color color){
        this(new Position(column, row), color);
    }

    public Position getPosition(){
        return this.position;
    }
    
    public char getColumn(){
        return this.position.getColumn();
    }
    
    public int getRow(){
        return this.position.getRow();
    }    
    
    public Color getColor(){
        return this.color;
    }
    
    public Figure getFigure(){
        return this.figure;
    }
    
    public Figure putFigure(Figure figure){
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