<%-- 
    Document   : succesArbitrer
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
        <h2>Succes Arbitrer</h2>
        <fieldset style="width:500px">
        <%
            String texte = (String) session.getAttribute("succesArbitrer");
            if(texte.equals("affecterArbitreMatch")) {
                String date = (String) session.getAttribute("matchDateArb");
                request.getSession().setAttribute("matchDateArb", "");
                String equipeLocale  = (String) session.getAttribute("nomEquipeLocaleArb");
                request.getSession().setAttribute("nomEquipeLocaleArb", "");
                String equipeVisiteur  = (String) session.getAttribute("nomEquipeVisiteurArb");
                request.getSession().setAttribute("nomEquipeVisiteurArb", "");
                String nomArbitre  = (String) session.getAttribute("nomArbitreArb");
                request.getSession().setAttribute("nomArbitreArb", "");
                String prenomArbitre  = (String) session.getAttribute("prenomArbitreArb");
                request.getSession().setAttribute("prenomArbitreArb", "");
        %>
        <h3>L'arbitre <%=prenomArbitre%> <%=nomArbitre%> a ete affecte au match du <%=date%> opposant l'equipe locale <%=equipeLocale%> a l'equipe visiteur <%=equipeVisiteur%>.</h3>
        <%
            }
        %>
        <FORM ACTION="RequetesArbitrer" METHOD="POST">
            <INPUT class="bouton" style="width:220px" TYPE="SUBMIT" NAME="afficherArbitrer" VALUE="Afficher tous les arbitrages">
        </FORM>
        </fieldset>
        <br><br>
         <div style="text-align:center;">
		   <a href="Login" ><INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu"></a>
		   <a href="Logout" ><INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Deconnexion"></a>
   		</div>
         <br><br>
    </body>
</html>
