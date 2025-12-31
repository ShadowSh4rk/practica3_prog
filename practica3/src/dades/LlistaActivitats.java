package dades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    public void afegir(Activitats activitat){
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

    }

    // Eliminar activitat per posicio
    public void eliminar(int pos){
        if(pos<0 || pos>= numActivitats){
            throw new IndexOutOfBoundsException("Posicio es troba fora dels limits.");
        }

        for(int i=pos; i<numActivitats-1; i++){
            llista[i]=llista[i+1];
        }
        llista[numActivitats-1]=null;
        numActivitats--; 
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

    public void llegeixFitxerActivitats() throws IOException{
        BufferedReader lectura;

        try{ //control de si podem obrir o no el fitxer
            lectura = new BufferedReader(new FileReader("FitxerLlistaActivitats.txt"));
            String linia;
            String [] trossos, trossosCol, col;

            linia = lectura.readLine();
            while(linia!=null && numActivitats<llista.length){
                    trossos = linia.split(";");

                    Activitats activitat;

                    String tipus = trossos [0];

                    //====== en comu ====
                    String nom = trossos[1];
                    trossosCol = trossos[2].split(",");
                    col = new String[trossosCol.length];
                    for(int i=0; i<trossosCol.length; i++){
                        col[i]=trossosCol[i];
                    }

                    Data iniciInscri = new Data(Integer.parseInt(trossos[3]), Integer.parseInt(trossos[4]), Integer.parseInt(trossos[5]));
                    Data fiInscri = new Data(Integer.parseInt(trossos[6]), Integer.parseInt(trossos[7]), Integer.parseInt(trossos[8]));

                    int limPlaces = Integer.parseInt(trossos[9]);

                    //====== per separat ====

                    if (tipus.equalsIgnoreCase("undia")){
                        Data data = new Data(Integer.parseInt(trossos[10]), Integer.parseInt(trossos[11]), Integer.parseInt(trossos[12]));

                        String ciutat = trossos[13];
                        double preu = Double.parseDouble(trossos[14]);

                        activitat = new ActivitatUnDia(nom, col, iniciInscri, fiInscri, data, ciutat, limPlaces, preu);
                        
                    }else if(tipus.equalsIgnoreCase("periodica")){
                        String diaSetmana = trossos[10];
                        //horari
                        Data dataIni = new Data(Integer.parseInt(trossos[11]), Integer.parseInt(trossos[12]), Integer.parseInt(trossos[13]));
                        int numSetmanes = Integer.parseInt(trossos[14]);
                        String centre = trossos[15];
                        String ciutat = trossos [16];
                        double preuTotal = Double.parseDouble(trossos[17]);

                        activitat = new ActivitatPeriodica(nom, col, iniciInscri, fiInscri, diaSetmana, dataIni, numSetmanes, centre, ciutat, limPlaces, preuTotal);

                    }else{ //activitatOnline
                        Data dataIni = new Data(Integer.parseInt(trossos[10]), Integer.parseInt(trossos[11]), Integer.parseInt(trossos[12]));

                        int periodeVis = Integer.parseInt(trossos[13]);
                        String enllac = trossos[14];

                        activitat = new ActivitatOnline(nom, col, iniciInscri, fiInscri, dataIni, periodeVis, enllac);
                    }

                    llista[numActivitats++] = activitat;

                    linia = lectura.readLine();
            }

            lectura.close();

        }catch(FileNotFoundException e){
            System.out.println("fitxer no trobat, no es pot llegir");
        }catch(IOException e){
            System.out.println("altres errors al llegir el fitxer");
        }
    }
}
