<%-- 
    Document   : succesJoueur
    Author     : Amandine Fouillet - Frank Chassing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Succes Joueur</h1>
        <%
            String texte = (String) session.getAttribute("succesJoueur");
            if(texte.equals("creationJoueur")) {
                String nomJoueur = (String) session.getAttribute("nomJoueurAdd");
                request.getSession().setAttribute("nomJoueurAdd", "");
                String prenomJoueur  = (String) session.getAttribute("prenomJoueurAdd");
                request.getSession().setAttribute("prenomJoueurAdd", "");
        %>
        Le joueur <%=nomJoueur%> <%=prenomJoueur%> a ete creee avec succes.
        <%
            }else if (texte.equals("suppressionJoueur")){
                String nomJoueur = (String) session.getAttribute("nomJoueurSup");
                request.getSession().setAttribute("nomJoueurSup", "");
                String prenomJoueur  = (String) session.getAttribute("prenomJoueurSup");
                request.getSession().setAttribute("prenomJoueurSup", "");
        %>
        Le joueur <%=nomJoueur%> <%=prenomJoueur%> a ete supprime avec succes.
        <%
            }
        %>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
            <INPUT TYPE="SUBMIT" NAME="afficherJoueurEquipe" VALUE="Afficher tous les joueurs">
        </FORM>
    </body>
</html>
