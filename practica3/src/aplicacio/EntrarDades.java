package aplicacio;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Clase que implementa la interacció dels botons de la capçalera superior per a incrementar o decrementar un any utilitzant.
 */
public class EntrarDades extends JDialog {
  private static final long serialVersionUID = 1L;
  private JLabel nom, cognom, edat, sexe;
  private JTextField nomF, cognomF, edatF;
  private JComboBox sexeC;

  private boolean ok;

  public EntrarDades(JFrame finestraPare) {
    super(finestraPare, "Entrar dades ...");

    // Inicialment l'usuari no ha entrat dades.
    ok = false;

    // Creem els components que es veuran
    nom = new JLabel("Nom:");
    cognom = new JLabel("Cognom:");
    edat = new JLabel("Edat:");
    sexe = new JLabel("Sexe:");
    nomF = new JTextField(10);
    cognomF = new JTextField(10);
    edatF = new JTextField(10);
    String[] opcions = {"Home","Dona"};
    sexeC = new JComboBox(opcions);

    // Creem el contenidor per als controls
    JPanel controls = new JPanel(new GridLayout(4,2));
    controls.add(nom);
    controls.add(nomF);
    controls.add(cognom);
    controls.add(cognomF);
    controls.add(edat);
    controls.add(edatF);
    controls.add(sexe);
    controls.add(sexeC);

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
    setVisible(true);
  }

  public boolean dadesEntrades() {
    return ok;
  }

  public String getNom() {
    return nomF.getText();
  }

  public String getCognom() {
    return cognomF.getText();
  }

  public String getSexe() {
    Object sel = sexeC.getSelectedItem();
    return sel.toString();
  }

  public int getEdat() {
    return Integer.parseInt(edatF.getText());
  }
}
