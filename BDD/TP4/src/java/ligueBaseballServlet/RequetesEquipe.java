/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ligueBaseballServlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ligueBaseball.GestionLigue;
/**
 *
 * @author Amandine
 */
public class RequetesEquipe extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer etat = (Integer) request.getSession().getAttribute("etat");
        if (etat == null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
        } else if (request.getParameter("creerEquipe") != null) {
            if(!request.getParameter("nomEquipeAdd").equals("")) {
               if (!request.getParameter("nomTerrainAdd").equals("")) {
                   if(!request.getParameter("adresseTerrainAdd").equals("")) {
                        creationEquipeTerrain(request, response);
                   }else{
                        //ERREUR MANQUE PARAMETRES
                   } 
               }else{
                   creationEquipe(request, response);
               }
            }else{
                //ERREUR MANQUE PARAMETRES
            }
        } else if (request.getParameter("afficherEquipes") != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/equipes.jsp");
            dispatcher.forward(request, response);
        }  else if (request.getParameter("supprimerEquipe") != null) {
            if(!request.getParameter("nomEquipeSup").equals("")) {
                supprimerEquipe(request, response);
            } else{
                //ERREUR MANQUE PARAMETRES
            }
        }else{
            //ERREUR CHOIX INCONNU
        }
            
    }
    
    private void creationEquipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nomEquipe = (String) request.getParameter("nomEquipeAdd");
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
            synchronized (ligue) {
                ligue.gestionEquipe.creerEquipe(nomEquipe);
            }
            request.getSession().setAttribute("succesEquipe", "creationEquipe");
            request.getSession().setAttribute("nomEquipeAdd", nomEquipe);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesEquipe.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    private void creationEquipeTerrain(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nomEquipe = (String) request.getParameter("nomEquipeAdd");
        String nomTerrain = (String) request.getParameter("nomTerrainAdd");
        String adresseTerrain = (String) request.getParameter("adresseTerrainAdd");
	GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
        try{
            synchronized (ligue) {
                ligue.gestionEquipe.creerEquipe(nomEquipe, nomTerrain,adresseTerrain);
            }
            request.getSession().setAttribute("succesEquipe", "creationEquipeTerrain");
            request.getSession().setAttribute("nomEquipeAdd", nomEquipe);
            request.getSession().setAttribute("nomTerrainAdd", nomTerrain);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesEquipe.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    private void supprimerEquipe(HttpServletRequest request, HttpServletResponse response) throws IOException {
       String nomEquipe = (String) request.getParameter("nomEquipeSup");
       GestionLigue ligue = (GestionLigue) request.getSession().getAttribute("ligue");
       try{
            synchronized (ligue) {
                ligue.gestionEquipe.supprimerEquipe(nomEquipe);
            }
            request.getSession().setAttribute("succesEquipe", "supprimerEquipe");
            request.getSession().setAttribute("nomEquipeSup", nomEquipe);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/succesEquipe.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
        }
    }


}
