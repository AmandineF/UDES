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
        <link href="style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h2>Succes Equipe</h2>
        <fieldset style="width:500px;">
        <%
            String texte = (String) session.getAttribute("succesEquipe");
            if(texte.equals("creationEquipe")) {
                String equipeAdd = (String) session.getAttribute("nomEquipeAdd");
                request.getSession().setAttribute("nomEquipeAdd", "");
        %>
       <h3>L'equipe <%=equipeAdd%> a ete creee avec succes.</h3>
        <%
            }else if (texte.equals("supprimerEquipe")){
                String equipeSup = (String) session.getAttribute("nomEquipeSup");
                request.getSession().setAttribute("nomEquipeSup", "");
        %>
        <h3>L'equipe <%=equipeSup%> a ete supprimee avec succes.</h3>
        <%
            }else if (texte.equals("creationEquipeTerrain")){
                String equipeAdd = (String) session.getAttribute("nomEquipeAdd");
                request.getSession().setAttribute("nomEquipeAdd", "");
                String terrainAdd = (String) session.getAttribute("nomTerrainAdd");
                request.getSession().setAttribute("nomTerrainAdd", "");
        %>
        <h3>L'equipe <%=equipeAdd%> et le terrain <%= terrainAdd%> ont ete crees avec succes.</h3>
        <%
            }
        %>
        <FORM ACTION="RequetesEquipe" METHOD="POST">
            <INPUT class="bouton" style="width:180px;" TYPE="SUBMIT" NAME="afficherEquipes" VALUE="Afficher les equipes">
        </FORM>
        </fieldset>
        <BR><br>
                <div style="text-align:center;">
		        <a href="Login" ><INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu"></a>
		        <a href="Logout" ><INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Deconnexion"></a>
		        </div>
                <br><br>
    </body>
</html>
