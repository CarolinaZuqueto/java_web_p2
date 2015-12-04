package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UsuarioDAO;
import model.dto.Usuario;

/**
 * Servlet implementation class UsuarioServlet
 */
@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (!checaDadosObrigatorios(request)) { // Usuário não preencheu campos
			// obrigatórios
			request.getRequestDispatcher("/cadastroUsuario.jsp").forward(
					request, response);
			return;
		}

		String login = request.getParameter("Login");
		String senha = request.getParameter("Senha");
		String email = request.getParameter("Email");
		String msg = "";

		Usuario user = new Usuario(login, email, senha);

		String acao = request.getParameter("button");

		Usuario result = null;
		Boolean res = false;

		switch (acao) {

		case "Cadastrar":
			result = UsuarioDAO.pesquisaUsuario(user);

			if (result == null) {
				UsuarioDAO.cadastraUsuario(user);
				msg = "Usuário inserido com sucesso!";
			} else {
				msg = "Usuário já existente!";
			}
			break;

		case "Alterar":
			result = UsuarioDAO.pesquisaUsuario(user);
			if (result == null) {
				msg = "Usuário não encontrado!";
			} else {
				res = UsuarioDAO.atualizaUsuario(user);
				if (res = true) {
					msg = "E-mail e senha atualizados com sucesso!";
				} else {
					msg = "Erro ao atualizar o dados do usuário!";
				}
			}

			break;
		}
		request.setAttribute("mensagem", msg);
		request.getRequestDispatcher("/cadastroUsuario.jsp").forward(request,
				response);

	}

	private boolean checaDadosObrigatorios(HttpServletRequest request) {

		String mensagemRetorno = "";

		String acao = request.getParameter("button");

		switch (acao) {
		case "Cadastrar":
			if (request.getParameter("Login").equalsIgnoreCase(""))
				mensagemRetorno = "O login deve ser informado!\r\n";
			if (request.getParameter("Email").equalsIgnoreCase(""))
				mensagemRetorno += "O e-mail deve ser informado!\r\n";
			if (!request.getParameter("Email").matches(".+@.+\\.[a-z]+")) /*Expressão regular para validar o e-mail*/
				mensagemRetorno += "E-mail inválido!\r\n";
			if (request.getParameter("Senha").equalsIgnoreCase(""))
				mensagemRetorno += "A senha deve ser informada!\r\n";
			if (request.getParameter("Senha").length() > 8)
				mensagemRetorno += "A senha deve ser formada por 8 caracteres!\r\n";
			break;

		case "Alterar":
			if (request.getParameter("Login").equalsIgnoreCase(""))
				mensagemRetorno = "O login deve ser informado!\r\n";
			if (request.getParameter("Email").equalsIgnoreCase(""))
				mensagemRetorno += "O e-mail deve ser informado!\r\n";
			if (!request.getParameter("Email").matches(".+@.+\\.[a-z]+")) /*Expressão regular para validar o e-mail*/
				mensagemRetorno += "E-mail inválido!\r\n";
			if (request.getParameter("Senha").equalsIgnoreCase(""))
				mensagemRetorno += "A senha deve ser informada!\r\n";
			if (request.getParameter("Senha").length() > 8)
				mensagemRetorno += "A senha deve ser formada por 8 caracteres!\r\n";
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
