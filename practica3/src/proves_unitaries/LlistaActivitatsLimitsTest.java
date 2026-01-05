package proves_unitaries;

import dades.*;
import java.io.IOException;

public class LlistaActivitatsLimitsTest {

    public static void main(String[] args) {
        System.out.println("=== PROVES LÍMIT LlistaActivitats ===");
        
        try {
            // Creem una llista petita per forçar errors de capacitat
            LlistaActivitats llista = new LlistaActivitats(2);

            ActivitatUnDia a1 = new ActivitatUnDia(
                    "A1", new String[]{"Estudiants"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(5,1,2026),
                    "10:00-12:00","Tarragona", 20, 5.0
            );

            ActivitatUnDia a2 = new ActivitatUnDia(
                    "A2", new String[]{"PDI"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(6,1,2026),
                    "12:00-13:00","Reus", 15, 7.5
            );

            ActivitatUnDia a3 = new ActivitatUnDia(
                    "A3", new String[]{"PTGAS"},
                    new Data(1,1,2026), new Data(10,1,2026),
                    new Data(7,1,2026),
                    "14:00-16:00","Valls", 10, 0.0
            );

            // Afegim dues activitats correctament
            llista.afegir(a1);
            llista.afegir(a2);
            System.out.println("provaAfegir normal CORRECTE");

            // Afegir quan la llista està plena ha de fallar
            try {
                llista.afegir(a3);
                System.out.println("provaAfegir plena INCORRECTE - hauria d'haver fallat");
            } catch (IllegalStateException e) {
                System.out.println("provaAfegir plena CORRECTE - capturada excepció: " + e.getMessage());
            }

            // Prova getActivitat amb index fora de rang
            try {
                llista.getActivitat(-1);
                System.out.println("provaGetIndexNegatiu INCORRECTE");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("provaGetIndexNegatiu CORRECTE - capturada excepció: " + e.getMessage());
            }

            try {
                llista.getActivitat(5);
                System.out.println("provaGetIndexAlt INCORRECTE");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("provaGetIndexAlt CORRECTE - capturada excepció: " + e.getMessage());
            }

            // Prova eliminar amb index fora de rang
            try {
                llista.eliminar(-1);
                System.out.println("provaEliminarIndexNegatiu INCORRECTE");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("provaEliminarIndexNegatiu CORRECTE - capturada excepció: " + e.getMessage());
            }

            try {
                llista.eliminar(3);
                System.out.println("provaEliminarIndexAlt INCORRECTE");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("provaEliminarIndexAlt CORRECTE - capturada excepció: " + e.getMessage());
            }

            // Prova període d'inscripció límit
            Data inici = new Data(1,1,2026);
            Data fi = new Data(10,1,2026);
            ActivitatUnDia a4 = new ActivitatUnDia(
                    "IniciFiTest", new String[]{"Estudiants"}, 
                    inici, fi,
                    new Data(5,1,2026), "10:00-12:00", "TGN", 20, 5.0
            );

            System.out.println("esEnPeriodeInscripcio inici: " + a4.esEnPeriodeInscripcio(inici)); // true
            System.out.println("esEnPeriodeInscripcio fi: " + a4.esEnPeriodeInscripcio(fi));       // true
            System.out.println("esEnPeriodeInscripcio abans: " + a4.esEnPeriodeInscripcio(new Data(31,12,2025))); // false
            System.out.println("esEnPeriodeInscripcio després: " + a4.esEnPeriodeInscripcio(new Data(11,1,2026))); // false

            // Afegim i eliminem activitats diverses per veure que l'ordre es manté
            LlistaActivitats llista2 = new LlistaActivitats(5);
            llista2.afegir(new ActivitatUnDia("C", new String[]{"Est"}, new Data(1,1,26), new Data(10,1,26), new Data(5,1,26),"10-11","TGN",5,5));
            llista2.afegir(new ActivitatUnDia("A", new String[]{"Est"}, new Data(1,1,26), new Data(10,1,26), new Data(5,1,26),"10-11","TGN",5,5));
            llista2.afegir(new ActivitatUnDia("B", new String[]{"Est"}, new Data(1,1,26), new Data(10,1,26), new Data(5,1,26),"10-11","TGN",5,5));

            System.out.println("Llista ordenada després d'afegir A,B,C:");
            for(int i=0;i<llista2.getNumActivitats();i++){
                System.out.println(llista2.getActivitat(i).getNom());
            }

            // Eliminem B i comprovem l'ordre
            for(int i=0;i<llista2.getNumActivitats();i++){
                if(llista2.getActivitat(i).getNom().equals("B")){
                    llista2.eliminar(i);
                    break;
                }
            }

            System.out.println("Llista després d'eliminar B:");
            for(int i=0;i<llista2.getNumActivitats();i++){
                System.out.println(llista2.getActivitat(i).getNom());
            }

        } catch (IOException e) {
            System.out.println("Error en proves: " + e.getMessage());
        }
    }
}
