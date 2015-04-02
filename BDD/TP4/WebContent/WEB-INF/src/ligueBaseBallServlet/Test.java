package biblioServlet;

import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import biblio.BiblioException;
import biblio.GestionBibliotheque;

/**
 * Classe pour login système de gestion de bibliothèque
 * <P>
 * Système de gestion de bibliothèque &copy; 2004 Marc Frappier, Université de
 * Sherbrooke
 */

public class Test extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String submit1 = request.getParameter("submit1");
		System.out.println("Valeur de submit 1 = " + submit1);
		String submit2 = request.getParameter("submit2");
		System.out.println("Valeur de submit 2 = " + submit2);
	}

	// Dans les formulaire, on utilise la méthode POST
	// donc, si le servlet est appelé avec la méthode GET
	// on retourne un page d'erreur, afin de ne pas permettre
	// à l'utilisateur d'appeler un servler directement
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendError(response.SC_INTERNAL_SERVER_ERROR, "Accès
		// invalide");
		doPost(request, response);
	}

} // class
