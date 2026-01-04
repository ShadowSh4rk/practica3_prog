package aplicacio;

import java.awt.*;
import javax.swing.*;

public class EntrarFiltres extends JDialog {
    private JLabel activitatUnDia, activitatPeriodica, activitatOnline;
    private JComboBox activitatUnDiaC, activitatPeriodicaC, activitatOnlineC;
    private boolean ok;

    private JPanel controls;
	private JPanel botons;
	private JButton acceptar;
	private JButton cancelar;

    public EntrarFiltres(JFrame finestraPare) {
        super(finestraPare, "Selecciona els filtres:");

        // Inicialment l'usuari no ha entrat dades
		ok = false;

        // Creem els components que es veuran
		activitatUnDia = new JLabel("Activitats d'un dia:");
		activitatPeriodica = new JLabel("Activitats periòdiques:");
        activitatOnline = new JLabel("Activitats online");
		String[] opcions = {"Visualitzar", "Ocultar"};
		activitatUnDiaC = new JComboBox(opcions);
        activitatPeriodicaC = new JComboBox(opcions);
        activitatOnlineC = new JComboBox(opcions);

        // Creem el contenidor per als controls
		controls = new JPanel(new GridLayout(3,3));
		controls.add(activitatUnDia);
		controls.add(activitatUnDiaC);
		controls.add(activitatPeriodica);
		controls.add(activitatUnDiaC);
        controls.add(activitatOnline);
        controls.add(activitatOnlineC);

		// Creem i afegim la interactivitat dels botons Acceptar/Cancelar
		acceptar = new JButton("Acceptar");
		cancelar = new JButton("Cancelar");
		acceptar.addActionListener(new AccioBotoAcceptarCancelarFiltre(this));
		cancelar.addActionListener(new AccioBotoAcceptarCancelarFiltre(this));

        // Creem un contenidor on posarem els botons Acceptar/Cancelar.
		botons = new JPanel(new FlowLayout());
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

    /**
	 * Mètode que retorna si hem clicat a acceptar les dades que hem introduït.
	 * @return boolean que indica si hem clicat a acceptar.
	 */
	public boolean dadesEntrades() {
		return ok;
	}

    /**
     * 
     * @return
     */
    public String getFiltreUnDia() {
        Object sel = activitatUnDiaC.getSelectedItem();
        return sel.toString();
    }

    /**
     * 
     * @return
     */
    public String getFiltrePeriodica() {
        Object sel = activitatPeriodicaC.getSelectedItem();
        return sel.toString();
    }

    /**
     * 
     * @return
     */
    public String getFiltreOnline() {
        Object sel = activitatOnlineC.getSelectedItem();
        return sel.toString();
    }
}
