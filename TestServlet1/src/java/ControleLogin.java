// ==========================================================================
// ControleLogin.java : servlet du projet testServlet1
// --------------------------------------------------------------------------
// Recuperation des parametres saisis sur l'ecran index.jsp
// ==========================================================================

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControleLogin extends HttpServlet
{
   protected void executeRequete(HttpServletRequest requete,
                                 HttpServletResponse reponse)
                                 throws ServletException,
                                        IOException
   {
      String nomUtil;
      String motPasseUtil;
      String choixCase;

      String affiche;
      
      String test;

// --------------------------------------------------------------------------
// Remarques sur le codage
// --------------------------------------------------------------------------
// Dans la jsp index.jsp, on a choisi l'encodage utf-8, pour pouvoir utiliser
// un caractère spécial : '€', qui n'est pas dans la norme iso-8859-1, en
// affichage comme en saisie. Il faut donc donner a la requete, en
// particulier a la methode getParameter(), le renseignement qui lui permet
// d'interpreter correctement les caracteres saisis. De meme, il faut
// le codage utilise dans la reponse. Le type de la reponse (setContentType)
// doit etre indique avant l'obtention du flux (getWriter). A defaut de tous
// ces renseignements, tous les textes sont codes en iso-8859-1...
// --------------------------------------------------------------------------
// Quand on choisit l'encodage iso-8859-1 dans index.jsp, la saisie et le
// reaffichage du caractere € marche. Il semble que les navigateurs IE et
// Firefox s'accommodent de ce caractere, certainement parce qu'il est prevu
// par la norme windows-1252, ou il est code 80. Par contre, le caractere €
// dans le code de la jsp n'est pas interprete correctement.
// --------------------------------------------------------------------------
// Bien entendu, le requete.setCharacterEncoding doit etre fait avant la
// lecture des parametres (getParameter).
// --------------------------------------------------------------------------
      requete.setCharacterEncoding("utf-8");
      reponse.setContentType("text/html;charset=utf-8");
      PrintWriter sortie = reponse.getWriter();

      try
      {
         nomUtil = requete.getParameter("nomUtil");
         motPasseUtil = requete.getParameter("motPasseUtil");
         choixCase = requete.getParameter("choixAcces");

         affiche =     "<body>";
         affiche +=       "<p>";
         affiche +=          "Merci, " + nomUtil + " " + motPasseUtil + ".";
         affiche +=       "</p>";
         affiche +=       "<p>";
         affiche +=          "Vous êtes maintenant enregistré dans le système.";
         affiche +=       "</p>";
         affiche +=       "<p>";
         affiche +=          "Vous avez choisi l'accès en " + choixCase + ".";
         affiche +=       "</p>";
         affiche +=     "</body>";
         affiche +=  "</html>";

         sortie.println(entete("Résultats") + affiche);
      }
      finally
      {
         sortie.close();
      }
   }

// --------------------------------------------------------------------------
// Constitution de l'entete du programme html
// --------------------------------------------------------------------------
public static String entete(String titre)
   {
      String entete ="";
      entete = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" ";
      entete += "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";

      entete += "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"fr\">";
      entete +=    "<head>";
      entete +=       "<title>" + titre + "</title>";
      entete +=       "<meta http-equiv=\"Content-Type\" ";
      entete +=             "content=\"text/html; charset=utf-8\" />";
      entete +=       "<link rel=\"stylesheet\" ";
      entete +=             "type=\"text/css\" ";
      entete +=             "href=\"miseEnPage.css\" />";
      entete +=    "</head>";

      return entete;
   }

   @Override
   protected void doGet(HttpServletRequest requete,
                        HttpServletResponse reponse)
                        throws ServletException,
                        IOException
   {
      executeRequete(requete, reponse);
   }

   @Override
   protected void doPost(HttpServletRequest requete,
                         HttpServletResponse reponse)
                         throws ServletException,
                         IOException
   {
      executeRequete(requete, reponse);
   }
}
