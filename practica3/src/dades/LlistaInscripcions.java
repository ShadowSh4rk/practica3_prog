package dades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LlistaInscripcions {
    private Inscripcio[] llistaInscri;
    private Inscripcio[] llistaEspera;
    private int nIns = 0; //nombre d'inscripcio (index llista inscripcio)
    private int nEsp = 0; //nombre d'espera (index llista espera)
    private final int limitEspera = 10;

    /**
     * afegeix una nova inscripcio a la llista
     * @param dada
     */
    public void afegir(Inscripcio dada) throws IOException {

        BufferedWriter escriptura = new BufferedWriter(new FileWriter("FitxerInscripcions.txt"));

        Activitats activitat = dada.getActivitats();
        Date n1 = dada.getDataInscripcio();
        if (nIns<activitat.getLimitPlaces()){ //si hi ha lloc dins la llista d'inscripcions, afegim l'inscripcio
            //AFEGIM PER DATA D'INSCRIPCIO
            //pas 1: buscar el lloc 
            int i=0;
            boolean trobat = false;
            while(i<nIns && trobat == false){
                if(n1.after(llistaInscri[i].getDataInscripcio()) && n1.before(llistaInscri[i+1].getDataInscripcio())){
                    trobat = true;
                }
                i++;
            }
            //pas 2: crear el lloc (desplaçar les dades de després cap a la dreta)
            for (int j=nIns; j>i; j--){
                llistaInscri[i+1]=llistaInscri[i];
            }
            //pas 3: col·locar la dada a afegir i incrementar nombre d'elements
            llistaInscri[i] = dada.copia();
            escriptura.write(llistaInscri[i] + ";");
            nIns++;

            escriptura.close();

        }else{ //si no hi ha lloc, mirem si la podem afegir a la llista d'espera

            if(nEsp<limitEspera){ 
                //AFEGIM PER DATA D'INSCRIPCIO
                //pas 1: buscar el lloc 
                int i=0;
                boolean trobat = false;
                while(i<nEsp && trobat == false){
                    if(n1.after(llistaEspera[i].getDataInscripcio()) && n1.before(llistaEspera[i+1].getDataInscripcio())){
                        trobat = true;
                    }
                    i++;
                }
                //pas 2: crear el lloc (desplaçar les dades cap a la dreta)
                for (int j=nIns; j>i; j--){
                    llistaEspera[i+1]=llistaEspera[i];
                }
                //pas 3: col·locar la dada a afegir i incrementar nombre d'elements
                llistaEspera[i] = dada.copia();
                nEsp++;
            }
        }
    }
    
    

    /**
     * 
     * @param dada
     */
    public void cancelar(Inscripcio dada){
        //busquem la dada
        int i=0;
        boolean trobat = false;
        while(i<nIns && trobat == false){
            if(llistaInscri[i]==dada){
                trobat = true;
            }
            i++;
        }

        //desplaçem les dades a l'esquerra
        while (i<nIns){
            llistaInscri[i]=llistaInscri[i+1];
            i++;
        }

        //si hi ha inscripcions a la llista d'espera, n'afegim una 
        if(nEsp>0){
            Date n1 = llistaEspera[0].getDataInscripcio();
            i=0;
            trobat = false;
            while(i<nIns && trobat == false){
                if(n1.after(llistaInscri[i].getDataInscripcio()) && n1.before(llistaInscri[i+1].getDataInscripcio())){
                    trobat = true;
                }
                i++;
            }
            //pas 2: crear el lloc (desplaçar les dades de després cap a la dreta)
            for (int j=nIns; j>i; j--){
                llistaInscri[i+1]=llistaInscri[i];
            }
            //pas 3: col·locar la dada a afegir i decrementar elements de la llista d'espera
            llistaInscri[i] = llistaEspera[0].copia();
            nEsp--;

        }else{ //si no, decrementem el nombre d'elements a la llista d'inscripcions
            nIns--;
        }

    }
    
    
}
