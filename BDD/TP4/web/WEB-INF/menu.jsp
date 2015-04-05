<%-- 
    Document   : Menu principal
    Author     : Amandine Fouillet - Frank Chassing
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu principal</title>
    </head>
    <body>
        <H2>Menu principal</H2>   
        <FORM ACTION="RequetesEquipe" METHOD="POST">
            1 - Creation d'une equipe
            <BR>
            Nom : <INPUT TYPE="TEXT" NAME="nomEquipeAdd" VALUE="">
            Nom du terrain : <INPUT TYPE="TEXT" NAME="nomTerrainAdd" VALUE="">
            Adresse du terrain : <INPUT TYPE="TEXT" NAME="adresseTerrainAdd" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="creerEquipe" VALUE="creerEquipe">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesEquipe" METHOD="POST">
            2 - Affichage des equipes
            <INPUT TYPE="SUBMIT" NAME="afficherEquipes" VALUE="afficherEquipes">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesEquipe" METHOD="POST">
            3 - Supprimer une equipe
            <BR>
            Nom : <INPUT TYPE="TEXT" NAME="nomEquipeSup" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="supprimerEquipe">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
            4 - Creation d'un joueur
            <BR>
            Nom : <INPUT TYPE="TEXT" NAME="nomJoueurAdd" VALUE="">
            Prenom : <INPUT TYPE="TEXT" NAME="prenomJoueurAdd" VALUE="">
            Nom de l'equipe : <INPUT TYPE="TEXT" NAME="nomEquipeAdd" VALUE="">
            Numero : <INPUT TYPE="TEXT" NAME="numeroJoueurAdd" VALUE="">
            Date de debut : <INPUT TYPE="DATE" NAME="dateDebutAdd" VALUE="yyyy-MM-dd">
            <INPUT TYPE="SUBMIT" NAME="creerJoueur" VALUE="creerJoueur">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
            5 - Afficher les joueurs d'une equipe
            <BR>
            Nom de l'equipe : <INPUT TYPE="TEXT" NAME="nomEquipeAff" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="afficherJoueurEquipe" VALUE="afficherJoueursEquipe">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
            6 - Supprimer un joueur
            <BR>
            Nom : <INPUT TYPE="TEXT" NAME="nomJoueurSup" VALUE="">
            Prenom : <INPUT TYPE="TEXT" NAME="prenomJoueurSup" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="supprimerJoueur" VALUE="supprimerJoueur">
        </FORM> 
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
            7 - Creation d'un match
            <BR>
            Match date : <INPUT TYPE="DATE" NAME="matchDateAdd" VALUE="yyyy-MM-dd">
            Match heure : <INPUT TYPE="TIME" NAME="matchHeureAdd" VALUE="hh:mm:ss">
            Nom equipe locale : <INPUT TYPE="TEXT" NAME="nomEquipeLocaleAdd" VALUE="">
            Nom equipe visiteur : <INPUT TYPE="TEXT" NAME="nomEquipeVisiteurAdd" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="creerMatch" VALUE="creerMatch">
        </FORM>  
        <BR>
        <BR>
        <FORM ACTION="RequetesArbitre" METHOD="POST">
            8 - Creation d'un arbitre
            <BR>
            Nom arbitre : <INPUT TYPE="TEXT" NAME="nomArbitre" VALUE="">
            Prenom arbitre : <INPUT TYPE="TEXT" NAME="prenomArbitre" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="creerArbitre" VALUE="creerArbitre">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesArbitre" METHOD="POST">
            9 - Afficher les arbitres
            <INPUT TYPE="SUBMIT" NAME="afficherArbitres" VALUE="afficherArbitres">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesArbitrer" METHOD="POST">
            10 - Affecter un arbitre a un match
            <BR>
            Date du match : <INPUT TYPE="DATE" NAME="matchDateArb" VALUE="yyyy-MM-dd">
            Heure du match : <INPUT TYPE="TIME" NAME="matchHeureArb" VALUE="hh:mm:ss">
            Nom equipe locale : <INPUT TYPE="TEXT" NAME="nomEquipeLocaleArb" VALUE="">
            Nom equipe visiteur : <INPUT TYPE="TEXT" NAME="nomEquipeVisiteurArb" VALUE="">
            Nom arbitre : <INPUT TYPE="TEXT" NAME="nomArbitreArb" VALUE="">
            Prenom arbitre : <INPUT TYPE="TEXT" NAME="prenomArbitreArb" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="arbitrerMatch" VALUE="arbitrerMatch">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
            11 - Entrer le resultat d’un match
            <BR>
            Date du match : <INPUT TYPE="DATE" NAME="matchDateRes" VALUE="yyyy-MM-dd">
            Heure du match : <INPUT TYPE="TIME" NAME="matchHeureRes" VALUE="hh:mm:ss">
            Nom equipe locale : <INPUT TYPE="TEXT" NAME="nomEquipeLocaleRes" VALUE="">
            Nom equipe visiteur : <INPUT TYPE="TEXT" NAME="nomEquipeVisiteurRes" VALUE="">
            Points local : <INPUT TYPE="TEXT" NAME="pointsLocalRes" VALUE="">
            Points visiteur : <INPUT TYPE="TEXT" NAME="pointsVisiteurRes" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="entrerResultatMatch" VALUE="entrerResultatMatch">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
            12 - Afficher les resultats de tous les matchs a partir d'une date
            <BR>
            Date du match : <INPUT TYPE="DATE " NAME="matchDateAff" VALUE="yyyy-MM-dd">
            <INPUT TYPE="SUBMIT" NAME="afficherResultatDate" VALUE="afficherResultatDate">
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
            12 - Afficher les resultats de tous les matchs ou une equipe a participe
            <BR>
            Nom de l'equipe : <INPUT TYPE="TEXT" NAME="nomEquipeAff" VALUE="">
            <INPUT TYPE="SUBMIT" NAME="afficherResultatEquipe" VALUE="afficherResultatEquipe">
        </FORM>
    </body>
</html>
