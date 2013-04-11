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
		System.out.println("TEST #1");
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
    }

    @Test
    public void test02() {
		System.out.println("TEST #2");
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
		System.out.println("TEST #3");
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
		System.out.println("TEST #4");
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

        System.out.println("Nelze skocit na jinou figurku e7->d6");
        assertTrue("Nelze skocit na jinou figurku e7->d6 - FAILED", !desk.move('e', 7, 'd', 6));
    }

    @Test
    public void test05() {
		System.out.println("TEST #5");
        Desk desk = new Desk(8);
        
        //Iniciliazace
        desk.move('a', 3, 'b', 4);
        desk.move('e', 3, 'f', 4);
        desk.move('f', 4, 'e', 5);
        desk.move('g', 3, 'f', 4);
        desk.move('b', 6, 'a', 5);
        desk.move('d', 6, 'c', 5);        
        
        System.out.println("Skok pres nepritele b4->d6");
        assertTrue("Skok pres nepritele b4->d6 - FAILED", desk.move('b', 4, 'd', 6));
        
        System.out.println("Skok pres nepritele f6->d4");
        assertTrue("Skok pres nepritele f6->d4 - FAILED", desk.move('f', 6, 'd', 4));
        
        desk.move('g', 7, 'f', 6);

        System.out.println("Skok pres nepritele c3->e5");
        assertTrue("Skok pres nepritele c3->e5 - FAILED", desk.move('c', 3, 'e', 5));
        
        desk.move('f', 6, 'g', 5);
        desk.move('h', 8, 'g', 7);
        desk.move('e', 5, 'f', 6);
        
        System.out.println("Skok pres nepritele f6->h8");
        assertTrue("Skok pres nepritele f6->h8 - FAILED", desk.move('f', 6, 'h', 8));
        
        System.out.println("Na pozici h8 je nyni vez");
        assertTrue("Na pozici h8 je nyni vez - FAILED", desk.at('h', 8).getFigure().getRole() == Role.QUEEN);

        desk.move('e', 7, 'f', 6);

        System.out.println("Skok pres nepritele h8->d4");
        assertTrue("Skok pres nepritele h8->d4 - FAILED", desk.move('h', 8, 'd', 4));

    }
}

