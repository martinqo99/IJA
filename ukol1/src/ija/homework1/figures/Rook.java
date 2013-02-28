/**
 * Homework1: uloha c. 1 z IJA
 * Trida reprezentujici vez
 */
package ija.homework1.figures;

import ija.homework1.basis.*;
/**
 *
 * @author xkolac12
 */
public class Rook extends Figure{
    
    public Rook(Position position){
        super(position);
    }
    
    @Override
    public boolean canMove(Position position){
        if(super.position.sameRow(position) || super.position.sameColumn(position))
            return true;
        else
            return false;
    }
}
