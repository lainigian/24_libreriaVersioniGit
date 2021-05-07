/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany._libreria_con_eccezioni1;

import java.io.Serializable;


/**
 * La classe rappresenta un libro.
 * I suoi suoi attributi sono:<br>
 * titolo <br>
 * autore <br>
 * numeroPagine <br>
 * costoPagina: attributo statico, rappresenta il costo per la produzione di una singola pagina
 * del libro.
 * COSTO_FISSO: è una costante, rappresenta il costo di base per la produzione di un libro
 * Il prezzo finale di un libro verrà calcolato come somma fra COSTO_FISSO   e il prodotto
 * del numero di pagine per il costo di una pagina.
 * @author Laini Gian Marco
 * @version 1.0
 */



public class Libro implements Serializable
{
  //attibuti
    private String titolo;
    private String autore;
    private int numeroPagine;
    private static double costoPagina=0.05;
    private final double COSTO_FISSO=5.5;
  
    
/**
 * Costruttore della classe Libro
 * @param titolo titolo del libro
 * @param autore autore del libro   
 * @param numeroPagine nuimero di pagine di cui è costituito il libro
 */
    public Libro(String titolo,String autore,int numeroPagine)
    {
        this.titolo=titolo;
        this.autore=autore;
        this.numeroPagine=numeroPagine;
    }
    
  /**
   * Costruttore di copia della classe Libro. Consente di istanziare un nuovo libro
   * @param l: libro da cui verrà istanziato il nuovo libro. 
   * Il libro istanziato sarà una copia del libro l
   */
    public Libro(Libro l)
    {
        titolo=l.getTitolo();
        autore=l.getAutore();
        numeroPagine=l.getNumeroPagine();
    }
    
    /**
     * Costruttore di default della classe Libro. 
     * Consente di istanziare un nuovo libro con i seguenti 
     * valori di default per gli attributi<br>
     * titolo: stringa vuota<br>
     * autore: stringa vuota<br>
     * numeroPagine=0
    */
    public Libro()
    {
        titolo="";
        autore="";
        numeroPagine=0;
    }
    
    /**
     * Restituisce il titolo del libro
     * @return titolo del libro
     */
    public String getTitolo()
    {
        return titolo;
    }
    
    /**
     * Assegna il titolo al libro
     * @param titolo titolo del libro
     */
    public void setTitolo(String titolo)
    {
        this.titolo=titolo;
    }
    
    /**
     * restituisce l'autore del libro
     * @return autore
     */
    public String getAutore()
    {
        return autore;   
    }
    
    /**
     * Assegna l'autore al libro
     * @param autore autore del libro
     */
    public void setAutore(String autore)
    {
        this.autore=autore;
    }
    
    /**
     * Restituisce il numero di pagine di cui è composto il libro
     * @return numeroPagine
     */
    public int getNumeroPagine()
    {
        return numeroPagine;
    }
    
    /**
     * Assegna il numero di pagine di cui è costituito il libro
     * @param numeroPagine numero di pagine di cui è composto il libro
     */
    public void setNumeroPagine(int numeroPagine)
    {
        this.numeroPagine=numeroPagine;
    }
    
    /**
     * Calcola e restituisce il prezzo di vendita del libro
     * @return p prezzo di vendita del libro
     */
    public double prezzo()
    {
        double p;
        p=COSTO_FISSO+costoPagina*getNumeroPagine();
        return p;
    }
    
    /**
     * Metodo statico
     * Assegna il costo unitario di vendita per ciascuna pagina del libro
     * 
     * @param costo costo di vendita di ogni singola pagina del libro
     */
    public static void setCostoPagina(double costo)
    {
        costoPagina=costo; 
        
    }
    
    /**
     * Restituisce il costo unitario per realizzare ciascuna pagina del libro
     * @return costoPagina
     */
    public static double getCostoPagina()
    {
        return costoPagina;
    }
    
    /**
     * Restituisce una stringa che rappresenta il libro
     * @return stringa con titolo,autore e numero di pagine del libro
     */
    public String toString()
    {
        String s;
        s=getTitolo()+";"+getAutore()+";"+getNumeroPagine()+" pagine";
        return s;
    }
    
    public boolean equals(Object o)
    {
        Libro l=(Libro)o;
        if (getTitolo().compareToIgnoreCase(l.getTitolo())==0 && getAutore().compareToIgnoreCase(l.getAutore())==0 && getNumeroPagine()==l.getNumeroPagine())
            return true;
        else
            return false;
              
            
    }
}
 