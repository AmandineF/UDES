<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>IFT287 - Syst�me de gestion de la ligue de Baseball</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META NAME="author" CONTENT="Amandine Fouillet - Frank Chassing">
<META NAME="description"
CONTENT="page d'accueil syst�me de gestion de la ligue de baseball.">
</HEAD>
<BODY>
<CENTER>
<H1>Syst�me de gestion de la ligue de Baseball</H1>
<FORM ACTION="Login" METHOD="POST">
    <BR>
    <BR>
    Identifiant : <INPUT TYPE="TEXT" NAME="userIdBD" VALUE="postgres">
    <BR>
    <BR>
    Mot de passe : <INPUT TYPE="TEXT" NAME="motDePasseBD" VALUE="amandine">
    <BR>
    <BR>
    Base de donn�es : <INPUT TYPE="TEXT" NAME="bd"  VALUE="postgres">
<BR>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Soumettre">
</FORM>
</CENTER>
<BR>
<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
<jsp:include page="/WEB-INF/messageErreur.jsp" />
<BR>
<%-- affichage de la date et heure; --%>
<%-- utile pour d�bogger et verifier si la page a �t� --%>
<%-- par le fureteur --%>
Date et heure normale de l'est: <%= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date()) %>
</BODY>
</HTML>