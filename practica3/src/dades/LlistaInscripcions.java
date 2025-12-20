package dades;

public class LlistaInscripcions {
    private Inscripcio[] llistaInscri;
    private Inscripcio[] llistaEspera;
    private int nIns = 0; //nombre d'inscripcio (index llista inscripcio)
    private int nEsp = 0; //nombre d'espera (index llista espera)
    private final int limitEspera = 10;

    /**
     * afegir una nova inscripcio, si hi cap, al final de la llista GESTIONAR LO DE LA LLISTA D'ESPERA AQUI!!
     * @param novaDada
     */
    public void afegir(Inscripcio dada) {
        Activitats activitat = dada.getActivitats();
        if (nIns<activitat.getLimitPlaces()){ //si hi ha lloc dins la llista d'inscripcions, afegim l'inscripcio
            llistaInscri[nIns]=dada.copia();
            nIns++;
        }else{
            if(nEsp<limitEspera){ //si no hi ha lloc, mirem si la podem afegir a la llista d'espera
                llistaEspera[nEsp] = dada.copia();
                nEsp++;
            }
        }
    }
    
    

    /**
     * 
     * @param dada
     */
    public void eliminar(Inscripcio dada){
        
    }
    
    
}
