/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package queen.figures;

import queen.basis.*;
import java.awt.*;

/**
 *
 * @author xkolac12 <xkolac12 at stud.fit.vutbr.cz>
 */
public class Stone extends Figure{
    
    public Stone(Position position, Color color){
        super(position, color);
    }
    
    @Override
    public boolean canMove(Position position){
        return true;
    }
}
