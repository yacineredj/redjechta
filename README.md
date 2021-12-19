
# Generate Private Key

Je parle ici de certificat auto-généré et par conséquent non-vérifié par une autorité de certification. C'est une approche tout à fait valable pour des tests mais non recommandée en production.

Un fichier .p12 combine à la fois un certificat et une clé privé. Il faut donc commencer par générer ces 2 éléments:


$ openssl req -x509 -newkey rsa:3072 -keyout rsa_private.pem -nodes -out rsa_cert.pem -subj "/CN=unsused"

Cela génère 2 fichiers:

    rsa_private.pem: la clé privée
    rsa_cert.pem: le certificat

Il ne reste plus qu'à combiner les 2 dans un seul ficher .p12 avec la commande suivante:

$ openssl pkcs12 -inkey rsa_private.pem -in rsa_cert.pem -export -out server.p12 -name episen


Une fois le fichier server.p12 générer on peut le vérifier avec la commande:

$ openssl pkcs12 -in server.p12 -noout -info


###### Docker 

###### Docker

- Pour commencer nous allons d'abord commencer par créer le jar de notre RESTAPI via la commande suivante :

    - MVN CLEAN INSTALL

- Une fois le jar généré nous allons devoir créer une image Docker de notre RESTAPI qu'on va utiliser plus tard dans le container :

    - docker build -t app.jar .

###### A noter que le Build va se faire suivant le dockerFile qui spécifie le chemin vers le Jar ainsi que le nouveau nom mais aussi la commande pour copier le fichier .p12 sur le container ou sera deployé notre API et la commande pour l'éxecution du Jar



- Pour repondre à la problématique du projet qui est de conteneuriser l'API et le service de BDD nous allons utiliser le principe de NETWORK dans docker. nous allons d'abord créer un network via la commande suivante et ensuite mettre les containers dans le network pour leur permettre de communiquer entre eux :

    - docker network create projetdocker

- Nous pouvons voir le network que nous avons créé via la commande suivante :

    - docker network ls

- Nous pouvons également consulter les images que nous avons créé via la commande suivante :

    - docker images

- Nous allons maintenant lancer un container qui va utiliser une image de postgres (la derniere) et qui va s'appeler postgres, on s'assure que le port 5432 est bien ouvert coté machine et coté container et nous passons également des variables d'environnemet pour définir le user, le mdp ainsi que la bdd, ces variables sont bien sur à spécifier selon les besoins de l'utilisateur. nous spécifions également le network dans lequel va se trouver notre container :

    - docker container run --name postgres --network projetdocker -p 5432:5432 -e POSTGRES_PASSWORD=admin -e POSTGRES_USER=postgres -e POSTGRES_DB=postgres -d postgres

- Nous allons également lancer un container avec l'image de notre API et qui va sa trouver dans le meme network que le container avec la bdd. Nous nous assurons de passer les variables d'environnement nécessaires pour Spring pour que la REST API soit bien connecté à la BDD on ouvre également le port du container qui sera utiliser pour pointer vers l'API et enfin nous appelons le container rest-api-container. la commande est la suivante :

    - container run --network projetdocker --name rest-api-container -p 8082:8082 -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/postgres -e SPRING_DATASOURCE_USERNAME=postgres -e SPRING_DATASOURCE_PASSWORD=admin -e SPRING_JPA_HIBERNATE_DDL_AUTO=update -d app.jar


##### Test Rest API

