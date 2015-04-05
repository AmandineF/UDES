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
    </head>
    <body>        
        <h1>Affichage des arbitres</h1>
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
                <td style="vertical-align: top;">ID</td>
                <td style="vertical-align: top;">Nom</td>
                <td style="vertical-align: top;">Prenom</td>
                </tr>
            <br>
                <%
                    for(int i = 0; i<listArbitre.size();i++) {
                        TupleArbitre tupleArbitre = listArbitre.elementAt(i);
                    
                %>
                <tr>
                <td style="vertical-align: top;"><%= tupleArbitre.idArbitre %></td>
                <td style="vertical-align: top;"><%= tupleArbitre.nom %></td>
                <td style="vertical-align: top;"><%= tupleArbitre.prenom %></td>
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
            Aucun arbitre dans table.
       <%
        }
                %>
        <FORM ACTION="RequetesArbitre" METHOD="POST">
            Creation d'un arbitre
            <BR>
            Nom arbitre : <INPUT TYPE="TEXT" NAME="nomArbitre" VALUE="">
            Prenom arbitre : <INPUT TYPE="TEXT" NAME="prenomArbitre" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="creerArbitre" VALUE="creerArbitre">
        </FORM>
        <jsp:include page="/WEB-INF/messageErreur.jsp" />
        <BR>
        <a href="Logout">Sortir</a>
        <BR>
                
    </body>
</html>
