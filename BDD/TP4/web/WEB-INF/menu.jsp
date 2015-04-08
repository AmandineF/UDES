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
        <link href="style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <H2>Menu principal</H2>   
        <FORM ACTION="RequetesEquipe" METHOD="POST">
        	<fieldset>
            <H3>Creer une equipe</H3>
            <INPUT  placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeAdd" VALUE=""><br>
            <INPUT placeholder="Nom du terrain"  TYPE="TEXT" NAME="nomTerrainAdd" VALUE=""><br>
            <INPUT placeholder="Adresse du terrain"  TYPE="TEXT" NAME="adresseTerrainAdd" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="creerEquipe" VALUE="Valider">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesEquipe" METHOD="POST">
        <fieldset>
        	<H3>Affichage des equipes</H3>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="afficherEquipes" VALUE="Valider">
        </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesEquipe" METHOD="POST">
            <fieldset>
            <H3>Supprimer une equipe</H3>
            <INPUT placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeSup" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Valider">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
        	<fieldset>
            <H3>Creer un joueur</H3>
            <INPUT placeholder="Nom du joueur" TYPE="TEXT" NAME="nomJoueurAdd" VALUE="">
            <INPUT placeholder="Prenom du joueur" TYPE="TEXT" NAME="prenomJoueurAdd" VALUE="">
            <INPUT placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeAdd" VALUE="">
            <INPUT placeholder="Numero du joueur" TYPE="TEXT" NAME="numeroJoueurAdd" VALUE="">
            <INPUT placeholder="debut : yyyy-MM-dd" TYPE="DATE" NAME="dateDebutAdd"><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="creerJoueur" VALUE="Valider">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
        	<fieldset>
            <h3>Afficher les joueurs d'une equipe</h3>
            <INPUT placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeAff" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="afficherJoueurEquipe" VALUE="Afficher">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesJoueur" METHOD="POST">
        	<fieldset>
            <h3>Supprimer un joueur</h3>
            <INPUT placeholder="Nom du joueur" TYPE="TEXT" NAME="nomJoueurSup" VALUE="">
            <INPUT placeholder="Prenom du joueur" TYPE="TEXT" NAME="prenomJoueurSup" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="supprimerJoueur" VALUE="Valider">
            </fieldset>
        </FORM> 
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
        	<fieldset>
            <h3>Creer un match<h3>
            <INPUT placeholder="Date : yyyy-MM-dd" TYPE="DATE" NAME="matchDateAdd" VALUE="">
            <INPUT placeholder="Heure : hh:mm:ss" TYPE="TIME" NAME="matchHeureAdd" VALUE="">
            <INPUT placeholder="Equipe locale" TYPE="TEXT" NAME="nomEquipeLocaleAdd" VALUE="">
            <INPUT placeholder="Equipe visiteur" TYPE="TEXT" NAME="nomEquipeVisiteurAdd" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="creerMatch" VALUE="Valider">
            </fieldset>
        </FORM>  
        <BR>
        <BR>
        <FORM ACTION="RequetesArbitre" METHOD="POST">
        	<fieldset>
            <h3>Creer un arbitre</h3>
            <INPUT placeholder="Nom de l'arbitre" TYPE="TEXT" NAME="nomArbitre" VALUE="">
            <INPUT placeholder="Prenom de l'arbitre" TYPE="TEXT" NAME="prenomArbitre" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="creerArbitre" VALUE="Valider">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesArbitre" METHOD="POST">
        	<fieldset>
            <h3>Afficher les arbitres</h3>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="afficherArbitres" VALUE="Afficher">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesArbitrer" METHOD="POST">
        	<fieldset>
            <h3>Affecter un arbitre a un match</h3>
            <INPUT placeholder="Date : yyyy-MM-dd" TYPE="DATE" NAME="matchDateArb" VALUE="">
            <INPUT placeholder="Heure : hh:mm:ss" TYPE="TIME" NAME="matchHeureArb" VALUE="">
            <INPUT placeholder="Equipe locale" TYPE="TEXT" NAME="nomEquipeLocaleArb" VALUE="">
            <INPUT placeholder="Equipe visiteur" TYPE="TEXT" NAME="nomEquipeVisiteurArb" VALUE="">
            <INPUT placeholder="Nom de l'arbitre" TYPE="TEXT" NAME="nomArbitreArb" VALUE="">
            <INPUT placeholder="Prenom de l'arbitre" TYPE="TEXT" NAME="prenomArbitreArb" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="arbitrerMatch" VALUE="Valider">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
        	<fieldset>
            <h3>Entrer le resultat d’un match</h3>
            <INPUT placeholder="Date : yyyy-MM-dd" TYPE="DATE" NAME="matchDateRes" VALUE="">
            <INPUT placeholder="Heure : hh:mm:ss" TYPE="TIME" NAME="matchHeureRes" VALUE="">
            <INPUT placeholder="Equipe locale" TYPE="TEXT" NAME="nomEquipeLocaleRes" VALUE="">
            <INPUT placeholder="Equipe visiteur" TYPE="TEXT" NAME="nomEquipeVisiteurRes" VALUE="">
            <INPUT placeholder="Points local" TYPE="TEXT" NAME="pointsLocalRes" VALUE="">
            <INPUT placeholder="Points visiteur" TYPE="TEXT" NAME="pointsVisiteurRes" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="entrerResultatMatch" VALUE="Valider">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
        	<fieldset>
            <h3>Afficher les resultats de tous les matchs a partir d'une date</h3>
            <INPUT placeholder="Date : yyyy-MM-dd" TYPE="DATE" NAME="matchDateAff" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="afficherResultatDate" VALUE="Afficher">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="RequetesMatch" METHOD="POST">
        	<fieldset>
            <h3>Afficher les resultats de tous les matchs d'une equipe</h3>
            <INPUT placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeAff" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="afficherResultatEquipe" VALUE="Afficher">
            </fieldset>
        </FORM>
       	<BR>
        <BR>
        <FORM ACTION="ExportationXML" METHOD="POST">
        <fieldset>
            <h3>Exportation XML d'une equipe</h3>
            <INPUT placeholder="Nom de l'equipe" TYPE="TEXT" NAME="nomEquipeXML" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="exporter" VALUE="Exporter">
            </fieldset>
        </FORM>
        <BR>
        <BR>
        <FORM ACTION="ImportationXML" METHOD="POST">
        <fieldset>
            <h3>Importation XML d'une equipe</h3>
            <BR>
            <INPUT placeholder="Nom du fichier" TYPE="TEXT" NAME="nomFichierXML" VALUE=""><br><br>
            <INPUT class="bouton" TYPE="SUBMIT" NAME="importer" VALUE="Importer">
           </fieldset>
        </FORM>
        <br><br>
             <div style="text-align:center;">
	        <a href="Logout" ><INPUT class="bouton2" style="width:150px" TYPE="SUBMIT" NAME="supprimerEquipe" VALUE="Deconnexion"></a>
	        </div>
             <br><br>
    </body>
</html>
