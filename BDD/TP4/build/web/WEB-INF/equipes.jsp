<%-- 
    Document   : equipes
    Author     : Amandine Fouillet - Frank Chassing
--%>

<%@page import="ligueBaseball.TupleEquipe"%>
<%@page import="java.util.Vector"%>
<%@page import="ligueBaseball.GestionLigue"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Affichage des equipes</h1>
        <%
        GestionLigue ligue = (GestionLigue) session.getAttribute("ligue");
        Vector<TupleEquipe> listEquipe = ligue.gestionEquipe.afficherEquipesVector();
        if(listEquipe.size() > 0) {
        %>
        <table
            style="width: 50%; text-align: left; margin-left: auto; margin-right: auto;"
    border="1" cellspacing="2" cellpadding="2">
            <tbody>
                <%-- titre des colonnes --%>
                <tr>
                <td style="vertical-align: top;">ID</td>
                <td style="vertical-align: top;">Nom</td>
                <td style="vertical-align: top;">Terrain ID</td>
                </tr>
            <br>
                <%
                    for(int i = 0; i<listEquipe.size();i++) {
                        TupleEquipe tupleEquipe = listEquipe.elementAt(i);
                    
                %>
                <tr>
                <td style="vertical-align: top;"><%= tupleEquipe.idEquipe %></td>
                <td style="vertical-align: top;"><%= tupleEquipe.nom %></td>
                <td style="vertical-align: top;"><%= tupleEquipe.idTerrain %></td>
                </tr>
                <br>
                <%
                    }
                %>
            </tbody>
        </table>
                <%
        }else{
            %>
            Aucune equipe dans table.
       <%
        }
                %>
                
        <FORM ACTION="RequetesEquipe" METHOD="POST">
            Creation d'une equipe
            <BR>
            Nom : <INPUT TYPE="TEXT" NAME="nomEquipeAdd" VALUE="">
            Nom du terrain : <INPUT TYPE="TEXT" NAME="nomTerrainAdd" VALUE="">
            Adresse du terrain : <INPUT TYPE="TEXT" NAME="adresseTerrainAdd" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="creerEquipe" VALUE="creerEquipe">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesEquipe" METHOD="POST">
            Supprimer une equipe
            <BR>
            Nom : <INPUT TYPE="TEXT" NAME="nomEquipeSup" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="supprimerEquipe">
        </FORM>
        <jsp:include page="/WEB-INF/messageErreur.jsp" />
        <BR>
        <a href="Logout">Sortir</a>
        <BR>
    </body>
</html>
