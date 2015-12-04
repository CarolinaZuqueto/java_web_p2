package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dto.Usuario;

/**
 * 
 * Classe utilizada para acessar a base de dados e manipular informações de um
 * Usuário. Tabela: USUARIO
 * 
 * @author Carolina Zuqueto
 */
public class UsuarioDAO {

	public static void cadastraUsuario(Usuario usuario) {

		Connection con = ConnectionFactory.getConnection();

		String sql = "INSERT INTO USUARIO (LOGIN,EMAIL,SENHA) VALUES (?, ?, ?)";

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			try {
				comando.setString(1, usuario.getLogin());
				comando.setString(2, usuario.getEmail());
				comando.setString(3, usuario.getSenha());
				comando.execute();
			} catch (SQLException ex) {
				throw ex;
			} finally {
				ConnectionFactory.closeStatement(comando);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Falha na chamada ao banco de dados: "
					+ ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con);
		}
	}

	public static Usuario pesquisaUsuario(Usuario usuario) {

		String sql = "";

		sql = "SELECT * FROM USUARIO WHERE LOGIN = ?";

		Connection con = ConnectionFactory.getConnection();

		Usuario user = null;

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, usuario.getLogin());

			try {
				ResultSet resultado = comando.executeQuery();
				if (resultado.next()) {
					user = new Usuario();
					user.setLogin(resultado.getString(1));
					user.setEmail(resultado.getString(2));
					user.setSenha(resultado.getString(3));
				}

			} catch (SQLException ex) {
				throw ex;
			} finally {
				ConnectionFactory.closeStatement(comando);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Falha na chamada ao banco de dados: "
					+ ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con);
		}

		return (user);
	}

	public static boolean atualizaUsuario(Usuario usuario) {

		String sql = "";
		Boolean result = false;

		sql = "UPDATE USUARIO SET EMAIL = ?, SENHA = ? WHERE LOGIN = ?";

		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, usuario.getEmail());
			comando.setString(2, usuario.getSenha());
			comando.setString(3, usuario.getLogin());
			try {
				comando.executeUpdate();
				result = true;
			} catch (SQLException ex) {
				throw ex;
			} finally {
				ConnectionFactory.closeStatement(comando);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Falha na chamada ao banco de dados: "
					+ ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con);
		}

		return (result);
	}
	
	public static Usuario Logar(Usuario usuario) {

		String sql = "";

		sql = "SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = ?";

		Connection con = ConnectionFactory.getConnection();

		Usuario user = null;

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, usuario.getLogin());
			comando.setString(2, usuario.getSenha());
			
			try {
				ResultSet resultado = comando.executeQuery();
				if (resultado.next()) {
					user = new Usuario();
					user.setLogin(resultado.getString(1));
					user.setEmail(resultado.getString(2));
					user.setSenha(resultado.getString(3));
				}

			} catch (SQLException ex) {
				throw ex;
			} finally {
				ConnectionFactory.closeStatement(comando);
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Falha na chamada ao banco de dados: "
					+ ex.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con);
		}

		return (user);
	}

}
