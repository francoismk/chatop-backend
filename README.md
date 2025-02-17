# Chatop-backend
Backend API for Châtop app

## Table des Matières

- [Installation](#installation)
- [Installation de la Base de Données](#installation-de-la-base-de-données)
- [Utilisation](#utilisation)
- [Documentation Swagger](#documentation-swagger)

## Installation

### Prérequis

- Java 11 ou supérieur
- Maven
- Git

### Procédure pas à pas

1. **Cloner le dépôt** :
   ```bash
   
   git@github.com:francoismk/chatop-backend.git
   cd chatop-backend
    ```
2. **Installer le projet** :
    ```bash
    mvn clean install
    ```
   
3. **Configurer les variables d'environnement** :
    
- Créez un fichier .env à la racine du projet et ajoutez les variables d'environnement nécessaires.

4. **Lancer le projet** :
    ```bash
    mvn spring-boot:run
    ```
   
## Installation de la Base de Données

### Prérequis

1. **SGBD** :

Téléchargez et installez un SGBD de votre choix.

2. **Créer une base de données** :

Récupérez le script SQL de création de la base de données dans le dossier `resources` du front et exécutez-le dans votre SGBD.

3. **Configurer les variables d'environnement** :

Les variables d'environnements sont pré-configurées avec le fichier.env

## Utilisation

### Utilisation de l'API

Une fois lancée, l'API est accessible à l'adresse `http://localhost:3001`.

### Utilisation de SWAGGER

Swagger est accessible à l'adresse `http://localhost:3001/swagger-ui.html`.

