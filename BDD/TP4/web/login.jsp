<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>IFT287 - Syst�me de gestion de la ligue de Baseball</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META NAME="author" CONTENT="Amandine Fouillet - Frank Chassing">
<META NAME="description"
CONTENT="page d'accueil syst�me de gestion de la ligue de baseball.">
<link href="style.css" rel="stylesheet" type="text/css" />
</HEAD>
<BODY>
<CENTER>
<br>
<H2>Syst�me de gestion de la ligue de Baseball</H2>
<br>
<FORM ACTION="Login" METHOD="POST">
    <fieldset>
    <h3>Acc�s a la base de donnees</h3>
    <INPUT placeholder="Identifiant" TYPE="TEXT" NAME="userIdBD" VALUE="postgres">
    <BR>
    <BR>
    <INPUT placeholder="Mot de passe" TYPE="TEXT" NAME="motDePasseBD" VALUE="frank">
    <BR>
    <BR>
    <INPUT placeholder="Base de donn�es" TYPE="TEXT" NAME="bd"  VALUE="postgres">
    <BR>
<BR>
<INPUT class="bouton" TYPE="SUBMIT" VALUE="Soumettre">
    </fieldset>

</FORM>
</CENTER>
<BR>
<BR>
<%-- affichage de la date et heure; --%>
<%-- utile pour d�bogger et verifier si la page a �t� --%>
<%-- par le fureteur --%>
<h3>
Date et heure normale de l'est: <%= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date()) %>
</h3>
</BODY>
</HTML>
