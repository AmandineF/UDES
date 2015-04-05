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
    </head>
    <body>
        <h1>Succes Arbitrer</h1>
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
        L'arbitre <%=prenomArbitre%> <%=nomArbitre%> a ete affecte au match du <%=date%> opposant l'equipe locale <%=equipeLocale%> a l'equipe visiteur <%=equipeVisiteur%>.
        <%
            }
        %>
        <FORM ACTION="RequetesEquipe" METHOD="POST">
            <INPUT TYPE="SUBMIT" NAME="afficherArbitrer" VALUE="Afficher tous les arbitrages">
        </FORM>
    </body>
</html>
