# Gestion des étudiants - Java

## Membres
- Yacine Lezoul  
- Abderraouf Kouadri Boudjelthia  

---

## 1. Description du projet

Ce projet Java permet de gérer des étudiants à partir d’un fichier CSV contenant leurs notes par matière.

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
  Contrat de lecture des données :
  - `List<Etudiant> lire(String fichier)`

- **DataWriter (interface)**  
  Contrat d’écriture des données :
  - `void ecrire(String fichier, List<Etudiant> etudiants)`

- **CSVReader**  
  - Lecture du fichier CSV
  - Validation structure (via validator)
  - Mapping vers objets métier

- **CSVWriter**  
  - Écriture du fichier résultat CSV

---

### mapper/
- **EtudiantMapper**
  - Transformation ligne CSV → objet Etudiant
  - Création des objets Note
  - Association avec MatiereRepository

---

### repository/
- **MatiereRepository**
  - Centralise les matières et leurs coefficients
  - Sert de référence unique métier

---

### validation/
- **CSVValidator**
  - Vérifie le format du CSV
  - Contrôle les en-têtes (id, nom, matières)
  - Vérifie la cohérence avec les matières disponibles

---

### service/
- **MoyenneService**
  - Calcul de la moyenne pondérée

- **MentionService**
  - Attribution des mentions selon la moyenne

- **GestionNotes**
  - Tri des étudiants par moyenne décroissante

---

### main/
- **Main**
  - Point d’entrée du programme
  - Orchestration globale :
    lecture → validation → mapping → calcul → tri → écriture

---

## 3. Format du fichier CSV

Exemple attendu :

id,nom,Math,Physique,Informatique  
1,Alice,15,14,16  
2,Bob,10,12,11  

### Règles :
- Les deux premières colonnes doivent être `id` et `nom`
- Les autres colonnes doivent correspondre aux matières du repository
- Les notes doivent être comprises entre 0 et 20
- Toute incohérence est gérée par validation

---

## 4. Fonctionnement global

1. Lecture du CSV (`CSVReader`)
2. Validation de la structure (`CSVValidator`)
3. Mapping des données (`EtudiantMapper`)
4. Création des objets métier (`Etudiant`, `Note`, `Matiere`)
5. Calcul de la moyenne (`MoyenneService`)
6. Attribution de la mention (`MentionService`)
7. Tri des étudiants (`GestionNotes`)
8. Écriture du fichier résultat (`CSVWriter`)

---

## 5. Principes SOLID appliqués

- **SRP** : chaque classe a une responsabilité unique
- **OCP** : extensible via nouvelles implémentations (Reader/Writer)
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

Le programme génère :
- `resultats.csv`

Contenant :
- id
- nom
- moyenne
- mention

---

## 8. Remarques techniques

- Les notes invalides (non numériques ou hors [0–20]) sont ignorées
- Les lignes mal formées sont rejetées ou ignorées selon validation
- Le système est extensible via nouvelles implémentations de Reader/Writer
- Architecture modulaire respectant les principes SOLID