package aplicacio;


import java.awt.*;
import javax.swing.*;

/**
 * Clase que implementa la interacció del botó superior esquerra que permet fixar una data determinada.
 */
public class EntrarDadesData extends JDialog {
  	private JLabel mes, any;	// Etiquetes del mes i any
	private JTextField anyF;
	private JComboBox mesC;		// Desplegable amb els noms de tots els mesos
	private boolean ok;			// Variable per a confirmar que l'usuari ha introduït les dades

	private JPanel controls;
	private JPanel botons;
	private JButton acceptar;
	private JButton cancelar;


	public EntrarDadesData(JFrame finestraPare) {
		super(finestraPare, "Entrar data ...");

		// Inicialment l'usuari no ha entrat dades
		ok = false;

		// Creem els components que es veuran
		mes = new JLabel("Mes:");
		any = new JLabel("Any:");
		anyF = new JTextField(10);
		String[] opcions = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Septembre", "Octubre", "Novembre", "Desembre"};
		mesC = new JComboBox(opcions);

		// Creem el contenidor per als controls
		controls = new JPanel(new GridLayout(2,2));
		controls.add(mes);
		controls.add(mesC);
		controls.add(any);
		controls.add(anyF);

		// Creem i afegim la interactivitat dels botons Acceptar/Cancelar
		acceptar = new JButton("Acceptar");
		cancelar = new JButton("Cancelar");
		acceptar.addActionListener(new AccioBotoAcceptarCancelarData(this, true));
		cancelar.addActionListener(new AccioBotoAcceptarCancelarData(this, false));

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
	 * Mètode que retorna el nom del mes que hem seleccionat al despeglable.
	 * @return nom del mes que hem seleccioant al desplegable
	 */
	public String getMes() {
		Object sel = mesC.getSelectedItem();
		return sel.toString();
	}

	/**
	 * Mètode que retorna l'any que hem indicat a la finestra.
	 * @return any que hem indicat a la finestra
	 */
	public int getAny() {
		return (Integer.parseInt(anyF.getText()));
	}

	/**
	 * Mètode per a posar la variable booleana OK com a cert si hem clicat a un botó acceptar.
	 * @param ok
	 */
	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
