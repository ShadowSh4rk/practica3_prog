package dades;

import java.io.*;

public abstract class Activitats{
private String nom;    //nom de l'activitat
private String[]colectius;   //PDI, PTGAS, Estudiants
protected Data dataIniciInscripcio; 
protected Data dataFiInscripcio; 
protected int limitPlaces;
private String tipus; // UnDia, Periodica, Online
//========
    public Inscripcio[] llistaInscri;
    protected Inscripcio[] llistaEspera;
    protected int nIns; //nombre d'inscripcio (index llista inscripcio)
    protected int nEsp; //nombre d'espera (index llista espera)
    private static final int limitEspera = 10;

    public Activitats(String nom, String[]colectius, Data dataIniciInscripcio, Data dataFiInscripcio, String tipus) {
        this.nom = nom;
        this.colectius = colectius; 
        this.dataIniciInscripcio = dataIniciInscripcio; 
        this.dataFiInscripcio = dataFiInscripcio;
        this.limitPlaces = 50;  //LOL
        this.tipus = tipus;

        //inicialitzem llistes (inscripcions i espera)
        llistaInscri = new Inscripcio[limitPlaces];
        llistaEspera = new Inscripcio[limitEspera];

        //inicialitzem els indexos de les llistes
        nIns = 0;
        nEsp = 0;
    }

    public Data getDataIniciInscripcio(){
        return dataIniciInscripcio; 
    }

    public Data getDataFiInscripcio(){
        return dataFiInscripcio; 
    }

    /**
     * Retorna els col·lectius als quals s'ofereix l'activitat
     * @return Array de col·lectius
     */
    public String [] getColectius(){
        return colectius; 
    }

    /**
     * Retorna el tipus d'activitat: "UnDia", "Periodica", "Online"
     * @return Tipus d'activitat
     */
    public String getTipus() {
        return tipus;
    }

     /**
     * Retorna el nom de l'activitat.
     * @return Nom de l'activitat
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retorna el límit de places disponibles.
     * @return Límite de places
     */
    public int getLimitPlaces() {
        return limitPlaces;
    }

    public int getnIns() {
        return nIns;
    }

    public int getnEsp() {
        return nEsp;
    }

    public boolean esEnPeriodeInscripcio(Data avui){
        return !avui.esAnterior(dataIniciInscripcio) && !avui.esPosterior(dataFiInscripcio); 
    }

    /**
     * Comprova si l'activitat accepta un col·lectiu determinat.
     * @param col Nom del col·lectiu a comprovar
     * @return true si l'activitat accepta el col·lectiu, false altrament
     */
    public boolean acceptaColectiu(String col){
        for(int i=0; i<colectius.length; i++){
            if(colectius[i].equalsIgnoreCase(col)){
                return true; 
            }
        }
        return false; 
    }

    public abstract boolean teClasseAvui(Data avui);
    public abstract boolean haAcabat(Data avui);
    public abstract boolean esActivaAvui(Data avui); 
    public abstract double getPreu(); 

    /**
     * Retorna una representació en String de l'activitat.
     * @return Nom de l'activitat i tipus entre parèntesis
     */
    @Override 
    public String toString() {
        // Convertir el array de col·lectius a un String "PDI,PTGAS,Estudiants" 
        String col = ""; 
        for (int i = 0; i < colectius.length; i++) { 
            col += colectius[i]; 
            if (i < colectius.length - 1) { 
                col += ","; // añadir coma entre elementos 
                } 
            }
    return nom + ";" + col + ";" + dataIniciInscripcio.getDia() + ";" 
    + dataIniciInscripcio.getMes() + ";" + dataIniciInscripcio.getAny() + ";" 
    + dataFiInscripcio.getDia() + ";" + dataFiInscripcio.getMes() + ";" 
    + dataFiInscripcio.getAny() + ";" + limitPlaces;
}

//GESTIO DE LA LLISTA D'INSCRIPCIONS

/**
     * afegeix una nova inscripcio a la llista
     * @param dada
     */
    public void afegir(Inscripcio dada) throws IOException {

        Activitats activitat = dada.getActivitats();
        Data n1 = dada.getDataInscripcio();
        if (nIns<activitat.getLimitPlaces()){ //si hi ha lloc dins la llista d'inscripcions, afegim l'inscripcio
            //AFEGIM PER DATA D'INSCRIPCIO
            //pas 1: buscar el lloc 
            int i=0;
            boolean trobat = false;
            while(i<nIns && trobat == false){
                if(n1.esPosterior(llistaInscri[i].getDataInscripcio()) && n1.esAnterior(llistaInscri[i+1].getDataInscripcio())){
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
            nIns++;


        }else{ //si no hi ha lloc, mirem si la podem afegir a la llista d'espera

            if(nEsp<limitEspera){ 
                //AFEGIM PER DATA D'INSCRIPCIO
                //pas 1: buscar el lloc 
                int i=0;
                boolean trobat = false;
                while(i<nEsp && trobat == false){
                    if(n1.esPosterior(llistaEspera[i].getDataInscripcio()) && n1.esAnterior(llistaEspera[i+1].getDataInscripcio())){
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
            Data n1 = llistaEspera[0].getDataInscripcio();
            i=0;
            trobat = false;
            while(i<nIns && trobat == false){
                if(n1.esPosterior(llistaInscri[i].getDataInscripcio()) && n1.esAnterior(llistaInscri[i+1].getDataInscripcio())){
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

    //GESTIO FITXER SERIALITZAT

    public static void storeData (Inscripcio[] llistaInscri) {
        ObjectOutputStream outputFile;
        try {
            outputFile = new ObjectOutputStream(new FileOutputStream("llistaInscripcions.ser"));
            for (int i=0; i<llistaInscri.length; i++) {
            outputFile.writeObject(llistaInscri[i]);
            }
            outputFile.close();
        }
        catch (IOException e) {
            System.out.println("Error en l'arxiu de sortida.");
        }
    }

    public static void readData (Inscripcio[] llistaInscri) {
        ObjectInputStream inputFile;
        try {
            inputFile = new ObjectInputStream(new FileInputStream("llistaInscripcions.ser"));
            for (int i=0; i<llistaInscri.length; i++) {
                llistaInscri[i]=(Inscripcio)inputFile.readObject();
            }
            inputFile.close();
        }
        catch (IOException e) {
            System.out.println("Error en l'arxiu d'entrada.");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error, no es troba la classe Inscripcio."+e);
        }
        catch (ClassCastException e){
            System.out.println("Error, el format de l'arxiu no és correcte per la definició actual de la classe Inscripcio."+e);
        }
    }

    public boolean estaInscrit(String nom){
        boolean trobat = false;
        int i=0;
        while (i<llistaInscri.length && !trobat) {
            if(llistaInscri[i].getNomInscrit().equalsIgnoreCase(nom)){
                trobat = true;
            }
            else{
                i++;
            }
        }
        return trobat;
    }

    }
    
