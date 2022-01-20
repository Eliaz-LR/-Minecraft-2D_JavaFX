# Diagramme de classe
## Patrons 


 Nous avons utilisé plusieurs patrons de conception pour faire de projet de jeu. Tout d'abord nous avons le patron du **Singleton**, ce dernier étant symbolisé dans notre projet par la classe GameManager. L'objectif était d'avoir une classe ayant toute les informations dont il aurait été nécessaire de connaitre la valeur pour d'autre classe. En en faisant un Singleton, cela permet d'avoir une seule instance de GameManager et ainsi les autres classes du modèle utilisent des valeurs cohérentes les unes avec les autres et l'on évite l'instanciation d'autre GameManager.

 Ensuite nous avons le patron **Observateur**, ce patron permet d'avertir notre classe Time qu'une unité de temps est passé (dans notre cas un tour de boucle). Ainsi Time peut afficher le temps passé en jeu. Pour ce faire Time implémente l'interface *InvalidationListener* qui lui permet d'être averti du changement d'état d'un objet qu'il observe. Ensuite il faut implémenter l'interface *Observable* sur notre objet que l'on veut observer, donc GameManager. Quand cela est fait, lors d'un changement GameManager peut notifier tous ses observateurs, dont Time, de son changement d'état.


