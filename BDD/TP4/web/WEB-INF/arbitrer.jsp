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
    </head>
    <body>
        <h1>Affichage des arbitrages</h1>
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
                <td style="vertical-align: top;">ID Arbitre</td>
                <td style="vertical-align: top;">ID Match</td>
                </tr>
            <br>
                <%
                    for(int i = 0; i<listArbitrer.size();i++) {
                        TupleArbitrer tupleArbitrer = listArbitrer.elementAt(i);
                    
                %>
                <tr>
                <td style="vertical-align: top;"><%= tupleArbitrer.idArbitre %></td>
                <td style="vertical-align: top;"><%= tupleArbitrer.idMatch %></td>
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
            Aucun arbitrage dans table.
       <%
        }
                %>
        <FORM ACTION="RequetesArbitrer" METHOD="POST">
            Affecter un arbitre a un match
            <BR>
            Date du match : <INPUT TYPE="DATE" NAME="matchDateArb" VALUE="yyyy-MM-dd">
            Heure du match : <INPUT TYPE="TIME" NAME="matchHeureArb" VALUE="hh:mm:ss">
            Nom equipe locale : <INPUT TYPE="TEXT" NAME="nomEquipeLocaleArb" VALUE="">
            Nom equipe visiteur : <INPUT TYPE="TEXT" NAME="nomEquipeVisiteurArb" VALUE="">
            Nom arbitre : <INPUT TYPE="TEXT" NAME="nomArbitreArb" VALUE="">
            Prenom arbitre : <INPUT TYPE="TEXT" NAME="prenomArbitreArb" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="arbitrerMatch" VALUE="arbitrerMatch">
        </FORM>
        <jsp:include page="/WEB-INF/messageErreur.jsp" />
        <BR>
        <a href="Logout">Sortir</a>
        <BR>
    </body>
</html>
