/** 
 * 
 * @brief       Class represents general figure
 * @file	Figure.java
 * @date	2013/02/28
 * @authors	Frantisek Kolacek <xkolac12 at stud.fit.vutbr.cz>
 *              Pavel Matyas <xmatya03 at stud.fit.vutbr.cz>
 */

package queen.basis;

/**
 *
 * @author xkolac12 <xkolac12 at stud.fit.vutbr.cz>
 */
abstract public class Figure {
    
    protected Position position;
    
    public Figure(Position position){
        this.position = position;
    }
    
    /**
     *
     * @param position
     * @return
     */
    public boolean isAtPosition(Position position){
        if(this.position.equals(position))
            return true;
        
        return false;
    }
    
    /**
     *
     * @return
     */
    public Position getPosition(){
        return this.position;
    }
    
    public boolean move(Position position){
        if(this.canMove(position)){
            this.position = position;
            
            return true;
        }
        
        return false;
    }
    
    abstract public boolean canMove(Position position);
    

}
