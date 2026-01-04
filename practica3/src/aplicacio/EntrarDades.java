package aplicacio;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Clase que implementa la interacció dels botons de la capçalera superior per a incrementar o decrementar un any utilitzant.
 */
public class EntrarDades extends JDialog {
  private JLabel mes;
  private JComboBox mesC;

  private boolean ok;

  public EntrarDades(JFrame finestraPare) {
    super(finestraPare, "Entrar dades ...");

    // Inicialment l'usuari no ha entrat dades.
    ok = false;

    // Creem els components que es veuran
    mes = new JLabel("Mes:");
    String[] opcions = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Septembre", "Octubre", "Novembre", "Desembre"};
    mesC = new JComboBox(opcions);

    // Creem el contenidor per als controls
    JPanel controls = new JPanel(new GridLayout(4,2));
    controls.add(mes);
    controls.add(mesC);

    // Creem els botons Acceptar/Cancelar
    JButton acceptar = new JButton("Acceptar");
    JButton cancelar = new JButton("Cancelar");
    acceptar.addActionListener( new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               ok = true;
               setVisible(false);
           }
        });
    cancelar.addActionListener( new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               ok = false;
               setVisible(false);
           }
        });

    // Creem un contenidor on posarem els botons Acceptar/Cancelar.
    JPanel botons = new JPanel(new FlowLayout());
    botons.add(acceptar);
    botons.add(cancelar);

    // Agafem el contenidor principal
    Container c = getContentPane();
    c.add(controls, BorderLayout.CENTER);
    c.add(botons, BorderLayout.SOUTH);

    pack();
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setModal(true);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public boolean dadesEntrades() {
    return ok;
  }

  public String getMes() {
    Object sel = mesC.getSelectedItem();
    return sel.toString();
  }
}
