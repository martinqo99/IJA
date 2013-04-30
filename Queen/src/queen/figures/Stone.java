/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */

package queen.figures;

import queen.basis.*;
import java.awt.*;
import java.util.Vector;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class Stone extends Figure{
    
    public Stone(Desk desk, Position position, Color color){
        super(desk, position, color);
    }
    
    @Override
    public boolean canMove(Position position){
        
        Vector possibilities = this.canMovePossibilities();
        
        if(possibilities.contains(position))
            return true;
        else
            return false;
        
        /*
        if(this.color == Color.BLACK && position.getRow() >= this.position.getRow())
            return false;
        
        if(this.color == Color.WHITE && position.getRow() <= this.position.getRow())
            return false;
        
        int stepRow = position.getRow() - this.position.getRow();
        int stepColumn = position.getColumn() - this.position.getColumn();
        
        if(stepRow < 0) stepRow *= -1;
        if(stepColumn < 0) stepColumn *= -1;
        
        // Pohyb po diagonale
        if(stepRow != stepColumn)
            return false;
        */
    }
    
    @Override
    public Vector canMovePossibilities(){
        Vector possibilities = new Vector();
        
        int rowStep = (this.color == Color.BLACK)? -1 : 1;
        
        Position step1;   
        Position step2;
        
        // Vlevo
        step1 = new Position((char)(this.position.getColumn() - 1), this.position.getRow() + rowStep);
        if(!this.desk.isDeserter(step1)){
            // Volna pozice
            if(this.desk.at(step1).getFigure() == null)
                possibilities.add(new Position(step1.getColumn(), step1.getRow()));
            else{
                step2 = new Position((char)(step1.getColumn() - 1), step1.getRow() + rowStep);
                
                if(this.desk.at(step2).getFigure() == null)
                    possibilities.add(new Position(step2.getColumn(), step2.getRow()));
            }
        }
        
        // Vpravo
        step1 = new Position((char)(this.position.getColumn() + 1), this.position.getRow() + rowStep);
        if(!this.desk.isDeserter(step1)){
            // Volna pozice
            if(this.desk.at(step1).getFigure() == null)
                possibilities.add(new Position(step1.getColumn(), step1.getRow()));
            else{
                step2 = new Position((char)(step1.getColumn() + 1), step1.getRow() + rowStep);
                
                if(this.desk.at(step2).getFigure() == null)
                    possibilities.add(new Position(step2.getColumn(), step2.getRow()));
            }
        }        

        return possibilities;
    }
}
