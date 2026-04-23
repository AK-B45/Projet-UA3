# Gestion des étudiants - Java

## Membres

* Yacine Lezoul
* Abderraouf Kouadri Boudjelthia

---

## Description

Ce projet Java permet de gérer des étudiants, leurs notes et leurs matières à partir d’un fichier CSV.

Le programme réalise les opérations suivantes :

* Lecture d’un fichier CSV contenant les notes des étudiants
* Validation de la structure du fichier
* Transformation des données en objets métier
* Calcul d’une moyenne pondérée selon les coefficients des matières
* Attribution automatique d’une mention
* Tri des étudiants par moyenne décroissante
* Génération d’un fichier de résultats

---

## Fonctionnalités

* Validation stricte du format CSV (structure et matières)
* Gestion des matières avec coefficients
* Calcul de moyenne pondérée
* Attribution de mentions (Très bien, Bien, etc.)
* Tri des étudiants
* Export des résultats dans un fichier CSV
* Gestion des erreurs (notes invalides, format incorrect)

---

## Structure du projet

* `model/` : classes métier (Etudiant, Note, Matiere)
* `repository/` : gestion des matières (MatiereRepository)
* `validation/` : validation des données (CSVValidator)
* `mapper/` : transformation CSV → objets (EtudiantMapper)
* `io/` : lecture et écriture (CSVReader, CSVWriter, interfaces)
* `service/` : logique métier (GestionNotes)
* `Main.java` : point d’entrée du programme

---

## Format attendu du fichier CSV

Le fichier `notes.csv` doit respecter la structure suivante :

id,nom,Math,Physique,Informatique
1,Alice,15,14,16
2,Bob,10,12,11

* Les deux premières colonnes doivent être `id` et `nom`
* Les autres colonnes doivent correspondre aux matières définies dans le code
* Les notes doivent être comprises entre 0 et 20

---

## Exécution du projet

### Option 1 – Depuis GitHub (recommandé)

1. Cloner le dépôt :

   ```
   git clone <URL_DU_REPO>
   ```
2. Se placer dans le dossier du projet :

   ```
   cd gestion-notes-java
   ```
3. Compiler le projet :

   ```
   javac -d bin src/**/*.java
   ```
4. Exécuter le programme :

   ```
   java -cp bin Main
   ```

---

### Option 2 – Depuis un IDE (IntelliJ, Eclipse, VS Code)

* Importer le projet
* Lancer directement la classe `Main.java`

---

## Fichier de sortie

Le programme génère un fichier :

`resultats.csv`

Contenant :

* rang
* id
* nom
* moyenne
* mention

---

## Conception

Le projet suit une architecture modulaire inspirée des principes SOLID :

* séparation des responsabilités
* utilisation d’interfaces (DataReader, DataWriter)
* injection des dépendances
* découplage entre lecture, traitement et écriture

---

## Remarques

* Le fichier `notes.csv` doit être placé à la racine du projet
* Les erreurs de format sont gérées pour éviter les crashs
* Le système est extensible (possibilité d’ajouter JSON, base de données, etc.)
