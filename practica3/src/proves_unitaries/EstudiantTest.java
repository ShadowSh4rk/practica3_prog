package proves_unitaries;

import dades.*;

public class EstudiantTest {
    public static void main(String [] args) {
        System.out.println("===PROVES Estudiant");

        provaCreacio();
        provaGetters();
        provaToString();
      
    }
    private static Estudiant crearEstudiantProva() {
        return new Estudiant ("paula3", "anna.garcia", "GEI", 2021);
    }

    
    public static void provaCreacio() {
        try {
            Estudiant e = crearEstudiantProva();
            System.out.println("Constructor Estudiant CORRECTE");
        } catch (Exception e) {
            System.out.println("Constructor Estudiant INCORRECTE");
        }
    }

    public static void provaGetters() {
        Estudiant e = crearEstudiantProva();

        if (e.getEnsenyament().equals("GEI") && e.getIniciEstudis() == 2021) {
            System.out.println("La prova dels getters ha funcionat CORRECTAMENT");
        } else {
            System.out.println("La prova dels getters no ha funcionat CORRECTAMENT");
        }
    }

    public static void provaColectiu() {
        Estudiant e = crearEstudiantProva();

        if (e.getColectiu().equals("Estudiants")) {
            System.out.println("La prova del Colectiu ha funcionat CORRECTAMENT");
        } else {
            System.out.println("La prova del Colectiu ha funcionat INCORRECTAMENT");
        }
    }

    public static void provaToString() {
        Estudiant e = crearEstudiantProva();
        System.out.println("toString Estudiant:");
        System.out.println(e);
        System.out.println("provaToString completada VISUALMENT");
    }
}

