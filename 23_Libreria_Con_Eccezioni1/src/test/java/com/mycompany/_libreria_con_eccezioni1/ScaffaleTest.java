/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany._libreria_con_eccezioni1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class ScaffaleTest
{
    
   
    /**
     * Test of getLibro method, of class Scaffale.
     */
    @Test
    public void testGetLibro() throws Exception 
    {
        Scaffale s1= new Scaffale();
        Libro l1=new Libro("I promessi sposi","Manzoni",600);
        s1.setLibro(l1, 1, 1);
        
        Libro atteso, attuale;
        atteso=l1;
        attuale=s1.getLibro(1, 1);
        
        boolean r=atteso.equals(attuale);
        
        assertEquals("Metodo getLibro su Scaffale",atteso,attuale);
    }

    
    
}
