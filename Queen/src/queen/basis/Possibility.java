/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */

package queen.basis;

import java.util.Vector;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class Possibility {
    
    private Position position;
    private Vector victims;

    public Possibility(Position position) {
        this.position = new Position(position);
        this.victims = new Vector();
    }
    
    public Position getPosition(){
        return this.position;
    }
    
    public void killVictim(Position victim){
        this.victims.add(new Position(victim));
    }
    
    public Vector getVictims(){
        return this.victims;
    }
    
    public int killed(){
        return this.victims.size();
    }    
}
