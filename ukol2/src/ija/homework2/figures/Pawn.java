/**
 * Homework1: uloha c. 2 z IJA
 * Trida reprezentujici pesce
 */
package ija.homework2.figures;

import ija.homework2.basis.*;
/**
 *
 * @author xkolac12
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
