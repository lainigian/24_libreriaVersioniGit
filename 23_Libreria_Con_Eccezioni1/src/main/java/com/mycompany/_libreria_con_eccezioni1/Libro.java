/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany._libreria_con_eccezioni1;

/**
 * La classe Libro rappresenta un libro. I suoi attributi sono:
 * titolo
 * autore
 * numeroPagine
 * costoPagina(attributo statico che indica il costo di una singola pagina del libro)
 * COSTO_FISSO (è una costante, indica la parte di costo fisso per la produzione di un libro).
 * Il prezzo finale di un libro sarà dato dalla somma fra il COSTO_FISSO  e numero di pagine moltiplicato per il costoPagina
 * @author Laini Gian Marco
 * @version 1.0
 */


public class Libro 
{
  //attibuti
    private String titolo;
    private String autore;
    private int numeroPagine;
    private static double costoPagina=0.05;
    private final double COSTO_FISSO=5.5;
  
/**
 * Costruttore della classe Libro. Consente di istanziare un nuovo libro
 * @param titolo titolo del libro
 * @param autore autore del libro
 * @param numeroPagine  numero di pagine di cui è composto il libro   
*/
    
 /**
  * @param titolo titolo del libro
  * @param autore autore del libro
  * @param numeroPagine numero di pagine di cui è composto il libro
  */   
    public Libro(String titolo,String autore,int numeroPagine)
    {
        this.titolo=titolo;
        this.autore=autore;
        this.numeroPagine=numeroPagine;
    }
    
  /**
   * Costruttore di copia della classe Libro. Consente di istanziare un nuovo libro
   * @param l: libro da cui verrà istanziato il nuovo libro. Il libro istanziato sarà una copia del libro l
   */
    public Libro(Libro l)
    {
        titolo=l.getTitolo();
        autore=l.getAutore();
        numeroPagine=l.getNumeroPagine();
    }
    
    /**
     * Costruttore di default della classe Libro. Consente di istanziare un nuovo libro con i seguenti 
     * valori di default per gli attributi
     * titolo: stringa vuota
     * autore: stringa vuota 
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
     * @return titolo
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
    
}
 