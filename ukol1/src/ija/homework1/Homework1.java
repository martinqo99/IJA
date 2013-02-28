
package ija.homework1;

import ija.homework1.basis.*;
import ija.homework1.figures.*;

import java.lang.reflect.*;

/**
 * Homework1: uloha c. 1 z IJA
 * Trida testujici implementaci zadani 1. ukolu.
 */
public class Homework1 {

    public static void main(String[] argv) {
        testPosition();
        testPawn();
        testRook();
        testClasses();
        System.out.println("OK");
    }

    /** Test tridy Position */
    public static void testPosition() {
        Position p0 = new Position('a',6);
        Position p1 = new Position('b',5);
        Position p2 = new Position('c',3);
        Position p3 = new Position('c',8);
        Position p4 = new Position('f',3);

        assert p2.sameRow(p4) : "c3 musi byt na stejnem radku jako f3";
        assert p2.sameColumn(p3) : "c3 musi byt na stejnem sloupci jako c8";

        assert p1.nextPosition(-1,1).equals(p0) : "posun pozice z b5 na a6";
    }

    /** Test tridy Pawn */
    public static void testPawn() {
        Figure p1 = new Pawn(new Position('c', 2));

        assert p1.canMove(new Position('c', 3)) : "test posunu pesce z c2 na c3";
        assert p1.canMove(new Position('e', 2)) == false : "test posunu pesce z c2 na e2";
        assert p1.canMove(new Position('b', 1)) == false : "test posunu pesce z c2 na b1";

        assert p1.move(new Position('c', 3)) : "presun pesce z c2 na c3";
        assert p1.move(new Position('c', 2)) == false : "presun pesce z c3 na c2";
        assert p1.move(p1.getPosition().nextPosition(1,-1)) == false : "presun pesce z c3 na d2";
        assert p1.move(p1.getPosition().nextPosition(0,1)) : "presun pesce z c3 na c4";

        assert p1.isAtPosition(new Position('c', 4)) : "test pozice pesce na c4";
    }

    /** Test tridy Rook */
    public static void testRook() {
        Figure p1 = new Rook(new Position('c', 2));

        assert p1.canMove(new Position('c', 7)) : "test posunu veze z c2 na c7";
        assert p1.canMove(new Position('f', 2)) : "test posunu pesce z c2 na f2";
        assert p1.canMove(new Position('e', 6)) == false : "test posunu veze z c2 na e6";

        assert p1.move(new Position('c', 5)) : "presun veze z c2 na c5";
        assert p1.move(new Position('g', 8)) == false : "presun veze z c5 na g8";
        assert p1.move(p1.getPosition().nextPosition(1,-1)) == false : "presun veze z c5 na d4";
        assert p1.move(p1.getPosition().nextPosition(0,3)) : "presun veze z c5 na c8";

        assert p1.isAtPosition(new Position('c', 8)) : "test pozice veze na c8";
    }

    /** Test parametru trid */
    public static void testClasses() {
        boolean ok = false;
        Field[] fields = Figure.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Class typeClass = fields[i].getType();
            if (typeClass.equals(Position.class)) ok=true;
        }
        assert ok : "Trida Figure neobsahuje objekt tridy Position";

        assert Modifier.isAbstract(Figure.class.getModifiers()) : "Trida Figure ma byt abstraktni.";
        try {
            assert Modifier.isAbstract(Figure.class.getDeclaredMethod("canMove", Position.class).getModifiers()) : "Metoda Figure.canMove(Position) ma byt abstraktni.";
            assert Modifier.isAbstract(Figure.class.getDeclaredMethod("move", Position.class).getModifiers()) == false : "Metoda Figure.move(Position) nema byt abstraktni.";
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
            assert false : "Chyby v deklaraci metod u tridy Figure";
        }

    }

}
