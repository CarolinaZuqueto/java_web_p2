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
	 * Rotina de conexão com a base ZuDoces
	 *
	 * @return Connexão com a base
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
					"Falha na conexão com o banco de dados: " + ex.getMessage());
		}
	}

	/**
	 * Rotina de fechamento de uma conexão com a base de dados
	 * 
	 * @param connection
	 *            Conexão a ser fechada
	 */
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException ex) {
			throw new RuntimeException(
					"Falha no fechamento da conexão com o banco de dados");
		}
	}

	/**
	 * 
	 * Rotina de fechamento de uma instrução de chamada ao banco de dados
	 * 
	 * @param statement
	 *            Instrução a ser fechada
	 */
	public static void closeStatement(Statement statement) {
		try {
			statement.close();
		} catch (SQLException ex) {
			throw new RuntimeException(
					"Falha no fechamento da instrução de chamada ao banco de dados");
		}
	}

}
