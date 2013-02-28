/**
 * Homework1: uloha c. 1 z IJA
 * Trida reprezentujici pesce
 */
package ija.homework1.figures;

import ija.homework1.basis.*;

/**
 *
 * @author filgy
 */
public class Pawn extends Figure{
    
    public Pawn(Position position){
        super(position);
    }
    
    @Override
    public boolean canMove(Position position){
        if(super.position.sameColumn(position) && ((position.getRow() - super.position.getRow()) == 1))
            return true;
        else
            return false;
    }
    
}
