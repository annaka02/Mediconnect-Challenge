# 🏥 MediConnect - Gestion médicale intelligente

MediConnect est une application de gestion médicale basée sur le standard FHIR. Elle permet la gestion des patients, des conditions médicales et offre une interface moderne pour les professionnels de santé.

## 🔧 Technologies utilisées

* **Backend** : Java, Spring Boot, HAPI FHIR
* **Base de données** : PostgreSQL
* **Frontend** : Angular 19 + Angular Material
* **Containerisation** : Docker, Docker Compose
* **Standard** : FHIR R5

## 📁 Structure du projet

```
mediconnect/
│
├── patient-api/              # Service FHIR pour la gestion des patients
├── condition-api/            # Service FHIR pour la gestion des conditions médicales
├── frontend/                 # Interface utilisateur Angular
├── docker-compose.yml        # Lancement simultané des services
└── README.md
```

## ⚙️ Lancement rapide (mode développeur)

### 1. Prérequis

* Java 17+
* Node.js 18+
* PostgreSQL
* Docker & Docker Compose

### 2. Clonage du projet

```bash
git clone https://github.com/annaka02/mediconnect-challenge.git
cd mediconnect
```

### 3. Lancer avec Docker

```bash
docker-compose up --build
```

### 4. Accès

| Service         | URL                          |
| --------------- | ---------------------------- |
| Frontend        | `http://localhost:4200`      |
| Patient API     | `http://localhost:8081/fhir` |
| Condition API   | `http://localhost:8082/fhir` |
| PostgreSQL (DB) | `localhost:5432`             |

## 🧪 Exemples d’Endpoints (FHIR)

* `GET /fhir/Patient`
* `POST /fhir/Patient`
* `GET /fhir/Condition?patient=123`
* `PUT /fhir/Condition/{id}`

Tu peux tester les endpoints avec **Postman** 

## 🛆 Base de données

Les identifiants par défaut sont :

```env
POSTGRES_DB=mediconnect_db
POSTGRES_USER=postgres
POSTGRES_PASSWORD=admin123
```

## 📝 TODO

* [x] Implémentation FHIR Patient
* [x] Implémentation FHIR Condition
* [ ] Intégration Authentification (Admin, Médecins)
* [ ] Gestion des consultations
* [ ] Statistiques médicales
* [ ] Documentation complète de l’API

## 👥 Auteur

* Anna Ka (Développeuse Fullstack & Cybersécurité)

## 📜 Licence

Ce projet est développé à des fins pédagogiques et n’est pas destiné à un usage médical réel pour l’instant.
