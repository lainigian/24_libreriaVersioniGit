
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany._libreria_con_eccezioni1;

import eccezioni.*;
import file.TextFile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe scaffale rapppresenta uno Scaffale di una libreria.
 * Uno scaffale è costituito da NUM_RIPIANI ripiani. Ogni ripiano 
 * è un oggetto di tipo Mensola. Ogni oggetto di tipo Mensola è un
 * aggregazione di oggetti di tipo Libro.
 * Questa classe consente di svolgere operazioni di ricerca, inserimento, eliminazione
 * dei libri nello Scaffale.
 * Gli attributi sono<br> 
 * ripiani: array di mensole <br>
 * NUM_RIPIANI: costante che indica il numero di ripiani presenti
 * 
 * @author Laini Gian Marco
 */
public class Scaffale implements Serializable
{
   private Mensola[] ripiani;
   private static final int NUM_RIPIANI=5;
   
   /**
    * Costruttore della classe Scaffale
    * Cinsente di istanziare un nuovo Scaffale
    * costituito da NUM_RIPIANI ripiani vuoti
    */
   
   public Scaffale()
   {
       ripiani=new Mensola[NUM_RIPIANI];
       
       for (int i=0;i<NUM_RIPIANI;i++)
       {
           ripiani[i]=new Mensola();
       }
       
   }
   
   /**
    * Costruttore di copia. Consente di istanziare un nuovo Scaffale
    * copia dello scaffale passato come parametro
    * @param s Lo Scaffale di cui creare la copia
    * 
    */
   public Scaffale(Scaffale s) throws EccezionePosizioneNonValida, EccezionePosizioneNonVuota
   {
        ripiani=new Mensola[NUM_RIPIANI];
        
        Libro l;
        for (int i=0;i<getNumRipiani();i++)
        {
            ripiani[i]=new Mensola();
            for(int j=0;j<ripiani[i].getNumMaxVolumi();j++)
            {
                if (s.getLibro(i, j)!=null)
                {
                    l=s.getLibro(i, j);
                    setLibro(l, i, j);
                }
            }
        }
        
   }
   
   /**
    * Restituisce un ripiano dello scaffale
    * @param ripiano Il numero che identifica il ripiano da restituire all'interno dello Scaffale
    * @return il ripiano, istanza della classe mensola, da restituire
    */
   public Mensola getRipiano(int ripiano) throws EccezionePosizioneNonValida
   {
       Mensola m;
       try
       {
            m=new Mensola(ripiani[ripiano]);
            return m;
       }
       catch(ArrayIndexOutOfBoundsException e1)
       {
           throw new EccezionePosizioneNonValida(ripiano,0);
       }
  
   }
   
  
   
   /*
   Inserisce il libro nella "posizione" del “ripiano”. 
    se il ripiano non è valido  --> return -3
    se la posizione non è valida --> return -1 se è occupata --> return -2
    se ok  return 0

   
   */
   public void setLibro(Libro libro,int ripiano, int posizione) throws EccezionePosizioneNonValida, EccezionePosizioneNonVuota
   {
       int inserimentoOk;
       try
       {
            inserimentoOk=ripiani[ripiano].setVolume(libro, posizione);
            if (inserimentoOk==-1)
                throw new EccezionePosizioneNonValida(ripiano, posizione);  //posizione non valida
            else if (inserimentoOk==-2)
                throw new EccezionePosizioneNonVuota(ripiano, posizione);   //posizione non vuota
     
       }
       catch(ArrayIndexOutOfBoundsException e1)
       {
           throw new EccezionePosizioneNonValida(ripiano, posizione);   //ripiano non valido
       }  
   }
   
   /*
   Restituisce il libro nella "posizione" del “ripiano”. 
se il ripiano non è valido, la posizione non è valida o vuota --> return null
se ok  ritorna l’oggetto libro
   */
    
   public Libro getLibro(int ripiano, int posizione) throws EccezionePosizioneNonValida
   {
       try
       {
           return(ripiani[ripiano].getVolume(posizione));
       }
       catch(ArrayIndexOutOfBoundsException e1)
       {
           throw new EccezionePosizioneNonValida(ripiano, posizione);
       }
       
       
   }
   
   
   /*
   Libera la "posizione" nel “ripiano”. 
se il ripiano non è valido  --> return -3
se la posizione non è valida --> return -1 se già vuota --> return -2
se ok  return 0

   */
   
   public void rimuoviLibro(int ripiano, int posizione) throws EccezionePosizioneNonValida, EccezionePosizioneVuota
   {
       int rimozioneOk;
       try
       {
           rimozioneOk=ripiani[ripiano].rimuoviVolume(posizione);
           if (rimozioneOk==-1)
                throw new EccezionePosizioneNonValida(ripiano, posizione);
           else if (rimozioneOk==-2)
               throw  new EccezionePosizioneVuota(ripiano, posizione);
       }
       catch(ArrayIndexOutOfBoundsException e1)
       {
           throw new EccezionePosizioneNonValida(ripiano, posizione);
       }
       
   }
   
   public static int getNumRipiani()
   {
       return NUM_RIPIANI;
   }
   
   public int getNumMaxLibri()
   {
       int numMaxLibri=0;
       for(int i=0;i<getNumRipiani();i++)
       {
           numMaxLibri+=ripiani[i].getNumMaxVolumi();
       }
       return numMaxLibri;
   }
   
   public int getNumLibri()
   {
       int numLibri=0;
       for(int i=0;i<getNumRipiani();i++)
       {
           numLibri+=ripiani[i].getNumVolumi();
       }
       return numLibri;
   }
   
   //se ripiano non valido --> return -1
   public int getNumLibri(int ripiano) throws EccezionePosizioneNonValida
   {
       try
       {
           return ripiani[ripiano].getNumVolumi();
       }
       catch(ArrayIndexOutOfBoundsException e1)
       {
           throw new EccezionePosizioneNonValida(ripiano, 0);
       }
       
       
   }
   
   public int getNumMaxLibri(int ripiano) throws EccezionePosizioneNonValida
   {
       try
       {
           return ripiani[ripiano].getNumMaxVolumi();
       }
       catch(ArrayIndexOutOfBoundsException e1)
       {
           throw new EccezionePosizioneNonValida(ripiano, 0);
       }
       
       
   }
   
   public String[] elencoTitoliAutore(String autore) throws EccezionePosizioneNonValida
   {
       int numeroLibriAutore=0;
       Libro libro;
       
       //conto il numero di libri dell'autore prensenti
       for (int i=0;i<getNumRipiani();i++)
       {
           for (int j=0;j<ripiani[i].getNumMaxVolumi();j++)
           {
               if (getLibro(i, j)!=null)
               {
                   libro=getLibro(i, j);
                   if (libro.getAutore().equalsIgnoreCase(autore))
                       numeroLibriAutore++;
               }
           }
       }
       
       // se nn ci sono libri di quell'autore, return null
       if (numeroLibriAutore==0)
           return null;
       
       String[] elencoTitoliAutore=new String[numeroLibriAutore];
       
       int posizioneTitolo=0;
       
       //Assegno ad ogni elemento dell'array il titolo del libro
       for (int i=0;i<getNumRipiani();i++)
       {
           for (int j=0;j<ripiani[i].getNumMaxVolumi();j++)
           {
               if (getLibro(i, j)!=null)
               {
                   libro=getLibro(i, j);
                   if (libro.getAutore().equalsIgnoreCase(autore))
                   {
                       elencoTitoliAutore[posizioneTitolo]=libro.getTitolo();
                       posizioneTitolo++;
                   }
               }
           }
       }
       return elencoTitoliAutore;
   }
   
  public String toString()
  {
      String s="";
      
      if (getNumLibri()==0)
          s="Nessun libro presente";
      else
      {
        Libro libro;
        for(int i=0;i<getNumRipiani();i++)
        {
            s=s+"Ripiano "+i+":\n";
            for (int j=0;j<ripiani[i].getNumMaxVolumi();j++)
            {
                try
                {
                    if (getLibro(i, j)!=null)
                    {
                        libro=getLibro(i, j);
                        s=s+j+" --> "+libro.toString()+"\n";
                    } 
                }
                catch (EccezionePosizioneNonValida e1)
                {
                    
                }                  
            }
        }     
      }
    return s;    
  }
  
  public String elencoAlfabeticoLibri() throws EccezionePosizioneNonValida
  {
      String s="";
      String elencoLibri[]=new String[getNumLibri()];
      int c=0;
      
      if (getNumLibri()==0)
          s="Nessun libro presente";
      else
      {
        Libro libro;
        for(int i=0;i<getNumRipiani();i++)
        {
            for (int j=0;j<ripiani[i].getNumMaxVolumi();j++)
            {
                if (getLibro(i, j)!=null)
                {
                    libro=getLibro(i, j);
                    elencoLibri[c]=libro.getTitolo()+" --> ripiano:"+i+" posizione: "+j;
                    c++;
                }    
            }
        }
        elencoLibri=Ordinatore.selectionSortCrescente(elencoLibri);
        for(int i=0;i<elencoLibri.length;i++)
            s+=elencoLibri[i]+"\n";
      }
    return s;    
  }
  
  // restituisce un array di libri ordinati in base al prezzo
  public Libro[] elencoLibriOrdinatiPrezzo() throws EccezionePosizioneNonValida
  {
      Libro[] elencoLibri=new Libro[getNumLibri()];
      Libro libro;
      int c=0;
      
      for(int i=0;i<NUM_RIPIANI;i++)
      {
          for(int j=0;j<ripiani[i].getNumMaxVolumi();j++)
          {
              libro=getLibro(i, j);
              if (libro!=null)
              {
                  elencoLibri[c]=libro;
                  c++;
              }
          }
      }
      
      elencoLibri=Ordinatore.selectionSortLibriPrezzoCrescente(elencoLibri);
      return elencoLibri;  
  }
  
  
  // restituisce un array di libri ordinati alfabeticamente per autore e in seguito per titolo
  public Libro[] elencoLibriAlfabeticoAutoreTitolo() throws EccezionePosizioneNonValida
  {
      Libro[] elencoLibri=new Libro[getNumLibri()];
      Libro libro;
      int c=0;
      
      for(int i=0;i<NUM_RIPIANI;i++)
      {
          for(int j=0;j<ripiani[i].getNumMaxVolumi();j++)
          {
              libro=getLibro(i, j);
              if (libro!=null)
              {
                  elencoLibri[c]=libro;
                  c++;
              }
          }
      }
      
      elencoLibri=Ordinatore.selectionSortAlfabeticoAutoreTitolo(elencoLibri);
      return elencoLibri;  
  }
  
  //Salva i libri e le posizioni su file CSV
  public void esportaLibriCSV(String filePathName) throws IOException, EccezionePosizioneNonValida, FileException
  {
      Libro libro;
      String stringaLibro;
      TextFile f1= new TextFile(filePathName, 'W');
      for (int i=0;i<getNumRipiani();i++)
      {
          for (int j=0;j<ripiani[i].getNumMaxVolumi();j++)
          {
              libro=getLibro(i,j);
              if (libro!=null)
              {
                  stringaLibro=i+";"+j+";"+libro.getTitolo()+";"+libro.getAutore()+";"+libro.getNumeroPagine()+";";
                  f1.toFile(stringaLibro);
              }
          }
      }
      f1.close(); 
  }
  
  public void salvaScaffale(String nomeFile) throws IOException
  {   
      FileOutputStream f1=new FileOutputStream(nomeFile);
      ObjectOutputStream writer=new ObjectOutputStream(f1);
      writer.writeObject(this);
      writer.flush();
      writer.close();   
  }
  
  public Scaffale caricaScaffale(String nomeFile) throws IOException, FileException
  {
      Scaffale s;
      FileInputStream f1=new FileInputStream(nomeFile);
      ObjectInputStream reader=new ObjectInputStream(f1);
      
       try 
       {
           s=(Scaffale)reader.readObject();
           reader.close();
           return s;
       } 
       catch (ClassNotFoundException ex) 
       {
           reader.close();
           throw new FileException("Errore di lettura");
       }   
  }
  
 /* //Legge i libri, con relative posizioni, da un file CSV e li posiziona nello scaffale
  public String caricaLibri(String filePathName) throws IOException, EccezionePosizioneNonValida, EccezionePosizioneNonVuota
  {
    Libro libro;
    String stringaLibro;
    String[] elementi;
    int ripiano,posizione;
    String titolo,autore;
    int numeroPagine;
    String stringaDiRitorno;
    TextFile f1=new TextFile(filePathName, 'R');  
    try
    {
        while(true)
        {
            stringaLibro=f1.fromFile();
            //metto, a uno a uno, gli elementi della stringa CSV che rappresenta un libro ( e la posizione) nell'array di stringhe
            elementi=stringaLibro.split(";"); 
            ripiano=Integer.parseInt(elementi[0]);
            posizione=Integer.parseInt(elementi[1]);
            titolo=elementi[2];
            autore=elementi[3];
            numeroPagine=Integer.parseInt(elementi[4]);
            libro=new Libro(titolo, autore, numeroPagine);
            setLibro(libro, ripiano, posizione);
        }
    }
    catch(FileException fineFile)
    {
        stringaDiRitorno=fineFile.toString();  // "fine del file"
        f1.close();
    }  
    return stringaDiRitorno;
  }
  
  */
  
  
  
  
  
  
  
  
  
  /*
  //ORDINAMENTO
  public Libro[] elencoLibriOrdineAlfabeticoAutoreTitolo()
  {
      Libro[] elencoLibri=new Libro[getNumLibri()];
      Libro libro;
      int c=0;
      
      for(int i=0;i<NUM_RIPIANI;i++)
      {
          for(int j=0;j<ripiani[i].getNumMaxVolumi();j++)
          {
              libro=getLibro(i, j);
              if (libro!=null)
              {
                  elencoLibri[c]=libro;
                  c++;
              }
          }
      }
      
      elencoLibri=Ordinatore.selectionSortLibriAutoreTitolo(elencoLibri);
      return elencoLibri;  
  }
  
  
  
  
  
  
  public Libro[] elencoLibriOrdinatiPrezzo()
  {
      Libro[] elencoLibri=new Libro[getNumLibri()];
      Libro libro;
      int c=0;
      
      for (int i=0;i<NUM_RIPIANI;i++)
      {
          for (int j=0;j<ripiani[i].getNumMaxVolumi();j++)
          {
              libro=getLibro(i, j);
              if (libro!=null)
              {
                  elencoLibri[c]=libro;
                  c++;
              }
          }
      }
      
      elencoLibri=Ordinatore.selectionSortPrezzoCrescente(elencoLibri);
      return elencoLibri;
      
  }
  
  
  public String elencoAlfabeticoLibri()
  {
      String s="";
      String[] elencoLibri=new String[getNumLibri()];
      int ripiano=0;
      int posizione=0;
      int c=0;
      
      if (getNumLibri()==0)
          s="Nessun libro presente";
      else
      {
        Libro libro;
        for(int i=0;i<getNumRipiani();i++)
        {
            s=s+"Ripiano "+i+":\n";
            for (int j=0;j<ripiani[i].getNumMaxVolumi();j++)
            {
                if (getLibro(i, j)!=null)
                {   ripiano=i;
                    posizione=j;
                    libro=getLibro(i, j);
                    elencoLibri[c]=libro.getTitolo()+" --> ripiano: "+ripiano+" posizione: "+posizione;
                    c++;
                }    
            }
        }
        elencoLibri=Ordinatore.selectionSortCrescente(elencoLibri);
        for (int i=0;i<elencoLibri.length;i++)
            s=s+elencoLibri[i]+"\n";
      }
    return s;  
  }
  
*/
}

