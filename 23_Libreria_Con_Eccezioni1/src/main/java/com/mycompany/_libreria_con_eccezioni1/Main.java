/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany._libreria_con_eccezioni1;

import eccezioni.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author User
 */
public class Main 
{
    
    public static void main(String[] args) 
    {
          
        String[] vociMenu=new String[11];
        int sceltaUtente=-1;
        Scanner tastiera=new Scanner(System.in);
        Scaffale s1=new Scaffale();
        Libro libro;
        int esitoOperazione,ripiano,posizione;
        String caricamentoDaFileOK;
        String nomeFileTesto="libriScaffale.txt";
        String nomeFileBinario="scaffale.bin";
        

        vociMenu[0]="Esci";
        vociMenu[1]="Aggiungi libro";
        vociMenu[2]="Visualizza libro";
        vociMenu[3]="Rimuovi libro";
        vociMenu[4]="Ottieni i titoli dei libri di un autore";
        vociMenu[5]="Visualizza lo scaffale con i libri presenti";
        vociMenu[6]="Visualizza elenco alfabetico libri";
        vociMenu[7]="Visualizza elenco libri ordinati per prezzo";
        vociMenu[8]="Visualizza elenco libri per autore in ordine alfabetico";
        vociMenu[9]="Esporta libri su file CSV";
        vociMenu[10]="Salva dati";
    
          
        
        try 
        {
            s1=s1.caricaScaffale(nomeFileBinario);
            System.out.println("Dati caricati correttamente");
        } 
        catch (IOException ex) 
        {
            System.out.println("Impossibile accedere al file in lettura. I dati non sono stati caricati");
        } 
        catch (FileException ex) 
        {
            System.out.println(ex.toString());
        }
        
        Menu menu= new Menu(vociMenu);
        
        do
        {
            try
            {
                sceltaUtente=menu.sceltaMenu();
                switch(sceltaUtente)
                {
                    case 0:
                    {
                        System.out.println("L'applicazione verrà terminata");
                        break;
                    }
                    case 1:
                    {
                        libro=new Libro();
                        System.out.println("Titolo-->");
                        libro.setTitolo(tastiera.nextLine());
                        System.out.println("Autore-->");
                        libro.setAutore(tastiera.nextLine());
                        System.out.println("Numero pagine-->");
                        libro.setNumeroPagine(tastiera.nextInt());
                        System.out.println("Ripiano [0..4]-->");
                        ripiano=tastiera.nextInt();
                        System.out.println("Posizione [0..14]-->");
                        posizione=tastiera.nextInt();
                        try
                        {
                            s1.setLibro(libro,ripiano ,posizione);
                            System.out.println("Ok inserimento eseguito correttamente");
                        }
                        catch(EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        }
                        catch(EccezionePosizioneNonVuota e2)
                        {
                            System.out.println(e2.toString());
                        }
                        System.out.println("Premi un pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Ripiano [0..4]-->");
                        ripiano=tastiera.nextInt();
                        System.out.println("Posizione [0..14]-->");
                        posizione=tastiera.nextInt();
                        try
                        {
                            libro=s1.getLibro(ripiano, posizione);
                            if (libro==null)
                                System.out.println("Nessun libro presente in questa posizione");
                            else
                            {
                                System.out.println(libro.toString());
                            }
                        }
                        catch (EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        }

                        System.out.println("Premi un pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Ripiano [0..4]-->");
                        ripiano=tastiera.nextInt();
                        System.out.println("Posizione [0..14]-->");
                        posizione=tastiera.nextInt();

                        try
                        {
                            s1.rimuoviLibro(ripiano, posizione);
                            System.out.println("Ok rimozione effettuata correttamente");
                        }
                        catch(EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        } 
                        catch (EccezionePosizioneVuota e2) 
                        {
                               System.out.println(e2.toString());
                        }
                        System.out.println("Premi un pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 4:
                    {
                        String[] elencoTitoli;
                        String autore;
                        System.out.println("Autore-->");
                        autore=tastiera.nextLine();
                        try
                        {
                            elencoTitoli=s1.elencoTitoliAutore(autore);
                            if (elencoTitoli==null)
                            System.out.println("Nessun libro presente dell'autore "+autore);
                            else
                            {
                                for (int i=0;i<elencoTitoli.length;i++)
                                    System.out.println(elencoTitoli[i]);
                            }
                        }
                        catch (EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        }
                        System.out.println("Premi un pulsante per continuare");
                        tastiera.nextLine();
                        break;
                    }
                    case 5:
                    {
                        System.out.println(s1.toString());
                        break;
                    }
                    case 6:
                    {
                        try
                        {
                            System.out.println(s1.elencoAlfabeticoLibri());
                        }
                        catch (EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        }
                        break;
                    }
                    case 7:
                    {
                        Libro[] elencoLibri;
                        try
                        {
                            elencoLibri=s1.elencoLibriOrdinatiPrezzo();
                            for (int i=0;i<elencoLibri.length;i++)
                                System.out.println(elencoLibri[i].toString()+ " € "+elencoLibri[i].prezzo());
                        }
                        catch (EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        }
                        break;

                    }
                    case 8:
                    {
                        Libro[] elencoLibri;
                        try
                        {
                            elencoLibri=s1.elencoLibriAlfabeticoAutoreTitolo();
                            for (int i=0;i<elencoLibri.length;i++)
                                System.out.println(elencoLibri[i].toString()+ " € "+elencoLibri[i].prezzo()); 
                        }
                        catch (EccezionePosizioneNonValida e1)
                        {
                            System.out.println(e1.toString());
                        }
                        break;
                    }
                    case 9:
                    {
                        try
                        {
                            s1.esportaLibriCSV(nomeFileTesto);
                            System.out.println("Libri esportati correttamente");
                        }
                        catch(IOException e1)
                        {
                            System.out.println("Impossibile accedere al file");
                        }
                        catch(FileException e2)
                        {
                            System.out.println(e2.toString());
                        }
                        catch(EccezionePosizioneNonValida e3)
                        {
                            System.out.println(e3.toString());
                        }
                        break;
                    }
                    case 10:
                    {
                        try 
                        {
                            s1.salvaScaffale(nomeFileBinario);
                            System.out.println("Dati salvati correttamente");
                        } catch (IOException ex) 
                        {
                            System.out.println("Impossibile accedere al file in scrittura");
                        }

                     }

                }
                
            }
            catch (InputMismatchException | NumberFormatException e1 )
            {
                tastiera.nextLine();
                System.out.println("Input non corretto");
            }
            
            
            
        }while (sceltaUtente!=0);
            
        
    /*   
        Libro l1=new Libro("Moby Dick", "Melville", 600);
        Libro l2=new Libro("I promessi sposi", "Manzoni", 500);
        Libro l3=new Libro("Le avventure di Pinocchio", "Collodi", 200);
        Libro l4=new Libro("Adelchi", "Manzoni", 300);
        
        
        
        Scaffale s1= new Scaffale();
        
        String[] vociMenu=new String[5];
        {
            vociMenu[0]="Esci";
            vociMenu[1]="Aggiungi libro";
            vociMenu[2]="Visualizza libro";
            vociMenu[3]="Rimuovi libro";
            vociMenu[4]="Elenco libri di un autore";
        }
        Menu menu=new Menu(vociMenu);
        int sceltaMenu=0;
        do
        {
            sceltaMenu=menu.sceltaMenu();
            switch(sceltaMenu)
            {
                case 0:
                {
                    System.out.println("Il programma verrà terminato");
                    break;
                }
                case 1:
                {
                    //inserimento libro in ripiano e posizione
                    break;
                }
                case 2:
                {
                    //visualizza libro in ripiano e posizione
                    break;
                }
                case 3:
                {
                    //rimuovi libro in ripiano e posizione
                    break;
                }
                case 4:
                {
                    //visualizza elenco titoli libri di un autore
                    break;
                }
            
            
            }
        } while (sceltaMenu!=0);
    */    
        /*
        Libro[] elencoLibri= new Libro[4];
        
        elencoLibri[0]=l1;
        elencoLibri[1]=l2;
        elencoLibri[2]=l3;
        elencoLibri[3]=l4;
        
        
        
        
        Mensola m1= new Mensola(elencoLibri);
        
        Libro libro;
        for (int i=0;i<m1.getNumMaxVolumi();i++)
        {
            if (m1.getVolume(i)!=null)
            {
                libro=m1.getVolume(i);
                System.out.println(libro.toString());
            }
        }
        
        */
        
        /*
        Scaffale s1= new Scaffale();
        
        s1.setLibro(l1, 0, 0);
        s1.setLibro(l2, 1, 1);
        s1.setLibro(l3, 2, 1);
        s1.setLibro(l4, 2, 10);
        
        String[] elencoTitoliAutore;
        elencoTitoliAutore=s1.elencoTitoliAutore("Melville");
        if (elencoTitoliAutore==null)
            System.out.println("Nessun libro presente per questo autore");
        else
        {
            for(int i=0;i<elencoTitoliAutore.length;i++)
                System.out.println(elencoTitoliAutore[i]);
        }
        
        */
        /*
        //Test inserimento libro:
        int inserimentoOK;
        inserimentoOK=s1.setLibro(l1, 0, 10);
        if (inserimentoOK==0)
            System.out.println("Inserimento ok");
        else if(inserimentoOK==-1)
            System.out.println("Posizione non valida");
        else if(inserimentoOK==-2)
            System.out.println("Posizione occupata");
        else if(inserimentoOK==-3)
            System.out.println("Ripiano non valido");
        
        inserimentoOK=s1.setLibro(l2, 0, 0);
        if (inserimentoOK==0)
            System.out.println("Inserimento ok");
        else if(inserimentoOK==-1)
            System.out.println("Posizione non valida");
        else if(inserimentoOK==-2)
            System.out.println("Posizione occupata");
        else if(inserimentoOK==-3)
            System.out.println("Ripiano non valido");
        
        Libro l4=new Libro("La divina commedia", "Dante", 230);
        inserimentoOK=s1.setLibro(l4, 1, 2);
        if (inserimentoOK==0)
            System.out.println("Inserimento ok");
        else if(inserimentoOK==-1)
            System.out.println("Posizione non valida");
        else if(inserimentoOK==-2)
            System.out.println("Posizione occupata");
        else if(inserimentoOK==-3)
            System.out.println("Ripiano non valido");
        
        
        //test errori inserimento
        inserimentoOK=s1.setLibro(l3, 10, 0);
        if (inserimentoOK==0)
            System.out.println("Inserimento ok");
        else if(inserimentoOK==-1)
            System.out.println("Posizione non valida");
        else if(inserimentoOK==-2)
            System.out.println("Posizione occupata");
        else if(inserimentoOK==-3)
            System.out.println("Ripiano non valido");
        
        inserimentoOK=s1.setLibro(l3, 0, 20);
        if (inserimentoOK==0)
            System.out.println("Inserimento ok");
        else if(inserimentoOK==-1)
            System.out.println("Posizione non valida");
        else if(inserimentoOK==-2)
            System.out.println("Posizione occupata");
        else if(inserimentoOK==-3)
            System.out.println("Ripiano non valido");
        
        inserimentoOK=s1.setLibro(l3, 0, 10);
        if (inserimentoOK==0)
            System.out.println("Inserimento ok");
        else if(inserimentoOK==-1)
            System.out.println("Posizione non valida");
        else if(inserimentoOK==-2)
            System.out.println("Posizione occupata");
        else if(inserimentoOK==-3)
            System.out.println("Ripiano non valido");
       
        inserimentoOK=s1.setLibro(l3, 1, 0);
        if (inserimentoOK==0)
            System.out.println("Inserimento ok");
        else if(inserimentoOK==-1)
            System.out.println("Posizione non valida");
        else if(inserimentoOK==-2)
            System.out.println("Posizione occupata");
        else if(inserimentoOK==-3)
            System.out.println("Ripiano non valido");
        
       //Test eliminazione
       int eliminazioneOk;
       eliminazioneOk=s1.rimuoviLibro(2,9);
        if (eliminazioneOk==0)
            System.out.println("eliminazione ok");
        else if(eliminazioneOk==-1)
            System.out.println("Posizione non valida");
        else if(eliminazioneOk==-2)
            System.out.println("Posizione già vuota");
        else if(eliminazioneOk==-3)
            System.out.println("Ripiano non valido");
        
        eliminazioneOk=s1.rimuoviLibro(9,9);
        if (eliminazioneOk==0)
            System.out.println("eliminazione ok");
        else if(eliminazioneOk==-1)
            System.out.println("Posizione non valida");
        else if(eliminazioneOk==-2)
            System.out.println("Posizione già vuota");
        else if(eliminazioneOk==-3)
            System.out.println("Ripiano non valido");
        
        eliminazioneOk=s1.rimuoviLibro(2,18);
        if (eliminazioneOk==0)
            System.out.println("eliminazione ok");
        else if(eliminazioneOk==-1)
            System.out.println("Posizione non valida");
        else if(eliminazioneOk==-2)
            System.out.println("Posizione già vuota");
        else if(eliminazioneOk==-3)
            System.out.println("Ripiano non valido");
        
        eliminazioneOk=s1.rimuoviLibro(0,0);
        if (eliminazioneOk==0)
            System.out.println("eliminazione ok");
        else if(eliminazioneOk==-1)
            System.out.println("Posizione non valida");
        else if(eliminazioneOk==-2)
            System.out.println("Posizione già vuota");
        else if(eliminazioneOk==-3)
            System.out.println("Ripiano non valido");
        
        //visualizzazione libri presenti
        Libro libro;
        for (int i=0;i<s1.getNumRipiani();i++)
        {
            for (int j=0;j<s1.getNumMaxLibri(i);j++)
            {
                if (s1.getLibro(i, j)!=null)
                {
                    libro=s1.getLibro(i, j);
                    System.out.println("Ripiano: "+i+" posizione:"+j+" "+libro.toString());
                }
            }
        }
        
        
        
       // System.out.println("Libro in 0,10 di s2: "+s2.getLibro(0, 10));
        
        Mensola m;
        m=s1.getRipiano(0);
        System.out.println("ripiano: libro in pos 10: "+m.getVolume(10).toString());
    */
    }
    
    
    
}




