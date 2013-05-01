/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */
package queen.basis;

import java.awt.Color;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class Move {
    
    private Position from;
    private Position to;
    private boolean fight;
    
    /**
     *
     * @param from
     * @param to
     * @param fight
     */
    public Move(Position from, Position to, boolean fight){
        this.from = new Position(from);
        this.to = new Position(to);
        this.fight = fight;
    }
    
    /**
     *
     * @param move
     */
    public Move(Move move){
        this.from = new Position(move.getFrom());
        this.to = new Position(move.getTo());
        this.fight = move.isFight();
    }

    /**
     *
     * @return
     */
    public Position getFrom(){
        return this.from;
    }
    
    /**
     *
     * @return
     */
    public Position getTo(){
        return this.to;
    }
    
    /**
     *
     * @return
     */
    public boolean isFight(){
        return this.fight;
    }
    
    @Override
    public String toString(){
        String output = "";
        
        output += this.from.toString();
        
        output += this.fight? "x" : "-";
        
        output += this.to.toString();
        
        return output;
    }
}
