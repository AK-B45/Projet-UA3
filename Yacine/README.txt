# Documentation technique – Partie Yacine

## 1. CSVReader.java + DataReader.java

### Objectif

Ces classes permettent de lire un fichier CSV contenant les données des étudiants et de les transformer en objets métier exploitables, tout en respectant une architecture modulaire (SOLID).

---

### DataReader.java

* Interface définissant le contrat de lecture des données.
* Méthode principale :

  * `List<Etudiant> lire(String fichier)`
* Permet de rendre le système extensible (ex: lecture JSON, base de données).
* Respecte le principe d’inversion des dépendances (DIP).

---

### CSVReader.java

#### Fonctionnalités principales

1. Lecture du fichier CSV

* Utilisation de `BufferedReader` pour lire le fichier ligne par ligne.
* Lecture de la première ligne (en-tête) pour identifier les colonnes.

2. Validation de la structure (via CSVValidator)

* Délégation de la validation à une classe dédiée (`CSVValidator`).
* Vérification :

  * présence des colonnes `id` et `nom`
  * cohérence des matières avec le référentiel métier
* Permet de respecter le principe de responsabilité unique (SRP).

3. Mapping des données (via EtudiantMapper)

* Transformation des lignes CSV en objets `Etudiant` via `EtudiantMapper`.
* Séparation claire entre lecture et transformation.
* Utilisation du `MatiereRepository` pour récupérer les objets `Matiere`.

4. Lecture des données étudiants

* Chaque ligne est transformée en objet `Etudiant`.
* Extraction de :

  * id (int)
  * nom (String)
  * notes (List<Note>)

5. Gestion des erreurs

* Lignes mal formées → ignorées
* Erreurs de lecture → capturées (IOException)
* Les erreurs de parsing et validation sont gérées dans les classes dédiées
* Permet une meilleure robustesse et un code plus maintenable

---

## 2. CSVValidator.java

### Objectif

Valider la structure du fichier CSV indépendamment de la lecture.

### Fonctionnalités

* Vérification des colonnes obligatoires (`id`, `nom`)
* Vérification des matières via `MatiereRepository`
* Détection des incohérences dans le fichier

### Rôle

* Externalise la validation
* Respecte le principe SRP
* Rend le système plus testable

---

## 3. EtudiantMapper.java

### Objectif

Transformer les données brutes du CSV en objets métier.

### Fonctionnalités

* Conversion des lignes CSV en objets `Etudiant`
* Mapping des colonnes vers les objets `Matiere`
* Création des objets `Note`
* Filtrage :

  * notes invalides
  * valeurs hors intervalle [0,20]

### Rôle

* Sépare la transformation des données du reste du système
* Facilite l’évolution (ex: autre format d’entrée)

---

## 4. Etudiant.java

### Objectif

Représenter un étudiant avec ses informations et encapsuler la logique métier associée.

### Attributs

* `id` : identifiant unique
* `nom` : nom de l’étudiant
* `notes` : liste des objets Note
* `moyenne` : moyenne pondérée calculée
* `mention` : mention associée à la moyenne

### Fonctionnalités principales

1. Calcul de la moyenne pondérée

* Chaque note est multipliée par le coefficient de sa matière
* Formule :
  somme(note × coefficient) / somme(coefficients)
* Gestion du cas division par zéro

2. Attribution de la mention

* Basée sur la moyenne :

  * ≥ 16 : Très bien
  * ≥ 14 : Bien
  * ≥ 12 : Assez bien
  * ≥ 10 : Passable
  * < 10 : Insuffisant

3. Encapsulation

* Les calculs sont effectués à la création de l’objet
* Les données sont accessibles via des getters

---

## 5. GestionNotes.java

### Objectif

Implémenter la logique métier liée à la manipulation des étudiants.

### Fonctionnalité principale

1. Tri des étudiants

* Tri de la liste des étudiants par moyenne décroissante
* Utilisation de `Comparator` :

  * `Comparator.comparingDouble(Etudiant::getMoyenne).reversed()`

### Rôle dans l’architecture

* Sépare la logique métier du reste du système
* Facilite la maintenance et l’évolution du code

---

## Conclusion

Cette partie du projet couvre :

* la lecture des données
* la validation du fichier CSV
* la transformation des données en objets métier
* l’implémentation de la logique métier (moyenne, mention, tri)

L’ensemble respecte les principes suivants :

* séparation des responsabilités (SRP)
* inversion des dépendances (DIP)
* modularité et extensibilité
* robustesse face aux erreurs de données

Cette architecture permet une évolution facile du système (ajout de nouveaux formats, nouvelles règles métier, etc.).
