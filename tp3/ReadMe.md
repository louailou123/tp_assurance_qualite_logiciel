# TP3 – Tests d'intégration avec Testcontainers

## Objectif

Réécrire les tests unitaires du TP2 (basés sur Mockito) en **tests d'intégration** utilisant **Testcontainers** avec un conteneur **MySQL 8.0** réel.

## Prérequis

- **Docker** doit être installé et en cours d'exécution sur la machine.
- **Java 8+** et **Maven** configurés.

---

## Exercice 1 – UserService (réécriture du TP2 Exercice 1)

### Scénarios couverts (correspondance TP2 → TP3)

| # | Scénario TP2 (Mockito) | Scénario TP3 (Testcontainers) | Statut |
|---|------------------------|-------------------------------|--------|
| 1 | `shouldReturnUserWhenIdExists` – Vérifie qu'un utilisateur est retourné quand l'ID existe (mock) | `shouldSaveAndRetrieveUser` – Sauvegarde un utilisateur dans MySQL et le récupère par ID | ✅ Couvert |

### Scénarios ajoutés (nouveaux dans TP3)

| # | Nouveau scénario | Description |
|---|-----------------|-------------|
| 1 | `shouldReturnNullWhenUserNotFound` | Vérifie que `null` est retourné quand l'ID n'existe pas dans la base |
| 2 | `shouldDeleteUser` | Vérifie la suppression d'un utilisateur dans MySQL |
| 3 | `shouldThrowExceptionWhenUserIsNull` | Vérifie qu'une exception est levée lors de la création d'un utilisateur `null` |
| 4 | `shouldSaveMultipleUsersAndRetrieveEach` | Vérifie l'insertion et la récupération de plusieurs utilisateurs |

> **Justification :** Ces scénarios supplémentaires testent les opérations CRUD complètes (Create, Read, Delete) contre une vraie base de données, ce qui est l'avantage principal des tests d'intégration par rapport aux mocks.

---

## Exercice 2 – TaskService (inspiré du projet `rengreen/task-manager`)

L'exercice 2 implémente un gestionnaire de tâches inspiré du projet open-source [rengreen/task-manager](https://github.com/rengreen/task-manager) et fournit des tests d'intégration complets avec Testcontainers.

### Scénarios de test

| # | Scénario | Description |
|---|----------|-------------|
| 1 | `testCreateTask` | Crée une tâche et vérifie sa persistance dans MySQL |
| 2 | `testCreateNullTask` | Vérifie qu'une exception est levée pour une tâche `null` |
| 3 | `testCreateTaskWithEmptyName` | Vérifie qu'une exception est levée si le nom de la tâche est vide |
| 4 | `testGetTask` | Récupère une tâche existante par son ID |
| 5 | `testGetTaskNotFound` | Vérifie qu'un `Optional.empty()` est retourné pour un ID inexistant |
| 6 | `testFindAllTasks` | Récupère toutes les tâches depuis MySQL |
| 7 | `testFindAllTasksEmpty` | Vérifie qu'une liste vide est retournée quand aucune tâche n'existe |
| 8 | `testDeleteTask` | Supprime une tâche et vérifie qu'elle n'existe plus |
| 9 | `testDeleteNonExistentTask` | Vérifie qu'aucune exception n'est levée lors de la suppression d'une tâche inexistante |
| 10 | `testToggleTaskCompleted` | Bascule le statut d'une tâche de `false` à `true` |
| 11 | `testToggleTaskCompletedTwice` | Double bascule pour revenir à `false` |
| 12 | `testToggleNonExistentTask` | Vérifie qu'une exception est levée pour le toggle d'une tâche inexistante |

---

## Structure du projet

```
tp3/
├── pom.xml
├── ReadMe.md
├── src/main/java/tpaql/
│   ├── exercise1/
│   │   ├── User.java
│   │   ├── UserRepository.java
│   │   ├── UserRepositoryImpl.java      (implémentation JDBC)
│   │   └── UserService.java
│   └── exercise2/
│       ├── Task.java
│       ├── TaskRepository.java
│       ├── TaskRepositoryImpl.java      (implémentation JDBC)
│       └── TaskService.java
└── src/test/java/tpaql/
    ├── exercise1/
    │   └── UserServiceIntegrationTest.java   (5 tests – MySQL container)
    └── exercise2/
        └── TaskServiceIntegrationTest.java   (12 tests – MySQL container)
```

## Comparaison Mockito vs Testcontainers

| Critère | TP2 (Mockito) | TP3 (Testcontainers) |
|---------|--------------|---------------------|
| Base de données | Simulée (mock) | Réelle (MySQL 8.0 dans Docker) |
| Dépendances externes | Aucune | Docker requis |
| Vitesse d'exécution | Très rapide (~ms) | Plus lent (~secondes, démarrage du conteneur) |
| Fiabilité | Teste le comportement du code uniquement | Teste le code + les requêtes SQL + la base |
| Détection de bugs SQL | ❌ Non | ✅ Oui |
| Isolation | Complète (mock isolé) | Complète (conteneur éphémère) |
| Cas d'usage | Tests unitaires | Tests d'intégration |

## Exécution des tests

```bash
cd tp3
mvn test
```

> ⚠️ Docker doit être démarré avant l'exécution des tests.
