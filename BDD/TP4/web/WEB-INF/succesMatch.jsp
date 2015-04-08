<%-- 
    Document   : succesMatch
    Author     : Amandine Fouillet - Frank Chassing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h2>Succes Match</h2> 
        <fieldset style="width:500px">
        <%
            String texte = (String) session.getAttribute("succesMatch");
            if(texte.equals("creationMatch")) {
                String date = (String) session.getAttribute("matchDateAdd");
                request.getSession().setAttribute("matchDateAdd", "");
                String equipeLocale  = (String) session.getAttribute("nomEquipeLocaleAdd");
                request.getSession().setAttribute("nomEquipeLocaleAdd", "");
                String equipeVisiteur  = (String) session.getAttribute("nomEquipeVisiteurAdd");
                request.getSession().setAttribute("nomEquipeVisiteurAdd", "");
        %>
        <h3>Le match du <%=date%> opposant l'equipe locale <%=equipeLocale%> a l'equipe visiteur <%=equipeVisiteur%>  a ete creee avec succes.</h3>
        <%
            }else if (texte.equals("resultatMatch")){
                String date = (String) session.getAttribute("matchDateRes");
                request.getSession().setAttribute("matchDateRes", "");
                String equipeLocale  = (String) session.getAttribute("nomEquipeLocaleRes");
                request.getSession().setAttribute("nomEquipeLocaleRes", "");
                String equipeVisiteur  = (String) session.getAttribute("nomEquipeVisiteurRes");
                request.getSession().setAttribute("nomEquipeVisiteurRes", "");
        %>
       <h3>Les resultats match du <%=date%> opposant l'equipe locale <%=equipeLocale%> a l'equipe visiteur <%=equipeVisiteur%> ont ete enregistres avec succes.</h3>
        <%
            }
        %>
        <FORM ACTION="RequetesMatch" METHOD="POST">
            <INPUT class="bouton" style="width:150px" TYPE="SUBMIT" NAME="afficherMatch" VALUE="Afficher matchs">
        </FORM>
        </fieldset>
                <br><br>
                <div style="text-align:center;">
		        <a href="Login" ><INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu"></a>
		        <a href="Logout" ><INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Deconnexion"></a>
		        </div>
                <br><br>
    </body>
</html>
