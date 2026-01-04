package dades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import excepcions.ActivitatInexistentException;

public class LlistaActivitats {

    private Activitats [] llista; 
    private int numActivitats; //index de la llista
    
    public LlistaActivitats(int mida){
        llista = new Activitats[mida];
        numActivitats=0; 
    }

    public int getNumActivitats(){
        return numActivitats; 
    }

    public Activitats getActivitat(int i){
        if(i<0 || i>= numActivitats){
            throw new IndexOutOfBoundsException("Posicio es troba fora dels limits.");
        }
        return llista[i];
    }

    //Afegir activitat ordenada per nom
    public void afegir(Activitats activitat) throws IOException{
        if(numActivitats>=llista.length){
            throw new IllegalStateException("La llista d'activitats esta plena."); 
        }
        int pos=0; 
        while(pos<numActivitats && llista[pos].getNom().compareToIgnoreCase(activitat.getNom())<0){
            pos++;
        }

        for(int i=numActivitats; i>pos; i--){
            llista[i]=llista[i-1]; 
        }

        llista[pos]=activitat; 
        numActivitats++; 
        //important: actualitzar fitxer de dades
        escriuFitxerActivitats();

    }

    // Eliminar activitat per posicio
    public void eliminar(int pos) throws IOException{
        if(pos<0 || pos>= numActivitats){
            throw new IndexOutOfBoundsException("Posicio es troba fora dels limits.");
        }

        for(int i=pos; i<numActivitats-1; i++){
            llista[i]=llista[i+1];
        }
        llista[numActivitats-1]=null;
        numActivitats--; 
        //important: actualitzar fitxer de dades
        escriuFitxerActivitats();
    }

    public Activitats buscarPerNom(String nom){
        for(int i=0; i<numActivitats; i++){
            if(llista[i].getNom().equalsIgnoreCase(nom)){
                return llista[i]; 
            }
        }
        return null; 
    }

    public Activitats obtenirPerNom(String nom) throws ActivitatInexistentException{
        Activitats a = buscarPerNom(nom);
        if(a==null){
            throw new ActivitatInexistentException(nom);
        }
        return a; 
    }

    //Activitats en periode d'inscripcio
    public Activitats[] activitatsEnInscripcio(Data avui){
        Activitats [] res = new Activitats[numActivitats];
        int j=0; 

        for(int i=0; i<numActivitats;i++){
            if(llista[i].esEnPeriodeInscripcio(avui)){
                res[j++]=llista[i];
            }
        }
        return res; 
    }

    //Activitats actives avui
    public Activitats [] activitatsActivesAvui(Data avui){
        Activitats [] res= new Activitats[numActivitats]; 
        int j=0; 

        for(int i=0; i<numActivitats; i++){
            if(llista[i].esActivaAvui(avui)){
                res[j++]=llista[i]; 
            }
        }
        return res; 
    }

    //Activitats amb classe avui
    public Activitats [] activitatsAmbClasseAvui(Data avui){
        Activitats [] res = new Activitats[numActivitats];
        int j=0; 

        for(int i=0; i<numActivitats; i++){
            if(llista[i].teClasseAvui(avui)){
                res[j++]=llista[i];
            }
        }
        return res; 
    }

    public Activitats [] activitatsAmbPlaces(){
        Activitats [] res = new Activitats[numActivitats];
        int j=0; 

        for(int i=0; i<numActivitats; i++){
            if(llista[i].getLimitPlaces()>0){
                res[j++]=llista[i];
            }
        }
        return res; 
    }

    public void escriuFitxerActivitats() throws IOException{
        BufferedWriter escriptura = new BufferedWriter(new FileWriter("FitxerLlistaActivitats.txt"));

        for(int i=0; i<numActivitats; i++){
            escriptura.write(llista[i] + ";");
        }

        escriptura.close();
    }

    public boolean hiHaActivitat(Data data) {

        boolean trobat = false;

        int i = 0;
        while ((i < numActivitats) && (!trobat)) {
            Activitats activitat = llista[i];
            String tipus = activitat.getTipus();

            switch (tipus) {
                case "UnDia":
                    if (activitat.teClasseAvui(data)) trobat = true;
                    break;
                case "Periodica":
                    if (activitat instanceof ActivitatPeriodica) {
                        ActivitatPeriodica ap = (ActivitatPeriodica) activitat;
                        if ((data.getDiaSetmana().equalsIgnoreCase(ap.getDiaSetmana()) && (!data.esAnterior(ap.getDataInici())) 
                            && (!data.esPosterior(ap.getDataInici().afegirDies(7*ap.getNumSetmanes()))))) trobat = true;
                    }
                    break;
                case "Online":
                    if (activitat instanceof ActivitatOnline) {
                        ActivitatOnline ao = (ActivitatOnline) activitat;
                        if (!data.esAnterior(ao.getDataInici()) 
                            && (!data.esPosterior(ao.getDataInici().afegirDies(ao.getPeriodeVisualitzacio())))) trobat = true;
                    }
                    break;
            }
            i++;
        }
        
        return trobat;
    }
}
