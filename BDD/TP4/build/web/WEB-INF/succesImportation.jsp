<%-- 
    Document   : succesImportation
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
        <h2>Succes Importation</h2>
        <%
            String nomFichier = (String) session.getAttribute("importation");
            request.getSession().setAttribute("importation", "");
        %>
        <fieldset style="width:500px">
        <h3>Le fichir <%=nomFichier%> a ete importe avec succes.</h3>
        </fieldset>
        <BR><br>
        <div style="text-align:center;">
		  <a href="Login" ><INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu"></a>
		  <a href="Logout" ><INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Deconnexion"></a>
 		 </div>
        <br><br>
    </body>
</html>
