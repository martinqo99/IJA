/**
 * Homework1: uloha c. 2 z IJA
 * Trida reprezentujici vez
 */
package ija.homework2.figures;

import ija.homework2.basis.*;
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
