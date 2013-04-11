/** 
 * 
 * @brief	Trida reprezentujici hraci desku
 * @file	Desk.java
 * @date	2013/04/07
 * @authors	Frantisek Kolacek <xkolac12 at stud.fit.vutbr.cz>
 *         	Petr Matyas <xmatya03 at stud.fit.vutbr.cz>
 */

package ukol3.src.basis;

import ukol3.src.figures.*;
import java.util.Iterator;
import java.util.ArrayList;

final public class Desk{
    
    private int dimension;
    private Field battleField[];
    
    public Desk(int dimension){
        this.dimension = dimension;
        
        this.prepareBattleGround();
        this.prepareSoldiers();
    }
    
    private void prepareBattleGround(){
        this.battleField = new Field[this.dimension * this.dimension];
        
        Color fieldColor = Color.BLACK;
        
        for(char column = 'a'; column < (char)('a' + this.dimension); column++){
            for(int row = 1; row <= this.dimension; row++){
                this.battleField[this.pos(column, row)] = new Field(column, row, fieldColor);
                
                if(fieldColor == Color.WHITE) fieldColor = Color.BLACK;
                else fieldColor = Color.WHITE;
            }
            
            if(fieldColor == Color.WHITE) fieldColor = Color.BLACK;
            else fieldColor = Color.WHITE;
        }
    }
    
    private void prepareSoldiers(){
        this.at('a', 7).putFigure(new Stone(Color.BLACK));  
        this.at('b', 6).putFigure(new Stone(Color.BLACK));
        this.at('b', 8).putFigure(new Stone(Color.BLACK));
        this.at('c', 7).putFigure(new Stone(Color.BLACK));
        this.at('d', 6).putFigure(new Stone(Color.BLACK));
        this.at('d', 8).putFigure(new Stone(Color.BLACK));
        this.at('e', 7).putFigure(new Stone(Color.BLACK));
        this.at('f', 6).putFigure(new Stone(Color.BLACK));
        this.at('f', 8).putFigure(new Stone(Color.BLACK));
        this.at('g', 7).putFigure(new Stone(Color.BLACK));
        this.at('h', 6).putFigure(new Stone(Color.BLACK));
        this.at('h', 8).putFigure(new Stone(Color.BLACK));
        
        this.at('a', 1).putFigure(new Stone(Color.WHITE));
        this.at('a', 3).putFigure(new Stone(Color.WHITE));
        this.at('b', 2).putFigure(new Stone(Color.WHITE));
        this.at('c', 1).putFigure(new Stone(Color.WHITE));
        this.at('c', 3).putFigure(new Stone(Color.WHITE));
        this.at('d', 2).putFigure(new Stone(Color.WHITE));
        this.at('e', 1).putFigure(new Stone(Color.WHITE));
        this.at('e', 3).putFigure(new Stone(Color.WHITE));
        this.at('f', 2).putFigure(new Stone(Color.WHITE));
        this.at('g', 1).putFigure(new Stone(Color.WHITE));
        this.at('g', 3).putFigure(new Stone(Color.WHITE));
        this.at('h', 2).putFigure(new Stone(Color.WHITE));
    }
    
    public Field at(char column, int row){
        return this.at(new Position(column, row));
    }
    
    public Field at(Position position){
        return this.battleField[this.pos(position.getColumn(), position.getRow())];
    }
    
    private int pos(char column, int row){
        return this.pos(new Position(column, row));
    }
    
    private int pos(Position position){
        //System.out.println("POS: [" + column + "][" + row + "]");
        int column = Character.toLowerCase(position.getColumn());
        
        column -= 'a';
        
        return ((position.getRow() - 1) * this.dimension) + column;
    }   
    
    private boolean isDeserter(char column, int row){
        return this.isDeserter(new Position(column, row));
    }
    
    private boolean isDeserter(Position position){
        if(position.getColumn() < 'a' || position.getColumn() >= 'a' + this.dimension) 
            return true;
        
        if(position.getRow() < 1 || position.getRow() > this.dimension)
            return true;
        
        return false;
    }
    
    public boolean move(char fromColumn, int fromRow, char toColumn, int toRow){
        return this.move(new Position(fromColumn, fromRow), new Position(toColumn, toRow));
    }
    
    public boolean move(Position from, Position to){
        if(this.isDeserter(from) || this.isDeserter(to))
            return false;
        
        //Nemuze skocit na stejne policko
        if(from.equals(to))
            return false;

        Figure figure = this.at(from).getFigure();
        
        //Neni cim skakat
        if(figure == null)
            return false;
        
        //Nemuze skocit na jinou figurku
        if(this.at(to).getFigure() != null)
            return false;
        
       //Figurka se neumi dostat na tuto pozici
        if(!figure.canMove(from, to))
            return false;
        
        boolean odd = false;
        Position tmp = new Position('a', 1);
        Position next = new Position('a', 1);
        ArrayList<Position> list = new ArrayList<>();
        
        tmp.setRow(from.getRow() + ((from.getRow() < to.getRow())? 1 : (-1)));
        tmp.setColumn((from.getColumn() < to.getColumn())? (char)(from.getColumn() + 1) : (char)(from.getColumn() - 1));
        
        //System.out.println("Trip from: " + from.getColumn() + from.getRow() + " to: " + to.getColumn() + to.getRow());
        
        //Hledani cesty
        while(!tmp.equals(to)){
			//Skoky
            if(this.at(tmp).getFigure() != null){
                //Nelze preskocit vlastniho            
                if(this.at(tmp).getFigure().getColor() == figure.getColor())
					return false;
                 //Nepritel
                else{
					if(odd) return false;
                   else{ odd = true; list.add(new Position(tmp.getColumn(), tmp.getRow()));}
                }
			}
			else{
				odd = false;
			}

            next.setRow(tmp.getRow() + ((from.getRow() < to.getRow())? 1 : (-1)));
            next.setColumn((from.getColumn() < to.getColumn())? (char)(tmp.getColumn() + 1) : (char)(tmp.getColumn() - 1));
            //System.out.println(" - move from: " + tmp.getColumn() + tmp.getRow() + " to: " + next.getColumn() + next.getRow());
           
            tmp = next;
        }

        //Bitevni vrava
        Iterator<Position> it = list.iterator();
        while(it.hasNext()){
            Position obj = it.next();
			
            this.at(obj).removeFigure();
        }
        
        this.at(from).removeFigure();
        this.at(to).putFigure(figure);
        
        if(figure.getColor() == Color.WHITE && to.getRow() == this.dimension)
			this.at(to).putFigure(new Queen(Color.WHITE));

		if(figure.getColor() == Color.BLACK && to.getRow() == 0)
			this.at(to).putFigure(new Queen(Color.BLACK));
			
        return true;
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
                        System.out.print((this.at(column, row).getFigure().getRole() == Role.QUEEN)? "B|" : "b|");
                    else
                        System.out.print((this.at(column, row).getFigure().getRole() == Role.QUEEN)? "W|" : "w|");               
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
