package proves_unitaries;

import dades.*;

public class PDITest {

    public static void main(String[] args) {

        System.out.println("=== PROVES COMPLETES PDI ===");

        provaCreacio();
        provaGetters();
        provaColectiu();
        provaToString();
    }

    private static PDI crearPDIProvaPdi() {
        return new PDI("joanpdi", "joan.perez", "DEIM", "Sescelades");
    }

    public static void provaCreacio() {
        try {
            PDI p = crearPDIProvaPdi();
            System.out.println("Constructor PDI CORRECTE");
        } catch (Exception e) {
            System.out.println("Constructor PDI INCORRECTE");
        }
    }

    public static void provaGetters() {
        PDI p = crearPDIProvaPdi();

        if (p.getNomDepartament().equals("DEIM")
                && p.getCampusTreballen().equals("Sescelades")) {
            System.out.println("La prova dels getters ha funcionat CORRECTAMENT");
        } else {
            System.out.println("La prova dels getters ha funcionat INCORRECTAMENT");
        }
    }

    public static void provaColectiu() {
        PDI p = crearPDIProvaPdi();

        if (p.getColectiu().equals("PDI")) {
            System.out.println("La prova del Colectiu ha funcionat CORRECTAMENT");
        } else {
            System.out.println("La prova del Colectiu ha funcionat INCORRECTAMENT");
        }
    }

    public static void provaToString() {
        PDI p = crearPDIProvaPdi();
        System.out.println("toString PDI:");
        System.out.println(p);
        System.out.println("provaToString completada VISUALMENT");
    }
}
