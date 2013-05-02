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
 *
 * @author xkolac12 <xkolac12 at stud.fit.vutbr.cz>
 */
public class Rook extends Figure{

    /**
     * Konstruktor pro objekt Rook
     * @param desk hraci plan
     * @param position pozice
     * @param color barva
     */
    public Rook(Desk desk, Position position, Color color){
        super(desk, position, color);
    }

    @Override
    public boolean canMove(Position position){
        Vector possibilities = this.canMovePossibilities();

        if(possibilities.contains(position))
            return true;
        else
            return false;
    }

    @Override
    public Vector canMovePossibilities(){
        Vector possibilities = new Vector();
        Vector kill = new Vector();

        Position step;
        int row;
        char column;
        Possibility possibility = null;
        boolean areYouThereAssassin = false;
        //vlevo nahoru
        for (row = this.getPosition().getRow()-1, column = (char)(this.getPosition().getColumn()-1); row > 0 && column >= 'a'; row--, column--) {
            step = new Position(column, row);
            if(this.desk.isDeserter(step))
                continue;
            if(this.desk.at(step).getFigure() == null) {
                possibility = new Possibility(step, kill);
                possibilities.add(possibility);
            }
            else
                if (this.getColor() == this.desk.at(step).getFigure().getColor())
                    break;
                else {
                    kill.add(new Position(step.getColumn(), step.getRow()));
                    areYouThereAssassin = true;
                }
        }
        kill = new Vector();
        //vpravo nahoru
        for (row = this.getPosition().getRow()-1, column = (char)(this.getPosition().getColumn()+1); row > 0 && column <= 'h'; row--, column++) {
            step = new Position(column, row);
            if(this.desk.isDeserter(step))
                continue;
            if(this.desk.at(step).getFigure() == null) {
                possibility = new Possibility(step, kill);
                possibilities.add(possibility);
            }
            else
                if (this.getColor() == this.desk.at(step).getFigure().getColor())
                    break;
                else {
                    kill.add(new Position(step.getColumn(), step.getRow()));
                    areYouThereAssassin = true;
                }
        }
        kill = new Vector();
        //vlevo dolu
        for (row = this.getPosition().getRow()+1, column = (char)(this.getPosition().getColumn()-1); row <= 8 && column >= 'a'; row++, column--) {
            step = new Position(column, row);
            if(this.desk.isDeserter(step))
                continue;
            if(this.desk.at(step).getFigure() == null) {
                possibility = new Possibility(step, kill);
                possibilities.add(possibility);
            }
            else
                if (this.getColor() == this.desk.at(step).getFigure().getColor())
                    break;
                else {
                    kill.add(new Position(step.getColumn(), step.getRow()));
                    areYouThereAssassin = true;
                }
        }
        kill = new Vector();
        //vpravo dolu
        for (row = this.getPosition().getRow()+1, column = (char)(this.getPosition().getColumn()+1); row <= 8 && column <= 'h'; row++, column++) {
            step = new Position(column, row);
            if(this.desk.isDeserter(step))
                continue;
            if(this.desk.at(step).getFigure() == null) {
                possibility = new Possibility(step, kill);
                possibilities.add(possibility);
            }
            else
                if (this.getColor() == this.desk.at(step).getFigure().getColor())
                    break;
                else {
                    kill.add(new Position(step.getColumn(), step.getRow()));
                    areYouThereAssassin = true;
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
