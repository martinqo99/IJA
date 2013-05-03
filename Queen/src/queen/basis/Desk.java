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
import java.util.Vector;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class Desk {

    private int dimension;    
    private Field battleField[];
    
    private Color roundColor;
    private Vector rounds;    

    /**
     * Konstruktor pro objekt Desk
     * @param dimension rozmery hraciho planu
     */
    public Desk(int dimension){
        this.dimension = dimension;        
        this.roundColor = Color.WHITE;        
        this.rounds = new Vector();

        this.prepareBattleGround();
        this.prepareSoldiers();
    }

    private void prepareBattleGround(){
        this.battleField = new Field[this.dimension * this.dimension];

        Color color = Color.BLACK;

        for(char column = 'a'; column < (char)('a' + this.dimension); column++){
            for(int row = 1; row <= this.dimension; row++){
                this.battleField[this.pos(column, row)] = new Field(new Position(column, row), color);
                
                color = (color == Color.WHITE)? Color.BLACK : Color.WHITE;
            }

            color = (color == Color.WHITE)? Color.BLACK : Color.WHITE;
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
     * Zjisteni, jestli je tato pozice v hracim planu
     * @param column znak sloupce
     * @param row cislo radku
     * @return jsme nebo nejsme na hracim planu
     */
    public boolean isDeserter(char column, int row){
        return this.isDeserter(new Position(column, row));
    }

    /**
     * Zjisteni, jestli je tato pozice v hracim planu
     * @param position pozice
     * @return jsme nebo nejsme na hracim planu
     */
    public boolean isDeserter(Position position){

        if(position.getColumn() < 'a' || position.getColumn() >= 'a' + this.dimension)
            return true;

        if(position.getRow() < 1 || position.getRow() > this.dimension)
            return true;

        return false;
    }

    /**
     * Vraci odkaz na pole na dane pozici
     * @param column znak sloupce
     * @param row cislo radku
     * @return odkaz na pole
     */
    public Field at(char column, int row){
        return this.at(new Position(column, row));
    }

    /**
     * Vraci odkaz na pole na dane pozici
     * @param position pozice
     * @return odkaz na pole
     */
    public Field at(Position position){
        return this.battleField[this.pos(position)];
    }

    /**
     * Vraci index dane pozice na hernim planu
     * @param column znak sloupce
     * @param row cislo radku
     * @return index pozice
     */
    public int pos(char column, int row){
        return this.pos(new Position(column, row));
    }

    /**
     * Vraci index dane pozice na hernim planu
     * @param position pozice
     * @return index pozice
     */
    public int pos(Position position){
        int column = Character.toLowerCase(position.getColumn());

        column -= 'a';

        return ((position.getRow() - 1) * this.dimension) + column;
    }

    /**
     * Skoci s figurkou z mista na misto, je-li to mozne
     * @param fromColumn znak zdrojoveho sloupce
     * @param fromRow cislo zdrojoveho radku
     * @param toColumn znak ciloveho sloupce
     * @param toRow cislo ciloveho radku
     * @return bylo pohnuto figurkou
     */
    public Vector move(char fromColumn, int fromRow, char toColumn, int toRow){
        return this.move(new Position(fromColumn, fromRow), new Position(fromColumn, fromRow));
    }

    /**
     * Skoci s figurkou z mista na misto, je-li to mozne
     * @param from zdrojova pozice
     * @param to cilova pozice
     * @return bylo pohnuto figurkou
     */
    public Vector move(Position from, Position to){
        
        /* PRESUNOUT!
        // Nelze skakat mimo sachovnici
        if(this.isDeserter(from) || this.isDeserter(to))
            return false;

        // Skok na stejnou pozici
        if(from.equals(to))
            return false;

        // Nelze skocit na jinou figurku
        if(this.at(to).getFigure() != null)
            return false;
        */

        Figure figure = this.at(from).getFigure();

        // Pokud neni cim skakat
        if(figure == null)
            throw new RuntimeException();
        
        if(figure.getColor() != this.roundColor)
            throw new RuntimeException();

        // Figurka se tam neumi dostat
        if(!figure.canMove(to))
            throw new RuntimeException();

        Vector victims = figure.move(to);
        
        for(int i = 0; i < victims.size(); i++){
            Position position = (Position) victims.get(i);
            
            this.at(position).removeFigure();
        }
        
        this.rounds.add(new Move(from, to, (victims.size() == 0)? false : true));
        
        if(figure.getColor() == Color.WHITE && to.getRow() == this.dimension && !"Rook".equals(figure.getClass().getSimpleName()))
            figure = new Rook(this, to, Color.WHITE);

        if(figure.getColor() == Color.BLACK && to.getRow() == 1 && !"Rook".equals(figure.getClass().getSimpleName()))
            figure = new Rook(this, to, Color.BLACK);

        this.at(from).removeFigure();
        this.at(to).setFigure(figure);
        
        this.roundColor = (this.roundColor == Color.WHITE)? Color.BLACK : Color.WHITE;

        return victims;
    }
    
    public boolean isEndOfAllHope(){

        for(int i = 0; i < this.battleField.length; i++){
            if(this.battleField[i].getFigure() == null)
                continue;
            
            if(this.battleField[i].getFigure().getColor() != this.roundColor)
                continue;
            
            if(this.battleField[i].getFigure().canMovePossibilities().size() > 0)
                return false;
        }
        
        return true;
    }
    
    /**
     *
     * @return
     */
    public Color getRoundColor(){
        return this.roundColor;
    }
    
    /**
     *
     * @return
     */
    public Vector getRounds(){
        return this.rounds;
    }


    /* DEBUG */

    /**
     *
     */
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
