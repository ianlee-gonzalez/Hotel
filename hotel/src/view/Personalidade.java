package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;

public class Personalidade extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personalidade dialog = new Personalidade();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Personalidade() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(135, 22, 290, 27);
		getContentPane().add(separator);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setName("Amor");
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setBounds(12, 130, 391, 61);
		getContentPane().add(scrollBar);

	}
}
