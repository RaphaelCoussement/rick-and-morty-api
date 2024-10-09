
# Rick and Morty App

Une application Android qui vous permet d'explorer les personnages de l'univers de Rick and Morty. Vous pouvez consulter une liste de personnages, afficher des informations détaillées sur chacun d'eux, et explorer leurs emplacements.

## Table des matières

- [Architecture du projet](#architecture-du-projet)
- [Fonctionnalités](#fonctionnalités)
- [Dépendances](#dépendances)
- [Installation](#installation)
- [Utilisation](#utilisation)
- [Contribuer](#contribuer)

## Architecture du projet

Le projet utilise une architecture **Clean Architecture** pour séparer les responsabilités et faciliter la maintenance. Voici les principales couches de l'architecture :

1. **app** : Le module principal de l'application.
2. **core** : Contient les éléments essentiels pour le projet, organisé en sous-modules :
   - **domain** : Contient la logique métier, y compris les entités et les contrats.
   - **data** : Gère les interactions avec les données, y compris les implémentations des repositories.
   - **ui** : Contient les composants d'interface utilisateur réutilisables.
3. **features** : Contient les différentes fonctionnalités de l'application. Chaque screen est relié à un ViewModel

## Fonctionnalités

- **Liste des personnages** : Affiche une liste de tous les personnages disponibles.
- **Détails du personnage** : En cliquant sur un personnage, vous pouvez afficher sa localisation et son type sous forme de carte.
- **Exploration des emplacements** : En cliquant sur une localisation, vous pouvez voir tous les personnages associés à cet emplacement, avec une vibration indiquant l'action.

## Récupération des données

- Les données des personnages sont récupérées via une API. Si la base de données locale est vide, l'application appelle l'API pour récupérer les données et les insère dans la base de données locale.
- Les données des emplacements sont récupérées uniquement via des appels API, sans stockage en base de données.

## Dépendances

- **Koin** : Pour l'injection de dépendances.
- **Realm** : Pour la gestion de la base de données locale.

## Installation

Instructions pour cloner le projet et le configurer sur votre machine locale.

```bash
git clone https://github.com/RaphaelCoussement/rick-and-morty-api.git
