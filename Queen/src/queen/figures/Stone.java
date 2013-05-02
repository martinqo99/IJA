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

    /**
     * Konstruktor pro objekt Stone
     * @param desk hraci plan
     * @param position pozice
     * @param color barva
     */
    public Stone(Desk desk, Position position, Color color){
        super(desk, position, color);
    }

    @Override
    public boolean canMove(Position position){

        Vector possibilities = this.canMovePossibilities();
        
        for(int i = 0; i < possibilities.size(); i++){
            Possibility possibility = (Possibility)possibilities.get(i);
            
            if(possibility.getPosition().equals(position))
                return true;
        }

        return false;
    }

    @Override
    public Vector canMovePossibilities(){
        Vector possibilities = new Vector();

        int rowStep = (this.color == Color.BLACK)? -1 : 1;

        Position step1;
        Position step2;
        Possibility possibility = null;
        boolean areYouThereAssassin = false;

        // Vlevo
        step1 = new Position((char)(this.position.getColumn() - 1), this.position.getRow() + rowStep);
        if(!this.desk.isDeserter(step1)){
            // Volna pozice
            if(this.desk.at(step1).getFigure() == null) {
                possibility = new Possibility(step1);
                possibilities.add(possibility);
            }
            else if(this.color != this.desk.at(step1).getFigure().getColor()){
                step2 = new Position((char)(step1.getColumn() - 1), step1.getRow() + rowStep);

                if(!this.desk.isDeserter(step2) && this.desk.at(step2).getFigure() == null) {
                    areYouThereAssassin = true;
                    possibility = new Possibility(step2);
                    possibility.killVictim(step1);
                    possibilities.add(possibility);
                }
            }
        }
        
        // Vpravo
        step1 = new Position((char)(this.position.getColumn() + 1), this.position.getRow() + rowStep);
        if(!this.desk.isDeserter(step1)){
            // Volna pozice
            if(this.desk.at(step1).getFigure() == null) {
                possibility = new Possibility(step1);
                possibilities.add(possibility);
            }
            else if(this.color != this.desk.at(step1).getFigure().getColor()){
                step2 = new Position((char)(step1.getColumn() + 1), step1.getRow() + rowStep);

                if(!this.desk.isDeserter(step2) && this.desk.at(step2).getFigure() == null) {
                    areYouThereAssassin = true;
                    possibility = new Possibility(step2);
                    possibility.killVictim(step1);
                    possibilities.add(possibility);
                }
            }
        }
        
        if(areYouThereAssassin){
            Vector tmp = possibilities;
            possibilities = new Vector();
            
            for(int i = 0; i < tmp.size(); i++){
                possibility = (Possibility)tmp.get(i);
                
                if(possibility.killed() > 0)
                    possibilities.add(possibility);                
            }
        }

        return possibilities;
    }
}
