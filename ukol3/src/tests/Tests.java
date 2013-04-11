/** 
 * 
 * @brief	Testovaci trida
 * @file	Tests.java
 * @date	2013/04/07
 * @authors	Frantisek Kolacek <xkolac12 at stud.fit.vutbr.cz>
 *         	Petr Matyas <xmatya03 at stud.fit.vutbr.cz>
 */
 
package ukol3.src.tests;

import java.lang.reflect.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import ukol3.src.basis.*;
import ukol3.src.figures.*;

public class Tests {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    
    @Test
    public void test01() {
    
		
		Desk desk = new Desk(8);
		System.out.println("Posun kamene e3->b4");
		assertTrue("Posun kamene a3->b4 - FAILED", desk.move('a', 3, 'b', 4));
		
		System.out.println("Posun kamene e3->f4");
		assertTrue("Posun kamene e3->f4 - FAILED", desk.move('e', 3, 'f', 4));
		
		System.out.println("Posun kamene f4->e5");
		assertTrue("Posun kamene f4->e5 - FAILED", desk.move('f', 4, 'e', 5));
		
		System.out.println("Posun neexistujici figury f4->e5");
		assertTrue("Posun neexistujici figury f4->e5 - FAILED", !desk.move('f', 4, 'e', 5));
		
		System.out.println("Kamen nelze posouvat smerem dozadu f4->g3");
		assertTrue("Posun neexistujici figury f4->e5 - FAILED", !desk.move('f', 4, 'g', 3));

		
    /*
        Desk desk = new Desk(8);
        
        desk.debug();

        assert(desk.move('a', 3, 'b', 4));
        assert(desk.move('e', 3, 'f', 4));
        assert(desk.move('f', 4, 'e', 5));
        assert(!desk.move('f', 4, 'e', 5)); // there is no figure
        
        assert(desk.move('g', 3, 'f', 4));
        assert(!desk.move('f', 4, 'g', 3)); // cannot go back with stone
        
        assert(!desk.move('f', 4, 'f', 2)); // only diagonale step is allowed
        assert(!desk.move('f', 4, 'f', 3)); // only diagonale step is allowed
        assert(!desk.move('f', 4, 'f', 4)); // cannot go to same field
        assert(!desk.move('f', 4, 'f', 5)); // only diagonale step is allowed
        assert(!desk.move('f', 4, 'f', 6)); // only diagonale step is allowed
        
        assert(!desk.move('a', 1, 'a', 0)); // deserters is not allowed
        assert(!desk.move('h', 2, 'i', 3)); // deserters is not allowed
        
        assert(!desk.move('c', 7, 'b', 6)); // cannot jump on another figure

        assert(desk.move('b', 6, 'a', 5));
        assert(desk.move('d', 6, 'c', 5));
        
        assert(desk.move('b', 4, 'd', 6));
        assert(desk.move('f', 6, 'd', 4));
        
        assert(!desk.move('e', 7, 'c', 5)); // cannot, there is figure
        
        assert(desk.move('d', 4, 'e', 3));
        
        assert(desk.move('f', 2, 'd', 4));
        
        //desk.debug();
        /*
        Position p1 = d1.getPositionAt('h', 5);
        Position p2 = p1.nextPosition(-1, 2);
        Position p3 = d1.getPositionAt('g', 7);
        assertEquals("Posun z pozice h5 o (-1,2) = pozice g7", p2, p3);

        Position p10 = d1.getPositionAt('h', 5);
        assertEquals("Spravna pozice h5", p10, p1);
        assertNull("Pozice h5 je prazdna (bez figurky).", p10.getFigure());
        System.out.println("Test presunu figury - OK"); 
        Position p11 = d1.getPositionAt('h', 12);
        assertNull("Pozice h12 neexistuje.", p11);
        System.out.println("Test pristupu na pozici h12 mimo desku - OK"); 
        Position p12 = d1.getPositionAt('d',3);
        Position p13 = d1.getPositionAt('f',5);
        assertTrue("d3 a f5 jsou v diagonale",p12.sameDiagonal(p13));
        System.out.println("kontrola diagonaly d3 a f5 - OK"); 
        System.out.println("Test tridy Desk a Position - Kompletni");
        */
        

    }

    @Test
    public void test02() {
        Desk desk = new Desk(8);
        
        //Iniciliazace
        desk.move('a', 3, 'b', 4);
        desk.move('e', 3, 'f', 4);
        desk.move('f', 4, 'e', 5);
        desk.move('g', 3, 'f', 4);
        
        System.out.println("Pohyb mimo diagonaly neni povolen f4->f2");
        assertTrue("Pohyb mimo diagonaly neni povolen f4->f2 - FAILED", !desk.move('f', 4, 'f', 2));
        
		System.out.println("Pohyb mimo diagonaly neni povolen f4->f3");
        assertTrue("Pohyb mimo diagonaly neni povolen f4->f3 - FAILED", !desk.move('f', 4, 'f', 3));
		
		System.out.println("Pohyb mimo diagonaly neni povolen f4->f5");
        assertTrue("Pohyb mimo diagonaly neni povolen f4->f5 - FAILED", !desk.move('f', 4, 'f', 5));
        
        System.out.println("Pohyb mimo diagonaly neni povolen f4->e4");
        assertTrue("Pohyb mimo diagonaly neni povolen f4->e4 - FAILED", !desk.move('f', 4, 'e', 4));

    }

    @Test
    public void test03() {
        Desk desk = new Desk(8);
        
        //Iniciliazace
        desk.move('a', 3, 'b', 4);
        desk.move('e', 3, 'f', 4);
        desk.move('f', 4, 'e', 5);
        desk.move('g', 3, 'f', 4);
        
        System.out.println("Skok na stejne pole neni povolen f4->f4");
        assertTrue("Skok na stejne pole neni povolen f4->f4 - FAILED", !desk.move('f', 4, 'f', 4));
        
        System.out.println("Skok mimo hranici hraci plochy neni povolen a1->a0");
        assertTrue("Skok mimo hranici hraci plochy neni povolen a1->a0 - FAILED", !desk.move('a', 1, 'a', 0));
        
        System.out.println("Skok mimo hranici hraci plochy neni povolen a1->a0");
        assertTrue("Skok mimo hranici hraci plochy neni povolen a1->a0 - FAILED", !desk.move('h', 2, 'i', 3));
    }

    @Test
    public void test04() {
        Desk desk = new Desk(8);
        
        //Iniciliazace
        desk.move('a', 3, 'b', 4);
        desk.move('e', 3, 'f', 4);
        desk.move('f', 4, 'e', 5);
        desk.move('g', 3, 'f', 4);
        
        System.out.println("Nelze preskocit vlastni figurku c7->b6");
        assertTrue("Nelze preskocit vlastni figurku c7->b6 - FAILED", !desk.move('c', 7, 'b', 6));
        
        desk.move('b', 6, 'a', 5);
        desk.move('d', 6, 'c', 5);        
        desk.move('b', 4, 'd', 6);
        desk.move('f', 6, 'd', 4);
        
        System.out.println("Nelze skocit na jinou figurku e7->c5");
        assertTrue("Nelze skocit na jinou figurku e7->c5 - FAILED", !desk.move('e', 7, 'c', 5));
    }

    @Test
    public void test05() {
        Desk desk = new Desk(8);
        
        //Iniciliazace
        desk.move('a', 3, 'b', 4);
        desk.move('e', 3, 'f', 4);
        desk.move('f', 4, 'e', 5);
        desk.move('g', 3, 'f', 4);
        desk.move('b', 6, 'a', 5);
        desk.move('d', 6, 'c', 5);        
        assertTrue("Skok pres nepritele b4->d6 - FAILED", desk.move('b', 4, 'd', 6));
        
        //desk.move('f', 6, 'd', 4);
        
        //desk.move('g', 7, 'f', 6); //p
        
        //desk.move('f', 6, 'e', 5);

        desk.debug();
    }
}
