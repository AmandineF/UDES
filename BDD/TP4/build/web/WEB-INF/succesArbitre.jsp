<%-- 
    Document   : succesArbitre
    Created on : 4 avr. 2015, 20:59:02
    Author     : Amandine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Succes Arbitre</h1>
        <%
            String texte = (String) session.getAttribute("succesArbitre");
            if(texte.equals("creationArbitre")) {
                String nomArbitre  = (String) session.getAttribute("nomArbitreAdd");
                request.getSession().setAttribute("nomArbitreAdd", "");
                String prenomArbitre  = (String) session.getAttribute("prenomArbitreAdd");
                request.getSession().setAttribute("prenomArbitreAdd", "");
        %>
        L'arbitre <%=prenomArbitre%> <%=nomArbitre%> a ete creee avec succes.
        <%
            }
        %>
        <FORM ACTION="RequetesArbitre" METHOD="POST">
            <INPUT TYPE="SUBMIT" NAME="afficherArbitres" VALUE="Afficher tous les arbitres">
        </FORM>
    </body>
</html>
