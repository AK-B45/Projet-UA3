# Documentation technique – Partie Yacine

---

## 1. Modélisation du système

### Objectif

Réaliser la conception complète du système avant l’implémentation, en définissant les classes, packages et relations du domaine métier.

---

### Travaux réalisés

- Conception de l’architecture globale du projet
- Définition de l’organisation en packages :
  - model
  - io
  - mapper
  - repository
  - service
  - validation
- Définition des responsabilités de chaque package
- Conception des classes principales :
  - Etudiant
  - Note
  - Matiere
- Définition des relations entre les classes :
  - Etudiant → liste de Note
  - Note → Matiere
- Structuration du flux global :
  lecture → validation → mapping → traitement → tri → écriture

---

### Lien avec les principes SOLID

La modélisation constitue la base permettant l’application des principes SOLID :

- **SRP (Single Responsibility Principle)** : séparation des responsabilités dès la conception (model, service, io, etc.)
- **OCP (Open/Closed Principle)** : architecture pensée pour permettre l’ajout de nouveaux formats sans modifier le cœur du système
- **DIP (Dependency Inversion Principle)** : introduction d’interfaces pour découpler lecture et écriture
- **ISP (Interface Segregation Principle)** : séparation des interfaces DataReader / DataWriter
- **LSP (Liskov Substitution Principle)** : possibilité de remplacer une implémentation (ex: CSVReader) par une autre sans modifier le reste du système

---

### Rôle de la modélisation

- Structurer le système avant implémentation
- Réduire les dépendances entre composants
- Préparer une architecture conforme aux principes SOLID
- Faciliter l’évolution et la maintenance du projet

---

## 2. CSVReader.java + DataReader.java

### Objectif

Lire les données CSV et produire des objets métier exploitables.

---

### DataReader.java

- Définition d’un contrat de lecture
- Méthode :
  List<Etudiant> lire(String fichier)
- Permet abstraction du format de données
- Supporte extensibilité (CSV, JSON, base de données)

---

### CSVReader.java

- Lecture du fichier ligne par ligne
- Extraction de l’en-tête CSV
- Délégation des responsabilités :
  - validation → CSVValidator
  - transformation → EtudiantMapper
- Retour d’une liste d’Etudiant

---

### Gestion des erreurs

- Gestion des erreurs de lecture (IOException)
- Données invalides gérées en validation/mapping
- Aucune logique métier dans la classe

---

## 3. CSVValidator.java

### Objectif

Valider la structure du fichier CSV avant traitement.

---

### Travaux réalisés

- Vérification des colonnes obligatoires (id, nom)
- Vérification des matières présentes
- Contrôle de cohérence avec MatiereRepository

---

### Rôle

- Garantir la validité des données en entrée
- Isoler la logique de validation
- Améliorer la robustesse du système

---

## 4. EtudiantMapper.java

### Objectif

Transformer les données CSV en objets métier.

---

### Travaux réalisés

- Conversion des lignes CSV en objets Etudiant
- Création des objets Note
- Association avec MatiereRepository
- Filtrage des données invalides :
  - valeurs non numériques
  - notes hors intervalle [0,20]
  - matières inconnues

---

### Rôle

- Séparer transformation et lecture
- Isoler la logique de mapping
- Faciliter l’évolution vers d’autres formats

---

## 5. Etudiant.java

### Objectif

Représenter un étudiant comme entité métier.

---

### Travaux réalisés

- Définition des attributs :
  - id
  - nom
  - notes
  - moyenne
  - mention
- Stockage des résultats calculés

---

### Rôle

- Entité métier simple (POJO)
- Aucune logique de calcul interne
- Les calculs sont effectués par :
  - MoyenneService
  - MentionService

---

## 6. GestionNotes.java

### Objectif

Gérer le classement des étudiants.

---

### Travaux réalisés

- Tri des étudiants par moyenne décroissante
- Implémentation du classement final

---

## 7. Services métier

### MoyenneService
- Calcul de la moyenne pondérée
- Application des coefficients des matières

---

### MentionService
- Attribution des mentions selon la moyenne :
  - Très bien
  - Bien
  - Assez bien
  - Passable
  - Insuffisant

---

## 8. Rôle global de la contribution

Cette partie couvre :

- la conception complète du système (modélisation)
- la lecture des données CSV
- la validation des données
- la transformation en objets métier
- la définition des règles métier (calculs et tri)

---

## 9. Conclusion

L’ensemble de cette contribution permet :

- une architecture modulaire et claire
- une séparation stricte des responsabilités
- une application des principes SOLID
- une base extensible pour évolution future du système