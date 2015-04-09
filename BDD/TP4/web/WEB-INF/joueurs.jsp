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
        <link href="style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
        GestionLigue ligue = (GestionLigue) session.getAttribute("ligue");
        String nomEquipe = (String) session.getAttribute("nomEquipeAffJE");
        request.getSession().setAttribute("nomEquipeAffJE", "");
        Vector<TupleJoueur> listJoueur = new Vector<TupleJoueur>();
        if(nomEquipe.equals("")) {
            %>
             <h2>Affichage des joueurs</h2>
             <%
            listJoueur = ligue.gestionJoueur.afficherJoueursEquipeVector();
        } else {
            %>
            <h2>Affichage des joueurs de l'equipe des <%=nomEquipe%></h2>
            <%
            listJoueur = ligue.gestionJoueur.afficherJoueursEquipeVector(nomEquipe);
            System.out.println(listJoueur);
        }
        
    	if(listJoueur == null){
	    	%>
	       <h3>L'equipe n'existe pas !</h3>
	   <%
    	}else if(!listJoueur.isEmpty()) {
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
                    for(int i = 0; i<listJoueur.size();i++) {
                        TupleJoueur tupleJoueur = listJoueur.elementAt(i);
                    
                %>
                <tr>
                <td style="vertical-align: top;"><%= tupleJoueur.idJoueur %></td>
                <td style="vertical-align: top;"><%= tupleJoueur.nom %></td>
                <td style="vertical-align: top;"><%= tupleJoueur.prenom %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
                <%
       
        }else{
            %>
            <h3>Aucun joueur dans la table.</h3>
       <%
        }
                %>
                <br><br><br>
                <div style="text-align:center;">
		        <a href="Login" ><INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu"></a>
		        <a href="Logout" ><INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Deconnexion"></a>
		        </div>
                <br><br>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
        <fieldset>
            <h3>Creer un joueur</h3>
            <INPUT placeholder="Nom du joueur" TYPE="TEXT" NAME="nomJoueurAdd" VALUE="">
            <INPUT placeholder="Prenom du joueur" TYPE="TEXT" NAME="prenomJoueurAdd" VALUE="">
            <INPUT placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeAdd" VALUE="">
            <INPUT placeholder="Numero du joueur" TYPE="TEXT" NAME="numeroJoueurAdd" VALUE="">
            <INPUT placeholder="Date : yyyy-MM-dd" TYPE="DATE" NAME="dateDebutAdd" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="creerJoueur" VALUE="Valider">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
        <fieldset>
            <h3>Afficher les joueurs d'une equipe</h3>
            <INPUT placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeAff" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="afficherJoueurEquipe" VALUE="Afficher">
        </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
        <fieldset>
            <h3>Supprimer un joueur</h3>
            <INPUT placeholder="Nom du joueur" TYPE="TEXT" NAME="nomJoueurSup" VALUE="">
            <INPUT placeholder="Prenom du joueur" TYPE="TEXT" NAME="prenomJoueurSup" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="supprimerJoueur" VALUE="Valider">
            </fieldset>
        </FORM> 
        <BR>
        <BR>
        <BR>
        <a href="Logout">Sortir</a>
        <BR>
    </body>
</html>
