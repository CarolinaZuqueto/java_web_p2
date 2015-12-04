package model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.LinkedList;

import model.dto.Produto;

/**
 * 
 * Classe utilizada para acessar a base de dados e manipular informações de um
 * Produto. Tabela: PRODUTO
 * 
 * @author Carolina Zuqueto
 */
public class ProdutoDAO {

	public static void insereProduto(Produto produto) {

		Connection con = ConnectionFactory.getConnection();

		String sql = "INSERT INTO PRODUTO (DESCRICAO,NOME,QUANTIDADE,VALOR) VALUES (?, ?, ?, ? )";

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			try {
				comando.setString(1, produto.getDescricao());
				comando.setString(2, produto.getNome());
				comando.setInt(3, produto.getQuantidade());
				comando.setDouble(4, produto.getValor());
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

	public static Produto pesquisaProduto(Produto produto) {

		String sql = "";

		if (produto.getCodigo() != 0) {
			sql = "SELECT * FROM PRODUTO WHERE CODIGO = ?";
		} else {
			sql = "SELECT * FROM PRODUTO WHERE UPPER(NOME) = ?";
		}

		Connection con = ConnectionFactory.getConnection();

		Produto prod = null;

		// System.out.println(sql);

		try {
			PreparedStatement comando = con.prepareStatement(sql);

			if (produto.getCodigo() != 0) {
				comando.setInt(1, produto.getCodigo());
			} else {
				comando.setString(1, produto.getNome().toUpperCase());
			}

			try {
				ResultSet resultado = comando.executeQuery();
				if (resultado.next()) {
					prod = new Produto();
					prod.setCodigo(resultado.getInt(1));
					prod.setDescricao(resultado.getString(2));
					prod.setNome(resultado.getString(3));
					prod.setQuantidade(resultado.getInt(4));
					prod.setValor(resultado.getDouble(5));
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

		return (prod);
	}

	public static boolean excluiProduto(Produto produto) {

		String sql = "";
		Boolean result = false;

		sql = "DELETE FROM PRODUTO WHERE CODIGO = ?";

		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setInt(1, produto.getCodigo());

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

	
	public static boolean atualizaProduto(Produto produto) {

		String sql = "";
		Boolean result = false;

		sql = "UPDATE PRODUTO SET DESCRICAO = ?, NOME = ?, QUANTIDADE = ?, VALOR = ? WHERE CODIGO = ?";

		Connection con = ConnectionFactory.getConnection();

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			comando.setString(1, produto.getDescricao());
			comando.setString(2, produto.getNome());
			comando.setInt(3, produto.getQuantidade());
			comando.setDouble(4, produto.getValor());
			comando.setInt(5, produto.getCodigo());
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
	
	public static LinkedList<Produto> listaProduto(){

		String sql = "";
		
		sql = "SELECT * FROM PRODUTO";

		Connection con = ConnectionFactory.getConnection();

		Produto prod = null;
		
		LinkedList<Produto> lista = new LinkedList<Produto>();

		// System.out.println(sql);

		try {
			PreparedStatement comando = con.prepareStatement(sql);
			
			try {
				ResultSet resultado = comando.executeQuery();
				while(resultado.next()) {
					prod = new Produto();
					prod.setCodigo(resultado.getInt(1));
					prod.setDescricao(resultado.getString(2));
					prod.setNome(resultado.getString(3));
					prod.setQuantidade(resultado.getInt(4));
					prod.setValor(resultado.getDouble(5));
					lista.add(prod);
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

		return (lista);
	}


}
