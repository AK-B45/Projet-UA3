# Gestion des étudiants - Java

## Membres
- Yacine Lezoul  
- Abderraouf Kouadri Boudjelthia  

---

## 1. Description du projet

Ce projet Java a pour objectif de gérer des étudiants à partir d’un fichier CSV contenant leurs notes par matière.

Le système permet de :
- Lire un fichier CSV
- Valider sa structure et son contenu
- Transformer les données en objets métier
- Calculer une moyenne pondérée selon les coefficients des matières
- Attribuer une mention à chaque étudiant
- Trier les étudiants par moyenne décroissante
- Générer un fichier de résultats

---

## 2. Architecture du projet

Le projet suit une architecture modulaire inspirée des principes SOLID.

### model/
Contient les objets métier :
- **Etudiant** : représente un étudiant (id, nom, notes, moyenne, mention)
- **Note** : association entre une matière et une valeur
- **Matiere** : définition d’une matière avec son coefficient

---

### io/
Gestion des entrées/sorties CSV :

- **DataReader (interface)**  
  Définit le contrat de lecture des données :
  - `List<Etudiant> lire(String fichier)`

- **DataWriter (interface)**  
  Définit le contrat d’écriture des données :
  - `void ecrire(String fichier, List<Etudiant> etudiants)`

- **CSVReader**  
  - Lecture du fichier CSV
  - Extraction des lignes
  - Transmission au validator et mapper
  - Création des objets Etudiant

- **CSVWriter**  
  - Écriture du fichier de sortie
  - Formatage des résultats (rang, moyenne, mention)

---

### mapper/
- **EtudiantMapper**
  - Transformation d’une ligne CSV en objet Etudiant
  - Création des objets Note
  - Association avec les matières via le repository

---

### repository/
- **MatiereRepository**
  - Centralise la liste des matières
  - Fournit les coefficients
  - Sert de référence métier unique

---

### validation/
- **CSVValidator**
  - Vérifie la structure du CSV
  - Contrôle les en-têtes (id, nom, matières)
  - Vérifie la cohérence avec le repository
  - Garantit la validité des données avant traitement

---

### service/
- **GestionNotes**
  - Trie les étudiants par moyenne décroissante
  - Contient la logique métier de classement

---

### Main.java
- Point d’entrée du programme
- Orchestration complète :
  - lecture → validation → mapping → traitement → écriture

---

## 3. Format du fichier CSV

Exemple attendu :

id,nom,Math,Physique,Informatique  
1,Alice,15,14,16  
2,Bob,10,12,11  

### Règles :
- Les deux premières colonnes doivent être `id` et `nom`
- Les autres colonnes doivent correspondre aux matières définies dans `MatiereRepository`
- Les notes doivent être comprises entre 0 et 20
- Les erreurs de format sont gérées par le système

---

## 4. Fonctionnement global

1. Lecture du fichier CSV (`CSVReader`)
2. Validation de la structure (`CSVValidator`)
3. Mapping des données (`EtudiantMapper`)
4. Création des objets métier (`Etudiant`, `Note`, `Matiere`)
5. Calcul de la moyenne pondérée (`Etudiant`)
6. Attribution de la mention (`Etudiant`)
7. Tri des étudiants (`GestionNotes`)
8. Écriture du fichier résultat (`CSVWriter`)

---

## 5. Principes SOLID appliqués

- **SRP** : chaque classe a une responsabilité unique
- **OCP** : extensible via interfaces (nouveaux formats possibles)
- **DIP** : dépendance aux interfaces (`DataReader`, `DataWriter`)
- **ISP** : interfaces simples et spécialisées
- **LSP** : interchangeabilité des implémentations (`CSVReader`, etc.)

---

## 6. Exécution du projet

### 1. Cloner le projet
git clone https://github.com/AK-B45/Projet-UA3.git  
cd Projet-UA3  

---

### 2. Compilation
javac -d bin src/**/*.java  

---

### 3. Exécution
java -cp bin Main  

---

## 7. Résultat attendu

Le programme génère un fichier :
- `resultats.csv`

Contenant :
- rang
- id
- nom
- moyenne
- mention

---

## 8. Remarques techniques

- Les notes non numériques sont ignorées
- Les notes hors intervalle [0–20] sont rejetées
- Les lignes mal formées sont ignorées
- Le système est conçu pour être extensible (nouveaux formats via interfaces)
- Architecture totalement modulaire et découplée