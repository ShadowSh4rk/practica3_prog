package dades;

/**
 * Classe de gestió de la llista d'usuaris.
 * 
 *
 *  carregar usuaris des d'arxiu
 *  cercar usuaris per alies
 *  afegir nous usuaris
 */

import java.util.*;

public class LlistaUsuaris {

    private Usuari[] llista;
    private int numUsuaris;

    /**
     * 
     * @param mida la mida de la llista
     */
    public LlistaUsuaris(int mida) {
        llista = new Usuari[mida];
        numUsuaris = 0;
    }

    /**
     * 
     * @param u un usuari nou de la classe Usuari
     */
    public void afegir(Usuari u) {
        if (numUsuaris < llista.length) {
            llista[numUsuaris] = u;
            numUsuaris++;
        }
    }

    /**
     * 
     * @param pos posició de l'usuari en la llista
     * @return retorna la llista amb els usuaris
     */
    public Usuari getUsuari(int pos) {
        if (pos < 0 || pos >= numUsuaris) {
            return null;
        }
        return llista[pos];
    }

    /**
     * 
     * @return retorna el número d'usuaris
     */
    public int getNumUsuaris() {
        return numUsuaris;
    }
}
