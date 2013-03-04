
package ija.homework2;

import java.lang.reflect.*;

//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ija.homework2.basis.*;
import ija.homework2.figures.*;

/**
 * Homework2: uloha c. 2 z IJA
 * Trida testujici implementaci zadani 2. ukolu.
 */
public class Homework2 {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    /** Test zakladnich vlastnosti trid Desk a Position */
    @Test
    public void test01() {
        Desk d1 = new Desk(8);

        Position p1 = d1.getPositionAt('h', 5);
        Position p2 = p1.nextPosition(-1, 2);
        Position p3 = d1.getPositionAt('g', 7);
        assertEquals("Posun z pozice h5 o (-1,2) = pozice g7", p2, p3);

        Position p10 = d1.getPositionAt('h', 5);
        assertEquals("Spravna pozice h5", p10, p1);
        assertNull("Pozice h5 je prazda (bez figurky).", p10.getFigure());

        Position p11 = d1.getPositionAt('h', 12);
        assertNull("Pozice h12 neexistuje.", p11);
    }

    /** Test zakladnich vlastnosti trid Position ve vztahu k Figure */
    @Test
    public void test02() {
        Desk d1 = new Desk(8);
        Position p1 = d1.getPositionAt('d', 2);
        Position p2 = d1.getPositionAt('d', 6);

        Figure f1 = new Rook(p1);
        Figure f2 = new Rook(p2);

        assertEquals("Na pozici d2 je vez.", p1.getFigure(), f1);

        Figure f10 = p1.removeFigure();
        Figure f11 = p2.putFigure(f10);
        assertEquals("Na pozici d6 byla vlozena vez f1.", f10, f1);
        assertEquals("Na pozici d6 je vez f1.", p2.getFigure(), f1);
        assertEquals("Z pozice d6 byla odstranena vez f2.", f11, f2);
        assertNull("Pozice d2 je prazdna", p1.getFigure());
    }


    /** Test tridy Desk a navazanych trid Figure (vcetne odvozenych) a Position */
    @Test
    public void test03() {
        Desk d1 = new Desk(8);

        Figure f1 = new Rook(d1.getPositionAt('d', 2));

        Figure f2 = d1.getPositionAt('d', 2).getFigure();
        assertEquals("Na pozici d2 je vez.", f2, f1);

        Figure f21 = d1.getFigureAt('d', 2);
        assertEquals("Na pozici d2 je vez", f21, f1);
        Figure f22 = d1.getFigureAt('d', 5);
        assertNull("Pozice d5 je prazda (bez figurky).", f22);


        Position p1 = d1.getPositionAt('d', 3);
        assertTrue("Vez se muze presunout z d2 na d3.", f2.canMove(p1));
        assertTrue("Vez se presune z d2 na d3.", f2.move(p1));
        assertTrue("Pozice d2 je prazdna", d1.getPositionAt('d', 2).getFigure() == null);

        Figure f3 = d1.getPositionAt('d', 3).getFigure();
        assertEquals("Na pozici d3 je vez.", f3, f1);
        assertEquals("Vez je na pozici d3.", f3.getPosition(), p1);

        Position p2 = d1.getPositionAt('g', 5);
        assertFalse("Vez se nemuze presunout z d3 na g5.", f2.canMove(p2));
        assertFalse("Pokus o presun veze z d3 na g5 je neuspesny.", f2.move(p2));
    }

    /** Test parametru trid */
    @Test
    public void test10() {
        boolean ok1, ok2;
        ok1 = false;
        Field[] fields = Desk.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Class typeClass = fields[i].getType();
            if (typeClass.isArray() && typeClass.getSimpleName().startsWith("Position[]")) ok1 = true;
        }
        assertTrue("Trida Desk neobsahuje pole objektu typu Position", ok1);

        ok1 = ok2 = false;
        fields = Position.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Class typeClass = fields[i].getType();
            if (typeClass.equals(Figure.class)) ok1 = true;
            if (typeClass.equals(Desk.class)) ok2 = true;
        }
        assertTrue("Trida Position neobsahuje atribut typu Figure", ok1);
        assertTrue("Trida Position neobsahuje atribut typu Desk", ok2);

        assertTrue("Trida Figure ma byt abstraktni.", Modifier.isAbstract(Figure.class.getModifiers()));
        try {
            assert Modifier.isAbstract(Figure.class.getDeclaredMethod("canMove", Position.class).getModifiers()) : "Metoda Figure.canMove(Position) ma byt abstraktni.";
            assert Modifier.isAbstract(Figure.class.getDeclaredMethod("move", Position.class).getModifiers()) == false : "Metoda Figure.move(Position) nema byt abstraktni.";
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
            assert false : "Chyby v deklaraci metod u tridy Figure";
        }

    }

}
