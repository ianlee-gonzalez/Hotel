package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Conex�o com o banco de dados
 * 
 * @author ian.lggonzalez
 * @version 1.0
 */
public class DAO {
	// parametros de conex�o
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/hotel";
	private String user = "root";
	private String password = "SuaSenha";

	/**
	 * Metodo resposavel pela conex�o com o banco *
	 * 
	 * @return con
	 */
	public Connection conectar() {
		// alinha abaixo cria um objeto com nome con
		Connection con = null;
		// tratamento de exce��es
		try {
			// as duas linhas abaixo estabelecem a conex�o
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
