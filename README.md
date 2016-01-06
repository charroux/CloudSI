# CloudSI

Page d'acceuil : 

https://github.com/charroux/CloudSI/blob/master/war/index.html

qui permet de saisir un nom ainsi qu'un client (pour faire fonctionner le multi-tenancy).

Quand on poste le formulaire, le code suivant est exécuté côté serveur :

https://github.com/charroux/CloudSI/blob/master/src/com/CloudSIServlet.java

Ce programme :
- récupère le nom du formulaire HTML
- déclenche un traitement en tâche de fond grâce à l'ajout dans la file de tâche de  l'url : urlTacheDeFond
- cette url est associée à du code Java :

  https://github.com/charroux/CloudSI/blob/master/src/com/TacheDeFondServlet.java

  Ce code écrit dans la base de données objet Datastore.
  
  L'association entre l'url et le code java se fait dans le fichier web.xml :
  
  https://github.com/charroux/CloudSI/blob/master/war/WEB-INF/web.xml
  
- affichage d'une page JSP de réponse qui affiche le nom saisi par l'utilisateur :

  https://github.com/charroux/CloudSI/blob/master/war/pageConfirmation.jsp
  
Le multi-tenancy est géré un filtre :

  https://github.com/charroux/CloudSI/blob/master/src/com/MonFiltreMultitenancy.java
  
  qui permet de changer de base de données en fonction du choix du client.
  
  Ce filtre est positionné en XML dans le fichier :
  
 https://github.com/charroux/CloudSI/blob/master/war/WEB-INF/web.xml
  
