package dades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import excepcions.ActivitatInexistentException;
import excepcions.NoInscrit;

/**
 * Gestiona una llista d'activitats i ofereix funcionalitats
 * per afegir, eliminar, buscar i filtrar activitats
 */
public class LlistaActivitats {

    private Activitats [] llista; 
    private int numActivitats; //index de la llista
    
    /**
     * Crea una llista d'activitats amb una mida màxima determinada.
     * @param mida Mida maxima de la llista
     */
    public LlistaActivitats(int mida){
        llista = new Activitats[mida];
        numActivitats=0; 
    }

    /**
     * Retorna el nombre d'activitats actuals a la llista
     * 
     * @return Nombre d'activitats
     */
    public int getNumActivitats(){
        return numActivitats; 
    }

    /**
     * Retorna l'activitat en la posicio especificada
     * @param i Index de l'activitat
     * @return Activitats a la posicio i 
     * @throws IndexOutOfBoundsException si l'index es fora de rang
     */
    public Activitats getActivitat(int i){
        if(i<0 || i>= numActivitats){
            throw new IndexOutOfBoundsException("Posicio es troba fora dels limits.");
        }
        return llista[i];
    }

    /**
     * Afegeix una activitat a la llista mantenint l'ordre alfabètic pel nom.
     * També actualitza el fitxer de dades.
     * 
     * @param activitat Activitat a afegir
     * @throws IOException Si hi ha un error escrivint el fitxer
     */
    
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

    /**
     * Elimina una activitat de la llista segons la seva posició.
     * També actualitza el fitxer de dades.
     * 
     * @param pos Posicio de l'activitat a eliminar
     * @throws IOException Si hi ha un error escrivint el fitxer
     * @throws IndexOutOfBoundsException Si la posicio es fora de rang
     */
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

    /**
     * Busca una activitat pel seu nom 
     * 
     * @param nom Nom de l'activitat
     * @return Activitat trobada o null si no existeix 
     */
    public Activitats buscarPerNom(String nom){
        for(int i=0; i<numActivitats; i++){
            if(llista[i].getNom().equalsIgnoreCase(nom)){
                return llista[i]; 
            }
        }
        return null; 
    }

    /**
     * Obte una activitat pel nom i llença excepcio si no existeix 
     * 
     * @param nom Nom de l'activitat
     * @return Activitat trobada 
     * @throws ActivitatInexistentException Si l'activitat no existeix
     */
    public Activitats obtenirPerNom(String nom) throws ActivitatInexistentException{
        Activitats a = buscarPerNom(nom);
        if(a==null){
            throw new ActivitatInexistentException(nom);
        }
        return a; 
    }

    /**
     * Retona un array amb les activitats que estan en periode d'inscripcio
     * 
     * @param avui Data actual
     * @return Array d'activitats disponibles per inscriure's avui 
     */
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

    /**
     * Retorna un array amb les activitat que estan actives avui 
     * 
     * @param avui Data actuual 
     * @return Array d'activitats actives avui 
     */

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

    /**
     * Retorna un array amb les activitats que tenen classe avui 
     * 
     * @param avui Data actual 
     * @return Array d'activitats amb classe avui 
     */
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

    /**
     * Retorna un array amb les activitats que encara tenen places disponibles
     * 
     * @return Array d'activitats amb places disponibles
     */
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

    /**
     * Crea una llista dels noms de les activitats a les que un usuari esta inscrit
     * @param nom
     * @return llista de noms de les activitats
     */
    public String[] activitatsUsuari(String nom) {
        String[] aux = new String[numActivitats];
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].estaInscrit(nom)) {
                aux[i] = llista[i].getNom();
            }
        }
        return aux;
    }

    /**
     * Crea una llista de les valoracions de les activitats a les que un usuari esta inscrit
     * @param nom
     * @return llista de valoracions de les activitats
     */
    public int[] valoracionsperUsuari(String nom) {
        int[] aux = new int[numActivitats];
        for (int i = 0; i < numActivitats; i++) {
            try {
                if (llista[i].obtenirInscripcioPerNom(nom).getValoracio()!=11) {
                    aux[i] = llista[i].obtenirInscripcioPerNom(nom).getValoracio();
                }
            } catch (NoInscrit e) {
                System.out.println(e.getMessage());
            }
        }
        return aux;
    }

    public int nombreActivitatsInscrit(String nomUsu){
        int compt = 0;
        for(int i=0; i<numActivitats; i++){
            if(llista[i].estaInscrit(nomUsu)){
                compt++;
            }
        }
        
        return compt;
    }

    /**
     * Escriu la llista completa d'activitats en un fitxer de text.
     * 
     * @throws IOException Si hi ha un error en escriure el fitxer
     */
    public void escriuFitxerActivitats() throws IOException{
        BufferedWriter escriptura = new BufferedWriter(new FileWriter("FitxerLlistaActivitats.txt"));

        for(int i=0; i<numActivitats; i++){
            escriptura.write(llista[i] + ";");
        }

        escriptura.close();
    }

    /**
     * Comprova si hi ha alguna activitat el dia indicat
     * 
     * @param data Data a comprovar
     * @param ocultarUnDia Boolean que indica si hem d'ignorar les Activitats d'Un Dia (true) o no (false)
     * @param ocultarPeriodic Boolean que indica si hem d'ignorar les Activitats Periòdiques (true) o no (false)
     * @param ocultarOnline Boolean que indica si hem d'ignorar les Activitats Online (true) o no (false)
     * @return true si hi ha alguna activitat aquell dia, false altrament 
     */
    public boolean hiHaActivitat(Data data, boolean ocultarUnDia, boolean ocultarPeriodic, boolean ocultarOnline) {

        boolean trobat = false;

        int i = 0;
        while ((i < numActivitats) && (!trobat)) {
            Activitats activitat = llista[i];
            String tipus = activitat.getTipus();

            // Si trobem cap activitat d'un dia en la data i no tenim activat ocultarUnDia retornem true
            if ((tipus.equalsIgnoreCase("UnDia")) && (!ocultarUnDia)) {
                if (activitat.teClasseAvui(data)) trobat = true;
            }

            // Si trobem cap activitat periòdica i no tenim activat ocularPeriodica retornem true
            else if ((tipus.equalsIgnoreCase("Periodica")) && (!ocultarPeriodic)) {
                if (activitat instanceof ActivitatPeriodica) {
                    ActivitatPeriodica ap = (ActivitatPeriodica) activitat;
                    if ((data.getDiaSetmana().equalsIgnoreCase(ap.getDiaSetmana()) && (!data.esAnterior(ap.getDataInici())) 
                        && (!data.esPosterior(ap.getDataInici().afegirDies(7*ap.getNumSetmanes()))))) trobat = true;
                }
            }

            // Si trobem cap activitat online i no tenim activat ocularOnline retornem true
            else if ((tipus.equalsIgnoreCase("Online")) && (!ocultarOnline)) {
                if (activitat instanceof ActivitatOnline) {
                    ActivitatOnline ao = (ActivitatOnline) activitat;
                    if (!data.esAnterior(ao.getDataInici()) 
                        && (!data.esPosterior(ao.getDataInici().afegirDies(ao.getPeriodeVisualitzacio())))) trobat = true;
                }
            }
            i++;
        }
        
        return trobat;
    }

    /**
     * Mètode per obtenir una llista d'activitats actives o amb classe una data determinada
     * @param data
     * @return
     * @throws IOException 
     */
    public LlistaActivitats llistaActivitatsData(Data data, boolean ocultarUnDia, boolean ocultarPeriodic, boolean ocultarOnline) throws IOException {
        LlistaActivitats llistaData = new LlistaActivitats(numActivitats);

        for (int i = 0; i < numActivitats; i++) {
            Activitats activitat = llista[i];
            String tipus = activitat.getTipus();

            // Si trobem cap activitat d'un dia en la data i no tenim activat ocultarUnDia retornem true
            if ((tipus.equalsIgnoreCase("UnDia")) && (!ocultarUnDia)) {
                if (activitat.teClasseAvui(data)) llistaData.afegir(activitat);
            }

            // Si trobem cap activitat periòdica i no tenim activat ocularPeriodica retornem true
            else if ((tipus.equalsIgnoreCase("Periodica")) && (!ocultarPeriodic)) {
                if (activitat instanceof ActivitatPeriodica) {
                    ActivitatPeriodica ap = (ActivitatPeriodica) activitat;
                    if ((data.getDiaSetmana().equalsIgnoreCase(ap.getDiaSetmana()) && (!data.esAnterior(ap.getDataInici())) 
                        && (!data.esPosterior(ap.getDataInici().afegirDies(7*ap.getNumSetmanes()))))) llistaData.afegir(activitat);
                }
            }

            // Si trobem cap activitat online i no tenim activat ocularOnline retornem true
            else if ((tipus.equalsIgnoreCase("Online")) && (!ocultarOnline)) {
                if (activitat instanceof ActivitatOnline) {
                    ActivitatOnline ao = (ActivitatOnline) activitat;
                    if (!data.esAnterior(ao.getDataInici()) 
                        && (!data.esPosterior(ao.getDataInici().afegirDies(ao.getPeriodeVisualitzacio())))) llistaData.afegir(activitat);
                }
            }
        }

        return llistaData;
    }
}
