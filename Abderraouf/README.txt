# Documentation technique – Partie ABDERRAOUF

---

## 1. CSVWriter.java + DataWriter.java

### Objectif

Ces classes permettent d’écrire les résultats du traitement dans un fichier CSV.

---

## DataWriter.java (interface)

### Rôle
Définir un contrat d’écriture des données.

### Méthode

void ecrire(String fichier, List<Etudiant> etudiants)

### Intérêt
- Permet une abstraction de l’écriture
- Rend possible l’ajout de nouveaux formats (CSV, JSON, etc.)

---

## CSVWriter.java

### Fonction principale

Écriture des résultats des étudiants dans un fichier CSV.

---

### Étapes :

- Création du fichier de sortie avec BufferedWriter
- Écriture de l’en-tête :

rang,id,nom,moyenne,mention

- Parcours de la liste des étudiants
- Écriture des données pour chaque étudiant :
  - id
  - nom
  - moyenne
  - mention

---

### Formatage

- Moyenne affichée avec 2 décimales
- Données structurées pour lecture facile

---

### Gestion des erreurs

- Utilisation de try-with-resources
- Gestion des erreurs d’écriture (IOException)

---

## 2. Matiere.java

### Objectif

Représenter une matière avec son coefficient.

---

### Attributs

- nom : nom de la matière
- coefficient : poids dans le calcul de la moyenne

---

### Rôle

- Permet le calcul pondéré des notes
- Utilisée dans les objets Note

---

## 3. Note.java

### Objectif

Associer une note à une matière.

---

### Structure

- Matiere : matière associée
- valeur : note obtenue

---

### Rôle

- Représentation d’une note dans une matière
- Utilisée pour le calcul de la moyenne

---

## 4. Main.java

### Objectif

Point d’entrée du programme.

---

### Rôle

- Initialisation des composants
- Appel des services
- Orchestration globale :
  lecture → traitement → tri → écriture

---

## 5. Contribution globale

Cette partie du projet permet :

- L’écriture des résultats dans un fichier CSV
- La définition des structures de base (Matiere, Note)
- L’exécution finale du programme

---

## 6. Conclusion

Cette contribution assure :

- La génération du fichier final
- La structuration des données de sortie
- La bonne exécution du flux global du programme