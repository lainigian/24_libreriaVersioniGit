/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany._libreria_con_eccezioni1;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Mensola implements Serializable
{
    //atributi
    private Libro[] volumi;
    private static int NUM_MAX_VOLUMI=15;
    
    public Mensola()
    {
        volumi=new Libro[NUM_MAX_VOLUMI];
    }
    
    public Mensola (Mensola m)
    {
        volumi=new Libro[NUM_MAX_VOLUMI];
        for (int i=0;i<getNumMaxVolumi();i++)
        {
            volumi[i]=m.getVolume(i);     
        } 
    }
    
    public Mensola (Libro[] elencoLibri)
    {
        volumi=new Libro[NUM_MAX_VOLUMI];
        
        int numeroLibri=0;
        //se elencoLibri non contiene libri
        if (elencoLibri.length==0)
            return;
        
        if (elencoLibri.length>getNumMaxVolumi())
            numeroLibri=getNumMaxVolumi();
        else
            numeroLibri=elencoLibri.length;
        
        for (int i=0;i<numeroLibri;i++)
        {
            if (elencoLibri[i]!=null)
            {
                volumi[i]=new Libro(elencoLibri[i]);
            }
        }
        
    }
   
    
    public static int getNumMaxVolumi()
    {
        return NUM_MAX_VOLUMI;
    }
    
    public int getNumVolumi()
    {
        int contatore=0;
        for (int i=0;i<getNumMaxVolumi();i++)
        {
            if (volumi[i]!=null)
                contatore++;
        }
        return contatore;
    }
    
    
    /*
    Aggiunge un libro alla mensola nella posizione indicata
    se la posizione non esiste --> return -1
    se la posizione è già occupata --> return -2
    se il libro viene posizionato --> return posizione
    */
    
    public int setVolume(Libro volume, int posizione)
    {
        /*if (posizione<0 || posizione>=getNumMaxVolumi())
            return -1;                  //posizione non valida
        */
        try
        {
            if (volumi[posizione]!=null)
                return -2;                  //posizione occupata
            volumi[posizione]=new Libro(volume);
            return posizione;
        }
        catch (ArrayIndexOutOfBoundsException posizioneNonValida)
        {
            return -1;
        } 
    }
    
    /*
    Restituisce il libro che si trova in una determinata posizione
    se la posizione è vuota --> return null
    se la posizione<0 o supera NUM_MAX_LIBRI (non valida) --> return null
    */
    public Libro getVolume(int posizione)
    {
        try
        {
             return new Libro(volumi[posizione]);
        }
      /*  catch (ArrayIndexOutOfBoundsException posizioneNonValida)
        {
            return null;
        }
        */
        catch (NullPointerException posizioneVuota)
        {
            return null;
        }
        
        
        /*
        if (posizione<0 || posizione>=getNumMaxVolumi())
            return null; 
        if (volumi[posizione]==null)
             return null;
        return new Libro(volumi[posizione]);  
        */
    }
    /*
    
    Libera (inserendo null) la posizione "posizione" e restituisce il numero della posizione "liberata"
    se la posizione indicata è<0 o supera  NUM_MAX_LIBRI (non valida) --> return -1
    se la posizione è già vuota --> return -2
    */
    
    public int rimuoviVolume(int posizione)
    {
        /*if (posizione<0 || posizione>=getNumMaxVolumi())
            return -1;                  //posizione non valida
        */
       
        try
        {
            if (volumi[posizione]==null)
                return -2;                  //posizione vuota
            volumi[posizione]=null;
            return posizione;
        }
        catch(ArrayIndexOutOfBoundsException posizioneNonValida)
        {
            return -1;
        }
        
    }
    
    public boolean presenzaTitolo(String titolo)
    {
        for (int i=0;i<getNumMaxVolumi();i++)
        {
            if (volumi[i]!=null)
            {
                if (volumi[i].getTitolo()==titolo)
                    return true;
            }
        }
        return false;
    }
    
}
