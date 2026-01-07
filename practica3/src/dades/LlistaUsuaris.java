package dades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import excepcions.*;

/**
 * Classe de gestió de la llista d'usuaris.
 * 
 *
 *  carregar usuaris des d'arxiu
 *  cercar usuaris per alies
 *  afegir nous usuaris
 */

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

    /**
     * escriu la llista d'usuaris en un fitxer
     * @throws IOException
     */
    public void escriuFitxerUsuaris() throws IOException{
        BufferedWriter escriptura = new BufferedWriter(new FileWriter("FitxerLlistaUsuaris.txt"));

        for(int i=0; i<llista.length; i++){
            escriptura.write(llista[i] + ";");
        }

        escriptura.close();
    }

    /**
     * Mètode per consultar si hi ha cap usuari amb l'alies indicat per paràmetre.
     *  El mètode retorna la primera instància trobada o null si no hi ha cap.
     * @param alies
     * @return
     */
    public Usuari buscarPerNom(String alies){
        Usuari usuari = null;

        int i = 0;
        while ((i < numUsuaris) && (usuari == null)) {
            // Si trobem un usuari el alies del qual coincideix amb l'alies passat per paràmetre, podem aturar el bucle
            if (llista[i].getAlies().equalsIgnoreCase(alies)) usuari = llista[i];

            i++;
        }

        return usuari;
    }

    /**
     * Obte un usuari pel nom i llença excepcio si no existeix 
     * 
     * @param alies Alies de l'usuari
     * @return Usuari trobat
     * @throws UsuariInexistentException Si l'usuari no existeix
     */
    public Usuari obtenirPerAlies(String alies) throws UsuariInexistentException{
        Usuari u = buscarPerNom(alies);
        if(u==null){
            throw new UsuariInexistentException(alies);
        }
        return u; 
    }
}
