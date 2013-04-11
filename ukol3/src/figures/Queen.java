/** 
 * 
 * @brief	Trida reprezentujici figkurku - Kralovna
 * @file	Queen.java
 * @date	2013/04/07
 * @authors	Frantisek Kolacek <xkolac12 at stud.fit.vutbr.cz>
 *         	Petr Matyas <xmatya03 at stud.fit.vutbr.cz>
 */

package ukol3.src.figures;
import ukol3.src.basis.*;

public class Queen extends Figure{
    
    public Queen(Color color){
        super(color);
    }

    @Override
    public boolean canMove(Position currentPosition, Position nextPosition){
        int stepRow = nextPosition.getRow() - currentPosition.getRow();
        int stepColumn = nextPosition.getColumn() - currentPosition.getColumn();
        
        if(stepColumn < 0)
			stepColumn *= (-1);
        
        if(stepColumn < 0)
            stepColumn *= (-1);
        
        if(stepColumn != stepRow)
            return false;
        
        return true;
    }
}
