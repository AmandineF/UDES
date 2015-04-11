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
        <link href="style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h2>Succes Exportation</h2>
        <%
            String nomEquipe = (String) session.getAttribute("exportation");
            request.getSession().setAttribute("exportation", "");
        %>
        <fieldset style="width:500px">
        <h3>L'equipe <%=nomEquipe%> a ete exportee avec succes. Le fichier <%=nomEquipe%>.xml a ete cree.</h3>
        </fieldset>
        <BR><br>
        <div style="text-align:center;">
 <FORM ACTION="Logout" METHOD="POST">
		        <INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu">
		        <INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="deconnexion" VALUE="Deconnexion">
                    </FORM>
 		 </div>
        <br><br>
    </body>
</html>
