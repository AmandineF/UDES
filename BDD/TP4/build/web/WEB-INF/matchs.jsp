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
    </head>
    <body>
        <h1>Affichage des matchs</h1>
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
            style="width: 50%; text-align: left; margin-left: auto; margin-right: auto;"
    border="1" cellspacing="2" cellpadding="2">
            <tbody>
                <%-- titre des colonnes --%>
                <tr>
                <td style="vertical-align: top;">ID</td>
                <td style="vertical-align: top;">Equipe locale</td>
                <td style="vertical-align: top;">Equipe visiteur</td>
                <td style="vertical-align: top;">Terrain ID</td>
                <td style="vertical-align: top;">Date</td>
                <td style="vertical-align: top;">Heure</td>
                <td style="vertical-align: top;">Points locaux</td>
                <td style="vertical-align: top;">Points visiteurs</td>
                </tr>
            <br>
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
                <br>
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
        <FORM ACTION="RequetesMatch" METHOD="POST">
            Creation d'un match
            <BR>
            Match date : <INPUT TYPE="DATE" NAME="matchDateAdd" VALUE="yyyy-MM-dd">
            Match heure : <INPUT TYPE="TIME" NAME="matchHeureAdd" VALUE="hh:mm:ss">
            Nom equipe locale : <INPUT TYPE="TEXT" NAME="nomEquipeLocaleAdd" VALUE="">
            Nom equipe visiteur : <INPUT TYPE="TEXT" NAME="nomEquipeVisiteurAdd" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="creerMatch" VALUE="creerMatch">
        </FORM>  
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
            Entrer le resultat d’un match
            <BR>
            Date du match : <INPUT TYPE="DATE" NAME="matchDateRes" VALUE="yyyy-MM-dd">
            Heure du match : <INPUT TYPE="TIME" NAME="matchHeureRes" VALUE="hh:mm:ss">
            Nom equipe locale : <INPUT TYPE="TEXT" NAME="nomEquipeLocaleRes" VALUE="">
            Nom equipe visiteur : <INPUT TYPE="TEXT" NAME="nomEquipeVisiteurRes" VALUE="">
            Points local : <INPUT TYPE="TEXT" NAME="pointsLocalRes" VALUE="">
            Points visiteur : <INPUT TYPE="TEXT" NAME="pointsVisiteurRes" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="entrerResultatMatch" VALUE="entrerResultatMatch">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
            Afficher les resultats de tous les matchs a partir d'une date
            <BR>
            Date du match : <INPUT TYPE="DATE " NAME="matchDateAff" VALUE="yyyy-MM-dd">
            <INPUT TYPE="SUBMIT" NAME="afficherResultatDate" VALUE="afficherResultatDate">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
            Afficher les resultats de tous les matchs ou une equipe a participe
            <BR>
            Nom de l'equipe : <INPUT TYPE="TEXT" NAME="nomEquipeAff" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="afficherResultatEquipe" VALUE="afficherResultatEquipe">
        </FORM>
        <jsp:include page="/WEB-INF/messageErreur.jsp" />
        <BR>
        <a href="Logout">Sortir</a>
        <BR>
    </body>
</html>
