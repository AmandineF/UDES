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
    </head>
    <body>
        <h1>Succes Match</h1>
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
        Le match du <%=date%> opposant l'equipe locale <%=equipeLocale%> a l'equipe visiteur <%=equipeVisiteur%>  a ete creee avec succes.
        <%
            }else if (texte.equals("resultatMatch")){
                String date = (String) session.getAttribute("matchDateRes");
                request.getSession().setAttribute("matchDateRes", "");
                String equipeLocale  = (String) session.getAttribute("nomEquipeLocaleRes");
                request.getSession().setAttribute("nomEquipeLocaleRes", "");
                String equipeVisiteur  = (String) session.getAttribute("nomEquipeVisiteurRes");
                request.getSession().setAttribute("nomEquipeVisiteurRes", "");
        %>
        Les resultats match du <%=date%> opposant l'equipe locale <%=equipeLocale%> a l'equipe visiteur <%=equipeVisiteur%> ont ete enregistres avec succes.
        <%
            }
        %>
        <FORM ACTION="RequetesMatch" METHOD="POST">
            <INPUT TYPE="SUBMIT" NAME="afficherMatch" VALUE="Afficher tous les matchs">
        </FORM>
    </body>
</html>
