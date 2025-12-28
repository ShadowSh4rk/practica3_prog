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

import java.util.Scanner;

public class Menu {
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args) {

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

    }

    public static void opcio2() {
        // 2. Mostrar les dades de les llistes. Es demanarà de quina llista es vol mostrar la informació. 
        //  En cas de llistes amb elements de diferent tipus, es demanarà també si es volen mostrar tots o només els d'un tipus

    }

    public static void opcio3() {
        // 3. Mostrar la informació de les activitats que es troben en període d'inscripció i si encara hi ha places disponibles

    }

    public static void opcio4() {
        // 4. Mostrar la informació de les activitats que tenen classe en la data d'avui (s'ha d'haver fet la opció 1 prèviament)
        //  De cada activitat es mostrarà tota la seva informació, si ha omplert places per la seva capacitat i si hi ha llista d'espera

    }

    public static void opcio5() {
        // 5. Mostrar el nom de les acrtivitats que estan actives en la data d'avui, no cal que hi hagi classe en la data indicada,
        //  però si que la data d'avui estigui en el període de l'activitat, entre la data inicial i la final

    }

    public static void opcio6() {
        // 6. Mostrar el nom de les activitats que tenen places disponibles (tant si estan encara en termini d'inscripció o no)
        
    }

    public static void opcio7() {
        // 7. Mostrar el detall d'informació d'una activitat a partir del seu nom
        
    }
    
    public static void opcio8() {
        // 8. Mostrar el detall d'informació d'un usuari a partir del seu nom
        
    }

    public static void opcio9() {
        // 9. Mostrar les activitats a les que està apuntat un usuari 
        
    }

    public static void opcio10() {
        // 10. Inscriure's a una activitat. Ens podem inscriure si estem dins el termini i l'activitat s'ofereix a usuaris del col·lectiu on pertanyem.
        //  L'usuari que es vol inscriure pot estar ja a la llista, llavors només caldrà indicar el seu alies. Si no el tenim a la llista li haurem de 
        //  demanar tota la resta d'informació per afegir-lo. Haurem de controlar si en el moment de fer la inscripció encara hi ha places disponibles,
        //  o bé, es passa a llista d'espera. També es podria donar el cas que la llista d'espera ja està plena i ja no es permet cap tipus d'inscripció.
        
    }

    public static void opcio11() {
        // 11. Mostrar els usuaris que estan apuntats a una activitat i quins estan en llista d'espera.
        
    }

    public static void opcio12() {
        // 12. Eliminar un usuari d'una activitat. Si hi ha llista d'espera, ek primer de la llista passa a formar part dels usuaris que poden accedir a l'activitat
    }

    public static void opcio13() {
        // 13. Afegir una nova activitat d'un dia
        
    }

    public static void opcio14() {
        // 14. Afegir una nova activitat periòdica
        
    }

    public static void opcio15() {
        // 15. Afegir una nova activitat en línia
        
    }

    public static void opcio16() {
        // 16. Valorar una activitat per part d'un assistent. Per poder fer la valoració, l'activitat ha d'haver acabat i l'usuari ha d'haver assistit a l'activitat
        
    }

    public static void opcio17() {
        // 17. Mostrar el resum de valoracions de les activitats que ja han acabat
        
    }

    public static void opcio18() {
        // 18. Mostrar el resum de valoracions que ha fet un usuari
        
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
    
}
