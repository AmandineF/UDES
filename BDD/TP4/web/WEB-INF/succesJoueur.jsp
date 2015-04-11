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
        <link href="style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h2>Succes Joueur</h2>
        <fieldset style="width:500px">
        <%
            String texte = (String) session.getAttribute("succesJoueur");
            if(texte.equals("creationJoueur")) {
                String nomJoueur = (String) session.getAttribute("nomJoueurAdd");
                request.getSession().setAttribute("nomJoueurAdd", "");
                String prenomJoueur  = (String) session.getAttribute("prenomJoueurAdd");
                request.getSession().setAttribute("prenomJoueurAdd", "");
        %>
        <h3>Le joueur <%=nomJoueur%> <%=prenomJoueur%> a ete cree avec succes.</h3>
        <%
            }else if (texte.equals("suppressionJoueur")){
                String nomJoueur = (String) session.getAttribute("nomJoueurSup");
                request.getSession().setAttribute("nomJoueurSup", "");
                String prenomJoueur  = (String) session.getAttribute("prenomJoueurSup");
                request.getSession().setAttribute("prenomJoueurSup", "");
        %>
       <h3>Le joueur <%=nomJoueur%> <%=prenomJoueur%> a ete supprime avec succes.</h3>
        <%
            }
        %>
        </fieldset>
        <br><br>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
        	<fieldset>
			<h3>Afficher les joueurs d'une equipe</h3>
        	<INPUT placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeAff" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="afficherJoueurEquipe" VALUE="Afficher">
            </fieldset>
        </FORM>
        
        <br><br>
                <div style="text-align:center;">
 <FORM ACTION="Logout" METHOD="POST">
		        <INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu">
		        <INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="deconnexion" VALUE="Deconnexion">
                    </FORM>
		        </div>
                <br><br>
    </body>
</html>
