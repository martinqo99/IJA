/*
 * Projekt: Queen
 * Predmet: IJA - Seminar Java
 * Autori:
 *          xkolac12 < xkolac12 @ stud.fit.vutbr.cz >
 *          xmatya03 < xmatya03 @ stud.fit.vutbr.cz >
 */
package queen.basis;

import java.awt.Color;

/**
 * @author      Frantisek Kolacek <xkolac12 @ stud.fit.vutbr.cz>
 * @author      Petr Matyas <xmatya03 @ stud.fit.vutbr.cz>
 * @version     0.91
 * @since       2013-04-30
 */
public class Move {

    private Position from;
    private Position to;
    private boolean fight;

    /**
     * Konstruktor pohybu figurky
     * @param from odkud
     * @param to kam
     * @param fight odstrani nejakou figurku?
     */
    public Move(Position from, Position to, boolean fight){
        this.from = new Position(from);
        this.to = new Position(to);
        this.fight = fight;
    }

    /**
     * Konstruktor pohybu figurky
     * @param move pohyb figurky
     */
    public Move(Move move){
        this.from = new Position(move.getFrom());
        this.to = new Position(move.getTo());
        this.fight = move.isFight();
    }

    /**
     * Getter odkud se figurka pohybuje
     * @return pozice odkud se figurka pohybuje
     */
    public Position getFrom(){
        return this.from;
    }

    /**
     * Getter kam se figurka pohybuje
     * @return kam se figurka pohybuje
     */
    public Position getTo(){
        return this.to;
    }

    /**
     * Metoda pro zjisteni, jestli probehl boj
     * @return probehl boj?
     */
    public boolean isFight(){
        return this.fight;
    }

    /**
     * Prevedeni objektu move na string
     * @return string odkud kam se figurka pohybuje
     */
    @Override
    public String toString(){
        String output = "";

        output += this.from.toString();

        output += this.fight? "x" : "-";

        output += this.to.toString();

        return output;
    }
}
