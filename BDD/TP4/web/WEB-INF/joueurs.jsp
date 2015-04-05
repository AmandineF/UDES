<%-- 
    Document   : joueurs
    Author     : Amandine Fouillet - Frank Chassing
--%>

<%@page import="ligueBaseball.TupleJoueur"%>
<%@page import="java.util.Vector"%>
<%@page import="ligueBaseball.GestionLigue"%>
<%@page import="ligueBaseball.GestionLigue"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        GestionLigue ligue = (GestionLigue) session.getAttribute("ligue");
        String nomEquipe = (String) session.getAttribute("nomEquipeAffJE");
        request.getSession().setAttribute("nomEquipeAffJE", "");
        Vector<TupleJoueur> listJoueur = new Vector<TupleJoueur>();
        if(nomEquipe.equals("")) {
            %>
             <h1>Affichage des joueurs</h1>
             <%
            listJoueur = ligue.gestionJoueur.afficherJoueursEquipeVector();
        } else {
            %>
            <h1>Affichage des joueurs de l'equipe des <%=nomEquipe%></h1>
            <%
            listJoueur = ligue.gestionJoueur.afficherJoueursEquipeVector(nomEquipe);
        }
        if(!listJoueur.isEmpty()) {
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
                    for(int i = 0; i<listJoueur.size();i++) {
                        TupleJoueur tupleJoueur = listJoueur.elementAt(i);
                    
                %>
                <tr>
                <td style="vertical-align: top;"><%= tupleJoueur.idJoueur %></td>
                <td style="vertical-align: top;"><%= tupleJoueur.nom %></td>
                <td style="vertical-align: top;"><%= tupleJoueur.prenom %></td>
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
            Aucun joueur dans la table.
       <%
        }
                %>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
            Creation d'un joueur
            <BR>
            Nom : <INPUT TYPE="TEXT" NAME="nomJoueurAdd" VALUE="">
            Prenom : <INPUT TYPE="TEXT" NAME="prenomJoueurAdd" VALUE="">
            Nom de l'equipe : <INPUT TYPE="TEXT" NAME="nomEquipeAdd" VALUE="">
            Numero : <INPUT TYPE="TEXT" NAME="numeroJoueurAdd" VALUE="">
            Date de debut : <INPUT TYPE="DATE" NAME="dateDebutAdd" VALUE="yyyy-MM-dd">
            <INPUT TYPE="SUBMIT" NAME="creerJoueur" VALUE="creerJoueur">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
            Afficher les joueurs d'une equipe
            <BR>
            Nom de l'equipe : <INPUT TYPE="TEXT" NAME="nomEquipeAff" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="afficherJoueurEquipe" VALUE="afficherJoueursEquipe">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
            Supprimer un joueur
            <BR>
            Nom : <INPUT TYPE="TEXT" NAME="nomJoueurSup" VALUE="">
            Prenom : <INPUT TYPE="TEXT" NAME="prenomJoueurSup" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="supprimerJoueur" VALUE="supprimerJoueur">
        </FORM> 
        <BR>
        <BR>
        <jsp:include page="/WEB-INF/messageErreur.jsp" />
        <BR>
        <a href="Logout">Sortir</a>
        <BR>
    </body>
</html>
