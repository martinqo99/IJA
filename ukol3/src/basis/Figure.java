/** 
 * 
 * @brief	Trida reprezentujici obecnou figurku
 * @file	Figure.java
 * @date	2013/04/07
 * @authors	Frantisek Kolacek <xkolac12 at stud.fit.vutbr.cz>
 *         	Petr Matyas <xmatya03 at stud.fit.vutbr.cz>
 */

package ukol3.src.basis;

abstract public class Figure{
    
    protected Color color;

    public Figure(Color color){
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }
    
    abstract public boolean canMove(Position currentPosition, Position nextPosition);
}
