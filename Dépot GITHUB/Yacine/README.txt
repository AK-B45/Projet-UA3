# Documentation technique – Partie Yacine

## 1. CSVReader.java + DataReader.java

### Objectif

Ces classes permettent de lire un fichier CSV contenant les données des étudiants et de les transformer en objets exploitables par le programme.

### DataReader.java

* Interface définissant le contrat de lecture des données.
* Méthode principale :

  * `List<Etudiant> lire(String fichier)`
* Permet de rendre le système extensible (ex: lecture JSON, base de données).

### CSVReader.java

#### Fonctionnalités principales

1. Lecture du fichier CSV

* Utilisation de `BufferedReader` pour lire le fichier ligne par ligne.
* Lecture de la première ligne (en-tête) pour identifier les colonnes.

2. Validation de la structure

* Vérification que les deux premières colonnes sont bien `id` et `nom`.
* Vérification que les colonnes suivantes correspondent à des matières connues.
* Si une matière est inconnue → arrêt du programme avec une erreur.

3. Mapping des colonnes vers les objets métier

* Utilisation d’une `Map<String, Matiere>` comme référence métier.
* Chaque nom de colonne est associé à un objet `Matiere` avec son coefficient.
* Permet de transformer les données CSV en objets `Note`.

4. Lecture des données étudiants

* Conversion de chaque ligne en objet `Etudiant`.
* Extraction de :

  * id (int)
  * nom (String)
  * notes (List<Note>)

5. Gestion des erreurs

* Valeurs non numériques → ignorées (NumberFormatException)
* Notes hors intervalle [0,20] → ignorées
* Lignes mal formées → ignorées
* Permet d’éviter les crashs et de rendre le système robuste

---

## 2. Etudiant.java

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

## 3. GestionNotes.java

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

* la transformation des données (CSV → objets métier)
* la validation et la robustesse des entrées
* l’implémentation de la logique métier (moyenne, mention, tri)

L’ensemble respecte les principes suivants :

* séparation des responsabilités
* modularité
* extensibilité via interfaces
* robustesse face aux erreurs de données
