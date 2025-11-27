/**
 * Codi per a implementar el Menú, encara molt abstracte
 * 
 * Tasques:
 *  - Carregar les dades dels fitxers
 *  - Inicialitzar les estructures de llistes amb tota la informació
 */

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

                    break;

                case 2:
                    // 2. Mostrar les dades de les llistes. Es demanarà de quina llista es vol mostrar la informació. 
                    //  En cas de llistes amb elements de diferent tipus, es demanarà també si es volen mostrar tots o només els d'un tipus

                    break;

                case 3:
                    // 3. Mostrar la informació de les activitats que es troben en període d'inscripció i si encara hi ha places disponibles

                    break;

                case 4:
                    // 4. Mostrar la informació de les activitats que tenen classe en la data d'avui (s'ha d'haver fet la opció 1 prèviament)
                    //  De cada activitat es mostrarà tota la seva informació, si ha omplert places per la seva capacitat i si hi ha llista d'espera
                    break;

                case 5:
                    // 5. Mostrar el nom de les acrtivitats que estan actives en la data d'avui, no cal que hi hagi classe en la data indicada,
                    //  però si que la data d'avui estigui en el període de l'activitat, entre la data inicial i la final 

                    break;

                default:
                    System.out.println("Opció incorrecta, torna a intentar-ho");
                    break;
            }
            mostraMenu();
            opcio = Integer.parseInt(teclat.nextLine());
        }
    }

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
}
