# Projet Openclassrooms n°6 : Créez un site communautaire autours de l'escalade

## Présentation du projet

Les amis de l'escalade est une association qui réunit les passionnés d'escalade dans toute la France. 
Ils souhaitent donc créer un site communautaire autours de cette discipline. Ce site aura pour but la mise en
relation et le partage d'informations. Il permettra de donner de la visibilité à l'association afin d'encourager des 
grimpeurs indépendants à y adhérer. 

### Fonctionnalités : 

* Un utilisateur doit pouvoir consulter les informations des sites d'escalades (secteurs, voies, longueurs, etc.).
* Un utilisateur doit pouvoir faire une recherche à l’aide de plusieurs critères pour trouver un site de grimpe et 
  consulter le résultat de cette recherche. Les critères peuvent être le lieu, la cotation, le nombre de
  secteurs, etc.
* Un utilisateur doit pouvoir s’inscrire gratuitement sur le site.
* Un utilisateur connecté doit pouvoir partager des informations sur un site d’escalade (secteurs, voies, longueurs, etc.).
* Un utilisateur connecté doit pouvoir laisser des commentaires sur les pages des sites d’escalade.
* Un membre de l'association doit pouvoir taguer un site d’escalade enregistré sur la plateforme comme
 « Officiel Les amis de l’escalade ».
 * Un membre de l'association doit pouvoir modifier et supprimer un commentaire.
 * Un utilisateur connecté doit pouvoir enregistrer dans son espace personnel les topos qu’ils possèdent et préciser 
 si ces derniers sont disponibles pour être prêtés ou non. Un topo est défini par un nom, une description, un lieu et 
 une date de parution.
 * Un utilisateur connecté doit pouvoir consulter la liste des topos disponibles par d’autres utilisateurs et faire une 
 demande de réservation. La réservation n’inclut pas les notions de date de début et date de fin.
* Un utilisateur connecté doit pouvoir accepter une demande de réservation. Si une réservation est acceptée, 
automatiquement le topo devient indisponible. L’utilisateur connecté pourra ensuite de nouveau changer le statut du 
topo en « disponible ». La plateforme se contente de mettre en contact les deux parties pour le prêt d’un topo 
(par échange de coordonnées).

### Contraintes fonctionnelles

* Le vocabulaire de l'escalade doit être utilisé : site, spot, voie, longueur, grimpeur, etc.
* Le site doit être responsive.
* Le site doit être sécurisé.
* Un SGBD est utilisé et les données sont persistées dans la base de données. 
* Utilisation d'un ORM
* Build Maven
* Application développée en utilisant JEE

## Partie BACK-END
La partie Back consiste en l'implémentation de web services pour requêter la base de données (CRUD).

* Java 11
* Spring boot 2.2.4
* Spring boot starter web 2.2.4
* Spring boot starter data jpa 2.2.4
* Spring boot starter security 2.2.4
* MySQL server 8.0.19
* Project Lombok
* Mapstruct 1.2.0.Final
* Swagger2 et Swagger ui 2.9.2
* Querydsl

## Déploiement

### Déploiement en local

* Installer Java 11 :
    - Télécharger le JDK de Java 11 : [Lien Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) / [Lien OpenJdk](http://jdk.java.net/archive/)
    - Définir la variable d'environnement `JAVA_HOME` et ajouter les binaires du JDK au `PATH`
* Installer Maven : 
    - [Lien Maven](https://maven.apache.org/download.cgi)
    - Définir la variable d'environnement `MAVEN_HOME` et ajouter les binaires de Maven au `PATJH`
* Clôner le projet
* Lancer la commande `mvn clean install` à la racine du projet
* Importer le dump de la base de données Mysql (répertoire "dump" à la racine du projet)
* Modifier le fichier "src/resources/application.properties" pour changer le port de la base de données s'il est 
différent
* Lancer l'application avec la commande `mvn spring-boot:run`

Si vous souhaitez packager l'application sous forme de jar et l'exécuter : 
* Lancer la commande `mvn package -Pprod`
* Le jar se trouvera ensuite dans le dossier "target" à la racine du projet
* Pour exécuter le jar, lancer la commande `java -jar ` suivi du chemin vers le jar. 
Exemple : `java -jar target/escalade-1.0.0.jar`

### Déploiement sur un serveur
 
  * Modifier la balise `<packaging>` du pom pour avoir : `<packaging>war</packaging>`
  * Executer la commande `mvn clean package`
  * Installer Tomcat : [lien](https://tomcat.apache.org/download-90.cgi)
  * Copier l'archive war de l'application depuis le dossier `target` dans le dossier `webapps` du répertoire Tomcat
  * Lancer le serveur Tomcat

## Lancement de l'application
    mvn spring-boot:run
Application disponible à l'adresse suivante : [http://localhost:8080](http://localhost:8080)

Deux utilisateurs sont déjà enregistrés : 
* username : admin / password : admin => rôle Admin
* username : user1 / password : user1 => rôle User

/!\ Ne pas modifier le mot de passe dans la base de données car ils sont encodés avant d'être enregistrés dans la base
