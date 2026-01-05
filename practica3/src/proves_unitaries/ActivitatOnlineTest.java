package proves_unitaries;

import dades.*;

public class ActivitatOnlineTest {

    public static void main(String[] args) {

        System.out.println("=== PROVES COMPLETES ActivitatOnline ===");

        provaCreacio();
        provaTeClasseAvui();
        provaEsActivaAvui();
        provaPreu();
        provaHaAcabat();
        provaPeriode0();
        provaFiIniciError();
    }

    private static ActivitatOnline crearActivitatBase() {
        String[] colectius = {"PDI", "Estudiants"};
        return new ActivitatOnline(
                "Curs Java Online",
                colectius,
                new Data(1, 3, 2024),
                new Data(10, 3, 2024),
                new Data(15, 3, 2024),
                10,
                "https://aula.virtual/java"
        );
    }

    public static void provaCreacio() {
        try {
            ActivitatOnline act = crearActivitatBase();
            System.out.println("provaCreacio CORRECTE");
        } catch (Exception e) {
            System.out.println("provaCreacio INCORRECTE - excepció: " + e.getMessage());
        }
    }

    public static void provaTeClasseAvui() {
        ActivitatOnline act = crearActivitatBase();
        boolean resultat = act.teClasseAvui(new Data(16, 3, 2024));

        if (!resultat) {
            System.out.println("provaTeClasseAvui CORRECTE");
        } else {
            System.out.println("provaTeClasseAvui INCORRECTE");
        }
    }

    public static void provaEsActivaAvui() {
        ActivitatOnline act = crearActivitatBase();

        Data avui = new Data(20, 3, 2024); // dins del període
        if (act.esActivaAvui(avui)) {
            System.out.println("provaEsActivaAvui CORRECTE");
        } else {
            System.out.println("provaEsActivaAvui INCORRECTE");
        }
    }

    public static void provaPreu() {
        ActivitatOnline act = crearActivitatBase();

        if (act.getPreu() == 0) {
            System.out.println("provaPreu CORRECTE");
        } else {
            System.out.println("provaPreu INCORRECTE");
        }
    }

    public static void provaHaAcabat() {
        ActivitatOnline act = crearActivitatBase();

        Data avui = new Data(30, 3, 2024); // després del període
        if (act.haAcabat(avui)) {
            System.out.println("provaHaAcabat CORRECTE");
        } else {
            System.out.println("provaHaAcabat INCORRECTE");
        }
    }

    public static void provaPeriode0() {
        try {
            String[] colectius = {"Adults"};
            ActivitatOnline act = new ActivitatOnline(
                    "Online curt",
                    colectius,
                    new Data(1, 3, 2024),
                    new Data(10, 3, 2024),
                    new Data(15, 3, 2024),
                    0,
                    "https://online.test"
            );

            Data avui = new Data(16, 3, 2024);
            if (!act.esActivaAvui(avui)) {
                System.out.println("provaPeriode0 CORRECTE");
            } else {
                System.out.println("provaPeriode0 INCORRECTE");
            }
        } catch (Exception e) {
            System.out.println("provaPeriode0 INCORRECTE - excepció: " + e.getMessage());
        }
    }

    public static void provaFiIniciError() {
        try {
            String[] colectius = {"Adults"};
            new ActivitatOnline(
                    "Error dates",
                    colectius,
                    new Data(10, 3, 2024),
                    new Data(1, 3, 2024), // fi anterior a inici
                    new Data(15, 3, 2024),
                    5,
                    "https://error.test"
            );
            System.out.println("provaFiIniciError INCORRECTE - hauria d'haver fallat");
        } catch (IllegalArgumentException e) {
            System.out.println("provaFiIniciError CORRECTE - capturada excepció: " + e.getMessage());
        }
    }
}
