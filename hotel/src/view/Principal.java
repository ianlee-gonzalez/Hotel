package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Principal extends JFrame {

	private JPanel contentPane;
	public JButton btnUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/imagens/pc.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Moderadores");
		btnNewButton.setBounds(42, 107, 142, 142);
		contentPane.add(btnNewButton);
		
		btnUsuarios = new JButton("Usuarios");
		btnUsuarios.setEnabled(false);
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		btnUsuarios.setBounds(42, 260, 142, 142);
		contentPane.add(btnUsuarios);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setBounds(532, 107, 142, 142);
		contentPane.add(btnClientes);
		
		JButton btnRelatorios = new JButton("Relatorios");
		btnRelatorios.setBounds(532, 260, 142, 142);
		contentPane.add(btnRelatorios);
		
		JButton btnFuncionarios = new JButton("Funcionarios");
		btnFuncionarios.setBounds(298, 260, 142, 142);
		contentPane.add(btnFuncionarios);
		
		JLabel label = new JLabel("");
		label.setBounds(298, 151, 70, 15);
		contentPane.add(label);
		
		JButton btnSobre = new JButton("Sobre");
		btnSobre.setBounds(298, 95, 142, 142);
		contentPane.add(btnSobre);
	}
}
