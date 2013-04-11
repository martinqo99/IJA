/** 
 * 
 * @brief	Trida reprezentujici figkurku - Kamen
 * @file	Stone.java
 * @date	2013/04/07
 * @authors	Frantisek Kolacek <xkolac12 at stud.fit.vutbr.cz>
 *         	Petr Matyas <xmatya03 at stud.fit.vutbr.cz>
 */

package ukol3.src.figures;
import ukol3.src.basis.*;

public class Stone extends Figure{

    public Stone(Color color){
        super(color);
    }

    @Override
    public boolean canMove(Position currentPosition, Position nextPosition){

        if(this.color == Color.BLACK && nextPosition.getRow() >= currentPosition.getRow())
            return false;
        
        if(this.color == Color.WHITE && nextPosition.getRow() <= currentPosition.getRow())
            return false;
        
        int stepRow = nextPosition.getRow() - currentPosition.getRow();
        int stepColumn = nextPosition.getColumn() - currentPosition.getColumn();
        
        if(stepRow < 0)
            stepRow *= (-1);
        
        if(stepColumn < 0)
            stepColumn *= (-1);
        
        if(stepColumn != stepRow || stepColumn > 2)
            return false;
        
        return true;
    }
}
