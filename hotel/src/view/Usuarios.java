package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;

public class Usuarios extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtId;
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JCheckBox chkSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
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
	public Usuarios() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				chkSenha.setVisible(false);
			}
		});
		setBounds(100, 100, 739, 417);
		getContentPane().setLayout(null);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarUsuario();
			}
		});
		txtPesquisar.setBounds(0, 8, 149, 31);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Usuarios.class.getResource("/imagens/pesquisar.png")));
		lblNewLabel.setBounds(162, 8, 70, 31);
		getContentPane().add(lblNewLabel);
		
		JLabel tx = new JLabel("Id");
		tx.setBounds(20, 206, 27, 15);
		getContentPane().add(tx);
		
		JLabel txt = new JLabel("Usuario");
		txt.setBounds(0, 238, 70, 15);
		getContentPane().add(txt);
		
		txtId = new JTextField();
		txtId.setBounds(87, 204, 62, 19);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(65, 236, 124, 19);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setBounds(0, 261, 70, 15);
		getContentPane().add(lblNewLabel_1);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(65, 267, 124, 19);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(0, 299, 70, 15);
		getContentPane().add(lblNewLabel_2);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(65, 298, 124, 17);
		getContentPane().add(txtSenha);
		
		JLabel lblNewLabel_3 = new JLabel("Perfil");
		lblNewLabel_3.setBounds(0, 361, 70, 12);
		getContentPane().add(lblNewLabel_3);
		
		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Operador"}));
		cboPerfil.setBounds(147, 355, 134, 24);
		getContentPane().add(cboPerfil);
		
		btnAdicionar = new JButton("Adicionar Usuario");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setBounds(273, 201, 203, 25);
		getContentPane().add(btnAdicionar);
		
		btnEditar = new JButton("Editar Usuario");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkSenha.isSelected()) {
					editarUsuario();
				}else {
					editarUsuarioPersonalizado();
				}
				
			}
		});
		btnEditar.setBounds(273, 256, 203, 25);
		getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("Excluir Usuario");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setBounds(273, 305, 215, 25);
		getContentPane().add(btnExcluir);
		
		chkSenha = new JCheckBox("Confirmar Senha");
		chkSenha.setBounds(31, 323, 129, 23);
		getContentPane().add(chkSenha);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 51, 520, 140);
		getContentPane().add(desktopPane);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 520, 140);
		desktopPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
				setarSenha();
			}
		});
		scrollPane.setViewportView(table);

	}//FIm do construtor
	DAO dao =new DAO();
	@SuppressWarnings("rawtypes")
	private JComboBox cboPerfil;
	private JDesktopPane desktopPane;
	private JScrollPane scrollPane;
	private JTable table;
	private void pesquisarUsuario() {
		String read="select * from usuarios where usuario like ?";
		try {
			Connection con =dao.conectar();
			PreparedStatement pst =con.prepareStatement(read);
			pst.setString(1,txtPesquisar.getText()+ "%");
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void adicionarUsuario() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Nome", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		//} else if (txtFone.getText().isEmpty()) {
			//JOptionPane.showMessageDialog(null, "preencha o campo Telefone", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			//txtFone.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo CPF", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Endere�o", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		//} else if ( {
			//JOptionPane.showMessageDialog(null, "preencha o campo Numero", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			//txtPerfil.requestFocus();
		} else {
			String create = "insert into usuarios(usuario,login,senha,perfil) values (?,?,md5(?),?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuario adicionado  com sucesso ", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);	
					con.close();
					limpar();

				}
				

			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login ja cadastrado", "Aten��o ", JOptionPane.ERROR_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();

			}
			catch (Exception e) {
				System.out.println(e);
			}

		}

	}
	private void editarUsuario() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Nome", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		//} else if (txtFone.getText().isEmpty()) {
			//JOptionPane.showMessageDialog(null, "preencha o campo Telefone", "Aten��o", JOptionPane.ERROR_MESSAGE);
			//txtFone.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo CPF", "Aten��o", JOptionPane.ERROR_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Senha", "Aten��o", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Perfil", "", JOptionPane.ERROR_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			String update = "update usuarios set usuario=?,login=?,senha=md5(?),perfil=? where idlog=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtId.getText());
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente editado  com sucesso ", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);	
				}
				con.close();
				limpar();
				

			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login ja cadastrado", "Atençao ", JOptionPane.ERROR_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();

			}catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao editar os dados do usuário", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			
			}

		}

	}//fim do metodo editar
	/**
	 * metodo responsavel por editar os dados do usuario  e a senha
	 */
	private void editarUsuarioPersonalizado() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Nome", "Aten��o P�", JOptionPane.ERROR_MESSAGE);
			txtUsuario.requestFocus();
		//} else if (txtFone.getText().isEmpty()) {
			//JOptionPane.showMessageDialog(null, "preencha o campo Telefone", "Aten��o", JOptionPane.ERROR_MESSAGE);
			//txtFone.requestFocus();

		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo CPF", "Aten��o", JOptionPane.ERROR_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Senha", "Aten��o", JOptionPane.ERROR_MESSAGE);
			txtSenha.requestFocus();
		} else if (cboPerfil.getSelectedItem().toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, "preencha o campo Perfil", "", JOptionPane.ERROR_MESSAGE);
			cboPerfil.requestFocus();
		} else {
			String update = "update usuarios set usuario=?,login=?,perfil=? where idlog=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, cboPerfil.getSelectedItem().toString());
				pst.setString(4, txtId.getText());
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente editado  com sucesso ", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);	
				}
				con.close();
				limpar();
				

			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login ja cadastrado", "Atençao ", JOptionPane.ERROR_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();

			}catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao editar os dados do usuário", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			
			}

		}

	}
	private void setarCampos() {
		// a linha abaixo obtem o conteúdo da linha da tabela
		// int (índice) [0], [1], [2],...
		int setar = table.getSelectedRow();
		// setar os campos
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
		txtUsuario.setText(table.getModel().getValueAt(setar, 1).toString());
		txtLogin.setText(table.getModel().getValueAt(setar, 2).toString());
		cboPerfil.setSelectedItem(table.getModel().getValueAt(setar, 4).toString());
		// gerenciar os botões
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
		chkSenha.setVisible(true);
	}
	public void setarSenha() {
		String read2 = "select senha from usuarios where idlog = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtId.getText());
			// a linha abaixo executa a instru�ao e armazena o resultado no objeto rs
			ResultSet rs = pst.executeQuery();
			// a linha abaixo verifica se existe uma senha para o id
			if (rs.next()) {
				txtSenha.setText(rs.getString(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void excluirUsuario() {
		// confimação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir este cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where idlog = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Erro ao excluir\nCliente possui pedido em aberto", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
				JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente", "Atenção!",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	private void limpar() {
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);	
		cboPerfil.setSelectedItem(null);
		// limpar a tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}	
}
