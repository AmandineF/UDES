<%-- 
    Document   : succesArbitre
    Created on : 4 avr. 2015, 20:59:02
    Author     : Amandine
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
        <h2>Succes Arbitre</h2>
        <fieldset style="width:500px">
        <%
            String texte = (String) session.getAttribute("succesArbitre");
            if(texte.equals("creationArbitre")) {
                String nomArbitre  = (String) session.getAttribute("nomArbitreAdd");
                request.getSession().setAttribute("nomArbitreAdd", "");
                String prenomArbitre  = (String) session.getAttribute("prenomArbitreAdd");
                request.getSession().setAttribute("prenomArbitreAdd", "");
        %>
        <h3>L'arbitre <%=prenomArbitre%> <%=nomArbitre%> a ete creee avec succes.</h3>
        <%
            }
        %>
        <FORM ACTION="RequetesArbitre" METHOD="POST">
            <INPUT class="bouton" style="width:150px" TYPE="SUBMIT" NAME="afficherArbitres" VALUE="Afficher arbitres">
        </FORM>
        </fieldset>
       <br><br>
       <div style="text-align:center;"> 
       <FORM ACTION="Logout" METHOD="POST">
		        <INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu">
		        <INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="deconnexion" VALUE="Deconnexion">
                    </FORM>
       </div>
             <br><br>
    </body>
</html>
