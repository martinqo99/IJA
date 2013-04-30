/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */

package queen.basis;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class Position {
    
    private char column;
    private int row;
    
    /**
     * Konstruktor pro objekt Position
     * @param column    znak sloupce
     * @param row       cislo radku
     */
    public Position(char column, int row){
        this.column = column;
        this.row = row;
    }
    
    /**
     * Getter pro znak sloupce
     * @return  znak sloupce
     */
    public char getColumn(){
        return this.column;
    }
    
    /**
     * Getter pro cislo radku
     * @return cislo radku
     */
    public int getRow(){
        return this.row;
    }
    
    /**
     * Porovnani dvou objektu Position
     * @param position
     * @return true pokud jsou shodne, jinak false
     */
    public boolean equals(Position position){
        if(this.column == position.getColumn() && this.row == position.getRow())
            return true;
        else
            return false;
    }
    
    @Override
    public String toString(){
        return Character.toString(column) + Integer.toString(row);
    }
    
}
