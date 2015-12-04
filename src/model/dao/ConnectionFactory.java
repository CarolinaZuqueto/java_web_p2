package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author carolina.zuqueto
 */
public class ConnectionFactory {

	/**
	 * Rotina de conex�o com a base ZuDoces
	 *
	 * @return Connex�o com a base
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			
			String url = "jdbc:sqlite:ZuDoces.db";
			//String url = "jdbc:sqlite:C:/Users/carolina.z.amaral/Desktop/Faculdade/2015.2/Java Web - LPIII/workspace/ZuDoces/dataBase/ZuDoces.db";
			
			return DriverManager.getConnection(url,
					"root", "");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("Falha no registro do driver: "
					+ ex.getMessage());
		} catch (SQLException ex) {
			throw new RuntimeException(
					"Falha na conex�o com o banco de dados: " + ex.getMessage());
		}
	}

	/**
	 * Rotina de fechamento de uma conex�o com a base de dados
	 * 
	 * @param connection
	 *            Conex�o a ser fechada
	 */
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException ex) {
			throw new RuntimeException(
					"Falha no fechamento da conex�o com o banco de dados");
		}
	}

	/**
	 * 
	 * Rotina de fechamento de uma instru��o de chamada ao banco de dados
	 * 
	 * @param statement
	 *            Instru��o a ser fechada
	 */
	public static void closeStatement(Statement statement) {
		try {
			statement.close();
		} catch (SQLException ex) {
			throw new RuntimeException(
					"Falha no fechamento da instru��o de chamada ao banco de dados");
		}
	}

}
