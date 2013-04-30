/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */

package queen.basis;

import queen.figures.*;
import java.awt.*;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class Desk {
    
    private int dimension;
    private Field battleField[];    
    
    public Desk(int dimension){
        this.dimension = dimension;
        
        this.prepareBattleGround();
        this.prepareSoldiers();
        
        //this.debug();
    }
    
    private void prepareBattleGround(){
        this.battleField = new Field[this.dimension * this.dimension];
        
        Color color = Color.BLACK;
        
        for(char column = 'a'; column < (char)('a' + this.dimension); column++){
            for(int row = 1; row <= this.dimension; row++){
                this.battleField[this.pos(column, row)] = new Field(new Position(column, row), color);
                
                if(color == Color.BLACK)
                    color = Color.WHITE;
                else
                    color = Color.BLACK;
            }
            
            if(color == Color.BLACK)
                color = Color.WHITE;
            else
                color = Color.BLACK;
        }
    }
    
    private void prepareSoldiers(){
        this.at('a', 7).setFigure(new Stone(this, new Position('a', 7), Color.BLACK));  
        this.at('b', 6).setFigure(new Stone(this, new Position('b', 6), Color.BLACK));
        this.at('b', 8).setFigure(new Stone(this, new Position('b', 8), Color.BLACK));
        this.at('c', 7).setFigure(new Stone(this, new Position('c', 7), Color.BLACK));
        this.at('d', 6).setFigure(new Stone(this, new Position('d', 6), Color.BLACK));
        this.at('d', 8).setFigure(new Stone(this, new Position('d', 8), Color.BLACK));
        this.at('e', 7).setFigure(new Stone(this, new Position('e', 7), Color.BLACK));
        this.at('f', 6).setFigure(new Stone(this, new Position('f', 6), Color.BLACK));
        this.at('f', 8).setFigure(new Stone(this, new Position('f', 8), Color.BLACK));
        this.at('g', 7).setFigure(new Stone(this, new Position('g', 7), Color.BLACK));
        this.at('h', 6).setFigure(new Stone(this, new Position('h', 6), Color.BLACK));
        this.at('h', 8).setFigure(new Stone(this, new Position('h', 8), Color.BLACK));
        
        this.at('a', 1).setFigure(new Stone(this, new Position('a', 1), Color.WHITE));
        this.at('a', 3).setFigure(new Stone(this, new Position('a', 3), Color.WHITE));
        this.at('b', 2).setFigure(new Stone(this, new Position('b', 2), Color.WHITE));
        this.at('c', 1).setFigure(new Stone(this, new Position('c', 1), Color.WHITE));
        this.at('c', 3).setFigure(new Stone(this, new Position('c', 3), Color.WHITE));
        this.at('d', 2).setFigure(new Stone(this, new Position('d', 2), Color.WHITE));
        this.at('e', 1).setFigure(new Stone(this, new Position('e', 1), Color.WHITE));
        this.at('e', 3).setFigure(new Stone(this, new Position('e', 3), Color.WHITE));
        this.at('f', 2).setFigure(new Stone(this, new Position('f', 2), Color.WHITE));
        this.at('g', 1).setFigure(new Stone(this, new Position('g', 1), Color.WHITE));
        this.at('g', 3).setFigure(new Stone(this, new Position('g', 3), Color.WHITE));
        this.at('h', 2).setFigure(new Stone(this, new Position('h', 2), Color.WHITE));
    }    
    
    /**
     *
     * @param column
     * @param row
     * @return
     */
    public boolean isDeserter(char column, int row){
        return this.isDeserter(new Position(column, row));
    }
    
    /**
     *
     * @param position
     * @return
     */
    public boolean isDeserter(Position position){
        
        if(position.getColumn() < 'a' || position.getColumn() >= 'a' + this.dimension)
            return true;
        
        if(position.getRow() < 1 || position.getRow() > this.dimension)
            return true;
        
        return false;
    }
    
    /**
     *
     * @param column
     * @param row
     * @return
     */
    public Field at(char column, int row){
        return this.at(new Position(column, row));
    }
    
    /**
     *
     * @param position
     * @return
     */
    public Field at(Position position){
        return this.battleField[this.pos(position)];
    }
    
    /**
     *
     * @param column
     * @param row
     * @return
     */
    public int pos(char column, int row){
        return this.pos(new Position(column, row));
    }
    
    /**
     *
     * @param position
     * @return
     */
    public int pos(Position position){
        int column = Character.toLowerCase(position.getColumn());
        
        column -= 'a';
        
        return ((position.getRow() - 1) * this.dimension) + column;
    }
    
    /**
     *
     * @param fromColumn
     * @param fromRow
     * @param toColumn
     * @param toRow
     * @return
     */
    public boolean move(char fromColumn, int fromRow, char toColumn, int toRow){
        return this.move(new Position(fromColumn, fromRow), new Position(fromColumn, fromRow));
    }
    
    /**
     *
     * @param from
     * @param to
     * @return
     */
    public boolean move(Position from, Position to){
        // Nelze skakat mimo sachovnici
        if(this.isDeserter(from) || this.isDeserter(to))
            return false;
        
        // Skok na stejnou pozici
        if(from.equals(to))
            return false;
        
        // Nelze skocit na jinou figurku
        if(this.at(to).getFigure() != null)
            return false;
        
        Figure figure = this.at(from).getFigure();

        // Pokud neni cim skakat
        if(figure == null)
            return false;
        
        // Figurka se tam neumi dostat
        if(!figure.canMove(to))
            return false;
        
        figure.move(to);
        
        if(figure.getColor() == Color.WHITE && to.getRow() == this.dimension)
            figure = new Rook(this, to, Color.WHITE);
        
        if(figure.getColor() == Color.BLACK && to.getRow() == 1)
            figure = new Rook(this, to, Color.BLACK);
        
        this.at(from).removeFigure();
        this.at(to).setFigure(figure); 
        
        return true;
    }
    
    
    /* DEBUG */
    
    public void debug(){
        System.out.print("#|");
        
        for(char column = 'a'; column < (char)('a' + this.dimension); column++)
            System.out.print(column + "|");
        
        System.out.print("\n-+-+-+-+-+-+-+-+-+\n");
        
        int rows = this.dimension;
        for(int row = this.dimension; row >= 1; row--){
            System.out.print((rows--) + "|");
            for(char column = 'a'; column < (char)('a' + this.dimension); column++){            
                //Zadna figurka
                if(this.at(column, row).getFigure() == null){
                    if(this.at(column, row).getColor() == Color.BLACK)
                        System.out.print("x|");
                    else
                        System.out.print(" |");
                }
                //Figurka se nasla
                else{
                    if(this.at(column, row).getFigure().getColor() == Color.BLACK)
                        System.out.print((this.at(column, row).getFigure().getClass().getSimpleName() == "Rook")? "B|" : "b|");
                    else
                        System.out.print((this.at(column, row).getFigure().getClass().getSimpleName() == "Rook")? "W|" : "w|");               
                }
            }
            System.out.print("\n");
        }
        
        System.out.print("-+-+-+-+-+-+-+-+-+\n#|");
        for(char column = 'a'; column < (char)('a' + this.dimension); column++)
            System.out.print(column + "|");
        
        System.out.print("\n");
    }
    
}
