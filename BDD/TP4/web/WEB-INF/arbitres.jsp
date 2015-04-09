<%-- 
    Document   : arbitres
    Author     : Amandine Fouillet - Frank Chassing
--%>

<%@page import="ligueBaseball.TupleArbitre"%>
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
        <h2>Affichage des arbitres</h2>
        <%
        GestionLigue ligue = (GestionLigue) session.getAttribute("ligue");
        Vector<TupleArbitre> listArbitre = ligue.gestionArbitre.afficherArbitreListe();
        if(listArbitre.size() > 0) {
        %>
        <table
            style="width: 50%; text-align: left; margin-left: auto; margin-right: auto;"
    border="1" cellspacing="2" cellpadding="2">
            <tbody>
                <%-- titre des colonnes --%>
                <tr>
                <td style="vertical-align: top;font-weight:bold;">ID</td>
                <td style="vertical-align: top;font-weight:bold;">Nom</td>
                <td style="vertical-align: top;font-weight:bold;">Prenom</td>
                </tr>
                <%
                    for(int i = 0; i<listArbitre.size();i++) {
                        TupleArbitre tupleArbitre = listArbitre.elementAt(i);
                    
                %>
                <tr>
                <td style="vertical-align: top;"><%= tupleArbitre.idArbitre %></td>
                <td style="vertical-align: top;"><%= tupleArbitre.nom %></td>
                <td style="vertical-align: top;"><%= tupleArbitre.prenom %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
                <%
        }else{
            %>
            <h3>Aucun arbitre dans table.</h3>
       <%
        }
                %>
                <br><br><br>
               	<div style="text-align:center;">
		        <a href="Login" ><INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu"></a>
		        <a href="Logout" ><INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Deconnexion"></a>
		        </div>
                <br><br>
        <FORM ACTION="RequetesArbitre" METHOD="POST">
        <fieldset>
            <h3>Creer d'un arbitre</h3>
            <INPUT placeholder="Nom de l'arbitre" TYPE="TEXT" NAME="nomArbitre" VALUE="">
            <INPUT placeholder="Prenom de l'arbitre" TYPE="TEXT" NAME="prenomArbitre" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="creerArbitre" VALUE="Valider">
        </fieldset>
        </FORM>
        <BR>
        <a href="Logout">Sortir</a>
        <BR>
                
    </body>
</html>
