/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package queen.basis;

import queen.figures.*;
import java.awt.*;
import java.util.Iterator;
import java.util.ArrayList;

/**
 *
 * @author xkolac12 <xkolac12 at stud.fit.vutbr.cz>
 */
public class Desk {
    
    private int dimension;
    private Field battleField[];    
    
    public Desk(int dimension){
        this.dimension = dimension;
        
        this.prepareBattleGround();
        this.prepareSoldiers();
        
        this.debug();
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
        this.at('a', 7).setFigure(new Stone(new Position('a', 7), Color.BLACK));  
        this.at('b', 6).setFigure(new Stone(new Position('b', 6), Color.BLACK));
        this.at('b', 8).setFigure(new Stone(new Position('b', 8), Color.BLACK));
        this.at('c', 7).setFigure(new Stone(new Position('c', 7), Color.BLACK));
        this.at('d', 6).setFigure(new Stone(new Position('d', 6), Color.BLACK));
        this.at('d', 8).setFigure(new Stone(new Position('d', 8), Color.BLACK));
        this.at('e', 7).setFigure(new Stone(new Position('e', 7), Color.BLACK));
        this.at('f', 6).setFigure(new Stone(new Position('f', 6), Color.BLACK));
        this.at('f', 8).setFigure(new Stone(new Position('f', 8), Color.BLACK));
        this.at('g', 7).setFigure(new Stone(new Position('g', 7), Color.BLACK));
        this.at('h', 6).setFigure(new Stone(new Position('h', 6), Color.BLACK));
        this.at('h', 8).setFigure(new Stone(new Position('h', 8), Color.BLACK));
        
        this.at('a', 1).setFigure(new Stone(new Position('a', 1), Color.WHITE));
        this.at('a', 3).setFigure(new Stone(new Position('a', 3), Color.WHITE));
        this.at('b', 2).setFigure(new Stone(new Position('b', 2), Color.WHITE));
        this.at('c', 1).setFigure(new Stone(new Position('c', 1), Color.WHITE));
        this.at('c', 3).setFigure(new Stone(new Position('c', 3), Color.WHITE));
        this.at('d', 2).setFigure(new Stone(new Position('d', 2), Color.WHITE));
        this.at('e', 1).setFigure(new Stone(new Position('e', 1), Color.WHITE));
        this.at('e', 3).setFigure(new Stone(new Position('e', 3), Color.WHITE));
        this.at('f', 2).setFigure(new Stone(new Position('f', 2), Color.WHITE));
        this.at('g', 1).setFigure(new Stone(new Position('g', 1), Color.WHITE));
        this.at('g', 3).setFigure(new Stone(new Position('g', 3), Color.WHITE));
        this.at('h', 2).setFigure(new Stone(new Position('h', 2), Color.WHITE));
    }
    
    public Field at(char column, int row){
        return this.at(new Position(column, row));
    }
    
    public Field at(Position position){
        return this.battleField[this.pos(position)];
    }
    
    public int pos(char column, int row){
        return this.pos(new Position(column, row));
    }
    
    public int pos(Position position){
        int column = Character.toLowerCase(position.getColumn());
        
        column -= 'a';
        
        return ((position.getRow() - 1) * this.dimension) + column;
    }
    
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
