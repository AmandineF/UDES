<%-- 
    Document   : succesExportation
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
        <h1>Succes Exportation</h1>
        <%
            String nomEquipe = (String) session.getAttribute("exportation");
            request.getSession().setAttribute("exportation", "");
        %>
        L'equipe <%=nomEquipe%> a ete exportee avec succes. Le fichier <%=nomEquipe%>.xml a ete cree.
    </body>
</html>
