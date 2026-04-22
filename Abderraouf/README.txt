# Documentation technique – Partie ABDERRAOUF

## 1. CSVWriter.java + DataWriter.java

### Objectif

Ces classes permettent d’écrire les résultats du traitement dans un fichier CSV de sortie.

### DataWriter.java

* Interface définissant le contrat d’écriture des données.
* Méthode principale :

  * `void ecrire(String fichier, List<Etudiant> etudiants)`
* Permet de rendre le système extensible (ex: écriture JSON, base de données).

### CSVWriter.java

#### Fonctionnalités principales

1. Création du fichier de sortie

* Utilisation de `BufferedWriter` pour écrire dans un fichier.
* Création ou écrasement du fichier existant.

2. Écriture de l’en-tête

* Ajout des colonnes :

  * rang
  * id
  * nom
  * moyenne
  * mention

3. Écriture des données

* Parcours de la liste des étudiants triés.
* Attribution d’un rang (ordre après tri).
* Formatage de la moyenne à deux décimales (`Locale.US` pour garantir le format avec point).

4. Gestion des erreurs

* Gestion des exceptions `IOException`.
* Utilisation de `try-with-resources` pour garantir la fermeture du fichier.

---

## 2. Matiere.java

### Objectif

Représenter une matière avec son coefficient.

### Attributs

* `nom` : nom de la matière
* `coefficient` : poids de la matière dans le calcul de la moyenne

### Rôle

* Fournir une base pour le calcul des moyennes pondérées.
* Être utilisée dans la classe `Note`.

---

## 3. Note.java

### Objectif

Associer une note à une matière.

### Attributs

* `matiere` : objet Matiere
* `valeur` : note obtenue (double)

### Rôle

* Permet de relier explicitement une note à sa matière.
* Facilite le calcul de la moyenne pondérée dans `Etudiant`.

---

## 4. Main.java

### Objectif

Point d’entrée du programme et orchestration des différentes étapes.

### Fonctionnement

1. Initialisation des composants

* Création d’un `DataReader` (CSVReader)
* Création d’un `DataWriter` (CSVWriter)
* Création du service `GestionNotes`

2. Exécution du traitement

* Lecture des étudiants depuis le fichier CSV
* Tri des étudiants par moyenne
* Écriture des résultats dans un fichier de sortie

3. Affichage

* Confirmation de la fin du traitement
* Possibilité d’afficher le chemin du fichier généré

---

## Rôle global dans l’architecture

Cette partie du projet couvre :

* la sortie des données (écriture CSV)
* la définition des structures de base (Matiere, Note)
* l’orchestration complète du programme

Elle complète la partie précédente en assurant :

* la transformation finale des données
* la cohérence du modèle métier
* le bon déroulement du programme

---

## Conclusion

Cette contribution permet :

* de produire un fichier résultat exploitable
* de structurer les données métier
* d’assurer l’exécution complète du programme

L’ensemble respecte :

* la séparation des responsabilités
* la modularité
* la clarté du flux de traitement (lecture → traitement → écriture)
