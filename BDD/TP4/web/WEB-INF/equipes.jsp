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
        <link href="style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h2>Affichage des equipes</h2>
        <%
        GestionLigue ligue = (GestionLigue) session.getAttribute("ligue");
        Vector<TupleEquipe> listEquipe = ligue.gestionEquipe.afficherEquipesVector();
        if(listEquipe.size() > 0) {
        %>
        <table
             style="width: 50%; text-align: left; margin-left: auto; margin-right: auto;position:relative;"
    border="1" cellspacing="2" cellpadding="2">
            <tbody>
                <%-- titre des colonnes --%>
                <tr>
                <td style="vertical-align: top;font-weight:bold;">ID</td>
                <td style="vertical-align: top;font-weight:bold;">Nom</td>
                <td style="vertical-align: top;font-weight:bold;">Terrain ID</td>
                </tr>
                <%
                    for(int i = 0; i<listEquipe.size();i++) {
                        TupleEquipe tupleEquipe = listEquipe.elementAt(i);
                    
                %>
                <tr>
                <td style="vertical-align: top;"><%= tupleEquipe.idEquipe %></td>
                <td style="vertical-align: top;"><%= tupleEquipe.nom %></td>
                <td style="vertical-align: top;"><%= tupleEquipe.idTerrain %></td>
                </tr>
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
                <br><br><br>
                <div style="text-align:center;">
		        <a href="Login" ><INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu"></a>
		        <a href="Logout" ><INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Deconnexion"></a>
		        </div>
                <br><br>
        <FORM ACTION="RequetesEquipe" METHOD="POST">
        	<fieldset>
            <h3>Creer une equipe</h3>
            <INPUT placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeAdd" VALUE="">
            <INPUT placeholder="Nom du terrain" TYPE="TEXT" NAME="nomTerrainAdd" VALUE="">
            <INPUT placeholder="Adresse du terrain" TYPE="TEXT" NAME="adresseTerrainAdd" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="creerEquipe" VALUE="Valider">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesEquipe" METHOD="POST">
        <fieldset>
            <h3>Supprimer une equipe</h3>
            <INPUT placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeSup" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Valider">
            </fieldset>
        </FORM>
        <BR>
        <BR>
    </body>
</html>
