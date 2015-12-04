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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (!checaDadosObrigatorios(request)) { // Usuário não preencheu campos
			// obrigatórios
			request.getRequestDispatcher("/logar.jsp").forward(request,
					response);
			return;
		}

		String email = "email@gmail.com";
		String user = request.getParameter("Usuario");
		String password = request.getParameter("Senha");

		String msg = null;
		Usuario result;

		Usuario usuario = new Usuario(user, email, password);

		result = UsuarioDAO.Logar(usuario);
		
		if (result == null) {
			msg = "Usuário/senha não conferem!";
			request.getSession().setAttribute("usuario", "Usuário");
		} else {
			request.getSession().setAttribute("usuario", user);
			msg = "Usuário " + usuario.getLogin() + " logado!";
		}
		
		request.setAttribute("mensagem", msg);
		request.getRequestDispatcher("/logar.jsp").forward(request,
				response);
	}

	private boolean checaDadosObrigatorios(HttpServletRequest request) {

		String mensagemRetorno = "";

		String acao = request.getParameter("button");

		switch (acao) {
		case "Logar":
			if (request.getParameter("Usuario").equalsIgnoreCase(""))
				mensagemRetorno = "O usuário deve ser informado!\r\n";
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
