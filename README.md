# WeGrow

API REST de gestion des départements et des employés, développée avec Java 17, Spring MVC, Spring Data JPA, Hibernate et PostgreSQL.

## Fonctionnalités

- Créer un département.
- Lister les départements avec le nombre d'employés associés.
- Supprimer un département uniquement s'il ne contient aucun employé.
- Ajouter un employé à un département depuis la couche métier.
- Lister les employés d'un département depuis la couche métier.
- Vérifier l'unicité du nom d'un département et du matricule d'un employé.

## Technologies

| Technologie | Version |
| --- | --- |
| Java | 17 |
| Spring MVC | 6.1.4 |
| Spring Data JPA | 3.2.3 |
| Hibernate | 6.4.4.Final |
| PostgreSQL JDBC | 42.7.2 |
| Jakarta Servlet | 6.0.0 |
| Maven | Packaging WAR |
| Serveur recommandé | Tomcat 10.1+ |

## Prérequis

- JDK 17 ou supérieur.
- Maven 3.8 ou supérieur.
- PostgreSQL.
- Tomcat 10.1 ou un serveur compatible Jakarta EE 10.

## Installation

### 1. Cloner le projet

```bash
git clone <URL_DU_DEPOT>
cd WeGrow
```

### 2. Créer la base PostgreSQL

```sql
CREATE DATABASE wegrow_db;
```

La configuration actuelle utilise les paramètres suivants :

| Paramètre | Valeur |
| --- | --- |
| Hôte | `localhost` |
| Port | `5432` |
| Base | `wegrow_db` |
| Utilisateur | `postgres` |
| Mot de passe | À remplacer dans la configuration Spring |

Ne pas conserver un mot de passe réel dans le dépôt. Pour un environnement de production, utiliser une configuration externe ou des variables d'environnement.

### 3. Vérifier la configuration Spring

La configuration XML doit pointer vers les packages du projet :

- scan des composants : `com.wegrow`
- entités JPA : `com.wegrow.entity`
- repositories : `com.wegrow.repository`

Le fichier `web.xml` référence actuellement `applicationContext.xml`, alors que le fichier présent dans le projet est `application.xml`. Ces deux noms doivent être alignés avant le déploiement.

### 4. Construire l'application

```bash
mvn clean package
```

Le fichier généré se trouve dans :

```text
target/WeGrow-1.0-SNAPSHOT.war
```

### 5. Déployer sur Tomcat

Copier le fichier WAR dans le répertoire `webapps` de Tomcat, puis démarrer le serveur. Avec le nom de fichier actuel, l'API sera disponible sous le contexte :

```text
http://localhost:8080/WeGrow-1.0-SNAPSHOT
```

## API REST

### Départements

#### Créer un département

```http
POST /api/departements
Content-Type: application/json
```

Exemple de requête :

```json
{
  "nom": "Ressources humaines",
  "description": "Gestion des collaborateurs"
}
```

Réponse : `201 Created`

#### Lister les départements

```http
GET /api/departements
```

Exemple de réponse :

```json
[
  {
    "id": 1,
    "nom": "Ressources humaines",
    "description": "Gestion des collaborateurs",
    "nombreEmployes": 3
  }
]
```

Réponse : `200 OK`

#### Supprimer un département

```http
DELETE /api/departements/{id}
```

Réponses possibles :

- `204 No Content` si la suppression réussit.
- `404 Not Found` si le département n'existe pas.
- `409 Conflict` si le département contient encore des employés.

## Modèle de données

### Département

- `id` : identifiant généré automatiquement.
- `nom` : obligatoire.
- `description` : obligatoire.
- `employes` : relation `OneToMany` vers les employés.

### Employé

- `id` : identifiant généré automatiquement.
- `matricule` : obligatoire et unique.
- `nom` : obligatoire.
- `prenom` : obligatoire.
- `contrat` : `CDI`, `CDD` ou `FREELANCE`.
- `departement` : département obligatoire de l'employé.

Les services `EmployeService` et `EmployeServiceImpl` prennent en charge l'ajout et la recherche d'employés par département. Aucun contrôleur REST d'employé n'est actuellement présent dans le projet.

## Structure du projet

```text
src/main/java/com/wegrow/
├── controller/    # Contrôleurs REST
├── dto/           # Objets de réponse API
├── entity/        # Entités JPA et enum Contrat
├── exeption/      # Exceptions métier
├── Mapper/        # Conversion entités -> DTO
├── repository/    # Interfaces Spring Data JPA
└── service/       # Interfaces et implémentations métier

src/main/webapp/WEB-INF/
├── application.xml # Configuration Spring et JPA
└── web.xml         # Configuration de l'application web
```

## Configuration de la base de données

Hibernate est configuré avec `hibernate.hbm2ddl.auto=update`. Le schéma est donc mis à jour automatiquement au démarrage à partir des entités JPA. Ce réglage est pratique en développement, mais il est recommandé d'utiliser des migrations versionnées et une stratégie plus restrictive en production.

## Tests et qualité

Le projet ne contient pas encore de tests automatisés. La commande de compilation et de packaging peut être utilisée comme vérification de base :

```bash
mvn clean package
```

## Licence

Aucune licence n'est actuellement définie pour ce projet.
