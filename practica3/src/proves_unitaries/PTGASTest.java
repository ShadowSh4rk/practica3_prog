package proves_unitaries;

import dades.*;

public class PTGASTest {

    public static void main(String[] args) {

        System.out.println("=== PROVES COMPLETES PTGAS ===");

        provaCreacio();
        provaGetters();
        provaColectiu();
        provaToString();
    }

    private static PTGAS crearPTGASProva() {
        return new PTGAS("laura", "laura.gomez", "Catalunya");
    }

    public static void provaCreacio() {
        try {
            PTGAS p = crearPTGASProva();
            System.out.println("Constructor PTGAS CORRECTE");
        } catch (Exception e) {
            System.out.println("Constructor PTGAS INCORRECTE");
        }
    }

    public static void provaGetters() {
        PTGAS p = crearPTGASProva();

        if (p.getCampusTreballen().equals("Catalunya")) {
            System.out.println("La prova dels getters ha funcionat CORRECTAMENT");
        } else {
            System.out.println("La prova dels getters ha funcionat INCORRECTAMENT");
        }
    }

    public static void provaColectiu() {
        PTGAS p = crearPTGASProva();

        if (p.getColectiu().equals("PTGAS")) {
            System.out.println("La prova del Colectiu ha funcionat CORRECTAMENT");
        } else {
            System.out.println("La prova del Colectiu ha funcionat INCORRECTAMENT");
        }
    }

    public static void provaToString() {
        PTGAS p = crearPTGASProva();
        System.out.println("toString PTGAS:");
        System.out.println(p);
        System.out.println("provaToString completada VISUALMENT");
    }
}
