package aplicacio;

import javax.swing.*;
import java.awt.*;

public class Visualitzacio extends JFrame {
    private JPanel panellMesos, panellSuperior;
    private JButton botoEsquerra, botoDreta;
    private JButton[][] graellaMesos;
    private JLabel etiquetaAny;
    private int anyActual;

    public Visualitzacio(String titol, String any) {
        super(titol);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 300);
        this.setLocation(750,300);
        this.setVisible(true);
        this.setLayout(new BorderLayout());

        this.anyActual = Integer.parseInt(any);         // Convertim l'any a un enter


        // Crear panell superior que conté botons per modificar l'any i l'etiqueta de l'any
        panellSuperior = new JPanel();
        panellSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Etiqueta de l'any
        etiquetaAny = new JLabel("Any: "+any);
        etiquetaAny.setFont(new Font("Calibri", Font.BOLD, 24));
        etiquetaAny.setForeground(Color.BLACK);
        etiquetaAny.setBounds(0, 10, 500, 30);

        // Botó de l'esquerra per a decrementar l'any
        botoEsquerra = new JButton("←");
        botoEsquerra.setFont(new Font("Calibri", Font.BOLD, 24));
        botoEsquerra.addActionListener(new AccioBotonsAny(this, false));

        // Botó de la dreta per a incrementar l'any 
        botoDreta = new JButton("→");
        botoDreta.setFont(new Font("Calibri", Font.BOLD, 24));
        botoDreta.addActionListener(new AccioBotonsAny(this, true));

        // Afegim el botó de l'esquerra, l'etiqueta de l'any i el botó de la dreta
        panellSuperior.add(botoEsquerra);
        panellSuperior.add(etiquetaAny);
        panellSuperior.add(botoDreta);

        this.add(panellSuperior, BorderLayout.NORTH);   // Afegim el panell superior a la visualització


        // Crear un panell per als mesos
        graellaMesos = new JButton[4][3];   // Panell que permet interactuar amb els mesos
        panellMesos = new JPanel();         // Panell que permet visualitzar els mesos
        panellMesos.setLayout(new GridLayout(4, 3, 10, 10));
        panellMesos.setBounds(50, 50, 400, 200);

        // Creació dels botons per a cada mes
        String mesos[] = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Septembre", "Octubre", "Novembre", "Desembre"};
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (index < mesos.length) {
                    graellaMesos[i][j] = new JButton(mesos[index]);
                    panellMesos.add(graellaMesos[i][j]);
                    index++;
                }
            }
        }

        this.add(panellMesos);      // Afegim el panell de mesos

        this.setVisible(true);
    }

    public void actualitzarAny(boolean esIncrement) {
        if (esIncrement) anyActual++;
        else anyActual--;
        etiquetaAny.setText("Any: "+anyActual);
    }

    public static void main(String[] args) {
        String any = JOptionPane.showInputDialog("Indica l'any a visualitzar");
        while ((any == null) || (any.equals(""))) {
            JOptionPane.showMessageDialog(null, "Cal un valor vàlid!", "ERROR", JOptionPane.ERROR_MESSAGE);
            any = JOptionPane.showInputDialog("Indica l'any a visualitzar");
        }
        new Visualitzacio("Calendari", any);
    }
}