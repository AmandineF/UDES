<%-- 
    Document   : arbitrer
    Author     : Amandine Fouillet - Frank Chassing
--%>

<%@page import="ligueBaseball.TupleArbitrer"%>
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
        <h2>Affichage des arbitrages</h2>
        <%
        GestionLigue ligue = (GestionLigue) session.getAttribute("ligue");
        Vector<TupleArbitrer> listArbitrer = ligue.gestionArbitrer.affiche();
        if(listArbitrer.size() > 0) {
        %>
        <table
            style="width: 50%; text-align: left; margin-left: auto; margin-right: auto;"
    border="1" cellspacing="2" cellpadding="2">
            <tbody>
                <%-- titre des colonnes --%>
                <tr>
                <td style="vertical-align: top;font-weight:bold;">ID Arbitre</td>
                <td style="vertical-align: top;font-weight:bold;">ID Match</td>
                </tr>
                <%
                    for(int i = 0; i<listArbitrer.size();i++) {
                        TupleArbitrer tupleArbitrer = listArbitrer.elementAt(i);
                    
                %>
                <tr>
                <td style="vertical-align: top;"><%= tupleArbitrer.idArbitre %></td>
                <td style="vertical-align: top;"><%= tupleArbitrer.idMatch %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
                <%
        }else{
            %>
            Aucun arbitrage dans table.
       <%
        }
                %>
                <br><br><br>
                <div style="text-align:center;">
		        <a href="Login" ><INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu"></a>
		        <a href="Logout" ><INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Deconnexion"></a>
		        </div>
                <br><br>
        <FORM ACTION="RequetesArbitrer" METHOD="POST">
        	<fieldset>
            <h3>Affecter un arbitre a un match</h3>
            <INPUT placeholder="Date : yyyy-MM-dd" TYPE="DATE" NAME="matchDateArb" VALUE="">
            <INPUT placeholder="Heure : hh:mm:ss" TYPE="TIME" NAME="matchHeureArb" VALUE="">
            <INPUT placeholder="Equipe locale" TYPE="TEXT" NAME="nomEquipeLocaleArb" VALUE="">
            <INPUT placeholder="Equipe visiteur" TYPE="TEXT" NAME="nomEquipeVisiteurArb" VALUE="">
            <INPUT placeholder="Nom de l'arbitre" TYPE="TEXT" NAME="nomArbitreArb" VALUE="">
            <INPUT placeholder="Prenom de l'arbitre" TYPE="TEXT" NAME="prenomArbitreArb" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="arbitrerMatch" VALUE="Valider">
            </fieldset>
        </FORM>
        <BR>
        <a href="Logout">Sortir</a>
        <BR>
    </body>
</html>
