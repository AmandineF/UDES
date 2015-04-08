<%-- 
    Document   : matchs
    Author     : Amandine Fouillet - Frank Chassing
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="ligueBaseball.TupleMatch"%>
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
        <h2>Affichage des matchs</h2>
        <%
        GestionLigue ligue = (GestionLigue) session.getAttribute("ligue");
        String equipenom = "";
        equipenom = (String) session.getAttribute("matchequipe");
        String datematch = "";
        datematch = (String) session.getAttribute("matchdate");
        Vector<TupleMatch> listMatch = new Vector<TupleMatch>();
        if(!equipenom.equals("")) {
            listMatch = ligue.gestionMatch.afficherResultatEquipeVector(equipenom);
        } else if(!datematch.equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dateDebut = sdf.parse(datematch);
            Date matchDateAff = new java.sql.Date(dateDebut.getTime());
            listMatch = ligue.gestionMatch.afficherResultatsDateVector(matchDateAff);
        } else {
            listMatch = ligue.gestionMatch.afficherResultatEquipeVector("");
        }
        if(listMatch.size() > 0) {
        %>
        <table
            style="width: 60%; text-align: left; margin-left: auto; margin-right: auto;"
    border="1" cellspacing="2" cellpadding="2">
            <tbody>
                <%-- titre des colonnes --%>
                <tr>
                <td style="vertical-align: top;font-weight:bold;width:30px">ID</td>
                <td style="vertical-align: top;font-weight:bold;">Equipe locale</td>
                <td style="vertical-align: top;font-weight:bold;">Equipe visiteur</td>
                <td style="vertical-align: top;font-weight:bold;">Terrain ID</td>
                <td style="vertical-align: top;font-weight:bold;width:100px">Date</td>
                <td style="vertical-align: top;font-weight:bold;width:100px">Heure</td>
                <td style="vertical-align: top;font-weight:bold;">Points locaux</td>
                <td style="vertical-align: top;font-weight:bold;">Points visiteurs</td>
                </tr>
                <%
                    for(int i = 0; i<listMatch.size();i++) {
                        TupleMatch tupleMatch = listMatch.elementAt(i);
                    
                %>
                <tr>
                <td style="vertical-align: top;"><%= tupleMatch.idMatch %></td>
                <td style="vertical-align: top;"><%= tupleMatch.equipelocal %></td>
                <td style="vertical-align: top;"><%= tupleMatch.equipevisiteur %></td>
                <td style="vertical-align: top;"><%= tupleMatch.terrainid %></td>
                <td style="vertical-align: top;"><%= tupleMatch.matchdate %></td>
                <td style="vertical-align: top;"><%= tupleMatch.matchheure %></td>
                <td style="vertical-align: top;"><%= tupleMatch.pointslocal %></td>
                <td style="vertical-align: top;"><%= tupleMatch.pointsvisiteur %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
                <%
        }else{
            %>
            Aucun joueur dans table.
       <%
        }
                %>
                <br><br><br>
                <div style="text-align:center;">
		        <a href="Login" ><INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu"></a>
		        <a href="Logout" ><INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Deconnexion"></a>
		        </div>
                <br><br>
        <FORM ACTION="RequetesMatch" METHOD="POST">
        <fieldset>
            <h3>Creer un match</h3>
            <INPUT placeholder="Date : yyyy-MM-dd" TYPE="DATE" NAME="matchDateAdd" VALUE="">
            <INPUT placeholder="Heure : hh:mm:ss" TYPE="TIME" NAME="matchHeureAdd" VALUE="">
            <INPUT placeholder="Equipe locale" TYPE="TEXT" NAME="nomEquipeLocaleAdd" VALUE="">
            <INPUT placeholder="Equipe visiteur" TYPE="TEXT" NAME="nomEquipeVisiteurAdd" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="creerMatch" VALUE="Valider">
            </fieldset>
        </FORM>  
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
        <fieldset>
           <h3>Entrer le resultat d’un match</h3>
            <INPUT placeholder="Date : yyyy-MM-dd" TYPE="DATE" NAME="matchDateRes" VALUE="">
            <INPUT placeholder="Heure : hh:mm:ss" TYPE="TIME" NAME="matchHeureRes" VALUE="">
            <INPUT placeholder="Equipe locale" TYPE="TEXT" NAME="nomEquipeLocaleRes" VALUE="">
            <INPUT placeholder="Equipe visiteur" TYPE="TEXT" NAME="nomEquipeVisiteurRes" VALUE="">
            <INPUT placeholder="Points locale" TYPE="TEXT" NAME="pointsLocalRes" VALUE="">
            <INPUT placeholder="Points visiteur" TYPE="TEXT" NAME="pointsVisiteurRes" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="entrerResultatMatch" VALUE="Valider">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
        <fieldset>
           <h3>Afficher les resultats de tous les matchs a partir d'une date</h3>
            <INPUT placeholder="Date : yyyy-MM-dd" TYPE="DATE" NAME="matchDateAff" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="afficherResultatDate" VALUE="Afficher">
        </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
        <fieldset>
           <h3>Afficher les resultats de tous les matchs ou une equipe a participe</h3>
            <INPUT placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeAff" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="afficherResultatEquipe" VALUE="Afficher">
            </fieldset>
        </FORM>
        <BR>
        <a href="Logout">Sortir</a>
        <BR>
    </body>
</html>
