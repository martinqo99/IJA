/** 
 * 
 * @brief       Class represents figure: stone
 * @file	Stone.java
 * @date	2013/02/28
 * @authors	Frantisek Kolacek <xkolac12 at stud.fit.vutbr.cz>
 *              Pavel Matyas <xmatya03 at stud.fit.vutbr.cz>
 */

package queen.figures;

import queen.basis.*;

/**
 *
 * @author xkolac12 <xkolac12 at stud.fit.vutbr.cz>
 */
public class Stone extends Figure{
    
    /**
     *
     * @param position
     */
    public Stone(Position position){
        super(position);
    }
    
    /**
     *
     * @param position
     * @return
     */
    @Override
    public boolean canMove(Position position){
        return true;
    }
}
