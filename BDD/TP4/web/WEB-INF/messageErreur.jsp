<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="style.css" rel="stylesheet" type="text/css" />
    </head>
    <br>
    <h2>Erreur !</h2>
<%
  // affichage de la liste des messages d'erreur
  if (request.getAttribute("listeMessageErreur") != null)
    {
%>
    <h3>
<%
    ListIterator it = ((List) request.
      getAttribute("listeMessageErreur")).listIterator();
    while (it.hasNext())
      {
%>
      <BR>
      <%= it.next() %>
<%    
      }
%>
    </h3>
                <div style="text-align:center;">
		    <FORM ACTION="Logout" METHOD="POST">
		        <INPUT class="bouton2" TYPE="SUBMIT" NAME="retourMenu" VALUE="Menu">
		        <INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="deconnexion" VALUE="Deconnexion">
                    </FORM>
                <br><br>
<%
    }
%>
