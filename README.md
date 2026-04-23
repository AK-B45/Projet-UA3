# Gestion des étudiants - Java

## Membres
- Yacine Lezoul  
- Abderraouf Kouadri Boudjelthia  

---

## Description
Ce projet Java permet de gérer des étudiants, leurs notes et leurs matières à partir d’un fichier CSV.

Le programme réalise les opérations suivantes :
- Lecture d’un fichier CSV contenant les notes des étudiants
- Validation de la structure du fichier
- Transformation des données en objets métier
- Calcul d’une moyenne pondérée selon les coefficients des matières
- Attribution automatique d’une mention
- Tri des étudiants par moyenne décroissante
- Génération d’un fichier de résultats

---

## Fonctionnalités
- Validation stricte du format CSV (structure et matières)
- Gestion des matières avec coefficients
- Calcul de moyenne pondérée
- Attribution de mentions
- Tri des étudiants
- Export des résultats dans un fichier CSV
- Gestion des erreurs (notes invalides, format incorrect)

---

## Structure du projet

- Model/ : classes métier (Etudiant, Note, Matiere)  
- io/ : lecture et écriture CSV  
- mapper/ : transformation des données CSV en objets métier  
- repository/ : gestion des matières et coefficients  
- service/ : logique métier (tri, traitements)  
- validation/ : validation de la structure du CSV  
- Main.java : point d’entrée du programme  

---

## Format du fichier CSV

Le fichier `notes.csv` doit respecter la structure suivante :

id,nom,Math,Physique,Informatique  
1,Alice,15,14,16  
2,Bob,10,12,11  

### Règles :
- Les deux premières colonnes doivent être `id` et `nom`
- Les autres colonnes doivent correspondre aux matières définies dans le système
- Les notes doivent être comprises entre 0 et 20

---

## Exécution du projet

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

## Résultat attendu
Le programme génère un fichier `resultats.csv` contenant :
- rang  
- id  
- nom  
- moyenne  
- mention  

---

## Architecture et conception

Le projet suit une architecture inspirée des principes SOLID :

- SRP : chaque classe a une seule responsabilité  
- OCP : extensible (ajout de nouveaux formats possible)  
- DIP : dépendance aux interfaces (DataReader, DataWriter)  
- ISP : interfaces simples et spécialisées  
- LSP : implémentations interchangeables  

---

## Remarques
- Les lignes mal formées du CSV sont ignorées  
- Les notes invalides (hors 0–20) sont ignorées  
- Le système est extensible  
- Le projet peut être exécuté via IDE ou terminal  