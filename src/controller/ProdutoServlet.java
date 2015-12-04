package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.Produto;
import model.dao.ProdutoDAO;

/**
 * Servlet implementation class ProdutoServlet
 */
@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * request.setAttribute("pesquisarMode", true);
		 * request.getRequestDispatcher("/cadastroProduto.jsp").forward(
		 * request,response);
		 */

		if (!checaDadosObrigatorios(request)) { // Usu�rio n�o preencheu campos
												// obrigat�rios
			request.getRequestDispatcher("/cadastroProduto.jsp").forward(
					request, response);
			return;
		}

		String cod = request.getParameter("CodigoProduto");
		String qnt = request.getParameter("QtdProduto");
		String val = request.getParameter("ValorProduto").replace(",", ".");
		String descricao = request.getParameter("DescricaoProduto");
		String nome = request.getParameter("NomeProduto");
		Integer codigo, quantidade;
		String msg = "";
		Double valor;

		if (cod == "") {
			codigo = 0;
		} else {
			codigo = Integer.parseInt(cod);
		}

		if (qnt == "") {
			quantidade = 0;
		} else {
			quantidade = Integer.parseInt(qnt);
		}

		if (val == "") {
			valor = 0.0;
		} else {
			valor = Double.parseDouble(val);
		}

		Produto prod = new Produto(codigo, descricao, nome, quantidade, valor);

		String acao = request.getParameter("button");

		Produto result = null;
		
		Boolean res = false;

		switch (acao) {

		case "Inserir":
			codigo = 1;
			result = ProdutoDAO.pesquisaProduto(prod);
			if (result == null) {
				ProdutoDAO.insereProduto(prod);
				msg = "Produto inserido com sucesso!";
			} else {
				msg = "Produto j� cadastrado!";
				request.setAttribute("pesquisarMode", false);
			}
			break;

		case "Pesquisar":
			result = ProdutoDAO.pesquisaProduto(prod);
			if (result == null) {
				msg = "Produto n�o encontrado!";
				request.setAttribute("pesquisarMode", false);
			} else {
				request.setAttribute("produto", result);
				request.setAttribute("pesquisarMode", true);
			}
			break;

		case "Excluir":
			result = ProdutoDAO.pesquisaProduto(prod);
			if (result == null) {
				msg = "Produto n�o cadastrado!";
				request.setAttribute("pesquisarMode", false);
			} else {
				res = ProdutoDAO.excluiProduto(prod);
				if (res = true) {
					msg = "Produto excluido com sucesso!";
				}else{
					msg = "Erro ao excluir o produto!";
				}
				request.setAttribute("pesquisarMode", false);
			}
			break;
			
		case "Atualizar":
			result = ProdutoDAO.pesquisaProduto(prod);
			if (result == null) {
				msg = "Produto n�o cadastrado!";
				request.setAttribute("pesquisarMode", false);
			} else {
				res = ProdutoDAO.atualizaProduto(prod);
				if (res = true) {
					msg = "Produto atualizado com sucesso!";
				}else{
					msg = "Erro ao atualizar o produto!";
				}
				request.setAttribute("pesquisarMode", false);
			}
			break;
			
		}

		request.setAttribute("mensagem", msg);
		request.getRequestDispatcher("/cadastroProduto.jsp").forward(request,
				response);

	}

	private boolean checaDadosObrigatorios(HttpServletRequest request) {

		String mensagemRetorno = "";

		String acao = request.getParameter("button");

		switch (acao) {
		case "Inserir":
			if (request.getParameter("DescricaoProduto").equalsIgnoreCase(""))
				mensagemRetorno = "A descri��o do produto deve ser informada!\r\n";
			if (request.getParameter("NomeProduto").equalsIgnoreCase(""))
				mensagemRetorno += "O nome do produto deve ser informado!\r\n";
			if (request.getParameter("ValorProduto").equalsIgnoreCase(""))
				mensagemRetorno += "O valor do produto deve ser informado!\r\n";
			if (!request.getParameter("QtdProduto").matches("^\\d+$"))/*Express�o regular para validar n�meros inteiros*/
				mensagemRetorno += "Quantidade do produto inv�lida!\r\n";
			if (!request.getParameter("ValorProduto").replace(",", ".").matches("^\\d+?(.\\d+)$"))/*Express�o regular para validar n�meros reais*/
				mensagemRetorno += "Valor do produto inv�lido!\r\n";
			break;

		case "Pesquisar":
			if (request.getParameter("CodigoProduto").equalsIgnoreCase("")
					&& request.getParameter("NomeProduto").equalsIgnoreCase(""))
				mensagemRetorno = "Para realizar a busca, favor preencher somente um dos seguintes campos: c�digo ou descri��o do produto.\n";
			if (!request.getParameter("CodigoProduto").equalsIgnoreCase("")
					&& !request.getParameter("NomeProduto")
							.equalsIgnoreCase(""))
				mensagemRetorno = "Para realizar a busca, favor preencher somente um dos seguintes campos: c�digo ou descri��o do produto.\n";
			break;

		case "Excluir":
			if (request.getParameter("CodigoProduto").equalsIgnoreCase(""))
				mensagemRetorno = "O c�digo do produto deve ser informado!\n";
			break;
			
		case "Atualizar":
			if (request.getParameter("CodigoProduto").equalsIgnoreCase(""))
				mensagemRetorno = "O c�digo do produto deve ser informado!\r\n";
			if (request.getParameter("NomeProduto").equalsIgnoreCase(""))
				mensagemRetorno += "O nome do produto deve ser informado!\r\n";
			if (request.getParameter("DescricaoProduto").equalsIgnoreCase(""))
				mensagemRetorno += "A descri��o do produto deve ser informada!\r\n";
			if (request.getParameter("QtdProduto").equalsIgnoreCase(""))
				mensagemRetorno += "A quantidade do produto deve ser informada!\r\n";
			if (request.getParameter("ValorProduto").equalsIgnoreCase(""))
				mensagemRetorno += "O valor do produto deve ser informado!";
			if (!request.getParameter("QtdProduto").matches("^\\d+$"))/*Express�o regular para validar n�meros inteiros*/
				mensagemRetorno += "Quantidade do produto inv�lida!\r\n";
			if (!request.getParameter("ValorProduto").replace(",", ".").matches("^\\d+?(.\\d+)$"))/*Express�o regular para validar n�meros reais*/
				mensagemRetorno += "Valor do produto inv�lido!\r\n";
			break;			
		}

		if (!mensagemRetorno.equals("")) {
			request.setAttribute("mensagem", mensagemRetorno);
			return false;
		} else {
			return true;
		}

	}

}
