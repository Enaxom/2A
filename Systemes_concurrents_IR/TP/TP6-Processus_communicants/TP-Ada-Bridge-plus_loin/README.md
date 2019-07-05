TP Synchronisation en Ada : tournoi de bridge
============================================
L'objectif de ce TP est d'expérimenter différentes implémentations de la tâche gérant 
les accès à une salle de tournoi de bridge, afin de garantir que le nombre de joueurs
présents dans la salle reste toujours un multiple de 4.

Les fournitures et paquetages
-----------------------------

`mkstrategie` : script shell permettant de définir, choisir et compiler une stratégie

`Bridge`  : vide, requis par l'implantation d'Ada utilisée ici (gnat)  
`Bridge.Affic` : tout ce qui concerne l'affichage  
`Bridge.Main` : programme principal  
`Bridge.Simu` : simulateur temporel  
`Bridge.Synchro` : paquetage de redirection vers la stratégie choisie  
`Bridge.Synchro.Vide` : implantation sans contrainte sur l'accès à la salle.
`Bridge.Tasks` : les tâches clientes (joueurs)  

Les paquetages à consulter sont principalement les paquetages `Bridge.Synchro.*`

Au besoin, il est possible de consulter  `Bridge.Tasks` qui contient la défintion du comportement 
des joueurs. Le code est simple, et il peut être utile de jouer (en les
éditant) sur les paramètres qui fixent la fréquence avec laquelle les différentes entrées
sont appelées.

Par curiosité, on peut aussi consulter `Bridge.Main` (qui lance les tâches) et les interfaces 
de `Bridge.Simu` et `Bridge.Affic` mais il n'est pas utile de se pencher sur les détails de 
l'implantation de ces deux derniers paquetages, qui ne présente pas d'intérêt particulier.

Principe de la synchronisation
------------------------------

Comme présenté en cours, deux approches sont possibles :

1 - définir une tâche de synchronisation (par exemple `BridgeTask` dans `Bridge.Synchro.Vide`) qui
possède des entrées ouvertes ou pas selon son état interne. La tâche de synchronisation 
est alors conçue comme un *automate*. L'interface (=
les entrées) et leur implantation peuvent varier selon la stratégie implantée.
Les procédures `Entrer` et `Sortir` permettent de présenter une interface uniforme pour 
les tâches définies dans `Bridge.Tasks`. 

2 - définir une tâche fournissant un *service*, en attente cyclique d'appels (requêtes) sur 
ses différentes entrées. A chaque itération, la tâche traite un appel. Elle reste en attente
s'il n'y a pas de client. La démarche de conception est alors très proche de celle des
 moniteurs : à chaque entrée est associé un traitement, gardé par une condition 
 d'acceptation ; la condition d'acceptation détermine la décision d'entamer (ou non)
  le traitement, en fonction du maintien de la cohérence (ou non) de la ressource gérée 
  par le service.


À Faire
-------

1. Créer un paquetage `bridge-synchro-acceptation.adb` implémentant une version basée sur les conditions
d'acceptation (approche 2).

2. Définir un automate permettant d'éviter une (éventuelle) scrutation au niveau du serveur,  et implanter cet automate (approche 1).

3. Etudier le risque de famine dans les stratégies précédentes, et réfléchir aux moyens d'éviter ce risque, lorsqu'il est présent. 

Définition des stratégies
-------------------------

L'absence d'introspection en Ada, qui aurait par exemple permis de paramétrer `Bridge.Synchro`
 avec le nom des paquetages/stratégies disponibles, alourdit un peu le travail d'édition
 des différents composants.
 
 Le script shell `./mkstrategie` vise à faciliter ce travail d'édition :
 
 - appelé sans paramètres, il permet de recompiler la dernière stratégie "installée" ;
 - avec l'option -i (`./mkstrategie -i`), il permet de créer et installer une nouvelle stratégie,
d'installer une stratégie existante, ou de recompiler la dernière stratégie installée.
 

Pour définir une stratégie (`Xxxx`), il suffit de travailler sur le fichier `bridge-synchro-xxxx.adb`, correspondant à son implantation. Seul le corps de la tâche `BridgeTask`, ainsi que la  fonction `Nom_Strategie` doivent être modifiés. En particulier, les procédures `Entrer` et `Sortir`, 
 qui appellent les entrées correspondantes de la tâche `BridgeTask` n'ont pas à être modifiées (sauf, bien sûr, si l'on souhaite modifier l'interface de la tâche `BridgeTask`). Leur rôle est d'abord d'assurer 
 une modularité, en permettant leur appel (depuis d'autres paquetages) en tant que procédures de paquetages, sans référence à la tâche `BridgeTask`.
     
Il est par ailleurs possible de réaliser les opérations du script `./mkstrategie` à la main. Le détail des opérations est donné en fin de document.

Pour exécuter
-------------

    ./bridge-main 5    (nb joueurs)
    
*Note :* le bouton d'aide de la fenêtre affichée par l'application en présente les fonctionnalités.
    
Annexe
=======
  
Ajouter une nouvelle stratégie
------------------------------

Soit la stratégie `Toto` que l'on souhaite implanter :

- dupliquer `bridge-synchro.ads` dans `bridge-synchro-toto.ads` ;
- éditer `bridge-synchro-toto.ads` pour nommer le paquetage `Bridge.Synchro.Toto` ;

      Toutes les stratégies ont ainsi la même interface, mais des noms différents.
- dans `bridge-synchro.adb`, remplacer `Bridge.Synchro.Vide` par `Bridge.Synchro.Toto` 
    (deux emplacements marqués par `XXXX`) ;
    
      Cette manipulation (pas vraiment élégante...) vise à pallier l'absence
       d'introspection en Ada, qui aurait ici permis de paramétrer `Bridge.Synchro`.
	 avec le nom des paquetages/stratégies disponibles.
- écrire l'implantation du paquetage `Bridge.Synchro.Toto` 
    dans le fichier `bridge-synchro-toto.adb` en s'inspirant d'une stratégie déjà existante.
    

Pour compiler
-------------

    /usr/bin/gnatmake bridge-main `gtkada-config`
