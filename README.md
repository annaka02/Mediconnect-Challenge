Voici un README complet et clair, prêt à être copié-collé dans ton fichier `README.md` :

```markdown
# Digital Health AfricaGroup - Backend APIs

## Présentation

Ce projet consiste en deux API REST FHIR développées avec Spring Boot pour la gestion des données médicales :  
- **Patient API** : gestion des ressources Patient  
- **Condition API** : gestion des ressources Condition médicale  

Les deux APIs communiquent entre elles via WebClient et sont connectées à des bases de données PostgreSQL distinctes.

---

## Technologies utilisées

- Java 17, Spring Boot 2.7.18  
- HAPI FHIR (versions 5.6.0 / 6.10.0) pour le standard FHIR (HL7)  
- PostgreSQL pour la persistance des données  
- Spring Data JPA pour la gestion des données  
- Spring Security pour la sécurisation basique  
- Spring WebFlux / WebClient pour la communication inter-API  
- Docker & Docker Compose pour la containerisation et orchestration  
- Maven pour la gestion des dépendances et le build  

---

## Architecture

```

PostgreSQL (patientdb) <-- Patient API (port 8080) <---> WebClient ---> Condition API (port 8081) --> PostgreSQL (conditiondb)

````

---

## Installation et lancement

### Prérequis

- Docker & Docker Compose installés  
- Maven installé (optionnel, pour build local)  

### Démarrage

Pour lancer tous les services (APIs + bases de données) :

```bash
docker-compose up --build
````

### Endpoints accessibles

* Patient API : `http://localhost:8080/fhir/Patient`
* Condition API : `http://localhost:8081/fhir/Condition`

---

## Fonctionnalités principales

* **Patient API** : CRUD complet sur les ressources Patient
* **Condition API** : CRUD complet sur les ressources Condition, avec validation liée au Patient via WebClient
* Gestion des erreurs centralisée avec ControllerAdvice
* Sécurisation basique avec Spring Security

---

## Communication entre APIs

La Condition API utilise un client WebClient pour interroger Patient API et récupérer les données Patient associées, permettant ainsi une cohérence des données entre les deux services.

---

## Problèmes rencontrés et solutions

* Alignement des versions HAPI FHIR entre les APIs
* Configuration Hibernate avec PostgreSQL (dialect, connexion)
* Gestion des erreurs personnalisée dans les APIs FHIR
* Containerisation multi-stage Docker optimisée pour build et runtime

---

## Prochaines étapes

* Renforcement de la sécurité (authentification JWT/OAuth)
* Développement du frontend Angular pour le dashboard
* Ajout des tests unitaires et d’intégration automatisés
* Documentation Swagger / OpenAPI plus détaillée

---

## Auteur

Projet développé par Anna Ka dans le cadre du challenge Digital Health AfricaGroup.

---

Merci pour votre lecture et votre confiance !


