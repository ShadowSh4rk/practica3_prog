/**
 * Codi per a implementar el Menú, encara molt abstracte
 * 
 * Tasques:
 *  - Carregar les dades dels fitxers
 *  - Inicialitzar les estructures de llistes amb tota la informació
 * 
 * Apunts:
 *  - Potser hi ha opcions que no cal que cridin a una funció, però al principi anem a posar-ho a totes les opcions
 */
package aplicacio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import dades.*;
import excepcions.*;

public class Menu {
    static Scanner teclat = new Scanner(System.in);

    public static Data avui = new Data(31, 12, 2025);
    public static LlistaActivitats llistaAct;
    public static LlistaUsuaris llistaUsu;

    public static void main(String[] args) throws IOException {

        int numLinies = llegeixLiniesFitxer("FitxerLlistaActivitats.txt");

        afegeixActivitatsDesdeFitxer(numLinies, llistaAct);

        avui = new Data(31, 12, 2025);

        mostraMenu();
        int opcio = Integer.parseInt(teclat.nextLine());
        while (opcio != 22) {
            switch (opcio) {
                case 1:
                    // 1. Indicar la data del dia d'avui, és a dir, hem de poder escriure en quina data volem estar
                    opcio1();
                    break;

                case 2:
                    // 2. Mostrar les dades de les llistes. Es demanarà de quina llista es vol mostrar la informació. 
                    //  En cas de llistes amb elements de diferent tipus, es demanarà també si es volen mostrar tots o només els d'un tipus
                    opcio2();
                    break;

                case 3:
                    // 3. Mostrar la informació de les activitats que es troben en període d'inscripció i si encara hi ha places disponibles
                    opcio3();
                    break;

                case 4:
                    // 4. Mostrar la informació de les activitats que tenen classe en la data d'avui (s'ha d'haver fet la opció 1 prèviament)
                    //  De cada activitat es mostrarà tota la seva informació, si ha omplert places per la seva capacitat i si hi ha llista d'espera
                    opcio4();
                    break;

                case 5:
                    // 5. Mostrar el nom de les acrtivitats que estan actives en la data d'avui, no cal que hi hagi classe en la data indicada,
                    //  però si que la data d'avui estigui en el període de l'activitat, entre la data inicial i la final 
                    opcio5();
                    break;

                case 6:
                    // 6. Mostrar el nom de les activitats que tenen places disponibles (tant si estan encara en termini d'inscripció o no)
                    opcio6();
                    break;

                case 7:
                    // 7. Mostrar el detall d'informació d'una activitat a partir del seu nom
                    opcio7();
                    break;

                case 8:
                    // 8. Mostrar el detall d'informació d'un usuari a partir del seu nom
                    opcio8();
                    break;

                case 9: 
                    // 9. Mostrar les activitats a les que està apuntat un usuari 
                    opcio9();
                    break;

                case 10: 
                    // 10. Inscriure's a una activitat. Ens podem inscriure si estem dins el termini i l'activitat s'ofereix a usuaris del col·lectiu on pertanyem.
                    //  L'usuari que es vol inscriure pot estar ja a la llista, llavors només caldrà indicar el seu alies. Si no el tenim a la llista li haurem de 
                    //  demanar tota la resta d'informació per afegir-lo. Haurem de controlar si en el moment de fer la inscripció encara hi ha places disponibles,
                    //  o bé, es passa a llista d'espera. També es podria donar el cas que la llista d'espera ja està plena i ja no es permet cap tipus d'inscripció.
                    opcio10();
                    break;

                case 11:
                    // 11. Mostrar els usuaris que estan apuntats a una activitat i quins estan en llista d'espera.
                    opcio11();
                    break;

                case 12:
                    // 12. Eliminar un usuari d'una activitat. Si hi ha llista d'espera, ek primer de la llista passa a formar part dels usuaris que poden accedir
                    //  a l'activitat
                    opcio12();
                    break;

                case 13:
                    // 13. Afegir una nova activitat d'un dia
                    opcio13();
                    break;

                case 14:
                    // 14. Afegir una nova activitat periòdica
                    opcio14();
                    break;

                case 15:
                    // 15. Afegir una nova activitat en línia
                    opcio15();
                    break;

                case 16:
                    // 16. Valorar una activitat per part d'un assistent. Per poder fer la valoració, l'activitat ha d'haver acabat i l'usuari ha d'haver assistit
                    //  a l'activitat
                    opcio16();
                    break;

                case 17:
                    // 17. Mostrar el resum de valoracions de les activitats que ja han acabat
                    opcio17();
                    break;

                case 18:
                    // 18. Mostrar el resum de valoracions que ha fet un usuari
                    opcio18();
                    break;

                case 19:
                    // 19. Valoren iguals els usuaris dels diferents col·lectius? Calcula i mostra la mitja de valoracions que han fet els usuaris de cada col·lectiu
                    opcio19();
                    break;

                case 20:
                    // Calcular l'usuari més actiu d'un cert col·lectiu, és a dir, el què s'ha apuntat a més activitats. En cas d'empat s'escull qualsevol dels
                    //  usuaris que compleixen els requisits
                    opcio20();
                    break;

                case 21:
                    // Donar de baixa les activitats que ja han acabat el període d'inscripció i no han arribat a omplir el 10% de les places que s'oferien.
                    //  En cas de les activitats en línia donar de baixa si el número d'inscrits és inferior a 20 persones.
                    opcio21();
                    break;

                default:
                    System.out.println("Codi d'opció no vàlid");
                    break;
            }
            mostraMenu();
            opcio = Integer.parseInt(teclat.nextLine());
        }
    }


    /**
     * Funció que escriu les opcions visibles per a l'usuari en l'aplicació E/S
     */
    public static void mostraMenu() {
        System.out.println("\n\n======================================== MENÚ PROGRAMA BENESTAR URV ========================================");
        System.out.println("Opcions del menú:");
        System.out.println("\t1. Indicar la data del dia d'avui");
        System.out.println("\t2. Mostrar les dades de les llistes");
        System.out.println("\t3. Mostrar la informació de les activitats que es troben en període d'inscripció i si encara hi ha places disponibles");
        System.out.println("\t4. Mostrar la informació de les activitats que tenen classe en la data d'avui");
        System.out.println("\t5. Mostrar el nom de les activitats que estan actives en la data d'avui");
        System.out.println("\t6. Mostrar el nom de les activitats que tenen places disponibles");
        System.out.println("\t7. Mostrar el detall d'informació d'una activitat a partir del seu nom");
        System.out.println("\t8. Mostrar el detall d'informació d'un usuari a partir del seu nom");
        System.out.println("\t9. Mostrar les activitats a les que està apuntat a un usuari");
        System.out.println("\t10. Inscriure's a una activitat");
        System.out.println("\t11. Mostrar els usuaris que estan apuntats a una activitat i quins estan en llista d'espera");
        System.out.println("\t12. Eliminar un usuari d'una activitat");
        System.out.println("\t13. Afegir una nova activitat d'un dia");
        System.out.println("\t14. Afegir una nova activitat periòdica");
        System.out.println("\t15. Afegir una nova activitat en línia");
        System.out.println("\t16. Valorar una activitat per part d'un assistent");
        System.out.println("\t17. Mostrar el resum de valoracions de les activitats que ja han acabat");
        System.out.println("\t18. Mostrar el resum de valoracions que ha fet un usuari");
        System.out.println("\t19. Càlcul de les valoracions que han fet els usuaris de cada col·lectiu");
        System.out.println("\t20. Calcular l'usuari més actiu d'un cert col·lectiu");
        System.out.println("\t21. Donar de baixa activitats");
        System.out.println("\t22. Sortir");
    }

    public static void opcio1() {
        // 1. Indicar la data del dia d'avui, és a dir, hem de poder escriure en quina data volem estar
        System.out.println("indica el dia");
        avui.setDia(Integer.parseInt(teclat.nextLine()));

        System.out.println("indica el mes");
        avui.setMes(Integer.parseInt(teclat.nextLine()));
        
        System.out.println("indica l'any");
        avui.setAny(Integer.parseInt(teclat.nextLine()));

        System.out.println("la data del dia d'avui es "+avui);
    }

    public static void opcio2() {
        // 2. Mostrar les dades de les llistes. Es demanarà de quina llista es vol mostrar la informació. 
        //  En cas de llistes amb elements de diferent tipus, es demanarà també si es volen mostrar tots o només els d'un tipus

        System.out.println("de quina llista vols mostrar informació?");
        System.out.println("\t[1] Activitats");
        System.out.println("\t[2] Usuaris");
        System.out.println("\t[3] Cancel·la accio");

        int opcio = Integer.parseInt(teclat.nextLine());
        if (opcio != 3) {
            switch (opcio) {
                case 1:
                    System.out.println("quines activitats vols mostrar?");
                    System.out.println("\t[1] Un dia");
                    System.out.println("\t[2] Periodiques");
                    System.out.println("\t[3] Online");
                    System.out.println("\t[4] Totes les activitats");

                    int seleccionaTipus = Integer.parseInt(teclat.nextLine());
                    switch (seleccionaTipus){
                        case 1:
                            for(int i=0; i<llistaAct.getNumActivitats(); i++){
                                if(llistaAct.getActivitat(i).getTipus().equalsIgnoreCase("undia")){
                                    System.out.println(llistaAct.getActivitat(i));
                                }
                            }
                            break;
                        case 2:
                            for(int i=0; i<llistaAct.getNumActivitats(); i++){
                                if(llistaAct.getActivitat(i).getTipus().equalsIgnoreCase("periodica")){
                                    System.out.println(llistaAct.getActivitat(i));
                                }
                            }
                            break;
                        case 3:
                            for(int i=0; i<llistaAct.getNumActivitats(); i++){
                                if(llistaAct.getActivitat(i).getTipus().equalsIgnoreCase("online")){
                                    System.out.println(llistaAct.getActivitat(i));
                                }
                            }
                            break;
                        case 4:
                            for(int i=0; i<llistaAct.getNumActivitats(); i++){
                                System.out.println(llistaAct.getActivitat(i));
                            }
                            break;
                        default:
                            System.out.println("opcio invalida");
                            break;
                    }
                    break;
                
                case 2:
                    System.out.println("printeja usuaris");
                    //...
                    break;
                
                default:
                    System.out.println("opcio no valida");
                    break;
            }
        }


    }

    public static void opcio3() {
        // 3. Mostrar la informació de les activitats que es troben en període d'inscripció i si encara hi ha places disponibles
        System.out.println("activitats que es troben en període d'inscripcio:");

        for(int i=0; i<llistaAct.getNumActivitats(); i++){
            if(llistaAct.getActivitat(i).esEnPeriodeInscripcio(avui)){
                System.out.println(llistaAct.getActivitat(i));
                int places = llistaAct.getActivitat(i).getLimitPlaces()-llistaAct.getActivitat(i).getnIns();
                if(places!=0){
                    System.out.println("te "+places+" places disponibles");
                }else{
                    System.out.println("no te places disponibles");
                }
            }
        }
    }

    public static void opcio4() {
        // 4. Mostrar la informació de les activitats que tenen classe en la data d'avui (s'ha d'haver fet la opció 1 prèviament)
        //  De cada activitat es mostrarà tota la seva informació, si ha omplert places per la seva capacitat i si hi ha llista d'espera

        System.out.println("activitats que tenen classe a la data d'avui:");

        for(int i=0; i<llistaAct.getNumActivitats(); i++){
            if(llistaAct.getActivitat(i).teClasseAvui(avui)){
                System.out.println(llistaAct.getActivitat(i));
                int places = llistaAct.getActivitat(i).getLimitPlaces()-llistaAct.getActivitat(i).getnIns();
                if(places!=0){
                    System.out.println("te "+places+" places disponibles");
                }else{
                    System.out.println("no te places disponibles");
                }
                int llistaEsp = llistaAct.getActivitat(i).getnEsp();
                if(llistaEsp!=0){
                    System.out.println("hi ha una llista d'espera de "+llistaEsp+" places");
                }else{
                    System.out.println("no hi ha llista d'espera");
                }
            }
        }

    }

    public static void opcio5() {
        // 5. Mostrar el nom de les activitats que estan actives en la data d'avui, no cal que hi hagi classe en la data indicada,
        //  però si que la data d'avui estigui en el període de l'activitat, entre la data inicial i la final

        System.out.println("activitats actives a la data d'avui:");

        for(int i=0; i<llistaAct.getNumActivitats(); i++){
            if(llistaAct.getActivitat(i).esActivaAvui(avui)){
                System.out.println(llistaAct.getActivitat(i).getNom());
            }
        }
    }

    public static void opcio6() {
        // 6. Mostrar el nom de les activitats que tenen places disponibles (tant si estan encara en termini d'inscripció o no)
        System.out.println("activitats amb places disponibles:");

        for(int i=0; i<llistaAct.getNumActivitats(); i++){
            if((llistaAct.getActivitat(i).getLimitPlaces()-llistaAct.getActivitat(i).getnIns())!=0){
                System.out.println(llistaAct.getActivitat(i).getNom()+" te "+llistaAct.getActivitat(i).getnIns()+" places");
            }
        }
    }

    public static void opcio7() {
        // 7. Mostrar el detall d'informació d'una activitat a partir del seu nom
        System.out.println("\tintrodueix el nom de l'activitat:");
        llistaAct.buscarPerNom(teclat.nextLine());
        
    }
    
    public static void opcio8() {
        // 8. Mostrar el detall d'informació d'un usuari a partir del seu nom
        System.out.println("\tintrodueix el nom de l'usuari:");
        llistaUsu.buscarPerNom(teclat.nextLine());
    }

    public static void opcio9() {
        // 9. Mostrar les activitats a les que està apuntat un usuari 
        System.out.println("\tintrodueix el nom de l'usuari:");
        llistaAct.activitatsUsuari(teclat.nextLine());
        
    }

    public static void opcio10(){
        // 10. Inscriure's a una activitat. Ens podem inscriure si estem dins el termini i l'activitat s'ofereix a usuaris del col·lectiu on pertanyem.
        //  L'usuari que es vol inscriure pot estar ja a la llista, llavors només caldrà indicar el seu alies. Si no el tenim a la llista li haurem de 
        //  demanar tota la resta d'informació per afegir-lo. Haurem de controlar si en el moment de fer la inscripció encara hi ha places disponibles,
        //  o bé, es passa a llista d'espera. També es podria donar el cas que la llista d'espera ja està plena i ja no es permet cap tipus d'inscripció.

        // Demanem a l'usuari quina activitat vol consultar
        System.out.println("Introdueix el nom de l'activitat:");
        String nomAct = teclat.nextLine();

        try {
            // Busquem l'activitat dins de la llista
            Activitats act = llistaAct.obtenirPerNom(nomAct); //llença ActivitatInexistentException

            //Demanem nom de l'usuari
            System.out.println("Introdueix el teu nom:");
            String nomUsu = teclat.nextLine();
            if(act.estaInscrit(nomUsu)){
                System.out.println("ja estas inscrit. el teu alies es: "+act.buscaAliesInscripcio(nomUsu));
            }else{
                Inscripcio inscri = new Inscripcio(nomAct, nomUsu, avui);

                try {
                    act.afegir(inscri);

                } catch (ForaPeriodeInscripcio e){
                    System.out.println(e.getMessage());

                } catch (NoAcceptaCol e){
                    System.out.println(e.getMessage());

                } catch (NoQuedenPlaces e){
                    System.out.println(e.getMessage());
                }
            }

        } catch (ActivitatInexistentException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void opcio11() {
    // 11. Mostrar els usuaris que estan apuntats a una activitat i quins estan en llista d'espera.

    // Demanem a l'usuari quina activitat vol consultar
    System.out.println("Introdueix el nom de l'activitat:");
    String nomAct = teclat.nextLine();

    // Busquem l'activitat dins de la llista
    Activitats act = llistaAct.buscarPerNom(nomAct);

    if (act != null) {
        System.out.println("Activitat: " + act.getNom());

        boolean hiHaInscrits = false;

        // Llista d'inscrits
        for (int i = 0; i < act.nIns; i++) {
            if (act.llistaInscri[i] != null) {
                System.out.println("Inscrit: " + act.llistaInscri[i].getNomInscrit());
                hiHaInscrits = true;
            }
        }

        if (!hiHaInscrits) {
            System.out.println("No hi ha usuaris inscrits en aquesta activitat.");
        }

        System.out.println("\nUsuaris en llista d'espera:");
        boolean hiHaEspera = false;

        for (int i = 0; i < act.nEsp; i++) {
            if (act.llistaEspera[i] != null) {
                System.out.println("Espera: " + act.llistaEspera[i].getNomInscrit());
                hiHaEspera = true;
            }
        }

        if (!hiHaEspera) {
            System.out.println("No hi ha usuaris en llista d'espera.");
        }

    } else {
        System.out.println("No s'ha trobat cap activitat amb aquest nom.");
    }
}

    public static void opcio12() {
    // 12. Eliminar un usuari d'una activitat.

    System.out.println("Introdueix el nom de l'activitat:");
    String nomAct = teclat.nextLine();
    Activitats act = llistaAct.buscarPerNom(nomAct);

    if (act != null) {
        System.out.println("Introdueix el nom de l'usuari a eliminar:");
        String nomUsu = teclat.nextLine();

        boolean trobat = false;
        int pos = 0;

        for (int i = 0; i < act.nIns && !trobat; i++) {
            if (act.llistaInscri[i] != null && act.llistaInscri[i].getNomInscrit().equalsIgnoreCase(nomUsu)) {
                pos = i;
                trobat = true;
            }
        }

        if (trobat) {
            // Desplacem els elements de la llista d'inscrits cap a l'esquerra
            for (int j = pos; j < act.nIns - 1; j++) {
                act.llistaInscri[j] = act.llistaInscri[j + 1];
            }
            act.llistaInscri[act.nIns - 1] = null; // netegem últim element
            act.nIns--;
            System.out.println("Usuari eliminat correctament.");

            // Si hi ha llista d'espera, afegim el primer de la llista a la llista d'inscrits
            if (act.nEsp > 0) {
                act.llistaInscri[act.nIns] = act.llistaEspera[0];
                act.nIns++;

                // Desplacem la llista d'espera cap a l'esquerra
                for (int j = 0; j < act.nEsp - 1; j++) {
                    act.llistaEspera[j] = act.llistaEspera[j + 1];
                }
                act.llistaEspera[act.nEsp - 1] = null; // netegem últim element
                act.nEsp--;
            }

        } else {
            System.out.println("Usuari no trobat a l'activitat.");
        }

    } else {
        System.out.println("No s'ha trobat cap activitat amb aquest nom.");
    }
}

    public static void opcio13() {
        // 13. Afegir una nova activitat d'un dia
       try{
        System.out.println("Nom de l'activitat: ");
        String nom=teclat.nextLine();

        System.out.println("Col·lectius: (separats per comes)");
        String [] col=teclat.nextLine().split(",");

        System.out.println("Data inici de la inscripcio (dia mes any):");
        Data iniciIns=new Data(Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()));
        System.out.println("Data fi de la inscripcio (dia mes any):");
        Data fiIns=new Data(Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()));
    
        System.out.println("Limit de places: ");
        int limitPlaces=Integer.parseInt(teclat.nextLine());

        System.out.println("Data de l'activitat (dia mes any)");
        Data dataAct = new Data(Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()));
        
        System.out.println("Horari (ex. 10:00-12:00): ");
        String horari = teclat.nextLine();

        System.out.println("Ciutat:");
        String ciutat = teclat.nextLine();

        System.out.println("Preu: ");
        Double preu = Double.parseDouble(teclat.nextLine());

        Activitats act = new ActivitatUnDia(nom, col, iniciIns, fiIns, dataAct, horari, ciutat, limitPlaces, preu);

        llistaAct.afegir(act);
        System.out.println("Activitat d'un dia s'ha afegit correctament!");
       }catch(Exception e){
        System.out.println("Error en afegir activitat d'un dia");
       }
        
    }

    public static void opcio14() {
        // 14. Afegir una nova activitat periòdica
        try{
        System.out.println("Nom de l'activitat: ");
        String nom=teclat.nextLine();

        System.out.println("Col·lectius: (separats per comes)");
        String [] col=teclat.nextLine().split(",");

        System.out.println("Data inici de la inscripcio (dia mes any):");
        Data iniciIns=new Data(Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()));
        System.out.println("Data fi de la inscripcio (dia mes any):");
        Data fiIns=new Data(Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()));
    
        System.out.println("Límit de places:");
        int limitPlaces = Integer.parseInt(teclat.nextLine());

        System.out.println("Dia de la setmana:");
        String diaSetmana = teclat.nextLine();

        System.out.println("Horari (ex. 10:00-12:00): ");
        String horari = teclat.nextLine();

        System.out.println("Data inici activitat (dia mes any):");
        Data dataIni = new Data(Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()));

        System.out.println("Numero de setmanes: ");
        int numSetmanes=Integer.parseInt(teclat.nextLine());

        System.out.println("Centre: ");
        String centre = teclat.nextLine();
        
        System.out.println("Ciutat:");
        String ciutat = teclat.nextLine();

        System.out.println("Preu total: ");
        Double preuTotal = Double.parseDouble(teclat.nextLine());

        Activitats act = new ActivitatPeriodica(nom, col, iniciIns, fiIns, diaSetmana, horari, dataIni, numSetmanes, centre, ciutat, limitPlaces, preuTotal);

        llistaAct.afegir(act);
        System.out.println("Activitat periodica s'ha afegit correctament!");
       }catch(Exception e){
        System.out.println("Error en afegir activitat periodica");
       }
    }

    public static void opcio15() {
        // 15. Afegir una nova activitat en línia
         try{
        System.out.println("Nom de l'activitat: ");
        String nom=teclat.nextLine();

        System.out.println("Col·lectius: (separats per comes)");
        String [] col=teclat.nextLine().split(",");

        System.out.println("Data inici de la inscripcio (dia mes any):");
        Data iniciIns=new Data(Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()));
        System.out.println("Data fi de la inscripcio (dia mes any):");
        Data fiIns=new Data(Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()));
    
        
        System.out.println("Data inici activitat (dia mes any):");
        Data dataIni = new Data(Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()), Integer.parseInt(teclat.nextLine()));

        System.out.println("Període de visualització (dies):");
        int periode = Integer.parseInt(teclat.nextLine());

        System.out.println("Enllaç:");
        String enllac = teclat.nextLine();
        
        Activitats act = new ActivitatOnline(nom, col, iniciIns, fiIns,dataIni, periode, enllac);

        llistaAct.afegir(act);
        System.out.println("Activitat online s'ha afegit correctament!");
       }catch(Exception e){
        System.out.println("Error en afegir activitat online");
       }
        
    }

    public static void opcio16() {
        // 16. Valorar una activitat per part d'un assistent. Per poder fer la valoració, l'activitat ha d'haver acabat i l'usuari ha d'haver assistit a l'activitat
        System.out.println("nom de l'activitat a valorar:");
        Activitats act = llistaAct.buscarPerNom(teclat.nextLine()); //obtenim l'activitat

        System.out.println("nom de l'usuari que la valora:");
        try {
            Inscripcio inscri = act.obtenirInscripcioPerNom(teclat.nextLine());
            try {
                System.out.println("valoracio (nombre del 0 al 10):");
                inscri.assignarValoracio(Integer.parseInt(teclat.nextLine()), avui);

            } catch (ActivitatNoFinalitzada e) {
                System.out.println(e.getMessage());

            } catch (ForaDeRang e){
                System.out.println(e.getMessage());
            }

        } catch (NoInscrit e) {
            System.out.println(e.getMessage());
        }
        
    }

    public static void opcio17() {
        // 17. Mostrar el resum de valoracions de les activitats que ja han acabat
        llistaAct.valoracionsActivitats();
        
    }

    public static void opcio18() {
        // 18. Mostrar el resum de valoracions que ha fet un usuari
        System.out.println("introdueix el nom d'usuari:");
        llistaAct.valoracionsperUsuari(teclat.nextLine());
        
    }

    public static void opcio19() {
        // 19. Valoren iguals els usuaris dels diferents col·lectius? Calcula i mostra la mitja de valoracions que han fet els usuaris de cada col·lectiu
        
    }

    public static void opcio20() {
        // Calcular l'usuari més actiu d'un cert col·lectiu, és a dir, el què s'ha apuntat a més activitats. En cas d'empat s'escull qualsevol dels
        //  usuaris que compleixen els requisits
        
    }

    public static void opcio21() {
        // Donar de baixa les activitats que ja han acabat el període d'inscripció i no han arribat a omplir el 10% de les places que s'oferien.
        //  En cas de les activitats en línia donar de baixa si el número d'inscrits és inferior a 20 persones.
        
    }

    public static int llegeixLiniesFitxer(String nomFitxer) throws IOException{
        BufferedReader lectura;
        int numLinies = 0;
        try{
            lectura = new BufferedReader(new FileReader(nomFitxer));

            while(lectura.readLine()!=null){
                numLinies++;
            }
            lectura.close();

        }catch(FileNotFoundException e){
            System.out.println("fitxer no trobat, no es pot llegir");
        }catch(IOException e){
            System.out.println("altres errors al llegir el fitxer");
        }
        return numLinies;
    }

    public static void afegeixActivitatsDesdeFitxer(int numActivitats, LlistaActivitats llista) throws IOException{
        BufferedReader lectura;

        try{ //control de si podem obrir o no el fitxer
            lectura = new BufferedReader(new FileReader("FitxerLlistaActivitats.txt"));
            String linia;
            String [] trossos, trossosCol, col;
            int i=0;

            linia = lectura.readLine();
            while(linia!=null && i>numActivitats){
                    trossos = linia.split(";");

                    Activitats activitat;

                    String tipus = trossos [0];

                    //====== en comu ====
                    String nom = trossos[1];
                    trossosCol = trossos[2].split(",");
                    col = new String[trossosCol.length];
                    for(int j=0; i<trossosCol.length; j++){
                        col[j]=trossosCol[j];
                    }

                    Data iniciInscri = new Data(Integer.parseInt(trossos[3]), Integer.parseInt(trossos[4]), Integer.parseInt(trossos[5]));
                    Data fiInscri = new Data(Integer.parseInt(trossos[6]), Integer.parseInt(trossos[7]), Integer.parseInt(trossos[8]));

                    int limPlaces = Integer.parseInt(trossos[9]);

                    //====== per separat ====

                    if (tipus.equalsIgnoreCase("undia")){
                        Data data = new Data(Integer.parseInt(trossos[10]), Integer.parseInt(trossos[11]), Integer.parseInt(trossos[12]));

                        String horari = trossos[13];
                        String ciutat = trossos[14];
                        double preu = Double.parseDouble(trossos[15]);

                        activitat = new ActivitatUnDia(nom, col, iniciInscri, fiInscri, data, horari, ciutat, limPlaces, preu);
                        
                    }else if(tipus.equalsIgnoreCase("periodica")){
                        String diaSetmana = trossos[10];
                        String horari = trossos[11];
                        Data dataIni = new Data(Integer.parseInt(trossos[12]), Integer.parseInt(trossos[13]), Integer.parseInt(trossos[14]));
                        int numSetmanes = Integer.parseInt(trossos[15]);
                        String centre = trossos[16];
                        String ciutat = trossos [17];
                        double preuTotal = Double.parseDouble(trossos[18]);

                        activitat = new ActivitatPeriodica(nom, col, iniciInscri, fiInscri, diaSetmana, horari, dataIni, numSetmanes, centre, ciutat, limPlaces, preuTotal);

                    }else{ //activitatOnline
                        Data dataIni = new Data(Integer.parseInt(trossos[10]), Integer.parseInt(trossos[11]), Integer.parseInt(trossos[12]));

                        int periodeVis = Integer.parseInt(trossos[13]);
                        String enllac = trossos[14];

                        activitat = new ActivitatOnline(nom, col, iniciInscri, fiInscri, dataIni, periodeVis, enllac);
                    }

                    llista.afegir(activitat);
                    i++;

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
