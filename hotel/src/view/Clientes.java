package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtIdade;
	private JTextField txtDoc;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtCidade;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTable table;
	private JTextField txtEmail;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JComboBox cboUf;
	private JTextField txtTelefone;
	private JTextField txtBairro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
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
	public Clientes() {
		setBounds(100, 100, 728, 453);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(12, 19, 141, 19);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/imagens/pesquisar.png")));
		lblNewLabel.setBounds(173, 12, 40, 26);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Id");
		lblNewLabel_1.setBounds(12, 171, 70, 15);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Nome");
		lblNewLabel_1_1.setBounds(12, 205, 70, 15);
		getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Idade");
		lblNewLabel_1_2.setBounds(12, 232, 70, 15);
		getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Doc");
		lblNewLabel_1_3.setBounds(12, 265, 70, 15);
		getContentPane().add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Cep");
		lblNewLabel_1_4.setBounds(12, 292, 70, 15);
		getContentPane().add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("Endereço");
		lblNewLabel_1_5.setBounds(12, 317, 70, 15);
		getContentPane().add(lblNewLabel_1_5);

		JLabel lblNewLabel_2 = new JLabel("Cidade");
		lblNewLabel_2.setBounds(12, 337, 70, 15);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1_6 = new JLabel("Numero");
		lblNewLabel_1_6.setBounds(12, 364, 70, 15);
		getContentPane().add(lblNewLabel_1_6);

		JLabel lblNewLabel_1_7 = new JLabel("Complemento");
		lblNewLabel_1_7.setBounds(0, 391, 97, 15);
		getContentPane().add(lblNewLabel_1_7);

		txtId = new JTextField();
		txtId.setBounds(55, 169, 114, 19);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(65, 203, 114, 19);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		txtIdade = new JTextField();
		txtIdade.setColumns(10);
		txtIdade.setBounds(75, 230, 114, 19);
		getContentPane().add(txtIdade);

		txtDoc = new JTextField();
		txtDoc.setColumns(10);
		txtDoc.setBounds(83, 263, 114, 19);
		getContentPane().add(txtDoc);

		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(83, 290, 114, 19);
		getContentPane().add(txtCep);

		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(83, 315, 114, 19);
		getContentPane().add(txtEndereco);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(83, 335, 114, 19);
		getContentPane().add(txtCidade);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(83, 362, 114, 19);
		getContentPane().add(txtNumero);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(99, 389, 114, 19);
		getContentPane().add(txtComplemento);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(new String[] { "SP", "RJ" }));
		cboUf.setBounds(220, 386, 77, 24);
		getContentPane().add(cboUf);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setToolTipText("Personagem");
		btnNewButton.setIcon(new ImageIcon(Clientes.class.getResource("/imagens/personalidadeicon.png")));
		btnNewButton.setBounds(583, 49, 117, 107);
		getContentPane().add(btnNewButton);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(22, 49, 539, 110);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 539, 110);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();

			}
		});
		scrollPane.setViewportView(table);

		btnAdicionar = new JButton("Adicionar Cliente");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarCliente();
			}
		});
		btnAdicionar.setBounds(471, 260, 229, 25);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("Editar Cliente");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditar.setBounds(467, 312, 235, 25);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("Excluir Cliente");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setBounds(497, 359, 205, 25);
		getContentPane().add(btnExcluir);

		JButton btnBuscar = new JButton("Buscar Cep");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnBuscar.setBounds(209, 287, 117, 25);
		getContentPane().add(btnBuscar);

		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(282, 171, 70, 15);
		getContentPane().add(lblNewLabel_3);

		txtEmail = new JTextField();
		txtEmail.setBounds(250, 198, 134, 19);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Telefone");
		lblNewLabel_4.setBounds(439, 171, 70, 15);
		getContentPane().add(lblNewLabel_4);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(419, 201, 114, 19);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Bairro");
		lblNewLabel_5.setBounds(227, 317, 70, 15);
		getContentPane().add(lblNewLabel_5);

		txtBairro = new JTextField();
		txtBairro.setBounds(209, 335, 114, 19);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

	}// fim do construtor

	DAO dao = new DAO();

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						// lblStatus.setIcon(new
						// javax.swing.ImageIcon(getClass().getResource("/img/check.png")));
					} else {
						JOptionPane.showMessageDialog(null, "CEP n�o encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void pesquisarCliente() {
		String read = "select * from clientes where nome  like ?";
		try {
			// abrir a conexao com o banco
			Connection con = dao.conectar();
			// preparar a query(instrucao sql) para pesquisar no banco
			PreparedStatement pst = con.prepareStatement(read);
			// substituir o parametro(?) Atencao ao % para completar a query
			pst.setString(1, txtPesquisar.getText() + "%");
			// obter os dados do banco (resultado)
			ResultSet rs = pst.executeQuery();
			// popular(preencher) a tabela com os dados do banco
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void adicionarCliente() {

		{
			if (txtNome.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Nome!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
				txtNome.requestFocus();
			} else if (txtEndereco.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Endereço Completo!", "Atenção!!",
						JOptionPane.ERROR_MESSAGE);
				txtEndereco.requestFocus();
			} else if (txtBairro.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Bairro Completo!", "Atenção!!",
						JOptionPane.ERROR_MESSAGE);
				txtBairro.requestFocus();
			} else if (txtCidade.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o campo cidade Completo!", "Atenção!!",
						JOptionPane.ERROR_MESSAGE);
				txtCidade.requestFocus();
			} else if (txtNumero.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o numero Completo!", "Atenção!!",
						JOptionPane.ERROR_MESSAGE);
				txtNumero.requestFocus();
			} else if (txtTelefone.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o Telefone!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
				txtTelefone.requestFocus();

			} else {
				String create = "insert into clientes(nome,idade,doc,cep,email,endereco,numero,cidade,uf,bairro,complemento) values (?,?,?,?,?,?,?,?,?,?,?)";
				try {
					Connection con = dao.conectar();
					PreparedStatement pst = con.prepareStatement(create);
					pst.setString(1, txtNome.getText());
					pst.setString(2, txtIdade.getText());
					pst.setString(3, txtDoc.getText());
					pst.setString(4, txtCep.getText());
					pst.setString(5, txtEmail.getText());
					pst.setString(6, txtEndereco.getText());
					pst.setString(7, cboUf.getSelectedItem().toString());
					pst.setString(8, txtNumero.getText());
					pst.setString(9, txtCidade.getText());
					pst.setString(10, txtBairro.getText());
					pst.setString(11, txtComplemento.getText());
					int confirma = pst.executeUpdate();
					if (confirma == 1) {
						JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso", "Mensagem",
								JOptionPane.INFORMATION_MESSAGE);
					}
					con.close();
					limpar();
				} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
					JOptionPane.showMessageDialog(null,
							"E-mail já cadastrado!\n Favor escolher outro e-mail para cadastrar!", "Mensagem",
							JOptionPane.WARNING_MESSAGE);
					txtEmail.setText(null);
					txtEmail.requestFocus();

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}

	private void setarCampos() {
		int setar = table.getSelectedRow();
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(table.getModel().getValueAt(setar, 1).toString());
		txtIdade.setText(table.getModel().getValueAt(setar, 2).toString());
		txtCep.setText(table.getModel().getValueAt(setar, 3).toString());
		txtDoc.setText(table.getModel().getValueAt(setar, 4).toString());
		txtCep.setText(table.getModel().getValueAt(setar, 5).toString());
		txtEmail.setText(table.getModel().getValueAt(setar, 6).toString());
		txtEndereco.setText(table.getModel().getValueAt(setar, 7).toString());
		txtNumero.setText(table.getModel().getValueAt(setar, 8).toString());
		cboUf.setSelectedItem(table.getModel().getValueAt(setar, 10).toString());
		txtCidade.setText(table.getModel().getValueAt(setar, 9).toString());
		txtBairro.setText(table.getModel().getValueAt(setar, 11).toString());
		txtComplemento.setText(table.getModel().getValueAt(setar, 12).toString());

		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);

	}

	private void editarCliente() {

		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtNome.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Cep Completo!", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Endereço Completo!", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtComplemento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo complemento Completo!", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
			txtComplemento.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro Completo!", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo bairro Completo!", "Atenção!!",
					JOptionPane.ERROR_MESSAGE);
			txtCidade.requestFocus();
		} else if (txtTelefone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Telefone!", "Atenção!!", JOptionPane.ERROR_MESSAGE);
			txtTelefone.requestFocus();

		} else {
			String update = "update clientes set nome=?,idade=?,doc=?,cep=?,email=?,endereco=?,numero=?,cidade=?,uf=?,bairro=?,complemento=? where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtIdade.getText());
				pst.setString(3, txtDoc.getText());
				pst.setString(4, txtCep.getText());
				pst.setString(5, txtEmail.getText());
				pst.setString(6, txtEndereco.getText());
				pst.setString(7, cboUf.getSelectedItem().toString());
				pst.setString(8, txtNumero.getText());
				pst.setString(9, txtCidade.getText());
				pst.setString(10, txtBairro.getText());
				pst.setString(11, txtComplemento.getText());
				pst.setString(11, txtId.getText());

				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do Cliente Atualizado com Sucesso!!", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null,
						"E-mail já cadastrado!\n Favor escolher outro e-mail para cadastrar!", "Mensagem",
						JOptionPane.WARNING_MESSAGE);
				txtEmail.setText(null);
				txtEmail.requestFocus();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void excluirCliente() {
		// Confirmação de Exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// codigo principal
			String delete = "delete from clientes where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}

				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Exclusão Negada. \nCliente Serviço pedido em aberto.");

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void limpar()

	{
		txtPesquisar.setText(null);
		txtId.setText(null);
		txtNome.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		cboUf.setSelectedItem(null);
		txtTelefone.setText(null);
		txtEmail.setText(null);
		txtCidade.setText(null);
		txtIdade.setText(null);

		// Limpar a tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		// geren btn
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}

}
