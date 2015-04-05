<%-- 
    Document   : succesEquipe
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
        <h1>Succes Equipe</h1>
        <%
            String texte = (String) session.getAttribute("succesEquipe");
            if(texte.equals("creationEquipe")) {
                String equipeAdd = (String) session.getAttribute("nomEquipeAdd");
                request.getSession().setAttribute("nomEquipeAdd", "");
        %>
        L'equipe <%=equipeAdd%> a ete creee avec succes.
        <%
            }else if (texte.equals("supprimerEquipe")){
                String equipeSup = (String) session.getAttribute("nomEquipeSup");
                request.getSession().setAttribute("nomEquipeSup", "");
        %>
        L'equipe <%=equipeSup%> a ete supprimee avec succes.
        <%
            }else if (texte.equals("creationEquipeTerrain")){
                String equipeAdd = (String) session.getAttribute("nomEquipeAdd");
                request.getSession().setAttribute("nomEquipeAdd", "");
                String terrainAdd = (String) session.getAttribute("nomTerrainAdd");
                request.getSession().setAttribute("nomTerrainAdd", "");
        %>
        L'equipe <%=equipeAdd%> et le terrain <%= terrainAdd%> ont ete crees avec succes.
        <%
            }
        %>
        <FORM ACTION="RequetesEquipe" METHOD="POST">
            <INPUT TYPE="SUBMIT" NAME="afficherEquipes" VALUE="Afficher les equipes">
        </FORM>
        <BR>
        <a href="Logout">Sortir</a>
        <BR>
    </body>
</html>
